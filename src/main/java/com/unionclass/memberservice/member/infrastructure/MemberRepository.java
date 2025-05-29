package com.unionclass.memberservice.member.infrastructure;

import com.unionclass.memberservice.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByAccount(String account);
    Optional<Member> findByMemberUuid(String memberUuid);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
}
