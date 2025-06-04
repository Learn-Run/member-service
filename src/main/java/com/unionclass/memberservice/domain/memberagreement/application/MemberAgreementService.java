package com.unionclass.memberservice.domain.memberagreement.application;

import com.unionclass.memberservice.domain.memberagreement.dto.in.RegisterMemberAgreementReqDto;
import com.unionclass.memberservice.domain.memberagreement.dto.in.UpdateMemberAgreementReqDto;

public interface MemberAgreementService {

    void registerMemberAgreement(RegisterMemberAgreementReqDto registerMemberAgreementReqDto);
    void updateMemberAgreement(UpdateMemberAgreementReqDto updateMemberAgreementReqDto);
}
