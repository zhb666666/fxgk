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
@Table(name = "waste_water")
public class WasteWater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate recordDate;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String treatmentProcess;

    @Column(nullable = false)
    private Double flowRate;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Double pH;

    @Column(nullable = false)
    private Double COD;

    @Column(nullable = false)
    private Double ammoniaNitrogen;

    @Column(nullable = false)
    private Double suspendedSolids;

    @Column(nullable = false)
    private String dischargeLocation;

    @Column(nullable = false)
    private String complianceStatus;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @Column(nullable = false)
    private String recordedBy;

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
