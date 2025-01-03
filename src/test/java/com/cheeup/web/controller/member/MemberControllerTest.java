package com.cheeup.web.controller.member;

import com.cheeup.converter.member.MemberConverterImpl;
import com.cheeup.service.member.MemberService;
import com.cheeup.web.dto.member.UpdateMemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import({MemberConverterImpl.class})
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemberService memberService;

    @Autowired
    ObjectMapper objectMapper;

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

    @Test
    @DisplayName("회원 수정 API - 성공")
    void updateMemberInfo() throws Exception {
        long memberId = 1L;
        UpdateMemberDto.Request request = createUpdateMemberRequest();
        String body = objectMapper.writeValueAsString(request);

        mockMvc.perform(put("/member/"+memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());

        then(memberService).should()
                .updateMemberInfo(memberId, request);
    }

    UpdateMemberDto.Request createUpdateMemberRequest() {
        return UpdateMemberDto.Request.builder()
                .nickname("김싸피")
                .email("kim-ssafy@example.com")
                .group("SSAFY 12기")
                .profileImage("https://kakao.com/profile-image.jpg")
                .skills(List.of(12L, 13L))
                .preferredJobs(List.of(2L, 3L))
                .build();
    }
}