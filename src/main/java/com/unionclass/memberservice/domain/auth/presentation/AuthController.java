package com.unionclass.memberservice.domain.auth.presentation;

import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.domain.auth.application.AuthService;
import com.unionclass.memberservice.domain.auth.dto.in.GetLoginIdReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignUpWithCredentialsReqDto;
import com.unionclass.memberservice.domain.auth.vo.in.GetLoginIdReqVo;
import com.unionclass.memberservice.domain.auth.vo.in.SignInReqVo;
import com.unionclass.memberservice.domain.auth.vo.in.SignUpReqVo;
import com.unionclass.memberservice.domain.auth.vo.out.SignInResVo;
import com.unionclass.memberservice.domain.member.dto.in.ChangePasswordReqDto;
import com.unionclass.memberservice.domain.member.vo.in.ChangePasswordReqVo;
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
     * 1. 회원가입 (With Credentials)
     * 2. 로그인
     * 3. 아이디 중복 검사
     * 4. 비밀번호 변경
     */

    /**
     * 1. 회원가입 (With Credentials)
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
                    - name: 이름
                    - birthDate: yyyy-MM-dd 형식, 생년월일 필수
                    - gender: 남성 또는 여성
                    - nickname: 2~12자, 공백 불가
                    - memberRole: 일반회원
                    """
    )
    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpReqVo signUpReqVo
    ) {
        authService.signUpWithCredentials(SignUpWithCredentialsReqDto.from(signUpReqVo));
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
                    사용자의 계정과 비밀번호를 입력받아 로그인을 수행합니다.
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
     * 3. 아이디 중복 검사
     *
     * @param getLoginIdReqVo
     * @return
     */
    @Operation(
            summary = "아이디 중복 검사",
            description = """
                    사용자가 입력한 아이디(loginId)가 이미 가입된 아이디인지 검사합니다.
                    
                    [요청 조건]
                    - loginId: 필수 입력, 공백 불가
                
                    [처리 방식]
                    - 아이디로 회원을 조회하여 존재 여부를 확인합니다.
                    - 이미 존재하는 경우 예외를 발생시킵니다.
                    - 존재하지 않으면 중복되지 않은 것으로 간주하고 성공 응답을 반환합니다.
                
                    [예외 코드]
                    - LOGIN_ID_ALREADY_EXISTS: 중복된 아이디일 경우
                    """
    )
    @PostMapping("/login-id/check-duplicate")
    public BaseResponseEntity<Void> checkLoginIdDuplicate(
            @Valid @RequestBody GetLoginIdReqVo getLoginIdReqVo
    ) {
        authService.checkLoginIdDuplicate(GetLoginIdReqDto.from(getLoginIdReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHECK_LOGIN_ID_DUPLICATE.getMessage());
    }

    /**
     * 4. 비밀번호 변경
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
        authService.changePassword(ChangePasswordReqDto.of(memberUuid, passwordReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHANGE_PASSWORD.getMessage());
    }
}