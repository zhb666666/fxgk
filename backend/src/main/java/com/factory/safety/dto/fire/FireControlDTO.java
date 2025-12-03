package com.factory.safety.dto.fire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FireControlDTO {
    private Long id;
    private String facilityName;
    private String facilityType;
    private String location;
    private Integer quantity;
    private LocalDate installationDate;
    private LocalDate lastMaintenanceDate;
    private LocalDate nextMaintenanceDate;
    private String maintenanceCompany;
    private String maintenanceNotes;
    private String status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
