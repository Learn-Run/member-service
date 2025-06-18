package com.unionclass.memberservice.common.security;

import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.domain.auth.infrastructure.AuthRepository;
import com.unionclass.memberservice.domain.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String memberUuid) throws UsernameNotFoundException {
        return new CustomUserDetails(
                authRepository.findByMemberUuid(memberUuid).orElseThrow(
                        () -> new BaseException(ErrorCode.AUTH_INFORMATION_NOT_FOUND)));
    }
}
