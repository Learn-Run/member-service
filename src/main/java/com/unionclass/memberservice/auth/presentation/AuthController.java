package com.unionclass.memberservice.auth.presentation;

import com.unionclass.memberservice.auth.application.AuthService;
import com.unionclass.memberservice.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.auth.vo.in.SignInReqVo;
import com.unionclass.memberservice.auth.vo.in.SignUpReqVo;
import com.unionclass.memberservice.auth.vo.out.SignInResVo;
import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/api/v1/auth")
@Tag(name = "auth")
public class AuthController {

    private final AuthService authService;

    /**
     * /member-service/api/v1/auth
     *
     * 1. 회원가입
     * 2. 로그인
     */

    /**
     * 1. 회원가입
     * @param signUpReqVo
     * @return
     */
    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpReqVo signUpReqVo
    ) {
        authService.signUp(SignUpReqDto.from(signUpReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SIGN_UP_SUCCESS);
    }

    /**
     * 2. 로그인
     * @param signInReqVo
     * @return
     */
    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public BaseResponseEntity<SignInResVo> signIn(
            @Valid @RequestBody SignInReqVo signInReqVo
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SIGN_IN_SUCCESS,
                authService.signIn(SignInReqDto.from(signInReqVo)).toVo()
        );
    }
}
