package com.cheeup.service.member;

import com.cheeup.domain.member.MemberInfo;

public interface MemberUseCase {
    MemberInfo getMemberInfo(Long id);
}
