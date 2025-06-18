package com.unionclass.memberservice.domain.member.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import com.unionclass.memberservice.domain.member.enums.Gender;
import com.unionclass.memberservice.domain.member.enums.MemberRole;
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
    private MemberRole memberRole;

    @Column(nullable = false)
    private boolean deleted;
    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime deletedAt;

    @Builder
    public Member(
            Long id, String memberUuid, String email, String name, LocalDate birthDate,
            Gender gender, MemberRole memberRole, boolean deleted, LocalDateTime deletedAt
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.memberRole = memberRole;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }
}