package com.unionclass.memberservice.auth.application;

import com.unionclass.memberservice.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.auth.dto.out.SignInResDto;

public interface AuthService {

    void signUp(SignUpReqDto signUpReqDto);
    SignInResDto signIn(SignInReqDto signInReqDto);
}
