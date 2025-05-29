package com.unionclass.memberservice.oauth.presentation;

import com.unionclass.memberservice.auth.vo.out.SignInResVo;
import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.oauth.application.MemberOAuthService;
import com.unionclass.memberservice.oauth.dto.in.BindOAuthAccountReqDto;
import com.unionclass.memberservice.oauth.dto.in.ProviderReqDto;
import com.unionclass.memberservice.oauth.vo.in.ProviderReqVo;
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
     * 2. OAuth 계정 연동
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
     * 2. OAuth 계정 연동
     *
     * @param memberUuid
     * @param providerReqVo
     * @return
     */
    @Operation(
            summary = "OAuth 계정 연동",
            description = """
    회원의 계정에 외부 소셜 계정(OAuth)을 연동합니다.
    
    [요구 상황]
    - 이미 회원가입을 했던 회원이 OAuth 를 통해 로그인하려는 경우
      → 로그인 후, 이 API 를 호출하여 OAuth 계정을 연동해야 합니다.
    
    - 우리 서비스에 회원가입하지 않은 사람이 OAuth 를 통해 로그인하려는 경우
      → 먼저 회원가입을 통해 memberUuid 를 발급받은 후, 즉시 이 API 를 호출해 OAuth 계정을 연동합니다.
    
    [요청 헤더]
    - X-Member-UUID: 필수 입력, 현재 로그인할 사용자의 UUID
    
    [요청 바디]
    - provider: 필수 입력, 소셜 로그인 제공자 (예: KAKAO)
    - providerAccountId: 필수, 해당 소셜 제공자에서 발급한 고유 계정 식별자
    
    [처리 로직]
    - 동일한 provider + providerAccountId 조합이 이미 존재하면 연동을 막습니다.
    - 존재하지 않으면, 현재 사용자(UUID)와 해당 OAuth 계정을 연동합니다.
    
    [예외 상황]
    - OAUTH_ACCOUNT_ALREADY_BOUND: 이미 연동된 소셜 계정입니다.
    """
    )
    @PostMapping("/bind-account")
    public BaseResponseEntity<Void> bindOAuthAccount(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @Valid @RequestBody ProviderReqVo providerReqVo
    ) {
        memberOAuthService.bindOAuthAccount(BindOAuthAccountReqDto.of(memberUuid, providerReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_BIND_OAUTH_ACCOUNT.getMessage());
    }
}
