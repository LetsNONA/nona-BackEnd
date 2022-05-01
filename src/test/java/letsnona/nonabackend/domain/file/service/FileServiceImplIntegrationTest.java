package letsnona.nonabackend.domain.file.service;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@Tag("IntegrationTest")
class FileServiceImplIntegrationTest {

    /*Todo
     *  -현재 시나리오 테스트로 작성되어있어,
     *  유닛테스트 추가해야됩니다
     *   또한 resources 폴더에서 사진 입출력 필요 */

    @Autowired
    MockMvc mockMvc;


    @Autowired
    FileServiceImpl service;

    @Test
    void dirTest() {
    service.getSaveDirectoryPath();
    }

/*
    @Test
    @WithUserDetails("testId")
    @DisplayName("controller_img저장")
    void makeTumbnail() throws Exception {
        //given
        MockMultipartFile file =
                new MockMultipartFile("file", "IMG_1024.jpeg", "image/jpeg", new FileInputStream("D:/IMG_1024.jpg"));
        this.mockMvc.perform(multipart("/user/post").file(file)).andDo(print()).andExpect(status().isOk());




    }
*/
}