package com.cheeup.web.controller.member;

import com.cheeup.converter.member.MemberConverterImpl;
import com.cheeup.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import({MemberConverterImpl.class})
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemberService memberService;

    @Test
    @DisplayName("올바른 memberId 요청 테스트 - 성공")
    void getMemberInfo_validMemberId() throws Exception{
        long memberId = 1L;

        mockMvc.perform(get("/member/"+memberId))
                .andExpect(status().isOk());

        then(memberService).should()
                .getMemberInfo(memberId);
    }

    @Test
    @DisplayName("올바르지 않은 memberId API 테스트 - 실패")
    void getMemberInfo_inValidMemberId() throws Exception{
        String memberId = "A";

        mockMvc.perform(get("/member/"+memberId))
                .andExpect(status().is4xxClientError());
    }
}