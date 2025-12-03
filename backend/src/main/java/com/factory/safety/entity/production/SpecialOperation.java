package com.factory.safety.entity.production;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "special_operation")
public class SpecialOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operationType;

    @Column(nullable = false)
    private String operatorName;

    @Column(nullable = false)
    private String certificateNumber;

    @Column(nullable = false)
    private LocalDate certificateIssuedDate;

    @Column(nullable = false)
    private LocalDate certificateExpiryDate;

    @Column(nullable = false)
    private String issuingAuthority;

    @Column(nullable = false)
    private LocalDate lastTrainingDate;

    @Column(nullable = false)
    private LocalDate nextTrainingDate;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String workLocation;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false)
    private String status;

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
