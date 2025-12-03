package com.factory.safety.repository.production;

import com.factory.safety.entity.production.SpecialOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialOperationRepository extends JpaRepository<SpecialOperation, Long> {
    List<SpecialOperation> findByOperationType(String operationType);
    List<SpecialOperation> findByDepartment(String department);
    List<SpecialOperation> findByStatus(String status);
    List<SpecialOperation> findByOperatorNameContaining(String keyword);
}
