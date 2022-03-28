package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.domain.post.dto.PostRequestDTO;
import letsnona.nonabackend.domain.post.dto.PostResponseDTO;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PostService {
    @Transactional
    PostResponseDTO savePost(PostRequestDTO postRequestDTO, List<MultipartFile> imgList);

}
