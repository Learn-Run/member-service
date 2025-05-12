package com.unionclass.memberservice.member.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import com.unionclass.memberservice.member.enums.Gender;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberUuid;

    @Column(unique = true)
    private String loginId;
    private String password;
    private String email;
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String nickname;
    private Boolean deletedStatus;
    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime deletedAt;

    @Builder
    public Member(
            Long id, Long memberUuid, String loginId, String password, String email,
            LocalDate birthDate, Gender gender, String nickname, Boolean deletedStatus, LocalDateTime deletedAt
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.deletedStatus = deletedStatus;
        this.deletedAt = deletedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.memberUuid.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}