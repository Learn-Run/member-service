package com.unionclass.memberservice.auth.application;

import com.unionclass.memberservice.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.email.dto.in.EmailReqDto;

public interface AuthService {

    void signUp(SignUpReqDto signUpReqDto);
    SignInResDto signIn(SignInReqDto signInReqDto);
    void checkEmailDuplicate(EmailReqDto emailReqDto);
}
