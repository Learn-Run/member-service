package com.unionclass.memberservice.agreement.infrastructure;

import com.unionclass.memberservice.agreement.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
}
