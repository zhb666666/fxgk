package com.factory.safety.repository.campus;

import com.factory.safety.entity.campus.SurveillanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveillanceLogRepository extends JpaRepository<SurveillanceLog, Long> {
    List<SurveillanceLog> findByCameraName(String cameraName);
    List<SurveillanceLog> findByEventType(String eventType);
    List<SurveillanceLog> findBySeverity(String severity);
    List<SurveillanceLog> findByStatus(String status);
}
