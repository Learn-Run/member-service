package com.unionclass.memberservice.domain.grade.application;

import com.unionclass.memberservice.domain.grade.dto.in.CreateGradeReqDto;

public interface GradeService {

    void createGrade(CreateGradeReqDto createGradeReqDto);
}
