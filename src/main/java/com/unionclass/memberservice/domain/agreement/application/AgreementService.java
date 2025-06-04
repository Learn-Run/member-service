package com.unionclass.memberservice.domain.agreement.application;

import com.unionclass.memberservice.domain.agreement.dto.in.CreateAgreementReqDto;
import com.unionclass.memberservice.domain.agreement.dto.in.UpdateAgreementReqDto;
import com.unionclass.memberservice.domain.agreement.dto.out.GetAgreementRequiredResDto;
import com.unionclass.memberservice.domain.agreement.dto.out.GetAgreementResDto;
import com.unionclass.memberservice.domain.agreement.dto.out.GetValidAgreementUuidResDto;

import java.util.List;

public interface AgreementService {
    void createAgreement(CreateAgreementReqDto createAgreementReqDto);
    GetAgreementResDto getAgreement(String agreementUuid);
    List<GetValidAgreementUuidResDto> getAllValidAgreementUuids();
    void updateAgreement(UpdateAgreementReqDto updateAgreementReqDto);
    void deleteAgreement(Long agreementUuid);
    GetAgreementRequiredResDto getAgreementRequired(Long agreementUuid);
}
