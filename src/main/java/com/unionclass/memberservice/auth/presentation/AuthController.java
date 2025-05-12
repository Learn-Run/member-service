package com.unionclass.memberservice.auth.presentation;

import com.unionclass.memberservice.auth.application.AuthService;
import com.unionclass.memberservice.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.auth.vo.in.SignUpReqVo;
import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.member.infrastructure.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-service/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 1. 회원가입
     * 2. 로그인
     */

    /**
     * 1. 회원가입
     * @param signUpReqVo
     * @return
     */
    @Operation(summary = "회원가입", tags = {"auth"})
    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpReqVo signUpReqVo
    ) {
        authService.signUp(SignUpReqDto.from(signUpReqVo));
        return new BaseResponseEntity<>("회원가입에 성공하였습니다.");
    }

//    @Operation(summary = "로그인", tags = {"auth"})
//    @PostMapping("/sign-in")
//    public Void signIn(
//            @Valid @RequestBody SignInReqVo signInReqVo
//    )
}
