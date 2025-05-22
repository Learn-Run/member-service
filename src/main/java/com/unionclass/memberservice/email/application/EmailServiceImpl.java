package com.unionclass.memberservice.email.application;

import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.email.util.EmailTemplateProvider;
import com.unionclass.memberservice.common.redis.RedisUtils;
import com.unionclass.memberservice.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.email.util.EmailUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final RedisUtils redisUtils;
    private final EmailUtils emailUtils;
    private final EmailTemplateProvider emailTemplateProvider;
    private final JavaMailSender mailSender;

    private static final String EMAIL_VERIFY_KEY_PREFIX = "verify:email:";
    private static final String EMAIL_TITLE = "이메일 인증코드 발송 메일입니다.";
    private static final long EMAIL_CODE_TTL = 5L;
    private static final TimeUnit EMAIL_CODE_TTL_UNIT = TimeUnit.MINUTES;

    /**
     * /member-service/api/v1/email
     *
     * 1. 메일을 통한 인증 코드 발송
     */

    /**
     * 1. 메일을 통한 인증 코드 발송
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
                            EMAIL_TITLE,
                            emailTemplateProvider.getVerificationCodeByEmailTemplate(verificationCode)
                    )
            );
            log.info("이메일 인증코드 발송 성공 - 수신자: {}", emailReqDto.getEmail());
        } catch (UnsupportedEncodingException e) {
            log.error("인코딩 설정 오류: {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.EMAIL_ENCODING_ERROR);
        } catch (MessagingException e) {
            log.error("메일 전송 실패: {}", e.getMessage(), e);
            throw new BaseException(ErrorCode.EMAIL_SEND_FAIL);
        }
    }
}