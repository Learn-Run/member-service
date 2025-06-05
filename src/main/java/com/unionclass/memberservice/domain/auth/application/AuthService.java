package com.unionclass.memberservice.domain.auth.application;

import com.unionclass.memberservice.domain.auth.dto.in.GetLoginIdReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.domain.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.domain.auth.dto.out.SignUpResDto;
import com.unionclass.memberservice.domain.email.dto.in.EmailReqDto;

public interface AuthService {

    SignUpResDto signUp(SignUpReqDto signUpReqDto);
    SignInResDto signIn(SignInReqDto signInReqDto);
    void checkEmailDuplicate(EmailReqDto emailReqDto);
    void checkLoginIdDuplicate(GetLoginIdReqDto getLoginIdReqDto);
}
