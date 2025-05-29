package com.unionclass.memberservice.oauth.application;

import com.unionclass.memberservice.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.oauth.dto.in.BindOAuthAccountReqDto;
import com.unionclass.memberservice.oauth.dto.in.ProviderReqDto;

public interface MemberOAuthService {

    SignInResDto signInWithOAuth(ProviderReqDto providerReqDto);
    void bindOAuthAccount(BindOAuthAccountReqDto bindOAuthAccountReqDto);
}
