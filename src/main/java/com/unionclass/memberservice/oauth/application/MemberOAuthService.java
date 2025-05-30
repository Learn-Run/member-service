package com.unionclass.memberservice.oauth.application;

import com.unionclass.memberservice.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.oauth.dto.in.ProviderReqDto;
import com.unionclass.memberservice.oauth.dto.in.SignUpWithOAuthReqDto;

public interface MemberOAuthService {

    SignInResDto signInWithOAuth(ProviderReqDto providerReqDto);
    void signUpWithOAuth(SignUpWithOAuthReqDto signUpWithOAuthReqDto);
}
