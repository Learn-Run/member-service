package com.unionclass.memberservice.domain.grade.infrastructure;

import com.unionclass.memberservice.domain.grade.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
