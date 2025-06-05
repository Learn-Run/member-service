package com.unionclass.memberservice.domain.grade.application;

import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import com.unionclass.memberservice.domain.grade.dto.in.CreateGradeReqDto;
import com.unionclass.memberservice.domain.grade.dto.out.GetAllGradeResDto;
import com.unionclass.memberservice.domain.grade.dto.out.GetGradeNameResDto;
import com.unionclass.memberservice.domain.grade.entity.Grade;
import com.unionclass.memberservice.domain.grade.infrastructure.GradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Transactional
    @Override
    public void createGrade(CreateGradeReqDto createGradeReqDto) {
        try {
            gradeRepository.save(createGradeReqDto.toEntity(createGradeReqDto));
            log.info("등급 생성 성공 - 등급명 : {}", createGradeReqDto.getGradeName());
        } catch (Exception e) {
            log.warn("등급 생성 실패 - 등급명 : {}", createGradeReqDto.getGradeName());
            throw new BaseException(ErrorCode.FAILED_TO_CREATE_GRADE);
        }
    }

    @Override
    public List<GetAllGradeResDto> getAllGrades() {
        return gradeRepository.findAll().stream().map(GetAllGradeResDto::from).toList();
    }

    @Override
    public GetGradeNameResDto getGradeNameByGradeId(Long gradeId) {
        return GetGradeNameResDto.from(gradeRepository.findById(gradeId)
                .orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_FIND_GRADE)));
    }
}