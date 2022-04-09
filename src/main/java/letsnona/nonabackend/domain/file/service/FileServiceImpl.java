package letsnona.nonabackend.domain.file.service;

import letsnona.nonabackend.domain.file.dto.PostImgRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {


    @Value("${file.path}")
    private String filePath;


    @Override
    public String getSaveDirectoryPath() {
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
    public Boolean makeTumbnail(File originalFile, PostImgRequestDTO postImgRequestDTO) throws IOException {
        BufferedImage srcImg = ImageIO.read(originalFile);

        int tumbImg_width = 250;
        int tumbImg_height = 250;
        int ow = srcImg.getWidth();
        int oh = srcImg.getHeight();

        // 원본 너비를 기준으로 하여 썸네일의 비율로 높이를 계산합니다.
        int nw = ow;
        int nh = (ow * tumbImg_height) / tumbImg_width;
        // 계산된 높이가 원본보다 높다면 crop이 안되므로
        // 원본 높이를 기준으로 썸네일의 비율로 너비를 계산합니다.
        if (nh > oh) {
            nw = (oh * tumbImg_width) / tumbImg_height;
            nh = oh;
        }
        // 계산된 크기로 원본이미지를 가운데에서 crop 합니다.
        BufferedImage cropImg = Scalr.crop(srcImg, (ow - nw) / 2, (oh - nh) / 2, nw, nh);
        // crop된 이미지로 썸네일을 생성합니다.
        BufferedImage destImg = Scalr.resize(cropImg, tumbImg_width, tumbImg_height);

        String cropImgName = getSaveDirectoryPath() + "\\s_" + originalFile.getName();
        try {
            File tumbImg = new File(cropImgName);
            ImageIO.write(destImg, "jpg", tumbImg);
            String encode = URLEncoder.encode(cropImgName, StandardCharsets.UTF_8);
            postImgRequestDTO.setThumbImgSrc(encode);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<PostImgRequestDTO> saveImage(List<MultipartFile> multipartFiles) {
        UUID uuid = UUID.randomUUID();

        List<PostImgRequestDTO> requestDTOList = new ArrayList<>();

        for (MultipartFile file : multipartFiles
        ) {

            PostImgRequestDTO postImgRequestDTO = new PostImgRequestDTO();

            postImgRequestDTO.setOriginalName(file.getOriginalFilename());

            String saveFileName = uuid.toString() + "_" + file.getOriginalFilename();
            String savePath = getSaveDirectoryPath();
            File target = new File(getSaveDirectoryPath(), saveFileName);
            try {
                file.transferTo(target);
                //postImgResponseDTO
                String originalSrc = (getSaveDirectoryPath() + "\\" + saveFileName).toString();
                String encode = URLEncoder.encode(originalSrc, StandardCharsets.UTF_8);
                postImgRequestDTO.setOriginalImgSrc(encode);
                makeTumbnail(target, postImgRequestDTO);
                //responseDTOList
                requestDTOList.add(postImgRequestDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return requestDTOList;
    }


}
