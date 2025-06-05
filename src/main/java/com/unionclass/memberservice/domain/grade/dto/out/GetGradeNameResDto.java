package com.unionclass.memberservice.domain.grade.dto.out;

import com.unionclass.memberservice.domain.grade.entity.Grade;
import com.unionclass.memberservice.domain.grade.vo.out.GetGradeNameResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetGradeNameResDto {

    private String gradeName;

    @Builder
    public GetGradeNameResDto(String gradeName) {
        this.gradeName = gradeName;
    }

    public static GetGradeNameResDto from(Grade grade) {
        return GetGradeNameResDto.builder()
                .gradeName(grade.getName())
                .build();
    }

    public GetGradeNameResVo toVo() {
        return GetGradeNameResVo.builder()
                .gradeName(gradeName)
                .build();
    }
}
