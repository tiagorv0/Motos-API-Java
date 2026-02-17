package com.vazzoller.motosapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    //@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    //@LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedDate;

    @Column(name = "is_active")
    private Boolean active;

    @PrePersist
    protected void onCreate(){
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
        active = true;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedDate = LocalDateTime.now();
    }

    @PreRemove()
    protected void onDelete(){
        updatedDate = LocalDateTime.now();
        active = false;
    }
}
