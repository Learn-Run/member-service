package com.unionclass.memberservice.domain.member.util;

import com.unionclass.memberservice.domain.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.domain.member.entity.Member;
import com.unionclass.memberservice.domain.member.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtilsImpl implements  MemberUtils {

    private final PasswordEncoder passwordEncoder;
    private static final Long gradeId = 1L;
    private static final UserRole DEFAULT_USER_ROLE = UserRole.ROLE_MEMBER;

    @Override
    public Member createMember(SignUpReqDto signUpReqDto) {
        return signUpReqDto.toEntity(passwordEncoder.encode(signUpReqDto.getPassword()), gradeId, DEFAULT_USER_ROLE);
    }
}
