package com.unionclass.memberservice.domain.grade.dto.in;

import com.unionclass.memberservice.domain.grade.entity.Grade;
import com.unionclass.memberservice.domain.grade.vo.in.CreateGradeReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CreateGradeReqDto {

    private String gradeName;
    private String gradeRequirement;
    private BigDecimal commissionDiscountRate;

    @Builder
    public CreateGradeReqDto(String gradeName, String gradeRequirement, BigDecimal commissionDiscountRate) {
        this.gradeName = gradeName;
        this.gradeRequirement = gradeRequirement;
        this.commissionDiscountRate = commissionDiscountRate;
    }

    public static CreateGradeReqDto from(CreateGradeReqVo createGradeReqVo) {
        return CreateGradeReqDto.builder()
                .gradeName(createGradeReqVo.getGradeName())
                .gradeRequirement(createGradeReqVo.getGradeRequirement())
                .commissionDiscountRate(createGradeReqVo.getCommissionDiscountRate())
                .build();
    }

    public Grade toEntity(CreateGradeReqDto createGradeReqDto) {
        return Grade.builder()
                .name(createGradeReqDto.gradeName)
                .requirement(createGradeReqDto.gradeRequirement)
                .commissionDiscountRate(createGradeReqDto.commissionDiscountRate)
                .build();
    }
}
