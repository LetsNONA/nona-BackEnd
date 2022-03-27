package letsnona.nonabackend.domain.file.service;

import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
import letsnona.nonabackend.domain.file.dto.PostImgResponseDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    String getSaveDirectoryPath();

    @Transactional
    Boolean makeTumbnail(File originalFile,  PostImgRequestDTO postImgRequestDTO) throws IOException;

    @Transactional
    List<PostImgRequestDTO> saveImage(Post post, List<MultipartFile> multipartFiles);
}
