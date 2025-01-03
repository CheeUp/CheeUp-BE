package com.cheeup.service.member;

import com.cheeup.web.dto.member.ReadMemberDto;
import com.cheeup.web.dto.member.UpdateMemberDto;

public interface MemberService {
    ReadMemberDto.ResponseDto getMemberInfo(long id);

    void updateMemberInfo(long id, UpdateMemberDto.Request request);
}
