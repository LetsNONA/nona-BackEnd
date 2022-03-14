package letsnona.nonabackend.domain.post.service;

import org.springframework.web.multipart.MultipartFile;

public interface PostServiceInterface {
    String getSaveDirectory();


    void saveImage(MultipartFile[] multipartFiles);
}
