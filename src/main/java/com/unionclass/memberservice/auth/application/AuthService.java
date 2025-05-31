package com.unionclass.memberservice.auth.application;

import com.unionclass.memberservice.auth.dto.in.GetLoginIdReqDto;
import com.unionclass.memberservice.auth.dto.in.GetNicknameReqDto;
import com.unionclass.memberservice.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.auth.dto.out.GetMemberUuidResDto;
import com.unionclass.memberservice.email.dto.in.EmailReqDto;

public interface AuthService {

    void signUp(SignUpReqDto signUpReqDto);
    SignInResDto signIn(SignInReqDto signInReqDto);
    void checkEmailDuplicate(EmailReqDto emailReqDto);
    void checkLoginIdDuplicate(GetLoginIdReqDto getLoginIdReqDto);
    void checkNicknameDuplicate(GetNicknameReqDto nicknameReqDto);
    GetMemberUuidResDto signUpAndReturnMemberUuid(SignUpReqDto signUpReqDto);
}
