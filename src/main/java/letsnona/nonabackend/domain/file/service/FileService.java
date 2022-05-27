package letsnona.nonabackend.domain.file.service;

import letsnona.nonabackend.domain.file.dto.MemberImgRequestDTO;
import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
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
    Boolean makeMemberTumbnail(File originalFile, MemberImgRequestDTO postImgRequestDTO) throws IOException;
    @Transactional
    //List<PostImgRequestDTO> saveImage(Post post, List<MultipartFile> multipartFiles);
    List<PostImgRequestDTO> saveImage(List<MultipartFile> multipartFiles);
    @Transactional
    MemberImgRequestDTO saveMemberImg(List<MultipartFile> multipartFiles);
}
