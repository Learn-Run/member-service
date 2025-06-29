package com.unionclass.memberservice.domain.auth.util;

import com.unionclass.memberservice.common.security.JwtProvider;
import com.unionclass.memberservice.domain.auth.entity.Auth;
import com.unionclass.memberservice.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtilsImpl implements AuthUtils {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    /**
     * 1. 토큰 생성
     *
     * @param authentication
     * @return
     */
    @Override
    public String createToken(Authentication authentication) {
        return jwtProvider.generateAccessToken(authentication);
    }

    /**
     * 2. 인증
     *
     * @param auth
     * @param inputPassword
     * @return
     */
    @Override
    public Authentication authenticate(Auth auth, String inputPassword) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(auth.getMemberUuid(), inputPassword));
    }
}