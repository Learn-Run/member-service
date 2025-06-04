package com.unionclass.memberservice.domain.grade.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Grade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String requirement;
    private BigDecimal commissionDiscountRate;

    @Builder
    public Grade(Long id, String name, String requirement, BigDecimal commissionDiscountRate) {
        this.id = id;
        this.name = name;
        this.requirement = requirement;
        this.commissionDiscountRate = commissionDiscountRate;
    }
}
