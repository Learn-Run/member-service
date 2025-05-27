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
    @Operation(summary = "회원가입")
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
    @Operation(summary = "로그인")
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
    @Operation(summary = "이메일 중복 검사")
    @PostMapping("/email/check-duplicate")
    public BaseResponseEntity<Void> checkEmailDuplicate(
            @Valid @RequestBody EmailReqVo emailReqVo
    ) {
        authService.checkEmailDuplicate(EmailReqDto.from(emailReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHECK_EMAIL_DUPLICATE.getMessage());
    }
}