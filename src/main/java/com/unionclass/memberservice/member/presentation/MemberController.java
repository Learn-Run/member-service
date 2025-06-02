package com.unionclass.memberservice.member.presentation;

import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.member.application.MemberService;
import com.unionclass.memberservice.member.dto.in.ChangeNicknameReqDto;
import com.unionclass.memberservice.member.dto.in.ChangePasswordReqDto;
import com.unionclass.memberservice.member.vo.in.ChangeNicknameReqVo;
import com.unionclass.memberservice.member.vo.in.ChangePasswordReqVo;
import com.unionclass.memberservice.member.vo.out.GetMyInfoResVo;
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
     * 2. 닉네임 변경
     * 3. 내 정보 조회
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
        memberService.changePassword(ChangePasswordReqDto.of(memberUuid, passwordReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHANGE_PASSWORD.getMessage());
    }

    /**
     * 2. 닉네임 변경
     *
     * @param memberUuid
     * @param changeNicknameReqVo
     * @return
     */
    @Operation(
            summary = "닉네임 변경",
            description = """
                    로그인한 회원 본인의 정보를 조회합니다.
    
                    [요청 헤더]
                    - X-Member-UUID : (String) 필수 입력, 회원 고유 식별자
                    
                    [응답 필드]
                    - email : (String) 이메일 주소
                    - nickname : (String) 닉네임
                    - gender : (String) 성별 (남성 또는 여성)
                    - birthDate : (LocalDate) 생년월일
                    
                    [처리 로직]
                    - memberUuid 를 기준으로 회원 정보를 조회
                    - 존재하지 않는 회원일 경우 예외 발생
                    
                    [예외 상황]
                    - NO_EXIST_MEMBER: 해당 UUID 로 조회된 회원이 없는 경우
                    """
    )
    @PutMapping("/change-nickname")
    public BaseResponseEntity<Void> changeNickname(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @Valid @RequestBody ChangeNicknameReqVo changeNicknameReqVo
    ) {
        memberService.changeNickname(ChangeNicknameReqDto.of(memberUuid, changeNicknameReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHANGE_NICKNAME.getMessage());
    }

    /**
     * 3. 내 정보 조회
     *
     * @param memberUuid
     * @return
     */
    @Operation(
            summary = "내 정보 조회",
            description = """
                    로그인한 회원의 정보를 조회합니다.
                
                    [요청 헤더]
                    - X-Member-UUID : (String) 필수 입력, 회원 고유 식별자
                
                    [응답 필드]
                    - email : (String) 이메일 주소
                    - nickname : (String) 닉네임
                    - gender : (String) 성별 (남성 또는 여성)
                    - birthDate : (LocalDate) 생년월일
                
                    [처리 로직]
                    - memberUuid 를 기준으로 회원 정보를 조회
                    - 존재하지 않는 회원일 경우 예외 발생
                
                    [예외 상황]
                    - NO_EXIST_MEMBER: 해당 UUID 로 조회된 회원이 없는 경우
                    """
    )
    @GetMapping("/my-info")
    public BaseResponseEntity<GetMyInfoResVo> getMyInfo(
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_MY_INFO.getMessage(),
                memberService.getMyInfo(memberUuid).toVo());
    }
}
