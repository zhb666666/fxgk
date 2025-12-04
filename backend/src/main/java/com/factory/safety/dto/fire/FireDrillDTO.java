package com.factory.safety.dto.fire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FireDrillDTO {
    private Long id;
    private String drillName;
    private LocalDate drillDate;
    private String drillType;
    private String drillContent;
    private Integer participantCount;
    private String organizer;
    private String objectives;
    private String executionProcess;
    private String findingsAndIssues;
    private String improvementMeasures;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
