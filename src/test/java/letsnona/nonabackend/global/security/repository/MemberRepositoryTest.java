package letsnona.nonabackend.global.security.repository;

import letsnona.nonabackend.global.security.dto.chart.GenderRatioDTO;
import letsnona.nonabackend.global.security.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("QLRM TEST")
    @Transactional
    void testQLRM() {
        //given
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        Query nativeQuery = em.createNativeQuery("select gender, count(*) as cnt from member group by gender");
        //when

        List<GenderRatioDTO> list = jpaResultMapper.list(nativeQuery, GenderRatioDTO.class);
        //then

        assertThat(list).isNotEmpty();
    }

/*
    @Test
    @DisplayName("성별 통계")
    void groupByGender() {
        List<Object[]>> genderDTOS = memberRepository.groupByGender();
        System.out.println("genderDTOS = " + genderDTOS);
    }
*/


    @Test
    @DisplayName("유저가입")
    void insertUser() {
        //given
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Member member = Member.builder()
                .username("testId")
                .password(passwordEncoder.encode("test"))
                .email("test@naver.com")
                .roles("ROLE_USER")
                .build();

        //when
        memberRepository.save(member);

        //then
        Member getDbMember = memberRepository.findByUsername("testId");
        getDbMember.toString();
        assertThat(getDbMember).isEqualTo(member);

    }
    @Test
    @DisplayName("멤버갖고오기")
    void getUser() {
        Member test3 = memberRepository.findByUsername("test3");
        System.out.println("test3 = " + test3);

    }


}