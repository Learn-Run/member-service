package com.unionclass.memberservice.memberagreement.application;

import com.unionclass.memberservice.agreement.application.AgreementService;
import com.unionclass.memberservice.agreement.entity.Agreement;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.memberagreement.dto.in.RegisterMemberAgreementReqDto;
import com.unionclass.memberservice.memberagreement.entity.MemberAgreement;
import com.unionclass.memberservice.memberagreement.infrastructure.MemberAgreementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberAgreementServiceImpl implements MemberAgreementService {

    private final MemberAgreementRepository memberAgreementRepository;
    private final AgreementService agreementService;

    /**
     * /api/v1/member/agreement
     *
     * 1. 회원 약관동의 여부 등록
     */

    /**
     * 1. 회원 약관동의 여부 등록
     *
     * @param registerMemberAgreementReqDto
     */
    @Transactional
    @Override
    public void registerMemberAgreement(RegisterMemberAgreementReqDto registerMemberAgreementReqDto) {

        if (!agreementService.getAgreementRequired(registerMemberAgreementReqDto.getAgreementUuid())
                .getRequired().equals(registerMemberAgreementReqDto.getAgreementStatus())) {
            log.warn("필수 동의항목 미동의 - agreementUuid: {}, memberUuid: {}",
                    registerMemberAgreementReqDto.getAgreementUuid(),
                    registerMemberAgreementReqDto.getMemberUuid());
            throw new BaseException(ErrorCode.MUST_AGREE_REQUIRED_AGREEMENT);
        }

        memberAgreementRepository.save(registerMemberAgreementReqDto.toEntity());
        log.info("약관동의 항목 동의 여부 저장 성공 - agreementUuid: {}, memberUuid: {}",
                registerMemberAgreementReqDto.getAgreementUuid(),
                registerMemberAgreementReqDto.getMemberUuid());
    }
}
