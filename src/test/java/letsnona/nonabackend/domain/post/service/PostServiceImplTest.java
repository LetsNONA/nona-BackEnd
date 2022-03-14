package letsnona.nonabackend.domain.post.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PostServiceImplTest {

@Autowired
PostServiceImpl service;
    @Test
    void isHaveDir(){
        service.getSaveDirectory();

    }

}