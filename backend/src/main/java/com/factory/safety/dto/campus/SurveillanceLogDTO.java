package com.factory.safety.dto.campus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveillanceLogDTO {
    private Long id;
    private String cameraName;
    private String location;
    private LocalDateTime eventTime;
    private String eventType;
    private String description;
    private String severity;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
