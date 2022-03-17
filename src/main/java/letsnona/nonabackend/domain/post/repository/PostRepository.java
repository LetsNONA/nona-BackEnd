package letsnona.nonabackend.domain.post.repository;

import letsnona.nonabackend.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
Post findByOwner(String username);
}
