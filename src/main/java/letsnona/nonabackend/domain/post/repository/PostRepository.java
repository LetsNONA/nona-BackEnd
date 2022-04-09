package letsnona.nonabackend.domain.post.repository;

import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.global.security.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
Post findByOwner(Member owner);
Optional<Post> findById(Long id);
//@Query("select p from Post p left join fetch p.owner")
@EntityGraph("PostWithMember")
Page<Post> findAll(Pageable pageable);
}
