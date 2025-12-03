package com.factory.safety.entity.fire;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fire_drill")
public class FireDrill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String drillName;

    @Column(nullable = false)
    private LocalDate drillDate;

    @Column(nullable = false)
    private String drillType;

    @Column(columnDefinition = "TEXT")
    private String drillContent;

    @Column(nullable = false)
    private Integer participantCount;

    @Column(nullable = false)
    private String organizer;

    @Column(columnDefinition = "TEXT")
    private String objectives;

    @Column(columnDefinition = "TEXT")
    private String executionProcess;

    @Column(columnDefinition = "TEXT")
    private String findingsAndIssues;

    @Column(columnDefinition = "TEXT")
    private String improvementMeasures;

    @Column(nullable = false)
    private String status;

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
