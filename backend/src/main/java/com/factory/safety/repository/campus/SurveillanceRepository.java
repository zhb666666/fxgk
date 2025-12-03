package com.factory.safety.repository.campus;

import com.factory.safety.entity.campus.Surveillance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveillanceRepository extends JpaRepository<Surveillance, Long> {
    List<Surveillance> findByLocation(String location);
    List<Surveillance> findByStatus(String status);
    List<Surveillance> findByCameraNameContaining(String keyword);
}
