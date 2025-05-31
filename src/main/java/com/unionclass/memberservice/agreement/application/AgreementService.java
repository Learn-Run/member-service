package com.unionclass.memberservice.agreement.application;

import com.unionclass.memberservice.agreement.dto.in.CreateAgreementReqDto;
import com.unionclass.memberservice.agreement.dto.out.GetAgreementResDto;

public interface AgreementService {
    void createAgreement(CreateAgreementReqDto createAgreementReqDto);
    GetAgreementResDto getAgreement(Long agreementUuid);
}
