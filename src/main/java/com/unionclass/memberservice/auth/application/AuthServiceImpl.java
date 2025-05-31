package com.unionclass.memberservice.auth.application;

import com.unionclass.memberservice.auth.dto.in.GetLoginIdReqDto;
import com.unionclass.memberservice.auth.dto.in.GetNicknameReqDto;
import com.unionclass.memberservice.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.auth.dto.in.SignUpReqDto;
import com.unionclass.memberservice.auth.dto.out.GetMemberUuidResDto;
import com.unionclass.memberservice.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.auth.util.AuthUtils;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.member.entity.Member;
import com.unionclass.memberservice.member.infrastructure.MemberRepository;
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

    /**
     * /api/v1/auth
     *
     * 1. 회원가입
     * 2. 로그인
     * 3. 이메일 중복 검사
     * 4. 아이디 중복 검사
     * 5. 닉네임 중복 검사
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
            memberRepository.save(
                    signUpReqDto.toEntity(passwordEncoder.encode(signUpReqDto.getPassword()))
            );
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
        if (memberRepository.findByLoginId(getLoginIdReqDto.getAccount()).isPresent()) {
            log.warn("아이디 중복됨 - 입력 아이디: {}", getLoginIdReqDto.getAccount());
            throw new BaseException(ErrorCode.LOGIN_ID_ALREADY_EXISTS);
        }
        log.info("아이디 중복 없음 - 입력 아이디: {}", getLoginIdReqDto.getAccount());
    }

    /**
     * 5. 닉네임 중복 검사
     *
     * @param nicknameReqDto
     */
    @Override
    public void checkNicknameDuplicate(GetNicknameReqDto nicknameReqDto) {
        if (memberRepository.findByNickname(nicknameReqDto.getNickname()).isPresent()) {
            log.warn("닉네임 중복됨 - 입력 닉네임: {}", nicknameReqDto.getNickname());
            throw new BaseException(ErrorCode.NICKNAME_ALREADY_EXISTS);
        }
        log.info("닉네임 중복 없음 - 입력 닉네임: {}", nicknameReqDto.getNickname());
    }

    @Transactional
    @Override
    public GetMemberUuidResDto signUpAndReturnMemberUuid(SignUpReqDto signUpReqDto) {
        try {
            return GetMemberUuidResDto.from(
                    memberRepository.save(
                            signUpReqDto.toEntity(passwordEncoder.encode(signUpReqDto.getPassword()))
                    )
            );
        } catch (Exception e) {
            throw new BaseException(ErrorCode.FAILED_TO_SIGN_UP);
        }
    }
}