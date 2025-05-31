package com.unionclass.memberservice.agreement.presentation;

import com.unionclass.memberservice.agreement.application.AgreementService;
import com.unionclass.memberservice.agreement.dto.in.CreateAgreementReqDto;
import com.unionclass.memberservice.agreement.vo.in.CreateAgreementReqVo;
import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/agreement")
@Tag(name = "agreement")
public class AgreementController {

    private final AgreementService agreementService;

    @Operation(
            summary = "약관동의 생성",
            description = """
    ⚠️ 본 API 는 운영 사용자에게 노출되지 않으며, 개발 환경 또는 테스트 목적에 한해 사용됩니다.
    
    새로운 약관 항목을 생성합니다.

    [요청 조건]
    - agreementName: 필수 입력, 약관 제목 (예: 홈페이지 이용약관, 개인정보 수집 및 이용동의 등)
    - agreementContent: 필수 입력, 약관 본문
    - required: 필수 여부 (true: 필수 동의, false: 선택 동의)

    [처리 로직]
    - 입력값 검증 후 약관 엔티티로 변환하고 DB에 저장

    [예외 상황]
    - FAILED_TO_CREATE_AGREEMENT: 약관 저장 실패
    """
    )
    @PostMapping
    public BaseResponseEntity<Void> createAgreement(
            @Valid @RequestBody CreateAgreementReqVo createAgreementReqVo
    ) {
        agreementService.createAgreement(CreateAgreementReqDto.from(createAgreementReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CREATE_AGREEMENT.getMessage());
    }


}