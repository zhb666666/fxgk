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
@Table(name = "hazardous_waste")
public class HazardousWaste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String wasteName;

    @Column(nullable = false)
    private String wasteCode;

    @Column(nullable = false)
    private String hazardCharacteristics;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private LocalDate storageDate;

    @Column(nullable = false)
    private LocalDate disposalDate;

    @Column(nullable = false)
    private String storageLocation;

    @Column(nullable = false)
    private String disposalMethod;

    @Column(nullable = false)
    private String disposalCompany;

    @Column(columnDefinition = "TEXT")
    private String storageConditions;

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
