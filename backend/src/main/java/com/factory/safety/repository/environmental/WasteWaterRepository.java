package com.factory.safety.repository.environmental;

import com.factory.safety.entity.environmental.WasteWater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteWaterRepository extends JpaRepository<WasteWater, Long> {
    List<WasteWater> findBySource(String source);
    List<WasteWater> findByComplianceStatus(String complianceStatus);
    List<WasteWater> findByDischargeLocation(String dischargeLocation);
}
