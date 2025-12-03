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
@Table(name = "waste_gas")
public class WasteGas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate recordDate;

    @Column(nullable = false)
    private String emissionSource;

    @Column(nullable = false)
    private String treatmentMethod;

    @Column(nullable = false)
    private Double sulphurDioxide;

    @Column(nullable = false)
    private Double nitrogenOxide;

    @Column(nullable = false)
    private Double particulateMatter;

    @Column(nullable = false)
    private Double volatileOrganicCompounds;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Double emissionRate;

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
