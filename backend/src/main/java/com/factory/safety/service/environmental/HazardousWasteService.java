package com.factory.safety.service.environmental;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.environmental.HazardousWasteDTO;
import com.factory.safety.entity.environmental.HazardousWaste;
import com.factory.safety.repository.environmental.HazardousWasteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HazardousWasteService {
    private final HazardousWasteRepository repository;

    public List<HazardousWasteDTO> getAllHazardousWastes() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public HazardousWasteDTO getHazardousWasteById(Long id) {
        HazardousWaste hazardousWaste = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hazardous Waste not found with id: " + id));
        return convertToDTO(hazardousWaste);
    }

    public HazardousWasteDTO createHazardousWaste(HazardousWasteDTO dto) {
        HazardousWaste hazardousWaste = convertToEntity(dto);
        HazardousWaste saved = repository.save(hazardousWaste);
        return convertToDTO(saved);
    }

    public HazardousWasteDTO updateHazardousWaste(Long id, HazardousWasteDTO dto) {
        HazardousWaste hazardousWaste = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hazardous Waste not found with id: " + id));
        
        hazardousWaste.setWasteName(dto.getWasteName());
        hazardousWaste.setWasteCode(dto.getWasteCode());
        hazardousWaste.setHazardCharacteristics(dto.getHazardCharacteristics());
        hazardousWaste.setQuantity(dto.getQuantity());
        hazardousWaste.setUnit(dto.getUnit());
        hazardousWaste.setStorageDate(dto.getStorageDate());
        hazardousWaste.setDisposalDate(dto.getDisposalDate());
        hazardousWaste.setStorageLocation(dto.getStorageLocation());
        hazardousWaste.setDisposalMethod(dto.getDisposalMethod());
        hazardousWaste.setDisposalCompany(dto.getDisposalCompany());
        hazardousWaste.setStorageConditions(dto.getStorageConditions());
        hazardousWaste.setStatus(dto.getStatus());
        hazardousWaste.setRemarks(dto.getRemarks());
        
        HazardousWaste updated = repository.save(hazardousWaste);
        return convertToDTO(updated);
    }

    public void deleteHazardousWaste(Long id) {
        HazardousWaste hazardousWaste = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hazardous Waste not found with id: " + id));
        repository.delete(hazardousWaste);
    }

    public List<HazardousWasteDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HazardousWasteDTO> searchByStorageLocation(String location) {
        return repository.findByStorageLocation(location).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HazardousWasteDTO> searchByKeyword(String keyword) {
        return repository.findByWasteNameContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private HazardousWasteDTO convertToDTO(HazardousWaste hazardousWaste) {
        HazardousWasteDTO dto = new HazardousWasteDTO();
        dto.setId(hazardousWaste.getId());
        dto.setWasteName(hazardousWaste.getWasteName());
        dto.setWasteCode(hazardousWaste.getWasteCode());
        dto.setHazardCharacteristics(hazardousWaste.getHazardCharacteristics());
        dto.setQuantity(hazardousWaste.getQuantity());
        dto.setUnit(hazardousWaste.getUnit());
        dto.setStorageDate(hazardousWaste.getStorageDate());
        dto.setDisposalDate(hazardousWaste.getDisposalDate());
        dto.setStorageLocation(hazardousWaste.getStorageLocation());
        dto.setDisposalMethod(hazardousWaste.getDisposalMethod());
        dto.setDisposalCompany(hazardousWaste.getDisposalCompany());
        dto.setStorageConditions(hazardousWaste.getStorageConditions());
        dto.setStatus(hazardousWaste.getStatus());
        dto.setRemarks(hazardousWaste.getRemarks());
        dto.setCreatedAt(hazardousWaste.getCreatedAt());
        dto.setUpdatedAt(hazardousWaste.getUpdatedAt());
        return dto;
    }

    private HazardousWaste convertToEntity(HazardousWasteDTO dto) {
        HazardousWaste hazardousWaste = new HazardousWaste();
        hazardousWaste.setWasteName(dto.getWasteName());
        hazardousWaste.setWasteCode(dto.getWasteCode());
        hazardousWaste.setHazardCharacteristics(dto.getHazardCharacteristics());
        hazardousWaste.setQuantity(dto.getQuantity());
        hazardousWaste.setUnit(dto.getUnit());
        hazardousWaste.setStorageDate(dto.getStorageDate());
        hazardousWaste.setDisposalDate(dto.getDisposalDate());
        hazardousWaste.setStorageLocation(dto.getStorageLocation());
        hazardousWaste.setDisposalMethod(dto.getDisposalMethod());
        hazardousWaste.setDisposalCompany(dto.getDisposalCompany());
        hazardousWaste.setStorageConditions(dto.getStorageConditions());
        hazardousWaste.setStatus(dto.getStatus());
        hazardousWaste.setRemarks(dto.getRemarks());
        return hazardousWaste;
    }
}
