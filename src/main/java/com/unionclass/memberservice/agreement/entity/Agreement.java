package com.unionclass.memberservice.agreement.entity;

import com.unionclass.memberservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Agreement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String content;
    private Boolean required;

    private Boolean deleted;
    private Boolean deletedAt;

    @Builder
    public Agreement(Long id, String name, String content, Boolean required, Boolean deleted, Boolean deletedAt) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.required = required;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
    }
}
