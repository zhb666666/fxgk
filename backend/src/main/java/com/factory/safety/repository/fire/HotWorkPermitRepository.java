package com.factory.safety.repository.fire;

import com.factory.safety.entity.fire.HotWorkPermit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotWorkPermitRepository extends JpaRepository<HotWorkPermit, Long> {
    List<HotWorkPermit> findByLocation(String location);
    List<HotWorkPermit> findByStatus(String status);
    List<HotWorkPermit> findByWorkType(String workType);
    List<HotWorkPermit> findByPermitNumberContaining(String keyword);
}
