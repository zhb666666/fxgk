package com.factory.safety.repository.production;

import com.factory.safety.entity.production.OperationSafety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationSafetyRepository extends JpaRepository<OperationSafety, Long> {
    List<OperationSafety> findByDepartment(String department);
    List<OperationSafety> findByStatus(String status);
    List<OperationSafety> findByOperationNameContaining(String keyword);
}
