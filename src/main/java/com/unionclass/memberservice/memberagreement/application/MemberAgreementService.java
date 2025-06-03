package com.unionclass.memberservice.memberagreement.application;

import com.unionclass.memberservice.memberagreement.dto.in.RegisterMemberAgreementReqDto;
import com.unionclass.memberservice.memberagreement.dto.in.UpdateMemberAgreementReqDto;

public interface MemberAgreementService {

    void registerMemberAgreement(RegisterMemberAgreementReqDto registerMemberAgreementReqDto);
    void updateMemberAgreement(UpdateMemberAgreementReqDto updateMemberAgreementReqDto);
}
