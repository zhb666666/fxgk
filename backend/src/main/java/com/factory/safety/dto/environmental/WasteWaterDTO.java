package com.factory.safety.dto.environmental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteWaterDTO {
    private Long id;
    private LocalDate recordDate;
    private String source;
    private String treatmentProcess;
    private Double flowRate;
    private String unit;
    private Double pH;
    private Double COD;
    private Double ammoniaNitrogen;
    private Double suspendedSolids;
    private String dischargeLocation;
    private String complianceStatus;
    private String remarks;
    private String recordedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
