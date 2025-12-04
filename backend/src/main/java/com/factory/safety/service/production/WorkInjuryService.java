package com.factory.safety.service.production;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.production.WorkInjuryDTO;
import com.factory.safety.entity.production.WorkInjury;
import com.factory.safety.repository.production.WorkInjuryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkInjuryService {
    private final WorkInjuryRepository repository;

    public List<WorkInjuryDTO> getAllWorkInjuries() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public WorkInjuryDTO getWorkInjuryById(Long id) {
        WorkInjury workInjury = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work Injury not found with id: " + id));
        return convertToDTO(workInjury);
    }

    public WorkInjuryDTO createWorkInjury(WorkInjuryDTO dto) {
        WorkInjury workInjury = convertToEntity(dto);
        WorkInjury saved = repository.save(workInjury);
        return convertToDTO(saved);
    }

    public WorkInjuryDTO updateWorkInjury(Long id, WorkInjuryDTO dto) {
        WorkInjury workInjury = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work Injury not found with id: " + id));
        
        workInjury.setInjuryType(dto.getInjuryType());
        workInjury.setAffectedEmployee(dto.getAffectedEmployee());
        workInjury.setEmployeeId(dto.getEmployeeId());
        workInjury.setIncidentDate(dto.getIncidentDate());
        workInjury.setIncidentLocation(dto.getIncidentLocation());
        workInjury.setDescription(dto.getDescription());
        workInjury.setSeverity(dto.getSeverity());
        workInjury.setTreatmentDescription(dto.getTreatmentDescription());
        workInjury.setHospitalName(dto.getHospitalName());
        workInjury.setDepartment(dto.getDepartment());
        workInjury.setReportedBy(dto.getReportedBy());
        workInjury.setRootCause(dto.getRootCause());
        workInjury.setPreventiveMeasures(dto.getPreventiveMeasures());
        workInjury.setStatus(dto.getStatus());
        
        WorkInjury updated = repository.save(workInjury);
        return convertToDTO(updated);
    }

    public void deleteWorkInjury(Long id) {
        WorkInjury workInjury = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work Injury not found with id: " + id));
        repository.delete(workInjury);
    }

    public List<WorkInjuryDTO> searchByDepartment(String department) {
        return repository.findByDepartment(department).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<WorkInjuryDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<WorkInjuryDTO> searchBySeverity(String severity) {
        return repository.findBySeverity(severity).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<WorkInjuryDTO> searchByKeyword(String keyword) {
        return repository.findByAffectedEmployeeContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private WorkInjuryDTO convertToDTO(WorkInjury workInjury) {
        WorkInjuryDTO dto = new WorkInjuryDTO();
        dto.setId(workInjury.getId());
        dto.setInjuryType(workInjury.getInjuryType());
        dto.setAffectedEmployee(workInjury.getAffectedEmployee());
        dto.setEmployeeId(workInjury.getEmployeeId());
        dto.setIncidentDate(workInjury.getIncidentDate());
        dto.setIncidentLocation(workInjury.getIncidentLocation());
        dto.setDescription(workInjury.getDescription());
        dto.setSeverity(workInjury.getSeverity());
        dto.setTreatmentDescription(workInjury.getTreatmentDescription());
        dto.setHospitalName(workInjury.getHospitalName());
        dto.setDepartment(workInjury.getDepartment());
        dto.setReportedBy(workInjury.getReportedBy());
        dto.setRootCause(workInjury.getRootCause());
        dto.setPreventiveMeasures(workInjury.getPreventiveMeasures());
        dto.setStatus(workInjury.getStatus());
        dto.setCreatedAt(workInjury.getCreatedAt());
        dto.setUpdatedAt(workInjury.getUpdatedAt());
        return dto;
    }

    private WorkInjury convertToEntity(WorkInjuryDTO dto) {
        WorkInjury workInjury = new WorkInjury();
        workInjury.setInjuryType(dto.getInjuryType());
        workInjury.setAffectedEmployee(dto.getAffectedEmployee());
        workInjury.setEmployeeId(dto.getEmployeeId());
        workInjury.setIncidentDate(dto.getIncidentDate());
        workInjury.setIncidentLocation(dto.getIncidentLocation());
        workInjury.setDescription(dto.getDescription());
        workInjury.setSeverity(dto.getSeverity());
        workInjury.setTreatmentDescription(dto.getTreatmentDescription());
        workInjury.setHospitalName(dto.getHospitalName());
        workInjury.setDepartment(dto.getDepartment());
        workInjury.setReportedBy(dto.getReportedBy());
        workInjury.setRootCause(dto.getRootCause());
        workInjury.setPreventiveMeasures(dto.getPreventiveMeasures());
        workInjury.setStatus(dto.getStatus());
        return workInjury;
    }
}
