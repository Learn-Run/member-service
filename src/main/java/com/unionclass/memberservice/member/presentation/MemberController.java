package com.unionclass.memberservice.member.presentation;

import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.member.application.MemberService;
import com.unionclass.memberservice.member.dto.in.ChangePasswordReqDto;
import com.unionclass.memberservice.member.vo.in.ChangePasswordReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@Tag(name = "member")
public class MemberController {

    private final MemberService memberService;

    /**
     * /api/v1/member
     *
     * 1. 비밀번호 변경
     */

    /**
     * 1. 비밀번호 변경
     *
     * @param memberUuid
     * @param passwordReqVo
     * @return
     */
    @Operation(
            summary = "비밀번호 변경",
            description = """
    사용자가 현재 비밀번호를 입력하고 새로운 비밀번호로 변경합니다.

    [요청 조건]
    - 헤더: JWT, X-Member-UUID (회원 고유 식별자) 필수
    - currentPassword: 필수, 현재 비밀번호
    - newPassword: 필수, 8~20자, 대/소문자, 숫자, 특수문자 포함

    [처리 로직]
    - UUID 로 회원 조회
    - 현재 비밀번호와 일치하는지 확인
    - 새로운 비밀번호로 회원 정보 갱신

    [예외 상황]
    - NO_EXIST_MEMBER: 해당 UUID 의 회원이 존재하지 않음
    - INVALID_CURRENT_PASSWORD: 현재 비밀번호가 일치하지 않음
    """
    )
    @PutMapping("/change-password")
    public BaseResponseEntity<Void> changePassword(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @Valid @RequestBody ChangePasswordReqVo passwordReqVo
    ) {
        memberService.changePassword(ChangePasswordReqDto.from(memberUuid, passwordReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHANGE_PASSWORD.getMessage());
    }
}
