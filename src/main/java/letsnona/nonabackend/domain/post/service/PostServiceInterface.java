package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.domain.post.dto.PostRequestDTO;

public interface PostServiceInterface {
    void savePost(PostRequestDTO postRequestDTO);
}
