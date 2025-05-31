package com.unionclass.memberservice.agreement.application;

import com.unionclass.memberservice.agreement.dto.in.CreateAgreementReqDto;
import com.unionclass.memberservice.agreement.dto.out.GetAgreementResDto;
import com.unionclass.memberservice.agreement.infrastructure.AgreementRepository;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.common.util.NumericUuidGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final NumericUuidGenerator numericUuidGenerator;

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

    @Override
    public GetAgreementResDto getAgreement(Long agreementUuid) {
        return GetAgreementResDto.from(agreementRepository.findByUuid(agreementUuid)
                .orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_FIND_AGREEMENT)));
    }
}
