package letsnona.nonabackend.domain.post.service;


import letsnona.nonabackend.domain.cataegory.entity.Category;
import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.post.dto.add.PostAddResponseDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResImgDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResReviewDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.auth.PrincipalDetails;
import letsnona.nonabackend.global.security.entity.Member;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostImgRepository imgRepository;
    private final FileService fileService;
    private final CategoryRepository categoryRepository;

    @Override
    public PostAddResponseDTO savePost(PostAddRequestDTO postDTO, List<MultipartFile> imgList) {
        Member requestUser = getRequestUser();
        postDTO.setOwner(requestUser);

        /*TODO
         *  Optional Refactoring **********
         *  && Pageable -> 동일함수 리팩토링 해야함
         * */

        Optional<Category> byCategoryCode = categoryRepository.findByCategoryCode(postDTO.getCategory());
        Post post = postDTO.toEntity(byCategoryCode.get());

        List<PostImgRequestDTO> postImgRequestDTOList = fileService.saveImage(imgList);
        List<PostImg> postImgEntityList = postImgRequestDTOList.stream()
                .map(PostImgRequestDTO::toEntity).collect(Collectors.toList());

        for (PostImg img : postImgEntityList
        ) {
            post.addImg(img);
        }

        imgRepository.saveAll(postImgEntityList);

        return new PostAddResponseDTO(post);
    }

    @Override
    public Page<PostReadResDTO> getSearchPost(Page<Post> postPage) {
        /*
         *  Response :  Entity -> DTO
         * */
        return postPage.map(new Function<Post, PostReadResDTO>() {
            @Override
            public PostReadResDTO apply(Post post) {
                // List<PostImg> -> List<PostResImgDTO>  { Entity -> DTO }
                List<PostReadResImgDTO> imgDTOList = getImageEntityToDTO(post.getImages());
                // List<Review> -> List<PostResReviewDTO>  { Entity -> DTO }
                List<PostReadResReviewDTO> reviewDTOList = getReviewEntityToDTO(post.getReviews());
                return new PostReadResDTO(post, imgDTOList, reviewDTOList);
            }
        });
    }


    @Override
    public Page<PostReadResDTO> getAllPost(Page<Post> postPage) {
        /*
         *  Response :  Entity -> DTO
         * */
        return postPage.map(new Function<Post, PostReadResDTO>() {
            @Override
            public PostReadResDTO apply(Post post) {
                // List<PostImg> -> List<PostResImgDTO>  { Entity -> DTO }
                List<PostReadResImgDTO> imgDTOList = getImageEntityToDTO(post.getImages());
                // List<Review> -> List<PostResReviewDTO>  { Entity -> DTO }
                List<PostReadResReviewDTO> reviewDTOList = getReviewEntityToDTO(post.getReviews());
                return new PostReadResDTO(post, imgDTOList, reviewDTOList);
            }
        });
    }

    @Override
    public PostReadResDTO getPostDetails(long index) {
        Optional<Post> byId = postRepository.findById(index);

        /*TODO
         *  Optional Refactoring ***********/
        List<PostReadResReviewDTO> postReadResReviewDTOS = getReviewEntityToDTO(byId.get().getReviews());
        List<PostReadResImgDTO> postReadResImgDTOS = getImageEntityToDTO(byId.get().getImages());

        return new PostReadResDTO
                (byId.get(), postReadResImgDTOS, postReadResReviewDTOS);
    }

    @Override
    public List<PostReadResReviewDTO> getReviewEntityToDTO(List<Review> reviewList) {
        return reviewList.stream().map(PostReadResReviewDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<PostReadResImgDTO> getImageEntityToDTO(List<PostImg> imgList) {
        return imgList.stream().map(PostReadResImgDTO::new).collect(Collectors.toList());
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
    public boolean deletePost(long postIndex) {
        /*TODO
         *  테스트 코드 작성 필요*/

        Member requestUser = getRequestUser();
        Optional<Post> byId = postRepository.findById(postIndex);

        byId.ifPresent(post -> {
            if (isPostOwner(post, requestUser)) post.deletePost();
        });
        return byId.map(Post::isFlagDelete).orElse(false);
    }

    @Override
    public boolean isPostOwner(Post post, Member requestMember) {
        return post.getOwner().equals(requestMember);
    }

    @Override
    public Member getRequestUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        return principal.getUser();
    }

}
