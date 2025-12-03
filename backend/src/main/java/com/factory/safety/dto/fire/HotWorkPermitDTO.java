package com.factory.safety.dto.fire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotWorkPermitDTO {
    private Long id;
    private String permitNumber;
    private LocalDate workDate;
    private String workType;
    private String location;
    private String operator;
    private String supervisor;
    private String safetyOfficer;
    private String workDescription;
    private String safetyMeasures;
    private String riskAssessment;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
    private LocalDateTime approvedAt;
    private String approvedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
