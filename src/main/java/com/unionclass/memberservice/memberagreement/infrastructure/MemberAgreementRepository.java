package com.unionclass.memberservice.memberagreement.infrastructure;

import com.unionclass.memberservice.memberagreement.entity.MemberAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAgreementRepository extends JpaRepository<MemberAgreement, Long> {
}
