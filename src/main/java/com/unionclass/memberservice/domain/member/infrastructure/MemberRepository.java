package com.unionclass.memberservice.domain.member.infrastructure;

import com.unionclass.memberservice.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);
    Optional<Member> findByMemberUuid(String memberUuid);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
}
