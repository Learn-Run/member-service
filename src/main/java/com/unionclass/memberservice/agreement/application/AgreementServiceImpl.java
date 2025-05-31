package com.unionclass.memberservice.agreement.application;

import com.unionclass.memberservice.agreement.dto.in.CreateAgreementReqDto;
import com.unionclass.memberservice.agreement.dto.in.UpdateAgreementReqDto;
import com.unionclass.memberservice.agreement.dto.out.GetAgreementResDto;
import com.unionclass.memberservice.agreement.dto.out.GetValidAgreementUuidResDto;
import com.unionclass.memberservice.agreement.entity.Agreement;
import com.unionclass.memberservice.agreement.infrastructure.AgreementRepository;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.common.util.NumericUuidGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final NumericUuidGenerator numericUuidGenerator;

    /**
     * /api/v1/agreement
     *
     * 1. 약관동의 항목 생성
     * 2. 약관동의 항목 단건 조회
     * 3. 유효한 약관동의 항목 UUID 전체 리스트 조회
     */

    /**
     * 1. 약관동의 항목 생성
     *
     * @param createAgreementReqDto
     */
    @Transactional
    @Override
    public void createAgreement(CreateAgreementReqDto createAgreementReqDto) {
        try {
            agreementRepository.save(createAgreementReqDto.toEntity(numericUuidGenerator.generate()));
            log.info("약관동의 항목 생성 완료 : name = {}, 필수여부 = {}",
                    createAgreementReqDto.getAgreementName(),
                    createAgreementReqDto.getRequired());
        } catch (Exception e) {
            log.error("약관동의 항목 생성 실패 : name = {}, 필수여부 = {}",
                    createAgreementReqDto.getAgreementName(),
                    createAgreementReqDto.getRequired());
            throw new BaseException(ErrorCode.FAILED_TO_CREATE_AGREEMENT);
        }
    }

    /**
     * 2. 약관동의 항목 단건 조회
     *
     * @param agreementUuid
     * @return
     */
    @Override
    public GetAgreementResDto getAgreement(Long agreementUuid) {
        return GetAgreementResDto.from(agreementRepository.findByUuid(agreementUuid)
                .orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_FIND_AGREEMENT)));
    }

    /**
     * 3. 유효한 약관동의 항목 UUID 전체 리스트 조회
     *
     * @return
     */
    @Override
    public List<GetValidAgreementUuidResDto> getAllValidAgreementUuids() {
        return agreementRepository.findAll().stream()
                .filter(agreement -> Boolean.FALSE.equals(agreement.getDeleted()))
                .map(GetValidAgreementUuidResDto::from)
                .toList();
    }

    @Transactional
    @Override
    public void updateAgreement(UpdateAgreementReqDto updateAgreementReqDto) {
        Agreement agreement = agreementRepository.findByUuid(updateAgreementReqDto.getAgreementUuid())
                .orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_FIND_AGREEMENT));

        agreementRepository.save(
                Agreement.builder()
                        .id(agreement.getId())
                        .uuid(agreement.getUuid())
                        .name(updateAgreementReqDto.getAgreementName() == null ? agreement.getName() : updateAgreementReqDto.getAgreementName())
                        .content(updateAgreementReqDto.getAgreementContent() == null ? agreement.getContent() : updateAgreementReqDto.getAgreementContent())
                        .required(updateAgreementReqDto.getRequired() == null ? agreement.getRequired() : updateAgreementReqDto.getRequired())
                        .deleted(agreement.getDeleted())
                        .deletedAt(agreement.getDeletedAt())
                        .build()
        );
    }
}
