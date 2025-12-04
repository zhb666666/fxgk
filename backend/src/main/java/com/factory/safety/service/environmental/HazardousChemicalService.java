package com.factory.safety.service.environmental;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.environmental.HazardousChemicalDTO;
import com.factory.safety.entity.environmental.HazardousChemical;
import com.factory.safety.repository.environmental.HazardousChemicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HazardousChemicalService {
    private final HazardousChemicalRepository repository;

    public List<HazardousChemicalDTO> getAllHazardousChemicals() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public HazardousChemicalDTO getHazardousChemicalById(Long id) {
        HazardousChemical hazardousChemical = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hazardous Chemical not found with id: " + id));
        return convertToDTO(hazardousChemical);
    }

    public HazardousChemicalDTO createHazardousChemical(HazardousChemicalDTO dto) {
        HazardousChemical hazardousChemical = convertToEntity(dto);
        HazardousChemical saved = repository.save(hazardousChemical);
        return convertToDTO(saved);
    }

    public HazardousChemicalDTO updateHazardousChemical(Long id, HazardousChemicalDTO dto) {
        HazardousChemical hazardousChemical = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hazardous Chemical not found with id: " + id));
        
        hazardousChemical.setChemicalName(dto.getChemicalName());
        hazardousChemical.setChemicalFormula(dto.getChemicalFormula());
        hazardousChemical.setClassificationCode(dto.getClassificationCode());
        hazardousChemical.setCurrentStock(dto.getCurrentStock());
        hazardousChemical.setUnit(dto.getUnit());
        hazardousChemical.setMaxStorageLimit(dto.getMaxStorageLimit());
        hazardousChemical.setStorageLocation(dto.getStorageLocation());
        hazardousChemical.setStorageMethod(dto.getStorageMethod());
        hazardousChemical.setContainerType(dto.getContainerType());
        hazardousChemical.setHazardDescription(dto.getHazardDescription());
        hazardousChemical.setSafetyPrecautions(dto.getSafetyPrecautions());
        hazardousChemical.setEmergencyMeasures(dto.getEmergencyMeasures());
        hazardousChemical.setLastInspectionDate(dto.getLastInspectionDate());
        hazardousChemical.setNextInspectionDate(dto.getNextInspectionDate());
        hazardousChemical.setStatus(dto.getStatus());
        hazardousChemical.setRemarks(dto.getRemarks());
        
        HazardousChemical updated = repository.save(hazardousChemical);
        return convertToDTO(updated);
    }

    public void deleteHazardousChemical(Long id) {
        HazardousChemical hazardousChemical = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hazardous Chemical not found with id: " + id));
        repository.delete(hazardousChemical);
    }

    public List<HazardousChemicalDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HazardousChemicalDTO> searchByStorageLocation(String location) {
        return repository.findByStorageLocation(location).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HazardousChemicalDTO> searchByKeyword(String keyword) {
        return repository.findByChemicalNameContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private HazardousChemicalDTO convertToDTO(HazardousChemical hazardousChemical) {
        HazardousChemicalDTO dto = new HazardousChemicalDTO();
        dto.setId(hazardousChemical.getId());
        dto.setChemicalName(hazardousChemical.getChemicalName());
        dto.setChemicalFormula(hazardousChemical.getChemicalFormula());
        dto.setClassificationCode(hazardousChemical.getClassificationCode());
        dto.setCurrentStock(hazardousChemical.getCurrentStock());
        dto.setUnit(hazardousChemical.getUnit());
        dto.setMaxStorageLimit(hazardousChemical.getMaxStorageLimit());
        dto.setStorageLocation(hazardousChemical.getStorageLocation());
        dto.setStorageMethod(hazardousChemical.getStorageMethod());
        dto.setContainerType(hazardousChemical.getContainerType());
        dto.setHazardDescription(hazardousChemical.getHazardDescription());
        dto.setSafetyPrecautions(hazardousChemical.getSafetyPrecautions());
        dto.setEmergencyMeasures(hazardousChemical.getEmergencyMeasures());
        dto.setLastInspectionDate(hazardousChemical.getLastInspectionDate());
        dto.setNextInspectionDate(hazardousChemical.getNextInspectionDate());
        dto.setStatus(hazardousChemical.getStatus());
        dto.setRemarks(hazardousChemical.getRemarks());
        dto.setCreatedAt(hazardousChemical.getCreatedAt());
        dto.setUpdatedAt(hazardousChemical.getUpdatedAt());
        return dto;
    }

    private HazardousChemical convertToEntity(HazardousChemicalDTO dto) {
        HazardousChemical hazardousChemical = new HazardousChemical();
        hazardousChemical.setChemicalName(dto.getChemicalName());
        hazardousChemical.setChemicalFormula(dto.getChemicalFormula());
        hazardousChemical.setClassificationCode(dto.getClassificationCode());
        hazardousChemical.setCurrentStock(dto.getCurrentStock());
        hazardousChemical.setUnit(dto.getUnit());
        hazardousChemical.setMaxStorageLimit(dto.getMaxStorageLimit());
        hazardousChemical.setStorageLocation(dto.getStorageLocation());
        hazardousChemical.setStorageMethod(dto.getStorageMethod());
        hazardousChemical.setContainerType(dto.getContainerType());
        hazardousChemical.setHazardDescription(dto.getHazardDescription());
        hazardousChemical.setSafetyPrecautions(dto.getSafetyPrecautions());
        hazardousChemical.setEmergencyMeasures(dto.getEmergencyMeasures());
        hazardousChemical.setLastInspectionDate(dto.getLastInspectionDate());
        hazardousChemical.setNextInspectionDate(dto.getNextInspectionDate());
        hazardousChemical.setStatus(dto.getStatus());
        hazardousChemical.setRemarks(dto.getRemarks());
        return hazardousChemical;
    }
}
