package com.unionclass.memberservice.domain.grade.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAllGradeResVo {

    private Long gradeId;
    private String gradeName;

    @Builder
    public GetAllGradeResVo(Long gradeId, String gradeName) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
    }
}
