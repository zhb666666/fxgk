package com.factory.safety.dto.environmental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteGasDTO {
    private Long id;
    private LocalDate recordDate;
    private String emissionSource;
    private String treatmentMethod;
    private Double sulphurDioxide;
    private Double nitrogenOxide;
    private Double particulateMatter;
    private Double volatileOrganicCompounds;
    private String unit;
    private Double emissionRate;
    private String complianceStatus;
    private String remarks;
    private String recordedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
