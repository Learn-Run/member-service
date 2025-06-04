package com.unionclass.memberservice.domain.grade.vo.in;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateGradeReqVo {

    private String gradeName;
    private String gradeRequirement;
    private BigDecimal commissionDiscountRate;
}
