package com.unionclass.memberservice.member.application;

import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.member.dto.in.ChangeNicknameReqDto;
import com.unionclass.memberservice.member.dto.in.ChangePasswordReqDto;
import com.unionclass.memberservice.member.dto.in.ResetPasswordReqDto;
import com.unionclass.memberservice.member.dto.out.GetMyInfoResDto;
import com.unionclass.memberservice.member.entity.Member;
import com.unionclass.memberservice.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * /api/v1/member
     *
     * 1. 비밀번호 변경
     * 2. 임시 비밀번호 설정
     * 3. 닉네임 변경
     * 4. 내 정보 조회
     */

    /**
     * 1. 비밀번호 변경
     *
     * @param changePasswordReqDto
     */
    @Transactional
    @Override
    public void changePassword(ChangePasswordReqDto changePasswordReqDto) {

        Member member = memberRepository.findByMemberUuid(changePasswordReqDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER));

        if (!passwordEncoder.matches(changePasswordReqDto.getCurrentPassword(), member.getPassword())) {
            log.warn("비밀번호 불일치 - UUID: {}", changePasswordReqDto.getMemberUuid());
            throw new BaseException(ErrorCode.INVALID_CURRENT_PASSWORD);
        }

        memberRepository.save(
                Member.builder()
                        .id(member.getId())
                        .memberUuid(member.getMemberUuid())
                        .loginId(member.getLoginId())
                        .password(passwordEncoder.encode(changePasswordReqDto.getNewPassword()))
                        .email(member.getEmail())
                        .birthDate(member.getBirthDate())
                        .gender(member.getGender())
                        .nickname(member.getNickname())
                        .userRole(member.getUserRole())
                        .deletedStatus(member.getDeletedStatus())
                        .deletedAt(member.getDeletedAt())
                        .build()
        );
        log.info("비밀번호 변경 완료 - Member UUID: {}", changePasswordReqDto.getMemberUuid());
    }

    /**
     * 2. 임시 비밀번호 설정
     *
     * @param resetPasswordReqDto
     */
    @Transactional
    @Override
    public void resetPasswordWithTemporary(ResetPasswordReqDto resetPasswordReqDto) {

        Member member = memberRepository.findByEmail(resetPasswordReqDto.getEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER));

        memberRepository.save(
                Member.builder()
                        .id(member.getId())
                        .memberUuid(member.getMemberUuid())
                        .loginId(member.getLoginId())
                        .password(passwordEncoder.encode(resetPasswordReqDto.getTemporaryPassword()))
                        .email(member.getEmail())
                        .birthDate(member.getBirthDate())
                        .gender(member.getGender())
                        .nickname(member.getNickname())
                        .userRole(member.getUserRole())
                        .deletedStatus(member.getDeletedStatus())
                        .deletedAt(member.getDeletedAt())
                        .build()
        );
        log.info("임시 비밀번호로 비밀번호 재설정 완료 - 이메일: {}", resetPasswordReqDto.getEmail());
    }

    /**
     * 3. 닉네임 변경
     *
     * @param changeNicknameReqDto
     */
    @Transactional
    @Override
    public void changeNickname(ChangeNicknameReqDto changeNicknameReqDto) {
        Member member = memberRepository.findByMemberUuid(changeNicknameReqDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER));

        memberRepository.save(
                Member.builder()
                        .id(member.getId())
                        .memberUuid(member.getMemberUuid())
                        .loginId(member.getLoginId())
                        .password(member.getPassword())
                        .email(member.getEmail())
                        .birthDate(member.getBirthDate())
                        .gender(member.getGender())
                        .nickname(changeNicknameReqDto.getNickname())
                        .userRole(member.getUserRole())
                        .deletedStatus(member.getDeletedStatus())
                        .deletedAt(member.getDeletedAt())
                        .build()
        );
        log.info("닉네임 변경 완료 - Member UUID: {}, 새로운 닉네임: {}",
                changeNicknameReqDto.getMemberUuid(),
                member.getNickname());
    }

    /**
     * 4. 내 정보 조회
     *
     * @param memberUuid
     * @return
     */
    @Override
    public GetMyInfoResDto getMyInfo(String memberUuid) {
        return GetMyInfoResDto.from(memberRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER)));
    }
}