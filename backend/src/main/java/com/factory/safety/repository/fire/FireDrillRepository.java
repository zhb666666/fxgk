package com.factory.safety.repository.fire;

import com.factory.safety.entity.fire.FireDrill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireDrillRepository extends JpaRepository<FireDrill, Long> {
    List<FireDrill> findByStatus(String status);
    List<FireDrill> findByDrillType(String drillType);
    List<FireDrill> findByDrillNameContaining(String keyword);
}
