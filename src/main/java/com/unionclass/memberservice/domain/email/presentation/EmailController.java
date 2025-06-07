package com.unionclass.memberservice.domain.email.presentation;

import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.domain.email.application.EmailService;
import com.unionclass.memberservice.domain.email.dto.in.EmailCodeReqDto;
import com.unionclass.memberservice.domain.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.domain.email.vo.in.EmailCodeReqVo;
import com.unionclass.memberservice.domain.email.vo.in.EmailReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
@Tag(name = "email")
public class EmailController {

    private final EmailService emailService;

    /**
     * /api/v1/email
     *
     * 1. 메일 인증코드 발송
     * 2. 메일 인증코드 검증
     * 3. 임시 비밀번호 발급 (시간 내 중복 요청 불가)
     */

    /**
     * 1. 메일 인증코드 발송
     *
     * @param emailReqVo
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @Operation(
            summary = "메일 인증코드 발송",
            description = """
                    사용자의 이메일로 인증코드를 발송합니다.
    
                    [요청 조건]
                    - email: 필수, 이메일 형식 준수
                
                    [처리 로직]
                    - 인증코드는 랜덤으로 6자리 숫자가 생성됩니다.
                    - Redis 에 5분 동안 저장됩니다.
                    - 이메일은 템플릿 기반으로 전송됩니다.
                
                    [예외 상황]
                    - EMAIL_SEND_FAIL: 메일 서버 오류
                    - EMAIL_ENCODING_ERROR: 메시지 인코딩 실패
                    """
    )
    @PostMapping("/send-code")
    public BaseResponseEntity<Void> sendVerificationCode(
            @Valid @RequestBody EmailReqVo emailReqVo
    ) throws MessagingException, UnsupportedEncodingException {
        emailService.sendVerificationCode(EmailReqDto.from(emailReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_SEND_VERIFICATION_EMAIL.getMessage());
    }

    /**
     * 2. 메일 인증코드 검증
     *
     * @param emailCodeReqVo
     * @return
     */
    @Operation(
            summary = "메일 인증코드 검증",
            description = """
                    이메일로 발송된 인증코드를 사용자가 입력하면 해당 인증코드의 유효성을 검증합니다.

                    [요청 조건]
                    - email: 필수, 이메일 형식
                    - verificationCode: 필수, 인증코드 (6자리 숫자)
                
                    [처리 로직]
                    - Redis 에 저장된 인증코드(`verify:email:{email}`)와 입력된 코드가 일치하는지 확인
                    - 인증에 성공하면 해당 Redis 키 삭제
                
                    [예외 상황]
                    - EMAIL_CODE_EXPIRED: 인증코드가 존재하지 않음 (TTL 초과 또는 잘못된 이메일)
                    - EMAIL_CODE_INVALID: 인증코드 불일치
                    """
    )
    @PostMapping("/verify-code")
    public BaseResponseEntity<Void> verifyEmailCode(
            @Valid @RequestBody EmailCodeReqVo emailCodeReqVo
    ) {
        emailService.verifyEmailCode(EmailCodeReqDto.from(emailCodeReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_VERIFY_EMAIL_CODE.getMessage());
    }

    /**
     * 3. 임시 비밀번호 발급 (시간 내 중복 요청 불가)
     *
     * @param emailReqVo
     * @return
     */
    @Operation(
            summary = "임시 비밀번호 발급 (시간(60초) 내 중복 요청 불가)",
            description = """
                    사용자의 이메일로 임시 비밀번호를 발급하여 전송합니다.
                    해당 API 는 60초 내에 중복 요청이 불가능합니다.
                    단, 서버 내부 장애로 인한 오류 발생 시 바로 재요청 가능합니다.
                    
                    [요청 조건]
                    - email: 필수 입력, 이메일 형식
                    
                    [처리 로직]
                    - Redis Deduplicator 사용
                    - Redis 키 `dedup:temp-password:{email}` 를 사용하여 중복 요청을 제어합니다.
                    - TTL(예: 60초) 동안 동일 이메일로 다시 요청 시 중복으로 간주되고 차단됩니다.
                    - 비밀번호는 길이 8자의 무작위 문자열로 생성되며, 대문자, 소문자, 숫자, 특수문자를 각각 1자 이상 포함합니다.
                    - 생성된 임시 비밀번호는 사용자 계정의 새 비밀번호로 바로 저장됩니다.
                    - 이후, 이메일로 임시 비밀번호가 전송됩니다.
                    
                    [예외 상황]
                    - DUPLICATE_TEMPORARY_PASSWORD_REQUEST: TTL 내 중복 요청 시 차단
                    - EMAIL_SEND_FAIL: 메일 서버 오류로 전송 실패
                    - EMAIL_ENCODING_ERROR: 메시지 인코딩 실패
                    """
    )
    @PostMapping("/send-password")
    public BaseResponseEntity<Void> sendTemporaryPasswordWithDeduplicator(
            @Valid @RequestBody EmailReqVo emailReqVo
    ) {
        emailService.sendTemporaryPasswordWithDeduplicator(EmailReqDto.from(emailReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_SEND_TEMPORARY_PASSWORD.getMessage());
    }
}
