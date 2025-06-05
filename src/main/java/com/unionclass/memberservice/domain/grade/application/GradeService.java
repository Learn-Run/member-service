package com.unionclass.memberservice.domain.grade.application;

import com.unionclass.memberservice.domain.grade.dto.in.CreateGradeReqDto;
import com.unionclass.memberservice.domain.grade.dto.out.GetAllGradeResDto;
import com.unionclass.memberservice.domain.grade.vo.out.GetAllGradeResVo;

import java.util.List;

public interface GradeService {

    void createGrade(CreateGradeReqDto createGradeReqDto);
    List<GetAllGradeResDto> getAllGrades();
}
