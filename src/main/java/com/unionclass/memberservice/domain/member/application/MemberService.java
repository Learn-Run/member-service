package com.unionclass.memberservice.domain.member.application;

import com.unionclass.memberservice.domain.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.domain.member.dto.in.CreateMemberReqDto;
import com.unionclass.memberservice.domain.member.dto.out.CreateMemberResDto;
import com.unionclass.memberservice.domain.member.dto.out.GetMemberUuidDto;
import com.unionclass.memberservice.domain.member.dto.out.GetMyInfoResDto;

public interface MemberService {

    void checkEmailDuplicate(EmailReqDto emailReqDto);
    CreateMemberResDto createMember(CreateMemberReqDto createMemberReqDto);
    GetMyInfoResDto getMyInfo(String memberUuid);
    GetMemberUuidDto getMemberUuidByEmail(String email);
}
