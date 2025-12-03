package com.factory.safety.repository.fire;

import com.factory.safety.entity.fire.FireControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireControlRepository extends JpaRepository<FireControl, Long> {
    List<FireControl> findByLocation(String location);
    List<FireControl> findByStatus(String status);
    List<FireControl> findByFacilityType(String facilityType);
    List<FireControl> findByFacilityNameContaining(String keyword);
}
