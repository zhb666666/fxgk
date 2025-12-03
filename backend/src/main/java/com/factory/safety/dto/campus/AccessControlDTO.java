package com.factory.safety.dto.campus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessControlDTO {
    private Long id;
    private String deviceName;
    private String location;
    private String deviceType;
    private String manufacturer;
    private String model;
    private LocalDateTime installationTime;
    private LocalDateTime lastMaintenanceTime;
    private LocalDateTime nextMaintenanceTime;
    private String maintenanceNotes;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
