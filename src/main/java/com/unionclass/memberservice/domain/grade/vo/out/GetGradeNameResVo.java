package com.unionclass.memberservice.domain.grade.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetGradeNameResVo {

    private String gradeName;

    @Builder
    public GetGradeNameResVo(String gradeName) {
        this.gradeName = gradeName;
    }
}
