package letsnona.nonabackend.domain.file.service;

import letsnona.nonabackend.domain.file.service.FileServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class FileServiceImplTest {

    /*Todo
    *  -현재 시나리오 테스트로 작성되어있어,
    *  유닛테스트 추가해야됩니다 */

    @Autowired
    MockMvc mockMvc;


    @Autowired
    FileServiceImpl service;

    @Test
    @WithUserDetails("testId")
    @DisplayName("controller_img저장")
    void isHaveDir() throws Exception {
        MockMultipartFile file =
                new MockMultipartFile("file", "IMG_1024.jpeg", "image/jpeg", new FileInputStream("D:/IMG_1024.jpg"));
        this.mockMvc.perform(multipart("/user/post").file(file)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("testId")
    void makeTumbnail() throws Exception {
        //given
        MockMultipartFile file =
                new MockMultipartFile("file", "IMG_1024.jpeg", "image/jpeg", new FileInputStream("D:/IMG_1024.jpg"));
        this.mockMvc.perform(multipart("/user/post").file(file)).andDo(print()).andExpect(status().isOk());




    }

}