package letsnona.nonabackend.domain.chat.repository;

import letsnona.nonabackend.domain.chat.dto.NotReadMessageDTO;
import letsnona.nonabackend.global.security.entity.Member;
import letsnona.nonabackend.global.security.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomMessageRepositoryImplTest {
    @Autowired
    CustomMessageRepositoryImpl customMessageRepository;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void getNotReadMessage(){
        //when
        Member test1 = memberRepository.findByUsername("test1");
        Member admin = memberRepository.findByUsername("admin");
        //given
        List<NotReadMessageDTO> notReadMessage = customMessageRepository.getNotReadMessage(test1);
        List<NotReadMessageDTO> notReadMessage1 = customMessageRepository.getNotReadMessage(admin);
        //then
       assertThat(notReadMessage).isEmpty();
        assertThat(notReadMessage1).isNotEmpty();
    }

}