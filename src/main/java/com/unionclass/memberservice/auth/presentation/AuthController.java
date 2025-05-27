package com.unionclass.memberservice.auth.presentation;

import com.unionclass.memberservice.auth.application.AuthService;
import com.unionclass.memberservice.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.auth.vo.in.SignInReqVo;
import com.unionclass.memberservice.auth.vo.in.SignUpReqVo;
import com.unionclass.memberservice.auth.vo.out.SignInResVo;
import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.email.vo.in.EmailReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "auth")
public class AuthController {

    private final AuthService authService;

    /**
     * /api/v1/auth
     *
     * 1. 회원가입
     * 2. 로그인
     * 3. 이메일 중복 검사
     */

    /**
     * 1. 회원가입
     *
     * @param signUpReqVo
     * @return
     */
    @Operation(
            summary = "회원가입",
            description = """
    회원가입을 위한 요청 정보입니다.

    - loginId: 4~20자, 공백 불가
    - password: 8~20자, 대문자/소문자/숫자/특수문자 포함 필수
    - email: 유효한 이메일 형식
    - birthDate: yyyy-MM-dd 형식, 생년월일 필수
    - gender: 남성 또는 여성
    - nickname: 2~12자, 공백 불가
    - userRole: 일반회원
    """
    )
    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpReqVo signUpReqVo
    ) {
        authService.signUp(SignUpReqDto.from(signUpReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_SIGN_UP.getMessage());
    }

    /**
     * 2. 로그인
     *
     * @param signInReqVo
     * @return
     */
    @Operation(
            summary = "로그인",
            description = """
    사용자의 아이디와 비밀번호를 입력받아 로그인을 수행합니다.
    인증에 성공하면 JWT 토큰을 반환합니다.

    [요청 조건]
    - loginId : 4~20자 (필수 입력)
    - password : 최소 8자 이상, 대/소문자 + 숫자 + 특수문자 포함 필수 (필수 입력)

    [인증 실패]
    - HTTP 401 UNAUTHORIZED
    - 에러 코드: FAILED_TO_SIGN_IN
    """
    )
    @PostMapping("/sign-in")
    public BaseResponseEntity<SignInResVo> signIn(
            @Valid @RequestBody SignInReqVo signInReqVo
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_SIGN_IN.getMessage(),
                authService.signIn(SignInReqDto.from(signInReqVo)).toVo()
        );
    }

    /**
     * 3. 이메일 중복 검사
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
        authService.checkEmailDuplicate(EmailReqDto.from(emailReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHECK_EMAIL_DUPLICATE.getMessage());
    }
}