package com.unionclass.memberservice.domain.agreement.infrastructure;

import com.unionclass.memberservice.domain.agreement.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    Optional<Agreement> findByUuid(Long agreementUuid);
}
