package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.post.dto.add.PostAddResponseDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PostService {
    @Transactional
    PostAddResponseDTO savePost(PostAddRequestDTO postAddRequestDTO, List<MultipartFile> imgList);
    @Transactional
    Page<PostReadResDTO> findAllPageToDTO(Page<Post> postPage);
}
