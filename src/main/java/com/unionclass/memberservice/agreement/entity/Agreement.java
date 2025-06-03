package com.unionclass.memberservice.agreement.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uuid;
    private String name;

    @Lob
    private String content;
    private Boolean required;

    private Boolean deleted;
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
