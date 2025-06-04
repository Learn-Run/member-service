package com.unionclass.memberservice.domain.auth.util;

import com.unionclass.memberservice.domain.member.entity.Member;
import org.springframework.security.core.Authentication;

public interface AuthUtils {

    /**
     * AuthUtils
     *
     * 1. 토큰 생성
     * 2. 인증
     */

    String createToken(Authentication authentication);
    Authentication authenticate(Member member, String inputPassword);
}
