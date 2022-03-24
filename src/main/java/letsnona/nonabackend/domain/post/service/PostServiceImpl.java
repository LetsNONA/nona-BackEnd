package letsnona.nonabackend.domain.post.service;


import letsnona.nonabackend.domain.post.dto.PostRequestDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public void savePost(PostRequestDTO postDTO) {
        Post post = postDTO.toEntity();
        postRepository.save(post);
    }
}
