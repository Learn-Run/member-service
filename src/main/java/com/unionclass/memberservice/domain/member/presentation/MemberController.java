package com.unionclass.memberservice.domain.member.presentation;

import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.domain.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.domain.email.vo.in.EmailReqVo;
import com.unionclass.memberservice.domain.member.application.MemberService;
import com.unionclass.memberservice.domain.member.vo.out.GetMyInfoResVo;
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
     * 1. 이메일 중복 검사
     * 2. 내 정보 조회
     */

    /**
     * 1. 이메일 중복 검사
     *
     * @param emailReqVo
     * @return
     */
    @Operation(
            summary = "이메일 중복 검사",
            description = """
                    입력된 이메일이 이미 가입되어 있는지 확인합니다.

                    - 사용 중인 이메일이면 409(CONFLICT) 에러 발생
                    - 사용 가능한 이메일이면 200 OK 응답 반환
                    """
    )
    @PostMapping("/email/check-duplicate")
    public BaseResponseEntity<Void> checkEmailDuplicate(
            @Valid @RequestBody EmailReqVo emailReqVo
    ) {
        memberService.checkEmailDuplicate(EmailReqDto.from(emailReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHECK_EMAIL_DUPLICATE.getMessage());
    }

    /**
     * 2. 내 정보 조회
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
                ResponseMessage.SUCCESS_GET_MY_INFORMATION.getMessage(),
                memberService.getMyInfo(memberUuid).toVo());
    }
}
