package com.factory.safety.dto.environmental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HazardousChemicalDTO {
    private Long id;
    private String chemicalName;
    private String chemicalFormula;
    private String classificationCode;
    private Double currentStock;
    private String unit;
    private Double maxStorageLimit;
    private String storageLocation;
    private String storageMethod;
    private String containerType;
    private String hazardDescription;
    private String safetyPrecautions;
    private String emergencyMeasures;
    private LocalDate lastInspectionDate;
    private LocalDate nextInspectionDate;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
