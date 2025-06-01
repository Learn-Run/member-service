package com.unionclass.memberservice.member.application;

import com.unionclass.memberservice.member.dto.in.ChangeNicknameReqDto;
import com.unionclass.memberservice.member.dto.in.ChangePasswordReqDto;
import com.unionclass.memberservice.member.dto.in.ResetPasswordReqDto;

public interface MemberService {

    void changePassword(ChangePasswordReqDto changePasswordReqDto);
    void resetPasswordWithTemporary(ResetPasswordReqDto resetPasswordReqDto);
    void changeNickname(ChangeNicknameReqDto changeNicknameReqDto);
}
