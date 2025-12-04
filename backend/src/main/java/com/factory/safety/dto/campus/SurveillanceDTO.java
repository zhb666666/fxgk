package com.factory.safety.dto.campus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveillanceDTO {
    private Long id;
    private String cameraName;
    private String location;
    private String cameraType;
    private String manufacturer;
    private String model;
    private Integer resolution;
    private LocalDateTime installationTime;
    private LocalDateTime lastMaintenanceTime;
    private LocalDateTime nextMaintenanceTime;
    private String recordingStatus;
    private String storageLocation;
    private Integer retentionDays;
    private String maintenanceNotes;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
