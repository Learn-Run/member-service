package com.unionclass.memberservice.agreement.application;

import com.unionclass.memberservice.agreement.dto.in.CreateAgreementReqDto;
import com.unionclass.memberservice.agreement.dto.out.GetAgreementResDto;
import com.unionclass.memberservice.agreement.dto.out.GetValidAgreementUuidResDto;

import java.util.List;

public interface AgreementService {
    void createAgreement(CreateAgreementReqDto createAgreementReqDto);
    GetAgreementResDto getAgreement(Long agreementUuid);
    List<GetValidAgreementUuidResDto> getAllValidAgreementUuids();
}
