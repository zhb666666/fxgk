package com.factory.safety.entity.fire;

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
@Table(name = "fire_control")
public class FireControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String facilityName;

    @Column(nullable = false)
    private String facilityType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDate installationDate;

    @Column(nullable = false)
    private LocalDate lastMaintenanceDate;

    @Column(nullable = false)
    private LocalDate nextMaintenanceDate;

    @Column(nullable = false)
    private String maintenanceCompany;

    @Column(columnDefinition = "TEXT")
    private String maintenanceNotes;

    @Column(nullable = false)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String notes;

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
