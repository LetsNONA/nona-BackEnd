package letsnona.nonabackend.domain.file.service;

import letsnona.nonabackend.domain.file.dto.MemberImgRequestDTO;
import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
import letsnona.nonabackend.domain.file.entity.PostImg;
import letsnona.nonabackend.domain.product.entity.Product;
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
    List<PostImg> saveImage(List<MultipartFile> multipartFiles, Product savedProduct);
    @Transactional
    MemberImgRequestDTO saveMemberImg(List<MultipartFile> multipartFiles);
}
