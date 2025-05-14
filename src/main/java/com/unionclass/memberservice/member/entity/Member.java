package com.unionclass.memberservice.member.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import com.unionclass.memberservice.member.enums.Gender;
import com.unionclass.memberservice.member.enums.UserRole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberUuid;

    @Column(unique = true)
    private String loginId;
    private String password;
    private String email;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private Boolean deletedStatus;
    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime deletedAt;

    @Builder
    public Member(
            Long id, String memberUuid, String loginId, String password, String email, LocalDate birthDate,
            Gender gender, String nickname, UserRole userRole, Boolean deletedStatus, LocalDateTime deletedAt
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.userRole = userRole;
        this.deletedStatus = deletedStatus;
        this.deletedAt = deletedAt;
    }
}