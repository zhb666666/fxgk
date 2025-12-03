package com.factory.safety.entity.fire;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hot_work_permit")
public class HotWorkPermit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String permitNumber;

    @Column(nullable = false)
    private LocalDate workDate;

    @Column(nullable = false)
    private String workType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String operator;

    @Column(nullable = false)
    private String supervisor;

    @Column(nullable = false)
    private String safetyOfficer;

    @Column(columnDefinition = "TEXT")
    private String workDescription;

    @Column(columnDefinition = "TEXT")
    private String safetyMeasures;

    @Column(columnDefinition = "TEXT")
    private String riskAssessment;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private String status;

    private LocalDateTime approvedAt;

    private String approvedBy;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
