package com.factory.safety.service.fire;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.fire.FireControlDTO;
import com.factory.safety.entity.fire.FireControl;
import com.factory.safety.repository.fire.FireControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireControlService {
    private final FireControlRepository repository;

    public List<FireControlDTO> getAllFireControl() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FireControlDTO getFireControlById(Long id) {
        FireControl fireControl = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fire Control not found with id: " + id));
        return convertToDTO(fireControl);
    }

    public FireControlDTO createFireControl(FireControlDTO dto) {
        FireControl fireControl = convertToEntity(dto);
        FireControl saved = repository.save(fireControl);
        return convertToDTO(saved);
    }

    public FireControlDTO updateFireControl(Long id, FireControlDTO dto) {
        FireControl fireControl = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fire Control not found with id: " + id));
        
        fireControl.setFacilityName(dto.getFacilityName());
        fireControl.setFacilityType(dto.getFacilityType());
        fireControl.setLocation(dto.getLocation());
        fireControl.setQuantity(dto.getQuantity());
        fireControl.setInstallationDate(dto.getInstallationDate());
        fireControl.setLastMaintenanceDate(dto.getLastMaintenanceDate());
        fireControl.setNextMaintenanceDate(dto.getNextMaintenanceDate());
        fireControl.setMaintenanceCompany(dto.getMaintenanceCompany());
        fireControl.setMaintenanceNotes(dto.getMaintenanceNotes());
        fireControl.setStatus(dto.getStatus());
        fireControl.setNotes(dto.getNotes());
        
        FireControl updated = repository.save(fireControl);
        return convertToDTO(updated);
    }

    public void deleteFireControl(Long id) {
        FireControl fireControl = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fire Control not found with id: " + id));
        repository.delete(fireControl);
    }

    public List<FireControlDTO> searchByLocation(String location) {
        return repository.findByLocation(location).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FireControlDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FireControlDTO> searchByFacilityType(String facilityType) {
        return repository.findByFacilityType(facilityType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FireControlDTO convertToDTO(FireControl fireControl) {
        FireControlDTO dto = new FireControlDTO();
        dto.setId(fireControl.getId());
        dto.setFacilityName(fireControl.getFacilityName());
        dto.setFacilityType(fireControl.getFacilityType());
        dto.setLocation(fireControl.getLocation());
        dto.setQuantity(fireControl.getQuantity());
        dto.setInstallationDate(fireControl.getInstallationDate());
        dto.setLastMaintenanceDate(fireControl.getLastMaintenanceDate());
        dto.setNextMaintenanceDate(fireControl.getNextMaintenanceDate());
        dto.setMaintenanceCompany(fireControl.getMaintenanceCompany());
        dto.setMaintenanceNotes(fireControl.getMaintenanceNotes());
        dto.setStatus(fireControl.getStatus());
        dto.setNotes(fireControl.getNotes());
        dto.setCreatedAt(fireControl.getCreatedAt());
        dto.setUpdatedAt(fireControl.getUpdatedAt());
        return dto;
    }

    private FireControl convertToEntity(FireControlDTO dto) {
        FireControl fireControl = new FireControl();
        fireControl.setFacilityName(dto.getFacilityName());
        fireControl.setFacilityType(dto.getFacilityType());
        fireControl.setLocation(dto.getLocation());
        fireControl.setQuantity(dto.getQuantity());
        fireControl.setInstallationDate(dto.getInstallationDate());
        fireControl.setLastMaintenanceDate(dto.getLastMaintenanceDate());
        fireControl.setNextMaintenanceDate(dto.getNextMaintenanceDate());
        fireControl.setMaintenanceCompany(dto.getMaintenanceCompany());
        fireControl.setMaintenanceNotes(dto.getMaintenanceNotes());
        fireControl.setStatus(dto.getStatus());
        fireControl.setNotes(dto.getNotes());
        return fireControl;
    }
}
