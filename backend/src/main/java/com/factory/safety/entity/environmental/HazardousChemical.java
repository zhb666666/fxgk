package com.factory.safety.entity.environmental;

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
@Table(name = "hazardous_chemical")
public class HazardousChemical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String chemicalName;

    @Column(nullable = false)
    private String chemicalFormula;

    @Column(nullable = false)
    private String classificationCode;

    @Column(nullable = false)
    private Double currentStock;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Double maxStorageLimit;

    @Column(nullable = false)
    private String storageLocation;

    @Column(nullable = false)
    private String storageMethod;

    @Column(nullable = false)
    private String containerType;

    @Column(columnDefinition = "TEXT")
    private String hazardDescription;

    @Column(columnDefinition = "TEXT")
    private String safetyPrecautions;

    @Column(columnDefinition = "TEXT")
    private String emergencyMeasures;

    @Column(nullable = false)
    private LocalDate lastInspectionDate;

    @Column(nullable = false)
    private LocalDate nextInspectionDate;

    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String remarks;

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
