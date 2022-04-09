package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.domain.post.dto.read.PostReadResDTO;
import letsnona.nonabackend.domain.post.dto.read.PostResImgDTO;
import letsnona.nonabackend.domain.post.dto.read.PostResReviewDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class PostPageableTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    PostRepository postRepository;


    @Test
    void setup() {
        postRepository.deleteAll();
    }

    @Test
    @Transactional
    @DisplayName("Post 페이징")
    void postPageable() throws Exception {
        /*mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andDo(print());*/
        Pageable pageable = PageRequest.of(0, 10);

        Page<Post> all = postRepository.findAll(pageable);
        Page<PostReadResDTO> dtoPage = all.map(new Function<Post, PostReadResDTO>() {
            @Override
            public PostReadResDTO apply(Post post) {

                List<PostResImgDTO> imgDTOList = post.getImages().stream().map(PostResImgDTO::new).collect(Collectors.toList());
                List<PostResReviewDTO> reivewDTOList = post.getReviews().stream().map(PostResReviewDTO::new).collect(Collectors.toList());

                PostReadResDTO dto = new PostReadResDTO(
                        post.getId(),
                        post.getOwner(),
                        imgDTOList,
                        reivewDTOList,
                        post.getTitle(),
                        post.getContent(),
                        post.getCategory(),
                        post.getTradePlace(),
                        post.getPrice(),
                        post.getHashTag(),
                        post.getHit(),
                        post.isFlagCourierFee()
                );
                return dto;
            }
        });
        assertThat(all).isEqualTo(dtoPage);

    }
}