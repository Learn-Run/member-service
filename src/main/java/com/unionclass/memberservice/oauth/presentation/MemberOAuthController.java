package com.unionclass.memberservice.oauth.presentation;

import com.unionclass.memberservice.oauth.dto.in.SignUpWithOAuthReqDto;
import com.unionclass.memberservice.auth.vo.out.SignInResVo;
import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.oauth.application.MemberOAuthService;
import com.unionclass.memberservice.oauth.dto.in.ProviderReqDto;
import com.unionclass.memberservice.oauth.vo.in.ProviderReqVo;
import com.unionclass.memberservice.oauth.vo.in.SignUpWithOAuthReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/oauth")
@Tag(name = "oauth")
public class MemberOAuthController {

    private final MemberOAuthService memberOAuthService;

    /**
     * /api/v1/oauth
     *
     * 1. 소셜 로그인
     * 2. 소셜 회원가입
     */

    /**
     * 1. 소셜 로그인
     *
     * @param providerReqVo
     * @return
     */
    @Operation(
            summary = "소셜 로그인",
            description = """
    소셜 계정(provider, providerAccountId)으로 로그인 요청을 처리합니다.
    
    [요청 조건]
    - provider: 필수 입력, 소셜 제공자 (예: KAKAO)
    - providerAccountId: 필수 입력, 소셜 제공자 내부 고유 식별자
    
    [처리 로직]
    - provider + providerAccountId 로 연동된 회원(OAuth 정보) 조회
    - 연동 정보가 존재하면 해당 회원의 UUID 로 회원 인증 처리
    - JWT accessToken 과 생성
    - JWT accessToken 과 Member UUID 반환
    
    [예외 상황]
    - NO_EXIST_OAUTH_MEMBER: 연동된 OAuth 계정 정보가 없을 경우 (→ 회원가입 유도 필요)
    """
    )
    @PostMapping("/sign-in")
    public BaseResponseEntity<SignInResVo> signInWithOAuth(
            @Valid @RequestBody ProviderReqVo providerReqVo
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_SIGN_IN_WITH_OAUTH.getMessage(),
                memberOAuthService.signInWithOAuth(ProviderReqDto.from(providerReqVo)).toVo()
        );
    }

    /**
     * 2. 소셜 회원가입
     *
     * @param signUpWithOAuthReqVo
     * @return
     */
    @Operation(
            summary = "소셜 회원가입"
    )
    @PostMapping("/sign-up")
    public BaseResponseEntity<Void> signUpWithOAuth(
            @Valid @RequestBody SignUpWithOAuthReqVo signUpWithOAuthReqVo
    ) {
        memberOAuthService.signUpWithOAuth(SignUpWithOAuthReqDto.from(signUpWithOAuthReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_SIGN_UP_WITH_OAUTH.getMessage());
    }
}