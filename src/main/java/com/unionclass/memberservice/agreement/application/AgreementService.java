package com.unionclass.memberservice.agreement.application;

import com.unionclass.memberservice.agreement.dto.in.CreateAgreementReqDto;

public interface AgreementService {
    void createAgreement(CreateAgreementReqDto createAgreementReqDto);
}
