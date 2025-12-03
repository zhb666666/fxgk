package com.factory.safety.repository.environmental;

import com.factory.safety.entity.environmental.HazardousWaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HazardousWasteRepository extends JpaRepository<HazardousWaste, Long> {
    List<HazardousWaste> findByStatus(String status);
    List<HazardousWaste> findByStorageLocation(String location);
    List<HazardousWaste> findByWasteNameContaining(String keyword);
}
