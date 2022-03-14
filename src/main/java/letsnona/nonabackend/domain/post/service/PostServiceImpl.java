package letsnona.nonabackend.domain.post.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class PostServiceImpl implements PostServiceInterface {
/*TODO
*  -file upload 후 썸네일처리*/

    @Value("${file.path}")
    private String filePath;

    @Override
    public String getSaveDirectory() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        String datePath = str.replace("-", File.separator);

        File uploadPath = new File(filePath, datePath);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        return uploadPath.toString();
    }

    @Override
    public void saveImage(MultipartFile[] multipartFiles) {
        UUID uuid = UUID.randomUUID();
        for (MultipartFile file : multipartFiles
        ) {
            String saveFileName = uuid.toString() + "_" + file.getOriginalFilename();
            String savePath = getSaveDirectory();

            File target = new File(getSaveDirectory(), saveFileName);

            try {
                file.transferTo(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
