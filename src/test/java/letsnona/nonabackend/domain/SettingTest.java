package letsnona.nonabackend.domain;

import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.product.dto.add.ProductAddRequestDTO;
import letsnona.nonabackend.domain.product.dto.add.ProductAddResponseDTO;
import letsnona.nonabackend.domain.product.entity.Product;
import letsnona.nonabackend.domain.product.repository.ProductRepository;
import letsnona.nonabackend.domain.product.service.ProductService;
import letsnona.nonabackend.domain.review.dto.ReviewDTO;
import letsnona.nonabackend.domain.review.dto.ReviewRequestDTO;
import letsnona.nonabackend.domain.review.entity.Review;
import letsnona.nonabackend.domain.review.repository.ReviewRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
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
public class SettingTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PostImgRepository imgRepository;
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ProductService productService;


    @Test
    @DisplayName("유저가입")
    void insertUser() {
        //given
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Member member = Member.builder()
                .username("testId")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();

        //when
        memberRepository.save(member);

        //then
        Member getDbMember = memberRepository.findByUsername("testId");
        getDbMember.toString();
        assertThat(getDbMember).isEqualTo(member);

    }


    @Test
    @DisplayName("게시글 백개저장")
    @WithUserDetails("admin")
    @Disabled
    void setPost1000() throws IOException {
        Member member = memberRepository.findByUsername("admin");

        for (int i = 0; i < 100; i++) {
            ProductAddRequestDTO productAddRequestDTO = ProductAddRequestDTO.builder()
                    .owner(member)
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
    @DisplayName("리뷰 save")
    @Transactional
    void saveReview() throws IOException {

        //given
        ReviewRequestDTO requestDTO = ReviewRequestDTO.builder()
                .postId(1L)
                .grade(4)
                .content("댓글 4")
                .build();
        //when
        Member member = memberRepository.findByUsername("testId");
        Optional<Product> byId = productRepository.findById(requestDTO.getPostId());

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .owner(member)
                .product(byId.get())
                .content(requestDTO.getContent())
                .grade(requestDTO.getGrade())
                .build();

        Review review = reviewDTO.toEntity();

        byId.get().addReview(review);

        Review savedReview = reviewRepository.save(review);
        // Review findReview = reviewRepository.findById(savedReview.getId());

        //then
        assertThat(savedReview.getId()).isNotNull();
        assertThat(savedReview.getContent()).isEqualTo(requestDTO.getContent());
        assertThat(savedReview.getGrade()).isEqualTo(requestDTO.getGrade());

    }
}
