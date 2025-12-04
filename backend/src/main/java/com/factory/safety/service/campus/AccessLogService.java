package com.factory.safety.service.campus;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.campus.AccessLogDTO;
import com.factory.safety.entity.campus.AccessLog;
import com.factory.safety.repository.campus.AccessLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessLogService {
    private final AccessLogRepository repository;

    public List<AccessLogDTO> getAllAccessLogs() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccessLogDTO getAccessLogById(Long id) {
        AccessLog accessLog = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Log not found with id: " + id));
        return convertToDTO(accessLog);
    }

    public AccessLogDTO createAccessLog(AccessLogDTO dto) {
        AccessLog accessLog = convertToEntity(dto);
        AccessLog saved = repository.save(accessLog);
        return convertToDTO(saved);
    }

    public AccessLogDTO updateAccessLog(Long id, AccessLogDTO dto) {
        AccessLog accessLog = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Log not found with id: " + id));
        
        accessLog.setVisitorName(dto.getVisitorName());
        accessLog.setVisitorId(dto.getVisitorId());
        accessLog.setAccessPoint(dto.getAccessPoint());
        accessLog.setAccessTime(dto.getAccessTime());
        accessLog.setAccessType(dto.getAccessType());
        accessLog.setPurpose(dto.getPurpose());
        accessLog.setDepartment(dto.getDepartment());
        accessLog.setRemarks(dto.getRemarks());
        
        AccessLog updated = repository.save(accessLog);
        return convertToDTO(updated);
    }

    public void deleteAccessLog(Long id) {
        AccessLog accessLog = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Access Log not found with id: " + id));
        repository.delete(accessLog);
    }

    public List<AccessLogDTO> searchByAccessPoint(String accessPoint) {
        return repository.findByAccessPoint(accessPoint).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AccessLogDTO> searchByAccessType(String accessType) {
        return repository.findByAccessType(accessType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AccessLogDTO> searchByDepartment(String department) {
        return repository.findByDepartment(department).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AccessLogDTO> searchByKeyword(String keyword) {
        return repository.findByVisitorNameContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AccessLogDTO convertToDTO(AccessLog accessLog) {
        AccessLogDTO dto = new AccessLogDTO();
        dto.setId(accessLog.getId());
        dto.setVisitorName(accessLog.getVisitorName());
        dto.setVisitorId(accessLog.getVisitorId());
        dto.setAccessPoint(accessLog.getAccessPoint());
        dto.setAccessTime(accessLog.getAccessTime());
        dto.setAccessType(accessLog.getAccessType());
        dto.setPurpose(accessLog.getPurpose());
        dto.setDepartment(accessLog.getDepartment());
        dto.setRemarks(accessLog.getRemarks());
        dto.setCreatedAt(accessLog.getCreatedAt());
        dto.setUpdatedAt(accessLog.getUpdatedAt());
        return dto;
    }

    private AccessLog convertToEntity(AccessLogDTO dto) {
        AccessLog accessLog = new AccessLog();
        accessLog.setVisitorName(dto.getVisitorName());
        accessLog.setVisitorId(dto.getVisitorId());
        accessLog.setAccessPoint(dto.getAccessPoint());
        accessLog.setAccessTime(dto.getAccessTime());
        accessLog.setAccessType(dto.getAccessType());
        accessLog.setPurpose(dto.getPurpose());
        accessLog.setDepartment(dto.getDepartment());
        accessLog.setRemarks(dto.getRemarks());
        return accessLog;
    }
}
