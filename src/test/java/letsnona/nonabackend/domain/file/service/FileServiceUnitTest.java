package letsnona.nonabackend.domain.file.service;

import letsnona.nonabackend.domain.file.dto.PostImgResponseDTO;
import letsnona.nonabackend.domain.post.entity.Post;
import letsnona.nonabackend.domain.post.repository.PostRepository;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FileServiceUnitTest {

    @Autowired
    FileService service;
    @Autowired
    PostRepository postRepository;

    @Test
    @DisplayName("이미지저장")
    void saveImg() throws IOException {
        /*   String directory = file.getPath();
        String path = directory.substring(0,directory.length()-13);
        int file_count = 0;
        File dir = new File(path);*/
        Post post = new Post();
        List<MultipartFile> imgLists = new ArrayList<>();
        int file_count = 4;

        for (int i = 1; i <= file_count; i++) {
            File file = new ClassPathResource("/testimg/test" + i + "Img.jpg").getFile();
            FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            imgLists.add(multipartFile);
        }

        service.saveImage(post, imgLists);


    }
}
