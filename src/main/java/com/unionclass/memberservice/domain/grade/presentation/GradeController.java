package com.unionclass.memberservice.domain.grade.presentation;

import com.unionclass.memberservice.common.response.BaseResponseEntity;
import com.unionclass.memberservice.common.response.ResponseMessage;
import com.unionclass.memberservice.domain.grade.application.GradeService;
import com.unionclass.memberservice.domain.grade.dto.in.CreateGradeReqDto;
import com.unionclass.memberservice.domain.grade.dto.out.GetAllGradeResDto;
import com.unionclass.memberservice.domain.grade.vo.in.CreateGradeReqVo;
import com.unionclass.memberservice.domain.grade.vo.out.GetAllGradeResVo;
import com.unionclass.memberservice.domain.grade.vo.out.GetGradeNameResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grade")
@Tag(name = "grade")
public class GradeController {

    private final GradeService gradeService;

    /**
     * /api/v1/grade
     *
     * 1. 등급 생성
     * 2. 등급 전체 조회
     * 3. 등급명 조회
     * 4. 등급 정보 변경
     */

    /**
     * 1. 등급 생성
     *
     * @param createGradeReqVo
     * @return
     */
    @Operation(
            summary = "등급 생성 (개발/테스트용)",
            description = """
                    ⚠️ 본 API 는 실제 사용자에게는 제공되지 않으며, 개발 환경 또는 테스트 목적에 한해 사용됩니다.
                    
                    새로운 등급을 생성할 때 사용하는 API 입니다.
        
                    [요청 바디]
                    - gradeName : (String) 등급 이름 (SILVER, GOLD, PLATINUM, DIAMOND, MASTER)
                    - gradeRequirement : (String) 등급 조건 설명
                    - commissionDiscountRate : (BigDecimal) 수수료 할인율
        
                    [처리 로직]
                    - 요청 데이터를 기반으로 새로운 등급 엔티티 생성
        
                    [예외 상황]
                    - FAILED_TO_CREATE_GRADE : 등급 저장 중 내부 서버 오류
                    """
    )
    @PostMapping
    public BaseResponseEntity<Void> createGrade(
            @RequestBody CreateGradeReqVo createGradeReqVo
    ) {
        gradeService.createGrade(CreateGradeReqDto.from(createGradeReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CREATE_GRADE.getMessage());
    }

    @Operation(
            summary = "등급 전체 조회",
            description = """
                    모든 등급 ID 와 등급명을 조회하는 API 입니다.
        
                    [응답 필드]
                    - gradeId : (Long) 등급  ID
                    - gradeName : (String) 등급명
        
                    [처리 로직]
                    - 등급 테이블에서 모든 등급 엔티티를 조회하여 등급 ID 와 등급명을 리스트로 반환
                    """
    )
    @GetMapping("/all")
    public BaseResponseEntity<List<GetAllGradeResVo>> getAllGrades() {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_ALL_GRADES.getMessage(),
                gradeService.getAllGrades().stream().map(GetAllGradeResDto::toVo).toList());
    }

    @Operation(
            summary = "등급명 조회",
            description = """
                    등급 ID를 기반으로 등급명을 조회하는 API 입니다.
        
                    [요청 경로]
                    - gradeId : (Long) 등급 고유 식별자
        
                    [응답 필드]
                    - gradeName : (String) 등급명 (SILVER, GOLD, PLATINUM, DIAMOND, MASTER)
        
                    [처리 로직]
                    - gradeId를 기준으로 등급 엔티티를 조회
                    - 존재하지 않으면 예외 발생
        
                    [예외 상황]
                    - FAILED_TO_FIND_GRADE : 해당 ID 에 해당하는 등급을 찾을 수 없는 경우
                    """

    )
    @GetMapping("/{gradeId}")
    public BaseResponseEntity<GetGradeNameResVo> getGradeNameByGradeId(
            @PathVariable Long gradeId
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_GRADE_NAME.getMessage(),
                gradeService.getGradeNameByGradeId(gradeId).toVo());
    }
}
