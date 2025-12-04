package com.factory.safety.service.environmental;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.environmental.WasteGasDTO;
import com.factory.safety.entity.environmental.WasteGas;
import com.factory.safety.repository.environmental.WasteGasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WasteGasService {
    private final WasteGasRepository repository;

    public List<WasteGasDTO> getAllWasteGas() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public WasteGasDTO getWasteGasById(Long id) {
        WasteGas wasteGas = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Gas not found with id: " + id));
        return convertToDTO(wasteGas);
    }

    public WasteGasDTO createWasteGas(WasteGasDTO dto) {
        WasteGas wasteGas = convertToEntity(dto);
        WasteGas saved = repository.save(wasteGas);
        return convertToDTO(saved);
    }

    public WasteGasDTO updateWasteGas(Long id, WasteGasDTO dto) {
        WasteGas wasteGas = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Gas not found with id: " + id));
        
        wasteGas.setRecordDate(dto.getRecordDate());
        wasteGas.setEmissionSource(dto.getEmissionSource());
        wasteGas.setTreatmentMethod(dto.getTreatmentMethod());
        wasteGas.setSulphurDioxide(dto.getSulphurDioxide());
        wasteGas.setNitrogenOxide(dto.getNitrogenOxide());
        wasteGas.setParticulateMatter(dto.getParticulateMatter());
        wasteGas.setVolatileOrganicCompounds(dto.getVolatileOrganicCompounds());
        wasteGas.setUnit(dto.getUnit());
        wasteGas.setEmissionRate(dto.getEmissionRate());
        wasteGas.setComplianceStatus(dto.getComplianceStatus());
        wasteGas.setRemarks(dto.getRemarks());
        wasteGas.setRecordedBy(dto.getRecordedBy());
        
        WasteGas updated = repository.save(wasteGas);
        return convertToDTO(updated);
    }

    public void deleteWasteGas(Long id) {
        WasteGas wasteGas = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Gas not found with id: " + id));
        repository.delete(wasteGas);
    }

    public List<WasteGasDTO> searchByEmissionSource(String emissionSource) {
        return repository.findByEmissionSource(emissionSource).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<WasteGasDTO> searchByComplianceStatus(String complianceStatus) {
        return repository.findByComplianceStatus(complianceStatus).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private WasteGasDTO convertToDTO(WasteGas wasteGas) {
        WasteGasDTO dto = new WasteGasDTO();
        dto.setId(wasteGas.getId());
        dto.setRecordDate(wasteGas.getRecordDate());
        dto.setEmissionSource(wasteGas.getEmissionSource());
        dto.setTreatmentMethod(wasteGas.getTreatmentMethod());
        dto.setSulphurDioxide(wasteGas.getSulphurDioxide());
        dto.setNitrogenOxide(wasteGas.getNitrogenOxide());
        dto.setParticulateMatter(wasteGas.getParticulateMatter());
        dto.setVolatileOrganicCompounds(wasteGas.getVolatileOrganicCompounds());
        dto.setUnit(wasteGas.getUnit());
        dto.setEmissionRate(wasteGas.getEmissionRate());
        dto.setComplianceStatus(wasteGas.getComplianceStatus());
        dto.setRemarks(wasteGas.getRemarks());
        dto.setRecordedBy(wasteGas.getRecordedBy());
        dto.setCreatedAt(wasteGas.getCreatedAt());
        dto.setUpdatedAt(wasteGas.getUpdatedAt());
        return dto;
    }

    private WasteGas convertToEntity(WasteGasDTO dto) {
        WasteGas wasteGas = new WasteGas();
        wasteGas.setRecordDate(dto.getRecordDate());
        wasteGas.setEmissionSource(dto.getEmissionSource());
        wasteGas.setTreatmentMethod(dto.getTreatmentMethod());
        wasteGas.setSulphurDioxide(dto.getSulphurDioxide());
        wasteGas.setNitrogenOxide(dto.getNitrogenOxide());
        wasteGas.setParticulateMatter(dto.getParticulateMatter());
        wasteGas.setVolatileOrganicCompounds(dto.getVolatileOrganicCompounds());
        wasteGas.setUnit(dto.getUnit());
        wasteGas.setEmissionRate(dto.getEmissionRate());
        wasteGas.setComplianceStatus(dto.getComplianceStatus());
        wasteGas.setRemarks(dto.getRemarks());
        wasteGas.setRecordedBy(dto.getRecordedBy());
        return wasteGas;
    }
}
