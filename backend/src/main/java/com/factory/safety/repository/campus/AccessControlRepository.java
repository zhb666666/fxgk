package com.factory.safety.repository.campus;

import com.factory.safety.entity.campus.AccessControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessControlRepository extends JpaRepository<AccessControl, Long> {
    List<AccessControl> findByLocation(String location);
    List<AccessControl> findByStatus(String status);
    List<AccessControl> findByDeviceNameContaining(String keyword);
}
