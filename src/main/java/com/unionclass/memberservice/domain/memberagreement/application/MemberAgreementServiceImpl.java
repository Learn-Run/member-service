package com.unionclass.memberservice.domain.memberagreement.application;

import com.unionclass.memberservice.domain.agreement.application.AgreementService;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.domain.memberagreement.dto.in.RegisterMemberAgreementReqDto;
import com.unionclass.memberservice.domain.memberagreement.dto.in.UpdateMemberAgreementReqDto;
import com.unionclass.memberservice.domain.memberagreement.entity.MemberAgreement;
import com.unionclass.memberservice.domain.memberagreement.infrastructure.MemberAgreementRepository;
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
     * 2. 회원 약관동의 여부 변경
     */

    /**
     * 1. 회원 약관동의 여부 등록
     *
     * @param registerMemberAgreementReqDto
     */
    @Transactional
    @Override
    public void registerMemberAgreement(RegisterMemberAgreementReqDto registerMemberAgreementReqDto) {

        if (Boolean.TRUE.equals(
                agreementService.getAgreementRequired(registerMemberAgreementReqDto.getAgreementUuid()).getRequired())
                && Boolean.FALSE.equals(registerMemberAgreementReqDto.getAgreementStatus())) {
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

    /**
     * 2. 회원 약관동의 여부 변경
     *
     * @param updateMemberAgreementReqDto
     */
    @Transactional
    @Override
    public void updateMemberAgreement(UpdateMemberAgreementReqDto updateMemberAgreementReqDto) {

        MemberAgreement memberAgreement = memberAgreementRepository.findByMemberUuidAndAgreementUuid(
                updateMemberAgreementReqDto.getMemberUuid(),
                updateMemberAgreementReqDto.getAgreementUuid()
        ).orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_FIND_MEMBER_AGREEMENT));

        if (Boolean.TRUE.equals(
                agreementService.getAgreementRequired(updateMemberAgreementReqDto.getAgreementUuid()).getRequired())
                && Boolean.FALSE.equals(updateMemberAgreementReqDto.getAgreementStatus())) {
            log.warn("필수 동의항목 상태 변경 불가 - agreementUuid: {}, memberUuid: {}",
                    updateMemberAgreementReqDto.getAgreementUuid(),
                    updateMemberAgreementReqDto.getMemberUuid());
            throw new BaseException(ErrorCode.CANNOT_UPDATE_REQUIRED_AGREEMENT);
        }

        memberAgreementRepository.save(
                MemberAgreement.builder()
                        .id(memberAgreement.getId())
                        .memberUuid(memberAgreement.getMemberUuid())
                        .agreementUuid(memberAgreement.getAgreementUuid())
                        .status(updateMemberAgreementReqDto.getAgreementStatus())
                        .build()
        );
    }
}
