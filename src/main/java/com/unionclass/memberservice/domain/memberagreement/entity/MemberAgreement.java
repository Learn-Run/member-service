package com.unionclass.memberservice.domain.memberagreement.entity;

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
@Table(
        name = "member_agreement",
        indexes = {
                @Index(name = "idx_member_uuid", columnList = "memberUuid")
        }
)
public class MemberAgreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("회원 UUID")
    @Column(nullable = false, length = 36)
    private String memberUuid;

    @Comment("이용약관 UUID")
    @Column(nullable = false)
    private Long agreementUuid;

    @Comment("동의 여부")
    @Column(nullable = false)
    private Boolean status;

    @Builder
    public MemberAgreement(Long id, String memberUuid, Long agreementUuid, Boolean status) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.agreementUuid = agreementUuid;
        this.status = status;
    }
}
