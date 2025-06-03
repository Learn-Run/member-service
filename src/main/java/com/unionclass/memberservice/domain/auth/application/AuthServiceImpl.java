package com.unionclass.memberservice.domain.auth.application;

import com.unionclass.memberservice.client.profile.application.ProfileServiceClient;
import com.unionclass.memberservice.client.profile.dto.in.RegisterNicknameReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.GetLoginIdReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.domain.auth.dto.out.GetMemberUuidResDto;
import com.unionclass.memberservice.domain.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.domain.auth.util.AuthUtils;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.domain.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.domain.member.entity.Member;
import com.unionclass.memberservice.domain.member.enums.UserRole;
import com.unionclass.memberservice.domain.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthUtils authUtils;
    private final ProfileServiceClient profileServiceClient;

    private static final UserRole DEFAULT_USER_ROLE = UserRole.ROLE_MEMBER;

    /**
     * /api/v1/auth
     *
     * 1. 회원가입
     * 2. 로그인
     * 3. 이메일 중복 검사
     * 4. 아이디 중복 검사
     */

    /**
     * 1. 회원가입
     *
     * @param signUpReqDto
     */
    @Transactional
    @Override
    public void signUp(SignUpReqDto signUpReqDto) {
        try {
            Member member = signUpReqDto.toEntity(
                    passwordEncoder.encode(signUpReqDto.getPassword()),
                    DEFAULT_USER_ROLE
            );
            memberRepository.save(member);

            profileServiceClient.registerNickname(
                    RegisterNicknameReqDto.of(member.getMemberUuid(), signUpReqDto.getNickname()));
        } catch (Exception e) {
            throw new BaseException(ErrorCode.FAILED_TO_SIGN_UP);
        }
    }

    /**
     * 2. 로그인
     *
     * @param signInReqDto
     * @return
     */
    @Transactional
    @Override
    public SignInResDto signIn(SignInReqDto signInReqDto) {
        try {
            Member member = memberRepository.findByLoginId(signInReqDto.getLoginId())
                    .orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_SIGN_IN));
            return SignInResDto.of(
                    member,
                    authUtils.createToken(authUtils.authenticate(member, signInReqDto.getPassword())).substring(7));
        } catch (Exception e) {
            throw new BaseException(ErrorCode.FAILED_TO_SIGN_IN);
        }
    }

    /**
     * 3. 이메일 중복 검사
     *
     * @param emailReqDto
     */
    @Override
    public void checkEmailDuplicate(EmailReqDto emailReqDto) {
        if (memberRepository.findByEmail(emailReqDto.getEmail()).isPresent()) {
            log.warn("이메일 중복됨 - 입력 이메일: {}", emailReqDto.getEmail());
            throw new BaseException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        log.info("이메일 중복 없음 - 입력 이메일: {}", emailReqDto.getEmail());
    }

    /**
     * 4. 아이디 중복 검사
     *
     * @param getLoginIdReqDto
     */
    @Override
    public void checkLoginIdDuplicate(GetLoginIdReqDto getLoginIdReqDto) {
        if (memberRepository.findByLoginId(getLoginIdReqDto.getLoginId()).isPresent()) {
            log.warn("아이디 중복됨 - 입력 아이디: {}", getLoginIdReqDto.getLoginId());
            throw new BaseException(ErrorCode.LOGIN_ID_ALREADY_EXISTS);
        }
        log.info("아이디 중복 없음 - 입력 아이디: {}", getLoginIdReqDto.getLoginId());
    }

    @Transactional
    @Override
    public GetMemberUuidResDto signUpAndReturnMemberUuid(SignUpReqDto signUpReqDto) {
        try {
            Member member = signUpReqDto.toEntity(
                    passwordEncoder.encode(signUpReqDto.getPassword()),
                    DEFAULT_USER_ROLE
            );
            memberRepository.save(member);

            profileServiceClient.registerNickname(
                    RegisterNicknameReqDto.of(member.getMemberUuid(), signUpReqDto.getNickname()));
            return GetMemberUuidResDto.from(member);
        } catch (Exception e) {
            throw new BaseException(ErrorCode.FAILED_TO_SIGN_UP);
        }
    }
}