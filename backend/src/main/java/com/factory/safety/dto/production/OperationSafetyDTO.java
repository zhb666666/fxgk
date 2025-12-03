package com.factory.safety.dto.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationSafetyDTO {
    private Long id;
    private String operationName;
    private String procedureContent;
    private String safetyLevel;
    private String department;
    private String owner;
    private String riskPoints;
    private String controlMeasures;
    private String status;
    private LocalDateTime lastReviewDate;
    private String lastReviewBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
