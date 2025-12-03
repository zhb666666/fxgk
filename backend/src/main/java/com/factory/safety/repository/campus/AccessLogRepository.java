package com.factory.safety.repository.campus;

import com.factory.safety.entity.campus.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
    List<AccessLog> findByAccessPoint(String accessPoint);
    List<AccessLog> findByAccessType(String accessType);
    List<AccessLog> findByVisitorNameContaining(String keyword);
    List<AccessLog> findByDepartment(String department);
}
