package letsnona.nonabackend.domain.post.service;


import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.post.dto.add.PostAddResponseDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResDTO;
import letsnona.nonabackend.domain.post.dto.read.PostResImgDTO;
import letsnona.nonabackend.domain.post.dto.read.PostResReviewDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import letsnona.nonabackend.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostImgRepository imgRepository;
    private final FileService fileService;


    @Override
    public PostAddResponseDTO savePost(PostAddRequestDTO postDTO, List<MultipartFile> imgList) {
        Post post = postDTO.toEntity();

        List<PostImgRequestDTO> postImgRequestDTOList = fileService.saveImage(imgList);
        List<PostImg> postImgEntityList = postImgRequestDTOList.stream()
                .map(PostImgRequestDTO::toEntity).collect(Collectors.toList());

        for (PostImg img : postImgEntityList
        ) {
            post.addImg(img);
        }

        imgRepository.saveAll(postImgEntityList);

        return new PostAddResponseDTO(post);
    }

    @Override
    public Page<PostReadResDTO> getAllPost(Page<Post> postPage) {
        return postPage.map(new Function<Post, PostReadResDTO>() {
            @Override
            public PostReadResDTO apply(Post post) {
                // List<PostImg> -> List<PostResImgDTO>  { Entity -> DTO }
                List<PostResImgDTO> imgDTOList = getImageEntityToDTO(post.getImages());
                // List<Review> -> List<PostResReviewDTO>  { Entity -> DTO }
                List<PostResReviewDTO> reviewDTOList = getReviewEntityToDTO(post.getReviews());
                return new PostReadResDTO(post, imgDTOList, reviewDTOList);
            }
        });
    }

    @Override
    public PostReadResDTO getPostDetails(long index) {
        Optional<Post> byId = postRepository.findById(index);

        List<PostResReviewDTO> postResReviewDTOS = getReviewEntityToDTO(byId.get().getReviews());
        List<PostResImgDTO> postResImgDTOS = getImageEntityToDTO(byId.get().getImages());

        return new PostReadResDTO
                (byId.get(), postResImgDTOS, postResReviewDTOS);
    }

    @Override
    public List<PostResReviewDTO> getReviewEntityToDTO(List<Review> reviewList) {
        return reviewList.stream().map(PostResReviewDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<PostResImgDTO> getImageEntityToDTO(List<PostImg> imgList) {
        return imgList.stream().map(PostResImgDTO::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<byte[]> getRespIMG(String filePath) throws IOException {
        InputStream imageStream = new FileInputStream(filePath);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }
}
