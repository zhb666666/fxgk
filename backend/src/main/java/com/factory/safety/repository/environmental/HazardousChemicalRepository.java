package com.factory.safety.repository.environmental;

import com.factory.safety.entity.environmental.HazardousChemical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HazardousChemicalRepository extends JpaRepository<HazardousChemical, Long> {
    List<HazardousChemical> findByStatus(String status);
    List<HazardousChemical> findByStorageLocation(String location);
    List<HazardousChemical> findByChemicalNameContaining(String keyword);
}
