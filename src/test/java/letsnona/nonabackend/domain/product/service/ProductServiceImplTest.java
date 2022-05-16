package letsnona.nonabackend.domain.product.service;

import letsnona.nonabackend.domain.cataegory.repository.CategoryRepository;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResDTO;
import letsnona.nonabackend.domain.product.dto.read.ProductReadResImgDTO;
import letsnona.nonabackend.domain.review.dto.ProductReadResReviewDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import letsnona.nonabackend.global.security.service.MemberService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Rollback(value = false)
/*
@DataJpaTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-test.yml"})
*/
class ProductServiceImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;
@Autowired
    MemberService memberService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PostImgRepository imgRepository;
    @Autowired
    ProductService productService;

    @Test
    void setup() {
        productRepository.deleteAll();
    }


    @Test
    @Transactional
    void getTest() {
        Optional<Product> post = productRepository.findById(4L);
        List<PostImg> images = post.get().getImages();

        System.out.println("images.toString() = " + images.toString());
    }

    @Test
    @DisplayName("게시글 상세보기")
    @Transactional
    void getPostDetails() {
        Optional<Product> byId = productRepository.findById(1L);
        List<ProductReadResReviewDTO> productReadResReviewDTOS = productService.getReviewEntityToDTO(byId.get().getReviews());
        List<ProductReadResImgDTO> productReadResImgDTOS = productService.getImageEntityToDTO(byId.get().getImages());

        ProductReadResDTO productReadResDTO = new ProductReadResDTO
                (byId.get(), productReadResImgDTOS, productReadResReviewDTOS);

        assertThat(byId.get().getTitle()).isEqualTo(productReadResDTO.getTitle());
        assertThat(productReadResDTO.getReviews()).isNotInstanceOf(Review.class);
        System.out.println("postReadResDTO = " + productReadResDTO);
    }

    @Test
    @DisplayName("게시글 천개저장")
    @WithUserDetails("admin")
    @Disabled
    void setPost1000() throws IOException {
        //Member member = memberRepository.findByUsername("admin");
        Member requestUser = memberService.getRequestUser();
        for (int i = 0; i < 10; i++) {
            ProductAddRequestDTO productAddRequestDTO = ProductAddRequestDTO.builder()
                    .owner(requestUser)
                    .title("test[" + i + "]제목입니다")
                    .content("test[" + i + "]내용입니다")
                    .categoryCode("cg001")
                    .tradePlace("test[" + i + "]임시거래지역")
                    .price(10000)
                    .hashTag("test[" + i + "]임시해쉬태그")
                    // .imgid("임시이미지ID")
                    .build();
            List<MultipartFile> imgLists = new ArrayList<>();
            int file_count = 4;

            for (int j = 1; j <= file_count; j++) {
                File file = new ClassPathResource("/testimg/test" + j + "Img.jpg").getFile();
                FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
                InputStream input = new FileInputStream(file);
                OutputStream os = fileItem.getOutputStream();
                IOUtils.copy(input, os);
                MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
                imgLists.add(multipartFile);
            }

            //when

            ProductAddResponseDTO responseDTO = productService.saveProduct(productAddRequestDTO, imgLists);
        }
    }

    @Test
    @DisplayName("게시물 등록테스트")
        //@WithUserDetails("testId3")
    void addPost() throws IOException {
        //given
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Member member = Member.builder()
                .username("testId2")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();

        ProductAddRequestDTO productAddRequestDTO = ProductAddRequestDTO.builder()
                .owner(member)
                .title("test_제목입니다2")
                .content("test_내용입니다2")
                .categoryCode("cg001")
                .tradePlace("임시거래지역2")
                .price(10000)
                .hashTag("임시해쉬태그2")
                // .imgid("임시이미지ID")
                .build();
        List<MultipartFile> imgLists = new ArrayList<>();
        int file_count = 4;

        for (int i = 1; i <= file_count; i++) {
            File file = new ClassPathResource("/testimg/test" + i + "Img.jpg").getFile();
            FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            imgLists.add(multipartFile);
        }

        //when
        memberRepository.save(member);
        ProductAddResponseDTO responseDTO = productService.saveProduct(productAddRequestDTO, imgLists);

        //then
        assertThat(responseDTO.getImages().size()).isEqualTo(4);
    }

    @Test
    @DisplayName("게시물 읽기테스트")
    void getPost() {

    }

}