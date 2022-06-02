package letsnona.nonabackend.global.security.controller;

import letsnona.nonabackend.global.security.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc

class MemberControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void getHome() throws Exception {

        mockMvc.perform(get("/home"))
                .andExpect(status().isOk());
    }

    @Test
        @WithUserDetails(value = "testId")
//    @WithMockUser(username = "admin", password = "admin") class 타입이 달라서 casting 실패
    void getUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("currentPrincipalName = " + currentPrincipalName);

        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk());

    }



}