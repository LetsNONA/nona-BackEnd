package letsnona.nonabackend.domain.post.service;

import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.post.dto.add.PostAddResponseDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResDTO;
import letsnona.nonabackend.domain.post.dto.read.PostResImgDTO;
import letsnona.nonabackend.domain.post.dto.read.PostResReviewDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface PostService {
    @Transactional
    PostAddResponseDTO savePost(PostAddRequestDTO postAddRequestDTO, List<MultipartFile> imgList);

    @Transactional
    Page<PostReadResDTO> getAllPost(Page<Post> postPage);

    @Transactional
    PostReadResDTO getPostDetails(long index);

    @Transactional
    ResponseEntity<byte[]> getRespIMG(String filePath) throws IOException;

    List<PostResReviewDTO> getReviewEntityToDTO(List<Review> reviewList);
    List<PostResImgDTO> getImageEntityToDTO(List<PostImg> imgList);

}
