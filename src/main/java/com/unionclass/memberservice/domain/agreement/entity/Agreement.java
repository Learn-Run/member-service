package com.unionclass.memberservice.domain.agreement.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("이용약관 UUID")
    @Column(nullable = false, unique = true, length = 36)
    private Long uuid;

    @Comment("이용약관 명")
    @Column(nullable = false)
    private String name;

    @Lob
    @Comment("이용약관 내용")
    private String content;

    @Comment("필수 여부")
    @Column(nullable = false)
    private Boolean required;

    @Comment("삭제 여부")
    @Column(nullable = false)
    private Boolean deleted;

    @Comment("삭제 일시")
    private LocalDateTime deletedAt;

    @Builder
    public Agreement(
            Long id, Long uuid, String name, String content, Boolean required,
            Boolean deleted, LocalDateTime deletedAt
    ) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.content = content;
        this.required = required;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }
}
