package com.unionclass.memberservice.memberagreement.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberUuid;
    private Long agreementUuid;
    private Boolean status;

    @Builder
    public MemberAgreement(Long id, String memberUuid, Long agreementUuid, Boolean status) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.agreementUuid = agreementUuid;
        this.status = status;
    }
}
