package com.unionclass.memberservice.agreement.presentation;

import com.unionclass.memberservice.agreement.application.AgreementService;
import com.unionclass.memberservice.agreement.dto.in.CreateAgreementReqDto;
import com.unionclass.memberservice.agreement.dto.in.UpdateAgreementReqDto;
import com.unionclass.memberservice.agreement.dto.out.GetValidAgreementUuidResDto;
import com.unionclass.memberservice.agreement.vo.in.CreateAgreementReqVo;
import com.unionclass.memberservice.agreement.vo.in.UpdateAgreementReqVo;
import com.unionclass.memberservice.agreement.vo.out.GetAgreementResVo;
import com.unionclass.memberservice.agreement.vo.out.GetValidAgreementUuidResVo;
import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/agreement")
@Tag(name = "agreement")
public class AgreementController {

    private final AgreementService agreementService;

    /**
     * /api/v1/agreement
     *
     * 1. 약관동의 항목 생성
     * 2. 약관동의 항목 단건 조회
     * 3. 유효한 약관동의 항목 UUID 전체 리스트 조회
     * 4. 약관동의 항목 수정
     * 5. 약관동의 항목 삭제 (소프트 딜리트)
     */

    /**
     * 1. 약관동의 항목 생성
     *
     * @param createAgreementReqVo
     * @return
     */
    @Operation(
            summary = "약관동의 항목 생성 (개발/테스트용)",
            description = """
    ⚠️ 본 API 는 실제 사용자에게는 제공되지 않으며, 개발 환경 또는 테스트 목적에 한해 사용됩니다.
    
    새로운 약관 항목을 생성합니다.

    [요청 바디]
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

    /**
     * 2. 약관동의 항목 단건 조회
     *
     * @param agreementUuid
     * @return
     */
    @Operation(
            summary = "약관동의 항목 단건 조회",
            description = """
    약관동의 UUID 를 통해 약관동의 항목 상세 정보를 조회합니다.

    [요청 경로]
    - GET /api/v1/agreement/{agreementUuid}

    [요청 파라미터]
    - agreementUuid: 약관 항목의 고유 식별자 (UUID)

    [응답 데이터]
    - agreementName: 약관동의 항목 명
    - agreementContent: 약관동의 항목 본문
    - required: 필수 여부 (true: 필수 동의, false: 선택 동의)

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

    /**
     * 3. 유효한 약관동의 항목 UUID 전체 리스트 조회
     *
     * @return
     */
    @Operation(
            summary = "유효한 약관동의 항목 UUID 전체 리스트 조회",
            description = """
    유효한 약관동의 항목들의 UUID 전체 리스트를 조회합니다.

    [요청 경로]
    - GET /api/v1/agreement/uuid/all

    [응답 데이터]
    - UUID (Long 타입) 만 리스트 형태로 응답됩니다.

    [처리 로직]
    - agreement 테이블에서 deleted = false 인 항목만 필터링
    - 각 항목의 uuid 값만 추출 후 리스트로 반환

    [예외 상황]
    - 약관동의 항목이 하나도 없는 경우 빈 리스트 반환
    """
    )
    @GetMapping("/uuid/all")
    public BaseResponseEntity<List<GetValidAgreementUuidResVo>> getAllValidAgreementUuids() {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_ALL_VALID_AGREEMENT_UUIDS.getMessage(),
                agreementService.getAllValidAgreementUuids().stream().map(GetValidAgreementUuidResDto::toVo).toList()
        );
    }

    /**
     * 4. 약관동의 항목 수정
     *
     * @param agreementUuid
     * @param updateAgreementReqVo
     * @return
     */
    @Operation(
            summary = "약관동의 항목 수정 (개발/테스트용)",
            description = """
    ⚠️ 본 API 는 실제 사용자에게는 제공되지 않으며, 개발 환경 또는 테스트 목적에 한해 사용됩니다.

    지정된 약관동의 UUID 에 해당하는 약관동의 항목의 내용을 수정합니다.
    요청 필드 중 null 로 전달된 항목은 기존 값을 유지합니다.

    [요청 경로]
    - PUT /api/v1/agreement/{agreementUuid}

    [요청 필드]
    - agreementName (선택): 수정할 약관동의 항목 제목
    - agreementContent (선택): 수정할 약관동의 항목 본문
    - required (선택): 필수 여부 (true: 필수 동의, false: 선택 동의)

    [예외 상황]
    - FAILED_TO_FIND_AGREEMENT: 존재하지 않는 UUID 를 조회한 경우 발생
    """
    )
    @PutMapping("/{agreementUuid}")
    public BaseResponseEntity<Void> updateAgreement(
            @PathVariable Long agreementUuid,
            @Valid @RequestBody UpdateAgreementReqVo updateAgreementReqVo
    ) {
        agreementService.updateAgreement(UpdateAgreementReqDto.of(agreementUuid, updateAgreementReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_UPDATE_AGREEMENT.getMessage());
    }

    /**
     * 5. 약관동의 항목 삭제 (소프트 딜리트)
     *
     * @param agreementUuid
     * @return
     */
    @Operation(
            summary = "약관동의 항목 삭제 (개발/테스트용)",
            description = """
    ⚠️ 본 API 는 실제 사용자에게는 제공되지 않으며, 개발 환경 또는 테스트 목적에 한해 사용됩니다.
    
    해당 UUID 에 해당하는 약관동의 항목을 소프트 딜리트 방식으로 삭제합니다.

    [요청 경로]
    - PUT /api/v1/agreement/{agreementUuid}/delete

    [처리 로직]
    - 실제 데이터를 삭제하지 않고, deleted = true, deletedAt = 현재 시간 으로 상태를 변경합니다.

    [예외 상황]
    - FAILED_TO_FIND_AGREEMENT: 존재하지 않는 UUID 를 입력한 경우 발생
    """
    )
    @PutMapping("/{agreementUuid}/delete")
    public BaseResponseEntity<Void> deleteAgreement(
            @PathVariable Long agreementUuid
    ) {
        agreementService.deleteAgreement(agreementUuid);
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_DELETE_AGREEMENT.getMessage());
    }
}