package com.factory.safety.service.campus;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.campus.SurveillanceDTO;
import com.factory.safety.entity.campus.Surveillance;
import com.factory.safety.repository.campus.SurveillanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveillanceService {
    private final SurveillanceRepository repository;

    public List<SurveillanceDTO> getAllSurveillances() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SurveillanceDTO getSurveillanceById(Long id) {
        Surveillance surveillance = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Surveillance not found with id: " + id));
        return convertToDTO(surveillance);
    }

    public SurveillanceDTO createSurveillance(SurveillanceDTO dto) {
        Surveillance surveillance = convertToEntity(dto);
        Surveillance saved = repository.save(surveillance);
        return convertToDTO(saved);
    }

    public SurveillanceDTO updateSurveillance(Long id, SurveillanceDTO dto) {
        Surveillance surveillance = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Surveillance not found with id: " + id));
        
        surveillance.setCameraName(dto.getCameraName());
        surveillance.setLocation(dto.getLocation());
        surveillance.setCameraType(dto.getCameraType());
        surveillance.setManufacturer(dto.getManufacturer());
        surveillance.setModel(dto.getModel());
        surveillance.setResolution(dto.getResolution());
        surveillance.setInstallationTime(dto.getInstallationTime());
        surveillance.setLastMaintenanceTime(dto.getLastMaintenanceTime());
        surveillance.setNextMaintenanceTime(dto.getNextMaintenanceTime());
        surveillance.setRecordingStatus(dto.getRecordingStatus());
        surveillance.setStorageLocation(dto.getStorageLocation());
        surveillance.setRetentionDays(dto.getRetentionDays());
        surveillance.setMaintenanceNotes(dto.getMaintenanceNotes());
        surveillance.setStatus(dto.getStatus());
        surveillance.setRemarks(dto.getRemarks());
        
        Surveillance updated = repository.save(surveillance);
        return convertToDTO(updated);
    }

    public void deleteSurveillance(Long id) {
        Surveillance surveillance = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Surveillance not found with id: " + id));
        repository.delete(surveillance);
    }

    public List<SurveillanceDTO> searchByLocation(String location) {
        return repository.findByLocation(location).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SurveillanceDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SurveillanceDTO> searchByKeyword(String keyword) {
        return repository.findByCameraNameContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SurveillanceDTO convertToDTO(Surveillance surveillance) {
        SurveillanceDTO dto = new SurveillanceDTO();
        dto.setId(surveillance.getId());
        dto.setCameraName(surveillance.getCameraName());
        dto.setLocation(surveillance.getLocation());
        dto.setCameraType(surveillance.getCameraType());
        dto.setManufacturer(surveillance.getManufacturer());
        dto.setModel(surveillance.getModel());
        dto.setResolution(surveillance.getResolution());
        dto.setInstallationTime(surveillance.getInstallationTime());
        dto.setLastMaintenanceTime(surveillance.getLastMaintenanceTime());
        dto.setNextMaintenanceTime(surveillance.getNextMaintenanceTime());
        dto.setRecordingStatus(surveillance.getRecordingStatus());
        dto.setStorageLocation(surveillance.getStorageLocation());
        dto.setRetentionDays(surveillance.getRetentionDays());
        dto.setMaintenanceNotes(surveillance.getMaintenanceNotes());
        dto.setStatus(surveillance.getStatus());
        dto.setRemarks(surveillance.getRemarks());
        dto.setCreatedAt(surveillance.getCreatedAt());
        dto.setUpdatedAt(surveillance.getUpdatedAt());
        return dto;
    }

    private Surveillance convertToEntity(SurveillanceDTO dto) {
        Surveillance surveillance = new Surveillance();
        surveillance.setCameraName(dto.getCameraName());
        surveillance.setLocation(dto.getLocation());
        surveillance.setCameraType(dto.getCameraType());
        surveillance.setManufacturer(dto.getManufacturer());
        surveillance.setModel(dto.getModel());
        surveillance.setResolution(dto.getResolution());
        surveillance.setInstallationTime(dto.getInstallationTime());
        surveillance.setLastMaintenanceTime(dto.getLastMaintenanceTime());
        surveillance.setNextMaintenanceTime(dto.getNextMaintenanceTime());
        surveillance.setRecordingStatus(dto.getRecordingStatus());
        surveillance.setStorageLocation(dto.getStorageLocation());
        surveillance.setRetentionDays(dto.getRetentionDays());
        surveillance.setMaintenanceNotes(dto.getMaintenanceNotes());
        surveillance.setStatus(dto.getStatus());
        surveillance.setRemarks(dto.getRemarks());
        return surveillance;
    }
}
