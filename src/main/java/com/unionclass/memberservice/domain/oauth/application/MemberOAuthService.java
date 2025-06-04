package com.unionclass.memberservice.domain.oauth.application;

import com.unionclass.memberservice.domain.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.domain.oauth.dto.in.ProviderReqDto;
import com.unionclass.memberservice.domain.oauth.dto.in.SignUpWithOAuthReqDto;

public interface MemberOAuthService {

    SignInResDto signInWithOAuth(ProviderReqDto providerReqDto);
    void signUpWithOAuth(SignUpWithOAuthReqDto signUpWithOAuthReqDto);
}
