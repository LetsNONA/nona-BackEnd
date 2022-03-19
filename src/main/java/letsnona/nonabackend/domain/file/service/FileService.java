package letsnona.nonabackend.domain.file.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    String getSaveDirectoryPath();

    @Transactional
    Boolean makeTumbnail(File originalFile) throws IOException;

    @Transactional
    void saveImage(List<MultipartFile> multipartFiles);
}
