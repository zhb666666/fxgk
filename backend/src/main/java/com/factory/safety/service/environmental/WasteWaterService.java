package com.factory.safety.service.environmental;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.environmental.WasteWaterDTO;
import com.factory.safety.entity.environmental.WasteWater;
import com.factory.safety.repository.environmental.WasteWaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WasteWaterService {
    private final WasteWaterRepository repository;

    public List<WasteWaterDTO> getAllWasteWater() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public WasteWaterDTO getWasteWaterById(Long id) {
        WasteWater wasteWater = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Water not found with id: " + id));
        return convertToDTO(wasteWater);
    }

    public WasteWaterDTO createWasteWater(WasteWaterDTO dto) {
        WasteWater wasteWater = convertToEntity(dto);
        WasteWater saved = repository.save(wasteWater);
        return convertToDTO(saved);
    }

    public WasteWaterDTO updateWasteWater(Long id, WasteWaterDTO dto) {
        WasteWater wasteWater = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Water not found with id: " + id));
        
        wasteWater.setRecordDate(dto.getRecordDate());
        wasteWater.setSource(dto.getSource());
        wasteWater.setTreatmentProcess(dto.getTreatmentProcess());
        wasteWater.setFlowRate(dto.getFlowRate());
        wasteWater.setUnit(dto.getUnit());
        wasteWater.setPH(dto.getPH());
        wasteWater.setCOD(dto.getCOD());
        wasteWater.setAmmoniaNitrogen(dto.getAmmoniaNitrogen());
        wasteWater.setSuspendedSolids(dto.getSuspendedSolids());
        wasteWater.setDischargeLocation(dto.getDischargeLocation());
        wasteWater.setComplianceStatus(dto.getComplianceStatus());
        wasteWater.setRemarks(dto.getRemarks());
        wasteWater.setRecordedBy(dto.getRecordedBy());
        
        WasteWater updated = repository.save(wasteWater);
        return convertToDTO(updated);
    }

    public void deleteWasteWater(Long id) {
        WasteWater wasteWater = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Waste Water not found with id: " + id));
        repository.delete(wasteWater);
    }

    public List<WasteWaterDTO> searchBySource(String source) {
        return repository.findBySource(source).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<WasteWaterDTO> searchByComplianceStatus(String complianceStatus) {
        return repository.findByComplianceStatus(complianceStatus).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private WasteWaterDTO convertToDTO(WasteWater wasteWater) {
        WasteWaterDTO dto = new WasteWaterDTO();
        dto.setId(wasteWater.getId());
        dto.setRecordDate(wasteWater.getRecordDate());
        dto.setSource(wasteWater.getSource());
        dto.setTreatmentProcess(wasteWater.getTreatmentProcess());
        dto.setFlowRate(wasteWater.getFlowRate());
        dto.setUnit(wasteWater.getUnit());
        dto.setPH(wasteWater.getPH());
        dto.setCOD(wasteWater.getCOD());
        dto.setAmmoniaNitrogen(wasteWater.getAmmoniaNitrogen());
        dto.setSuspendedSolids(wasteWater.getSuspendedSolids());
        dto.setDischargeLocation(wasteWater.getDischargeLocation());
        dto.setComplianceStatus(wasteWater.getComplianceStatus());
        dto.setRemarks(wasteWater.getRemarks());
        dto.setRecordedBy(wasteWater.getRecordedBy());
        dto.setCreatedAt(wasteWater.getCreatedAt());
        dto.setUpdatedAt(wasteWater.getUpdatedAt());
        return dto;
    }

    private WasteWater convertToEntity(WasteWaterDTO dto) {
        WasteWater wasteWater = new WasteWater();
        wasteWater.setRecordDate(dto.getRecordDate());
        wasteWater.setSource(dto.getSource());
        wasteWater.setTreatmentProcess(dto.getTreatmentProcess());
        wasteWater.setFlowRate(dto.getFlowRate());
        wasteWater.setUnit(dto.getUnit());
        wasteWater.setPH(dto.getPH());
        wasteWater.setCOD(dto.getCOD());
        wasteWater.setAmmoniaNitrogen(dto.getAmmoniaNitrogen());
        wasteWater.setSuspendedSolids(dto.getSuspendedSolids());
        wasteWater.setDischargeLocation(dto.getDischargeLocation());
        wasteWater.setComplianceStatus(dto.getComplianceStatus());
        wasteWater.setRemarks(dto.getRemarks());
        wasteWater.setRecordedBy(dto.getRecordedBy());
        return wasteWater;
    }
}
