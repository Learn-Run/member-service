package com.unionclass.memberservice.domain.member.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import com.unionclass.memberservice.domain.member.enums.Gender;
import com.unionclass.memberservice.domain.member.enums.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, unique = true, length = 36)
    private String memberUuid;

    @Comment("로그인 아이디")
    @Column(nullable = false, unique = true, length = 20)
    private String loginId;

    @Comment("비밀번호")
    @Column(nullable = false, length = 60)
    private String password;

    @Comment("이메일")
    @Column(nullable = false, unique = true)
    private String email;

    @Comment("이름")
    @Column(nullable = false)
    private String name;

    @Comment("생년월일")
    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    private Boolean deletedStatus;
    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime deletedAt;

    @Builder
    public Member(
            Long id, String memberUuid, String loginId, String password,
            String email, String name, LocalDate birthDate, Gender gender,
            UserRole userRole, Boolean deletedStatus, LocalDateTime deletedAt
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.userRole = userRole;
        this.deletedStatus = deletedStatus;
        this.deletedAt = deletedAt;
    }
}