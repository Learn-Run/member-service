package com.unionclass.memberservice.email.presentation;

import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.email.application.EmailService;
import com.unionclass.memberservice.email.dto.in.EmailCodeReqDto;
import com.unionclass.memberservice.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.email.vo.in.EmailCodeReqVo;
import com.unionclass.memberservice.email.vo.in.EmailReqVo;
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
@Tag(name = "auth")
public class EmailController {

    private final EmailService emailService;

    /**
     * /api/v1/email
     *
     * 1. 메일 인증코드 발송
     * 2. 메일 인증코드 검증
     */

    /**
     * 1. 메일 인증코드 발송
     *
     * @param emailReqVo
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @Operation(summary = "메일 인증코드 발송")
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
    @Operation(summary = "메일 인증코드 검증")
    @PostMapping("/verify-code")
    public BaseResponseEntity<Void> verifyEmailCode(
            @Valid @RequestBody EmailCodeReqVo emailCodeReqVo
    ) {
        emailService.verifyEmailCode(EmailCodeReqDto.from(emailCodeReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_VERIFY_EMAIL_CODE.getMessage());
    }
}
