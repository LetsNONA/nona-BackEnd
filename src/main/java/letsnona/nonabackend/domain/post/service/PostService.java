package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.domain.post.dto.PostRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface PostService {
    @Transactional
    void savePost(PostRequestDTO postRequestDTO);
}
