package com.factory.safety.service.campus;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.campus.AccessControlDTO;
import com.factory.safety.entity.campus.AccessControl;
import com.factory.safety.repository.campus.AccessControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessControlService {
    private final AccessControlRepository repository;

    public List<AccessControlDTO> getAllAccessControl() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccessControlDTO getAccessControlById(Long id) {
        AccessControl accessControl = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Control not found with id: " + id));
        return convertToDTO(accessControl);
    }

    public AccessControlDTO createAccessControl(AccessControlDTO dto) {
        AccessControl accessControl = convertToEntity(dto);
        AccessControl saved = repository.save(accessControl);
        return convertToDTO(saved);
    }

    public AccessControlDTO updateAccessControl(Long id, AccessControlDTO dto) {
        AccessControl accessControl = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Control not found with id: " + id));
        
        accessControl.setDeviceName(dto.getDeviceName());
        accessControl.setLocation(dto.getLocation());
        accessControl.setDeviceType(dto.getDeviceType());
        accessControl.setManufacturer(dto.getManufacturer());
        accessControl.setModel(dto.getModel());
        accessControl.setLastMaintenanceTime(dto.getLastMaintenanceTime());
        accessControl.setNextMaintenanceTime(dto.getNextMaintenanceTime());
        accessControl.setMaintenanceNotes(dto.getMaintenanceNotes());
        accessControl.setStatus(dto.getStatus());
        accessControl.setRemarks(dto.getRemarks());
        
        AccessControl updated = repository.save(accessControl);
        return convertToDTO(updated);
    }

    public void deleteAccessControl(Long id) {
        AccessControl accessControl = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Control not found with id: " + id));
        repository.delete(accessControl);
    }

    public List<AccessControlDTO> searchByLocation(String location) {
        return repository.findByLocation(location).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AccessControlDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AccessControlDTO> searchByKeyword(String keyword) {
        return repository.findByDeviceNameContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AccessControlDTO convertToDTO(AccessControl accessControl) {
        AccessControlDTO dto = new AccessControlDTO();
        dto.setId(accessControl.getId());
        dto.setDeviceName(accessControl.getDeviceName());
        dto.setLocation(accessControl.getLocation());
        dto.setDeviceType(accessControl.getDeviceType());
        dto.setManufacturer(accessControl.getManufacturer());
        dto.setModel(accessControl.getModel());
        dto.setInstallationTime(accessControl.getInstallationTime());
        dto.setLastMaintenanceTime(accessControl.getLastMaintenanceTime());
        dto.setNextMaintenanceTime(accessControl.getNextMaintenanceTime());
        dto.setMaintenanceNotes(accessControl.getMaintenanceNotes());
        dto.setStatus(accessControl.getStatus());
        dto.setRemarks(accessControl.getRemarks());
        dto.setCreatedAt(accessControl.getCreatedAt());
        dto.setUpdatedAt(accessControl.getUpdatedAt());
        return dto;
    }

    private AccessControl convertToEntity(AccessControlDTO dto) {
        AccessControl accessControl = new AccessControl();
        accessControl.setDeviceName(dto.getDeviceName());
        accessControl.setLocation(dto.getLocation());
        accessControl.setDeviceType(dto.getDeviceType());
        accessControl.setManufacturer(dto.getManufacturer());
        accessControl.setModel(dto.getModel());
        accessControl.setInstallationTime(dto.getInstallationTime());
        accessControl.setLastMaintenanceTime(dto.getLastMaintenanceTime());
        accessControl.setNextMaintenanceTime(dto.getNextMaintenanceTime());
        accessControl.setMaintenanceNotes(dto.getMaintenanceNotes());
        accessControl.setStatus(dto.getStatus());
        accessControl.setRemarks(dto.getRemarks());
        return accessControl;
    }
}
