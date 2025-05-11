package com.unionclass.memberservice.member.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
    private String memberUuid;
    private String password;
    private Long birthDate;
    private String nickname;
    private Boolean deletedStatus;
    private LocalDateTime deletedAt;

    @Builder
    public Member(
            Long id, String memberUuid, String password, Long birthDate, String nickname,
            Boolean deletedStatus, LocalDateTime deletedAt
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.password = password;
        this.birthDate = birthDate;
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
        return this.memberUuid;
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