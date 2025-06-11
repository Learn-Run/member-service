package com.unionclass.memberservice.domain.auth.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth extends BaseEntity {

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

    @Builder
    public Auth(Long id, String memberUuid, String loginId, String password) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.loginId = loginId;
        this.password = password;
    }
}
