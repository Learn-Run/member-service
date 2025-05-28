package com.unionclass.memberservice.oauth.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import com.unionclass.memberservice.oauth.enums.Provider;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member_oauth")
public class MemberOAuth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberUuid;

    @Enumerated(EnumType.STRING)
    private Provider provider;
    private String providerAccountId;

    @Builder
    public MemberOAuth(Long id, String memberUuid, Provider provider, String providerAccountId) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.provider = provider;
        this.providerAccountId = providerAccountId;
    }
}
