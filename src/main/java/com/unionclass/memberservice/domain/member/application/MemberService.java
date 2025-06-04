package com.unionclass.memberservice.domain.member.application;

import com.unionclass.memberservice.domain.member.dto.in.ChangePasswordReqDto;
import com.unionclass.memberservice.domain.member.dto.in.ResetPasswordReqDto;
import com.unionclass.memberservice.domain.member.dto.out.GetMyInfoResDto;

public interface MemberService {

    void changePassword(ChangePasswordReqDto changePasswordReqDto);
    void resetPasswordWithTemporary(ResetPasswordReqDto resetPasswordReqDto);
    GetMyInfoResDto getMyInfo(String memberUuid);
}
