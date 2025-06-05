package com.unionclass.memberservice.domain.grade.dto.out;

import com.unionclass.memberservice.domain.grade.entity.Grade;
import com.unionclass.memberservice.domain.grade.vo.out.GetAllGradeResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAllGradeResDto {

    private Long gradeId;
    private String gradeName;

    @Builder
    public GetAllGradeResDto(Long gradeId, String gradeName) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
    }

    public static GetAllGradeResDto from(Grade grade) {
        return GetAllGradeResDto.builder()
                .gradeId(grade.getId())
                .gradeName(grade.getName())
                .build();
    }

    public GetAllGradeResVo toVo() {
        return GetAllGradeResVo.builder()
                .gradeId(gradeId)
                .gradeName(gradeName)
                .build();
    }
}
