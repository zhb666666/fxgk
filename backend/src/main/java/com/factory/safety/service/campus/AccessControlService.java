package com.factory.safety.service.campus;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.entity.campus.AccessControl;
import com.factory.safety.repository.campus.AccessControlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessControlService {
    private final AccessControlRepository repository;

    public List<AccessControl> getAllAccessControl() {
        return repository.findAll();
    }

    public AccessControl getAccessControlById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Control not found with id: " + id));
    }

    public AccessControl createAccessControl(AccessControl accessControl) {
        return repository.save(accessControl);
    }

    public AccessControl updateAccessControl(Long id, AccessControl accessControl) {
        AccessControl existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Control not found with id: " + id));
        
        existing.setDeviceName(accessControl.getDeviceName());
        existing.setLocation(accessControl.getLocation());
        existing.setDeviceType(accessControl.getDeviceType());
        existing.setManufacturer(accessControl.getManufacturer());
        existing.setModel(accessControl.getModel());
        existing.setLastMaintenanceTime(accessControl.getLastMaintenanceTime());
        existing.setNextMaintenanceTime(accessControl.getNextMaintenanceTime());
        existing.setMaintenanceNotes(accessControl.getMaintenanceNotes());
        existing.setStatus(accessControl.getStatus());
        existing.setRemarks(accessControl.getRemarks());
        
        return repository.save(existing);
    }

    public void deleteAccessControl(Long id) {
        AccessControl accessControl = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Control not found with id: " + id));
        repository.delete(accessControl);
    }

    public List<AccessControl> searchByLocation(String location) {
        return repository.findByLocation(location);
    }

    public List<AccessControl> searchByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<AccessControl> searchByKeyword(String keyword) {
        return repository.findByDeviceNameContaining(keyword);
    }
}
