package letsnona.nonabackend.domain.post.repository;

import letsnona.nonabackend.domain.post.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    void findByTitleContaining() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> like = postRepository.findByTitleContaining(pageable, "test");
    }
}