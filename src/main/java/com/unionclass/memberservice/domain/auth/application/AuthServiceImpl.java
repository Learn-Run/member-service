package com.unionclass.memberservice.domain.auth.application;

import com.unionclass.memberservice.client.profile.application.ProfileServiceClient;
import com.unionclass.memberservice.client.profile.dto.in.RegisterNicknameReqDto;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.common.kafka.event.MemberCreatedEvent;
import com.unionclass.memberservice.common.kafka.util.KafkaProducer;
import com.unionclass.memberservice.domain.auth.dto.in.GetLoginIdReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignInReqDto;
import com.unionclass.memberservice.domain.auth.dto.in.SignUpWithCredentialsReqDto;
import com.unionclass.memberservice.domain.auth.dto.out.SignInResDto;
import com.unionclass.memberservice.domain.auth.entity.Auth;
import com.unionclass.memberservice.domain.auth.infrastructure.AuthRepository;
import com.unionclass.memberservice.domain.auth.util.AuthUtils;
import com.unionclass.memberservice.domain.member.application.MemberService;
import com.unionclass.memberservice.domain.member.dto.in.ChangePasswordReqDto;
import com.unionclass.memberservice.domain.member.dto.in.CreateMemberReqDto;
import com.unionclass.memberservice.domain.member.dto.in.ResetPasswordReqDto;
import com.unionclass.memberservice.domain.memberagreement.application.MemberAgreementService;
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

    private final ProfileServiceClient profileServiceClient;
    private final MemberAgreementService memberAgreementService;
    private final MemberService memberService;
    private final AuthUtils authUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;

    private final KafkaProducer kafkaProducer;

    /**
     * 1. 회원가입 (With Credentials)
     *
     * @param signUpWithCredentialsReqDto
     * @return
     */
    @Transactional
    @Override
    public void signUpWithCredentials(SignUpWithCredentialsReqDto signUpWithCredentialsReqDto) {
        try {
            String memberUuid = memberService.createMember(
                    CreateMemberReqDto.from(signUpWithCredentialsReqDto)).getMemberUuid();

            authRepository.save(signUpWithCredentialsReqDto.toEntity(
                    memberUuid, passwordEncoder.encode(signUpWithCredentialsReqDto.getPassword())));
            log.info("계정 정보 저장 성공 - memberUuid: {}", memberUuid);

            signUpWithCredentialsReqDto.getRegisterMemberAgreementReqVoList().stream()
                    .map(vo -> vo.toDto(memberUuid))
                    .forEach(memberAgreementService::registerMemberAgreement);

            kafkaProducer.sendMemberCreatedEvent(MemberCreatedEvent.of(memberUuid, signUpWithCredentialsReqDto));

//            profileServiceClient.registerNickname(
//                    RegisterNicknameReqDto.of(memberUuid, signUpWithCredentialsReqDto.getNickname()));

            log.info("회원가입 전체 완료 - memberUuid: {}", memberUuid);

        } catch (Exception e) {
            log.error("회원가입 실패 - 입력 데이터: {}, 에러 메시지: {}", signUpWithCredentialsReqDto, e.getMessage(), e);
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
            Auth auth = authRepository.findByLoginId(signInReqDto.getLoginId())
                    .orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_SIGN_IN));
            return SignInResDto.of(
                    auth,
                    authUtils.createToken(authUtils.authenticate(auth, signInReqDto.getPassword())).substring(7));
        } catch (Exception e) {
            throw new BaseException(ErrorCode.FAILED_TO_SIGN_IN);
        }
    }

    /**
     * 3. 아이디 중복 검사
     *
     * @param getLoginIdReqDto
     */
    @Override
    public void checkLoginIdDuplicate(GetLoginIdReqDto getLoginIdReqDto) {
        if (authRepository.findByLoginId(getLoginIdReqDto.getLoginId()).isPresent()) {
            log.warn("아이디 중복됨 - 입력 아이디: {}", getLoginIdReqDto.getLoginId());
            throw new BaseException(ErrorCode.LOGIN_ID_ALREADY_EXISTS);
        }
        log.info("아이디 중복 없음 - 입력 아이디: {}", getLoginIdReqDto.getLoginId());
    }

    /**
     * 4. 비밀번호 변경
     *
     * @param changePasswordReqDto
     */
    @Transactional
    @Override
    public void changePassword(ChangePasswordReqDto changePasswordReqDto) {

        Auth auth = authRepository.findByMemberUuid(changePasswordReqDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(ErrorCode.AUTH_INFORMATION_NOT_FOUND));

        if (!passwordEncoder.matches(changePasswordReqDto.getCurrentPassword(), auth.getPassword())) {
            log.warn("비밀번호 불일치 - UUID: {}", changePasswordReqDto.getMemberUuid());
            throw new BaseException(ErrorCode.INVALID_CURRENT_PASSWORD);
        }

        authRepository.save(
                Auth.builder()
                        .id(auth.getId())
                        .memberUuid(auth.getMemberUuid())
                        .loginId(auth.getLoginId())
                        .password(passwordEncoder.encode(changePasswordReqDto.getNewPassword()))
                        .build()
        );
        log.info("비밀번호 변경 완료 - Member UUID: {}", changePasswordReqDto.getMemberUuid());
    }

    /**
     * 5. 임시 비밀번호 설정
     *
     * @param resetPasswordReqDto
     */
    @Transactional
    @Override
    public void resetPasswordWithTemporary(ResetPasswordReqDto resetPasswordReqDto) {

        Auth auth = authRepository.findByMemberUuid(
                memberService.getMemberUuidByEmail(resetPasswordReqDto.getEmail()).getMemberUuid())
                .orElseThrow(() -> new BaseException(ErrorCode.AUTH_INFORMATION_NOT_FOUND));

        authRepository.save(
                Auth.builder()
                        .id(auth.getId())
                        .memberUuid(auth.getMemberUuid())
                        .loginId(auth.getLoginId())
                        .password(passwordEncoder.encode(resetPasswordReqDto.getTemporaryPassword()))
                        .build()
        );
        log.info("임시 비밀번호로 비밀번호 재설정 완료 - 이메일: {}", resetPasswordReqDto.getEmail());
    }
}