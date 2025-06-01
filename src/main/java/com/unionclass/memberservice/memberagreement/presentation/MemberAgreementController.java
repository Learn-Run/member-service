package com.unionclass.memberservice.memberagreement.presentation;

import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.memberagreement.application.MemberAgreementService;
import com.unionclass.memberservice.memberagreement.dto.in.RegisterMemberAgreementReqDto;
import com.unionclass.memberservice.memberagreement.vo.in.RegisterMemberAgreementReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member/agreement")
@Tag(name = "member_agreement")
public class MemberAgreementController {

    private final MemberAgreementService memberAgreementService;

    /**
     * /api/v1/member/agreement
     *
     * 1. 회원 약관동의 여부 등록
     */

    /**
     * 1. 회원 약관동의 여부 등록
     *
     * @param memberUuid
     * @param registerMemberAgreementReqVo
     * @return
     */
    @Operation(
            summary = "회원 약관동의 여부 등록",
            description = """
    회원이 특정 약관 항목에 대해 동의 또는 비동의 여부를 등록합니다.
    
    [요청 헤더]
    - X-Member-UUID : (String) 필수 입력, 회원 식별자
    
    [요청 바디]
    - agreementUuid : (Long)  필수 입력, 약관 항목의 고유 식별자
    - agreementStatus : (Boolean) 필수 입력, 동의 여부 (true: 동의, false: 비동의)
    
    [처리 로직]
    - 필수 항목인데 agreementStatus 가 false 인 경우 예외 처리
    - 유효한 경우 동의 내역 저장
    
    [예외 상황]
    - MUST_AGREE_REQUIRED_AGREEMENT: 필수 약관 항목에 동의하지 않은 경우
    - FAILED_TO_FIND_AGREEMENT: 존재하지 않는 agreementUuid 로 요청한 경우
    """
    )
    @PostMapping
    public BaseResponseEntity<Void> registerMemberAgreement(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @Valid @RequestBody RegisterMemberAgreementReqVo registerMemberAgreementReqVo
    ) {
        memberAgreementService.registerMemberAgreement(
                RegisterMemberAgreementReqDto.from(memberUuid, registerMemberAgreementReqVo)
        );
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_REGISTER_MEMBER_AGREEMENT.getMessage());
    }
}
