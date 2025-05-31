package com.unionclass.memberservice.agreement.presentation;

import com.unionclass.memberservice.agreement.application.AgreementService;
import com.unionclass.memberservice.agreement.dto.in.CreateAgreementReqDto;
import com.unionclass.memberservice.agreement.dto.out.GetAgreementResDto;
import com.unionclass.memberservice.agreement.vo.in.CreateAgreementReqVo;
import com.unionclass.memberservice.agreement.vo.out.GetAgreementResVo;
import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/agreement")
@Tag(name = "agreement")
public class AgreementController {

    private final AgreementService agreementService;

    @Operation(
            summary = "약관동의 생성 (개발/테스트용)",
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

    @Operation(
            summary = "약관동의 항목 단건 조회",
            description = """
    약관 UUID 를 통해 약관 항목 상세 정보를 조회합니다.

    [요청 경로]
    - GET /api/v1/agreement/{agreementUuid}

    [요청 파라미터]
    - agreementUuid: 약관 항목의 고유 식별자 (UUID)

    [응답 데이터]
    - agreementName: 약관 명
    - agreementContent: 약관 본문
    - required: 필수 여부 (필수: true / 선택: false)

    [예외 상황]
    - FAILED_TO_FIND_AGREEMENT: 존재하지 않는 UUID 를 조회한 경우 발생
    """
    )
    @GetMapping("/{agreementUuid}")
    public BaseResponseEntity<GetAgreementResVo> getAgreement(
            @PathVariable Long agreementUuid
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_AGREEMENT.getMessage(),
                agreementService.getAgreement(agreementUuid).toVo());
    }
}