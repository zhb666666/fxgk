package com.factory.safety.entity.production;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operation_safety")
public class OperationSafety {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operationName;

    @Column(columnDefinition = "TEXT")
    private String procedureContent;

    @Column(nullable = false)
    private String safetyLevel;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String owner;

    @Column(columnDefinition = "TEXT")
    private String riskPoints;

    @Column(columnDefinition = "TEXT")
    private String controlMeasures;

    @Column(nullable = false)
    private String status;

    private LocalDateTime lastReviewDate;

    private String lastReviewBy;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
