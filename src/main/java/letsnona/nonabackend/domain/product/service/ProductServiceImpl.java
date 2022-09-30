package letsnona.nonabackend.domain.product.service;


import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.cataegory.service.CategoryService;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.domain.product.dto.CategoryRecommendProductDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResImgDTO;
import letsnona.nonabackend.domain.product.dto.update.ProductUpdateRequestDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.enums.ProductState;
import letsnona.nonabackend.domain.product.repository.CustomProductRepositoryImpl;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.review.dto.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.enums.TradeState;
import letsnona.nonabackend.global.security.dto.MemberRecommendProductDTO;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.CustomMemberRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final PostImgRepository imgRepository;
    private final FileService fileService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final CustomMemberRepository customMemberRepository;
    private final CustomProductRepositoryImpl customProductRepository;

    @Override
    public ProductAddResponseDTO saveProduct(Member requestUser, ProductAddRequestDTO postDTO) {

        postDTO.setOwner(requestUser);
        Product product = postDTO.toEntity();

        if (categoryService.existCategory(postDTO.getCategoryCode()))
            product.setCategory(categoryService.getCategory(postDTO.getCategoryCode()));

        Product savedProduct = productRepository.save(product);

        List<PostImg> savedImgs = fileService.saveImage(postDTO.getFile(), savedProduct);
        product.setImgList(savedImgs);
        return new ProductAddResponseDTO(product);
    }


    @Override
    public ProductAddResponseDTO updateProduct(ProductUpdateRequestDTO postDTO) {

        /*TODO
         *  -리팩토링 매우 필요 더러운 코드 **/
        Optional<Product> byId = productRepository.findById(postDTO.getId());
        byId.ifPresent(post -> {
            if (isPostOwner(post, memberService.getRequestUser()))
                post.updatePost(postDTO);
        });
        return new ProductAddResponseDTO(byId.get());

    }


    public Page<ProductReadResDTO> getProductReadResDTOS(Page<Product> postPage) {
        /*
         *  Response :  Entity -> DTO
         * */
        return postPage.map(post -> {
            List<ProductReadResImgDTO> imgDTOList = getImageEntityToDTO(post.getImages());
            List<ProductReadResReviewDTO> reviewDTOList = getReviewEntityToDTO(post.getReviews());
            return new ProductReadResDTO(post, imgDTOList, reviewDTOList);
        });
    }

    @Override
    public List<MemberRecommendProductDTO> getRecommendProductList(Member reqUser){

        return customMemberRepository.getRecommendProduct(reqUser.getId());
    }

    @Override
    public Page<ProductReadResDTO> getAllProduct(Pageable pageable) {
        Page<Product> allByFlagDelete = productRepository.findAllByProductState(pageable, ProductState.SELL);
        return getProductReadResDTOS(allByFlagDelete);
    }

    @Override
    public Page<ProductReadResDTO> getSearchProduct(String keyword, Pageable pageable) {
        /*Todo
         *  -test code 필요*/
        Page<Product> byTitleContaining = productRepository.findByTitleContaining(pageable, keyword);
        return getProductReadResDTOS(byTitleContaining);
    }

    @Override
    public Page<ProductReadResDTO> getSearchForOwner(Pageable pageable) {
        Member requestUser = memberService.getRequestUser();
        Page<Product> byOwner = productRepository.findByOwner(pageable, requestUser);
        return getProductReadResDTOS(byOwner);
    }


    @Override
    public Page<ProductReadResDTO> getProductByCategory(String categoryCode, Pageable pageable) {
        Optional<Category> byCategoryCode = categoryRepository.findByCategoryCode(categoryCode);
        Page<Product> byCategory = productRepository.findByCategoryAndProductState(pageable, byCategoryCode.get(), ProductState.SELL);
        return getProductReadResDTOS(byCategory);
    }

    @Override
    public ProductReadResDTO getProductDetails(long index) {
        Optional<Product> byId = productRepository.findById(index);
        byId.ifPresent(Product::increaseHit);

        /*TODO
         *  Optional Refactoring ***********/
        List<ProductReadResReviewDTO> productReadResReviewDTOS = getReviewEntityToDTO(byId.get().getReviews());
        List<ProductReadResImgDTO> productReadResImgDTOS = getImageEntityToDTO(byId.get().getImages());

        return new ProductReadResDTO
                (byId.get(), productReadResImgDTOS, productReadResReviewDTOS);
    }

    @Override
    public List<ProductReadResReviewDTO> getReviewEntityToDTO(List<Review> reviewList) {
        return reviewList.stream()
                .filter(review -> review.getTradeState().equals(TradeState.COMPLETED))
                .map(ProductReadResReviewDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<ProductReadResImgDTO> getImageEntityToDTO(List<PostImg> imgList) {
        return imgList.stream().map(ProductReadResImgDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<byte[]> getRespIMG(String filePath) throws IOException {
        /*
         *  filePath -> Image(ByteCode)
         * */
        InputStream imageStream = new FileInputStream(filePath);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }

    @Override
    public String deleteProduct(long postIndex) {
        /*TODO
         *  테스트 코드 작성 필요*/

        Member requestUser = memberService.getRequestUser();
        Optional<Product> byId = productRepository.findById(postIndex);

        byId.ifPresent(post -> {
            if (isPostOwner(post, requestUser)) post.deletePost();
        });
        return byId.map(Product::getProductState).get().toString();
    }

    @Override
    public boolean isPostOwner(Product product, Member requestMember) {
//        return product.getOwner().equals(requestMember);
        return product.getOwner().getUsername().equals(requestMember.getUsername());
    }

    @Override
    public List<CategoryRecommendProductDTO> getCategoryRecommendProduct(String categoryCode){
        return customProductRepository.getCategoryProductsRecommend(categoryCode);
    }

}
