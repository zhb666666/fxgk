package com.factory.safety.repository.environmental;

import com.factory.safety.entity.environmental.WasteGas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasteGasRepository extends JpaRepository<WasteGas, Long> {
    List<WasteGas> findByEmissionSource(String emissionSource);
    List<WasteGas> findByComplianceStatus(String complianceStatus);
}
