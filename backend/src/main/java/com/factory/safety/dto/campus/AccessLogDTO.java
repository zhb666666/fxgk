package com.factory.safety.dto.campus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessLogDTO {
    private Long id;
    private String visitorName;
    private String visitorId;
    private String accessPoint;
    private LocalDateTime accessTime;
    private String accessType;
    private String purpose;
    private String department;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
