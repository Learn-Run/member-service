package com.unionclass.memberservice.domain.memberagreement.infrastructure;

import com.unionclass.memberservice.domain.memberagreement.entity.MemberAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberAgreementRepository extends JpaRepository<MemberAgreement, Long> {

    Optional<MemberAgreement> findByMemberUuidAndAgreementUuid(String memberUuid, Long agreementUuid);
}
