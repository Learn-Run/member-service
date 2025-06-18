package com.unionclass.memberservice.domain.email.application;

import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.common.redis.deduplicator.RedisDeduplicator;
import com.unionclass.memberservice.common.redis.util.RedisUtils;
import com.unionclass.memberservice.domain.auth.application.AuthService;
import com.unionclass.memberservice.domain.email.dto.in.EmailCodeReqDto;
import com.unionclass.memberservice.domain.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.domain.email.enums.EmailTitle;
import com.unionclass.memberservice.domain.email.util.EmailTemplateProvider;
import com.unionclass.memberservice.domain.email.util.EmailUtils;
import com.unionclass.memberservice.domain.member.application.MemberService;
import com.unionclass.memberservice.domain.member.dto.in.ResetPasswordReqDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final RedisUtils redisUtils;
    private final EmailUtils emailUtils;
    private final EmailTemplateProvider emailTemplateProvider;
    private final JavaMailSender mailSender;
    private final AuthService authService;
    private final RedisDeduplicator redisDeduplicator;

    private static final String EMAIL_VERIFY_KEY_PREFIX = "verify:email:";
    private static final String TEMP_PASSWORD_DEDUPLICATION_KEY_PREFIX = "dedup:temp-password:";
    private static final long EMAIL_CODE_TTL = 5L;
    private static final TimeUnit EMAIL_CODE_TTL_UNIT = TimeUnit.MINUTES;
    private static final int PASSWORD_LENGTH = 8;
    private static final Duration DEDUPLICATION_TTL = Duration.ofSeconds(60);

    /**
     * /api/v1/email
     *
     * 1. 메일 인증코드 발송
     * 2. 메일 인증코드 검증
     * 3. 임시 비밀번호 발급 (Redis Deduplicator 사용)
     */

    /**
     * 1. 메일 인증코드 발송
     *
     * @param emailReqDto
     */
    @Override
    public void sendVerificationCode(EmailReqDto emailReqDto) {

        String verificationCode = emailUtils.generateRandomCode();
        redisUtils.setValueWithTTL(EMAIL_VERIFY_KEY_PREFIX + emailReqDto.getEmail(), verificationCode, EMAIL_CODE_TTL, EMAIL_CODE_TTL_UNIT);

        try {
            mailSender.send(
                    emailUtils.createMessage(
                            emailReqDto.getEmail(),
                            EmailTitle.VERIFY_CODE.getEmailTitle(),
                            emailTemplateProvider.getVerificationCodeByEmailTemplate(verificationCode)
                    )
            );
            log.info("메일 인증코드 발송 성공 - 수신자: {}", emailReqDto.getEmail());
        } catch (UnsupportedEncodingException e) {
            log.error("인코딩 설정 오류: {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.EMAIL_ENCODING_ERROR);
        } catch (MessagingException e) {
            log.error("메일 전송 실패: {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.EMAIL_SEND_FAIL);
        }
    }

    /**
     * 2. 메일 인증코드 검증
     *
     * @param emailCodeReqDto
     */
    @Override
    public void verifyEmailCode(EmailCodeReqDto emailCodeReqDto) {

        String redisKey = EMAIL_VERIFY_KEY_PREFIX + emailCodeReqDto.getEmail();
        String storedCode = redisUtils.getValue(redisKey).toString();

        if (!redisUtils.hasKey(redisKey)) {
            log.warn("인증코드 키 없음 - 이메일: {}", emailCodeReqDto.getEmail());
            throw new BaseException(ErrorCode.EMAIL_CODE_EXPIRED);
        }

        if (!storedCode.equals(emailCodeReqDto.getVerificationCode())) {
            log.warn("메일 인증코드 불일치 - 이메일 : {}, 입력값 : {}, 실제값 : {}",
                    emailCodeReqDto.getEmail(), emailCodeReqDto.getVerificationCode(), storedCode);
            throw new BaseException(ErrorCode.EMAIL_CODE_INVALID);
        }

        redisUtils.delete(redisKey);
        log.info("메일 인증코드 검증 성공 - 이메일 : {}", emailCodeReqDto.getEmail());
    }

    /**
     * 3. 임시 비밀번호 발급 (Redis Deduplicator 사용)
     *
     * @param emailReqDto
     */
    @Override
    public void sendTemporaryPasswordWithDeduplicator(EmailReqDto emailReqDto) {

        String email = emailReqDto.getEmail();
        String redisKey = TEMP_PASSWORD_DEDUPLICATION_KEY_PREFIX + email;

        if (!redisDeduplicator.isFirstRequest(redisKey, DEDUPLICATION_TTL)) {
            log.warn("임시 비밀번호 발급 중복 요청됨 - 수신자: {}", email);
            throw new BaseException(ErrorCode.DUPLICATE_TEMPORARY_PASSWORD_REQUEST);
        }

        String tempPassword = emailUtils.generateRandomPassword(PASSWORD_LENGTH);
        boolean isCompleted = false;

        try {
            authService.resetPasswordWithTemporary(
                    ResetPasswordReqDto.of(email, tempPassword));

            mailSender.send(
                    emailUtils.createMessage(
                            email,
                            EmailTitle.TEMPORARY_PASSWORD.getEmailTitle(),
                            emailTemplateProvider.getTemporaryPasswordByEmailTemplate(tempPassword)
                    )
            );
            log.info("임시 비밀번호 발급 성공 - 수신자: {}", email);
            isCompleted = true;

        } catch (UnsupportedEncodingException e) {
            log.error("인코딩 오류: {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.EMAIL_ENCODING_ERROR);
        } catch (MessagingException e) {
            log.error("메일 전송 실패: {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.EMAIL_SEND_FAIL);
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            log.error("임시 비밀번호 발급 중 예외 발생 - 수신자: {}", email, e);
            throw new BaseException(ErrorCode.FAILED_TO_RESET_PASSWORD);
        } finally {
            if (!isCompleted) {
                redisUtils.delete(redisKey);
            }
        }
    }
}