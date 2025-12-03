package com.factory.safety.entity.campus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "surveillance")
public class Surveillance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cameraName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String cameraType;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer resolution;

    @Column(nullable = false)
    private LocalDateTime installationTime;

    @Column(nullable = false)
    private LocalDateTime lastMaintenanceTime;

    @Column(nullable = false)
    private LocalDateTime nextMaintenanceTime;

    @Column(nullable = false)
    private String recordingStatus;

    @Column(nullable = false)
    private String storageLocation;

    @Column(nullable = false)
    private Integer retentionDays;

    @Column(columnDefinition = "TEXT")
    private String maintenanceNotes;

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
