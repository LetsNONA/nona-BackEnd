package letsnona.nonabackend.domain.post.service;


import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.domain.post.dto.add.PostAddRequestDTO;
import letsnona.nonabackend.domain.post.dto.add.PostAddResponseDTO;
import letsnona.nonabackend.domain.post.dto.read.PostReadResDTO;
import letsnona.nonabackend.domain.post.dto.read.PostResImgDTO;
import letsnona.nonabackend.domain.post.dto.read.PostResReivewDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
    public Page<PostReadResDTO> findAllPageToDTO(Page<Post> postPage) {
        Page<PostReadResDTO> dtoPage = postPage.map(new Function<Post, PostReadResDTO>() {
            @Override
            public PostReadResDTO apply(Post post) {

                // List<PostImg> -> List<PostResImgDTO>  { Entity -> DTO }
                List<PostResImgDTO> imgDTOList = post.getImages().stream().map(PostResImgDTO::new).collect(Collectors.toList());
                // List<Review> -> List<PostResReviewDTO>  { Entity -> DTO }
                List<PostResReivewDTO> reivewDTOList = post.getReviews().stream().map(PostResReivewDTO::new).collect(Collectors.toList());

                PostReadResDTO dto = new PostReadResDTO(post , imgDTOList,reivewDTOList);
                return dto;
            }
        });
        return dtoPage;
    }
}
