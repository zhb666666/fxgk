package com.factory.safety.dto.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialOperationDTO {
    private Long id;
    private String operationType;
    private String operatorName;
    private String certificateNumber;
    private LocalDate certificateIssuedDate;
    private LocalDate certificateExpiryDate;
    private String issuingAuthority;
    private LocalDate lastTrainingDate;
    private LocalDate nextTrainingDate;
    private String department;
    private String workLocation;
    private String notes;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
