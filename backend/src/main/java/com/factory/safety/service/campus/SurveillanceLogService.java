package com.factory.safety.service.campus;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.campus.SurveillanceLogDTO;
import com.factory.safety.entity.campus.SurveillanceLog;
import com.factory.safety.repository.campus.SurveillanceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveillanceLogService {
    private final SurveillanceLogRepository repository;

    public List<SurveillanceLogDTO> getAllSurveillanceLogs() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SurveillanceLogDTO getSurveillanceLogById(Long id) {
        SurveillanceLog surveillanceLog = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Surveillance Log not found with id: " + id));
        return convertToDTO(surveillanceLog);
    }

    public SurveillanceLogDTO createSurveillanceLog(SurveillanceLogDTO dto) {
        SurveillanceLog surveillanceLog = convertToEntity(dto);
        SurveillanceLog saved = repository.save(surveillanceLog);
        return convertToDTO(saved);
    }

    public SurveillanceLogDTO updateSurveillanceLog(Long id, SurveillanceLogDTO dto) {
        SurveillanceLog surveillanceLog = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Surveillance Log not found with id: " + id));
        
        surveillanceLog.setCameraName(dto.getCameraName());
        surveillanceLog.setLocation(dto.getLocation());
        surveillanceLog.setEventTime(dto.getEventTime());
        surveillanceLog.setEventType(dto.getEventType());
        surveillanceLog.setDescription(dto.getDescription());
        surveillanceLog.setSeverity(dto.getSeverity());
        surveillanceLog.setStatus(dto.getStatus());
        surveillanceLog.setRemarks(dto.getRemarks());
        
        SurveillanceLog updated = repository.save(surveillanceLog);
        return convertToDTO(updated);
    }

    public void deleteSurveillanceLog(Long id) {
        SurveillanceLog surveillanceLog = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Surveillance Log not found with id: " + id));
        repository.delete(surveillanceLog);
    }

    public List<SurveillanceLogDTO> searchByCameraName(String cameraName) {
        return repository.findByCameraName(cameraName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SurveillanceLogDTO> searchByEventType(String eventType) {
        return repository.findByEventType(eventType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SurveillanceLogDTO> searchBySeverity(String severity) {
        return repository.findBySeverity(severity).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SurveillanceLogDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SurveillanceLogDTO convertToDTO(SurveillanceLog surveillanceLog) {
        SurveillanceLogDTO dto = new SurveillanceLogDTO();
        dto.setId(surveillanceLog.getId());
        dto.setCameraName(surveillanceLog.getCameraName());
        dto.setLocation(surveillanceLog.getLocation());
        dto.setEventTime(surveillanceLog.getEventTime());
        dto.setEventType(surveillanceLog.getEventType());
        dto.setDescription(surveillanceLog.getDescription());
        dto.setSeverity(surveillanceLog.getSeverity());
        dto.setStatus(surveillanceLog.getStatus());
        dto.setRemarks(surveillanceLog.getRemarks());
        dto.setCreatedAt(surveillanceLog.getCreatedAt());
        dto.setUpdatedAt(surveillanceLog.getUpdatedAt());
        return dto;
    }

    private SurveillanceLog convertToEntity(SurveillanceLogDTO dto) {
        SurveillanceLog surveillanceLog = new SurveillanceLog();
        surveillanceLog.setCameraName(dto.getCameraName());
        surveillanceLog.setLocation(dto.getLocation());
        surveillanceLog.setEventTime(dto.getEventTime());
        surveillanceLog.setEventType(dto.getEventType());
        surveillanceLog.setDescription(dto.getDescription());
        surveillanceLog.setSeverity(dto.getSeverity());
        surveillanceLog.setStatus(dto.getStatus());
        surveillanceLog.setRemarks(dto.getRemarks());
        return surveillanceLog;
    }
}
