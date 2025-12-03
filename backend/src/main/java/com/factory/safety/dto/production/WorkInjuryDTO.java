package com.factory.safety.dto.production;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkInjuryDTO {
    private Long id;
    private String injuryType;
    private String affectedEmployee;
    private String employeeId;
    private LocalDate incidentDate;
    private String incidentLocation;
    private String description;
    private String severity;
    private String treatmentDescription;
    private String hospitalName;
    private String department;
    private String reportedBy;
    private String rootCause;
    private String preventiveMeasures;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
