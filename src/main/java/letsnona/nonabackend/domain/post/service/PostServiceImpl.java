package letsnona.nonabackend.domain.post.service;


import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
import letsnona.nonabackend.domain.file.dto.PostImgResponseDTO;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.file.repository.PostImgRepository;
import letsnona.nonabackend.domain.file.service.FileService;
import letsnona.nonabackend.domain.post.dto.PostRequestDTO;
import letsnona.nonabackend.domain.post.dto.PostResponseDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostImgRepository imgRepository;
    private final FileService fileService;


    @Override
    public PostResponseDTO savePost(PostRequestDTO postDTO, List<MultipartFile> imgList) {
        Post post = postDTO.toEntity();
        //List<PostImgResponseDTO> postImgResponseDTOS = fileService.saveImage(post, imgList);
        List<PostImgRequestDTO> postImgRequestDTOList = fileService.saveImage(post, imgList);
       //List<PostImg> postImgEntityList = new ArrayList<>(postImgResponseDTOS.stream()
        List<PostImg> postImgEntityList = new ArrayList<>(postImgRequestDTOList.stream()
                .map(PostImgRequestDTO::toEntity)
                .collect(Collectors.toList()));

        imgRepository.saveAll(postImgEntityList);

        PostResponseDTO responseDTO = new PostResponseDTO(post);
        return responseDTO;
    }
}
