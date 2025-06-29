package com.unionclass.memberservice.domain.oauth.application;

import com.unionclass.memberservice.client.profile.application.ProfileServiceClient;
import com.unionclass.memberservice.client.profile.dto.in.RegisterNicknameReqDto;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.common.security.CustomUserDetailsService;
import com.unionclass.memberservice.common.security.OAuthUserDetails;
import com.unionclass.memberservice.domain.auth.application.AuthService;
import com.unionclass.memberservice.domain.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.domain.auth.util.AuthUtils;
import com.unionclass.memberservice.domain.auth.util.AuthUtilsImpl;
import com.unionclass.memberservice.domain.member.application.MemberService;
import com.unionclass.memberservice.domain.member.dto.in.CreateMemberReqDto;
import com.unionclass.memberservice.domain.memberagreement.application.MemberAgreementService;
import com.unionclass.memberservice.domain.oauth.dto.in.ProviderReqDto;
import com.unionclass.memberservice.domain.oauth.dto.in.SignUpWithOAuthReqDto;
import com.unionclass.memberservice.domain.oauth.entity.MemberOAuth;
import com.unionclass.memberservice.domain.oauth.infrastructure.MemberOAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberOAuthServiceImpl implements  MemberOAuthService {

    private final ProfileServiceClient profileServiceClient;
    private final MemberAgreementService memberAgreementService;
    private final MemberService memberService;
    private final AuthUtils authUtils;
    private final MemberOAuthRepository memberOAuthRepository;

    /**
     * /api/v1/oauth
     *
     * 1. 소셜 로그인
     * 2. 소셜 회원가입
     */

    /**
     * 1. 소셜 로그인
     *
     * @param providerReqDto
     * @return
     */
    @Transactional
    @Override
    public SignInResDto signInWithOAuth(ProviderReqDto providerReqDto) {

        MemberOAuth memberOAuth = memberOAuthRepository.findByProviderAndProviderAccountId(
                providerReqDto.getProvider(),
                providerReqDto.getProviderAccountId()
        ).orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_OAUTH_MEMBER));

        UserDetails userDetails = new OAuthUserDetails(memberOAuth.getMemberUuid());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = authUtils.createToken(authentication);

        log.info("OAuth 로그인 성공 - memberUuid: {}", memberOAuth.getMemberUuid());
        return SignInResDto.of(memberOAuth.getMemberUuid(), accessToken);
    }

    /**
     * 2. 소셜 회원가입
     *
     * @param signUpWithOAuthReqDto
     */
    @Transactional
    @Override
    public void signUpWithOAuth(SignUpWithOAuthReqDto signUpWithOAuthReqDto) {

        if (memberOAuthRepository.existsByProviderAndProviderAccountId(
                signUpWithOAuthReqDto.getProvider(),
                signUpWithOAuthReqDto.getProviderAccountId()
        )) {
            log.warn("이미 연동된 OAuth 계정이 존재 - provider: {}, accountId: {}",
                    signUpWithOAuthReqDto.getProvider(), signUpWithOAuthReqDto.getProviderAccountId());
            throw new BaseException(ErrorCode.OAUTH_ACCOUNT_ALREADY_BOUND);
        }

        String memberUuid = memberService.createMember(
                CreateMemberReqDto.from(signUpWithOAuthReqDto)).getMemberUuid();

        signUpWithOAuthReqDto.getRegisterMemberAgreementReqVoList().stream()
                .map(vo -> vo.toDto(memberUuid))
                .forEach(memberAgreementService::registerMemberAgreement);

        profileServiceClient.registerNickname(
                RegisterNicknameReqDto.of(memberUuid, signUpWithOAuthReqDto.getNickname()));
        log.info("닉네임 등록 성공 - memberUuid: {}, nickname: {}",
                memberUuid, signUpWithOAuthReqDto.getNickname());

        memberOAuthRepository.save(signUpWithOAuthReqDto.toEntity(memberUuid));
        log.info("OAuth 계정 연동 완료 - provider: {}, accountId: {}, memberUuid: {}",
                signUpWithOAuthReqDto.getProvider(),
                signUpWithOAuthReqDto.getProviderAccountId(),
                memberUuid
        );
    }
}