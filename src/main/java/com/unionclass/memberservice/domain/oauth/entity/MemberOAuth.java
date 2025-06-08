package com.unionclass.memberservice.domain.oauth.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import com.unionclass.memberservice.domain.oauth.enums.Provider;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_oauth")
public class MemberOAuth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36)
    private String memberUuid;

    @Comment("OAuth Provider")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Comment("Provider 계정 ID")
    @Column(nullable = false, unique = true)
    private String providerAccountId;

    @Builder
    public MemberOAuth(Long id, String memberUuid, Provider provider, String providerAccountId) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.provider = provider;
        this.providerAccountId = providerAccountId;
    }
}
