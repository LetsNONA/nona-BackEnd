package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.post.dto.PostRequestDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
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
class PostServiceImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
     PostRepository postRepository;

    @Autowired
     PostImgRepository imgRepository;
    @Autowired
    PostService postService;

@Test
void setup (){
    postRepository.deleteAll();
}


@Test
@Transactional
void getTest(){
    Optional<Post> post = postRepository.findById(4L);
    List<PostImg> images = post.get().getImages();

    System.out.println("images.toString() = " + images.toString());
}


    @Test
    @DisplayName("게시물 등록테스트")
        //@WithUserDetails("testId3")
    void addPost() throws IOException {
        //given
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Member member = Member.builder()
                .username("testId")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();

        PostRequestDTO postRequestDTO = PostRequestDTO.builder()
                .owner(member)
                .title("test_제목입니다")
                .content("test_내용입니다")
                .category("임시카테리고")
                .tradePlace("임시거래지역")
                .price(10000)
                .hashTag("임시해쉬태그")
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
        postService.savePost(postRequestDTO, imgLists);
       // Post getPost = postRepository.findByOwner(member);
        //then

        //assertThat(postRequestDTO.getTitle()).isEqualTo(getPost.getTitle());
        //assertThat(postRequestDTO.getContent()).isEqualTo(getPost.getContent());
    }
}