package com.factory.safety.repository.production;

import com.factory.safety.entity.production.WorkInjury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkInjuryRepository extends JpaRepository<WorkInjury, Long> {
    List<WorkInjury> findByDepartment(String department);
    List<WorkInjury> findByStatus(String status);
    List<WorkInjury> findBySeverity(String severity);
    List<WorkInjury> findByAffectedEmployeeContaining(String keyword);
}
