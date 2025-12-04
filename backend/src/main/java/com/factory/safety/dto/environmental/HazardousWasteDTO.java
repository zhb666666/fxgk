package com.factory.safety.dto.environmental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HazardousWasteDTO {
    private Long id;
    private String wasteName;
    private String wasteCode;
    private String hazardCharacteristics;
    private Double quantity;
    private String unit;
    private LocalDate storageDate;
    private LocalDate disposalDate;
    private String storageLocation;
    private String disposalMethod;
    private String disposalCompany;
    private String storageConditions;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
