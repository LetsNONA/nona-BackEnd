package letsnona.nonabackend.domain.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileServiceInterface {
    String getSaveDirectoryPath();


    Boolean makeTumbnail(File originalFile) throws IOException;

    void saveImage(List<MultipartFile> multipartFiles);
}
