package letsnona.nonabackend.domain.file.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileServiceUnitTest {

    @Autowired
    FileServiceImpl service;

    @Test
    void saveImg() throws IOException {
//
        String fileName = "test1Img.jpg";
        Path filePath = Paths.get(File.separatorChar + "testImg", File.separatorChar + fileName);
       InputStream file =  getClass().getResourceAsStream(filePath.toString());



    }
}
