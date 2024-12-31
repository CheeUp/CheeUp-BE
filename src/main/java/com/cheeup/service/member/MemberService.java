package com.cheeup.service.member;

import com.cheeup.web.dto.ReadMemberDto;
import com.cheeup.web.dto.UpdateMemberDto;

public interface MemberService {
    ReadMemberDto.Response getMemberInfo(long id);

    void updateMemberInfo(long id, UpdateMemberDto.Request request);
}
