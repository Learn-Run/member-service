package com.unionclass.memberservice.domain.auth.application;

import com.unionclass.memberservice.domain.auth.dto.in.GetLoginIdReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignUpWithCredentialsReqDto;
import com.unionclass.memberservice.domain.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.domain.member.dto.in.ChangePasswordReqDto;
import com.unionclass.memberservice.domain.member.dto.in.ResetPasswordReqDto;

public interface AuthService {

    void signUpWithCredentials(SignUpWithCredentialsReqDto signUpWithCredentialsReqDto);
    SignInResDto signIn(SignInReqDto signInReqDto);
    void checkLoginIdDuplicate(GetLoginIdReqDto getLoginIdReqDto);
    void changePassword(ChangePasswordReqDto changePasswordReqDto);
    void resetPasswordWithTemporary(ResetPasswordReqDto resetPasswordReqDto);
}
