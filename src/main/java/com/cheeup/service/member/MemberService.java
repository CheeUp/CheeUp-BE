package com.cheeup.service.member;

import com.cheeup.web.dto.ReadMemberDto;

public interface MemberService {
    ReadMemberDto.Response getMemberInfo(long id);
}
