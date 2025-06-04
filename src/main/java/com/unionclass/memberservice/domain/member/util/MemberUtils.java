package com.unionclass.memberservice.domain.member.util;

import com.unionclass.memberservice.domain.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.domain.member.entity.Member;

public interface MemberUtils {

    Member createMember(SignUpReqDto signUpReqDto);
}
