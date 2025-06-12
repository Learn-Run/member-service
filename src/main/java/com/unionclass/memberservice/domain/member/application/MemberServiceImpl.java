package com.unionclass.memberservice.domain.member.application;

import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.domain.email.dto.in.EmailReqDto;
import com.unionclass.memberservice.domain.member.dto.in.CreateMemberReqDto;
import com.unionclass.memberservice.domain.member.dto.out.CreateMemberResDto;
import com.unionclass.memberservice.domain.member.dto.out.GetMemberUuidDto;
import com.unionclass.memberservice.domain.member.dto.out.GetMyInfoResDto;
import com.unionclass.memberservice.domain.member.entity.Member;
import com.unionclass.memberservice.domain.member.enums.MemberRole;
import com.unionclass.memberservice.domain.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private static final MemberRole DEFAULT_MEMBER_ROLE = MemberRole.ROLE_MEMBER;

    /**
     * /api/v1/member
     *
     * 1. 이메일 중복 검사
     * 2. 회원 정보 저장
     * 3. 내 정보 조회
     */

    /**
     * 1. 이메일 중복 검사
     *
     * @param emailReqDto
     */
    @Override
    public void checkEmailDuplicate(EmailReqDto emailReqDto) {
        if (memberRepository.findByEmail(emailReqDto.getEmail()).isPresent()) {
            log.warn("이메일 중복됨 - 입력 이메일: {}", emailReqDto.getEmail());
            throw new BaseException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        log.info("이메일 중복 없음 - 입력 이메일: {}", emailReqDto.getEmail());
    }

    /**
     * 2. 회원 정보 저장
     *
     * @param createMemberReqDto
     */
    @Transactional
    @Override
    public CreateMemberResDto createMember(CreateMemberReqDto createMemberReqDto) {
        try {
            Member member = createMemberReqDto.toEntity(DEFAULT_MEMBER_ROLE);
            memberRepository.save(member);
            log.info("회원 정보 저장 성공 - email: {}, memberUuid: {}",
                    createMemberReqDto.getEmail(), member.getMemberUuid());

            return CreateMemberResDto.from(member);
        } catch (Exception e) {
            log.warn("회원 정보 저장 중 알 수 없는 오류 발생 - email: {}", createMemberReqDto.getEmail());
            throw new BaseException(ErrorCode.FAILED_TO_SAVE_MEMBER);
        }
    }

    /**
     * 3. 내 정보 조회
     *
     * @param memberUuid
     * @return
     */
    @Override
    public GetMyInfoResDto getMyInfo(String memberUuid) {
        return GetMyInfoResDto.from(memberRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER)));
    }

    @Override
    public GetMemberUuidDto getMemberUuidByEmail(String email) {
        return GetMemberUuidDto.from(memberRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER)));
    }
}