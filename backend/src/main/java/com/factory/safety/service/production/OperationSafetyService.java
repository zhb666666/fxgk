package com.factory.safety.service.production;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.production.OperationSafetyDTO;
import com.factory.safety.entity.production.OperationSafety;
import com.factory.safety.repository.production.OperationSafetyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationSafetyService {
    private final OperationSafetyRepository repository;

    public List<OperationSafetyDTO> getAllOperationSafety() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public OperationSafetyDTO getOperationSafetyById(Long id) {
        OperationSafety operationSafety = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation Safety not found with id: " + id));
        return convertToDTO(operationSafety);
    }

    public OperationSafetyDTO createOperationSafety(OperationSafetyDTO dto) {
        OperationSafety operationSafety = convertToEntity(dto);
        OperationSafety saved = repository.save(operationSafety);
        return convertToDTO(saved);
    }

    public OperationSafetyDTO updateOperationSafety(Long id, OperationSafetyDTO dto) {
        OperationSafety operationSafety = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation Safety not found with id: " + id));
        
        operationSafety.setOperationName(dto.getOperationName());
        operationSafety.setProcedureContent(dto.getProcedureContent());
        operationSafety.setSafetyLevel(dto.getSafetyLevel());
        operationSafety.setDepartment(dto.getDepartment());
        operationSafety.setOwner(dto.getOwner());
        operationSafety.setRiskPoints(dto.getRiskPoints());
        operationSafety.setControlMeasures(dto.getControlMeasures());
        operationSafety.setStatus(dto.getStatus());
        
        OperationSafety updated = repository.save(operationSafety);
        return convertToDTO(updated);
    }

    public void deleteOperationSafety(Long id) {
        OperationSafety operationSafety = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operation Safety not found with id: " + id));
        repository.delete(operationSafety);
    }

    public List<OperationSafetyDTO> searchByDepartment(String department) {
        return repository.findByDepartment(department).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OperationSafetyDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OperationSafetyDTO> searchByKeyword(String keyword) {
        return repository.findByOperationNameContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OperationSafetyDTO convertToDTO(OperationSafety operationSafety) {
        OperationSafetyDTO dto = new OperationSafetyDTO();
        dto.setId(operationSafety.getId());
        dto.setOperationName(operationSafety.getOperationName());
        dto.setProcedureContent(operationSafety.getProcedureContent());
        dto.setSafetyLevel(operationSafety.getSafetyLevel());
        dto.setDepartment(operationSafety.getDepartment());
        dto.setOwner(operationSafety.getOwner());
        dto.setRiskPoints(operationSafety.getRiskPoints());
        dto.setControlMeasures(operationSafety.getControlMeasures());
        dto.setStatus(operationSafety.getStatus());
        dto.setLastReviewDate(operationSafety.getLastReviewDate());
        dto.setLastReviewBy(operationSafety.getLastReviewBy());
        dto.setCreatedAt(operationSafety.getCreatedAt());
        dto.setUpdatedAt(operationSafety.getUpdatedAt());
        return dto;
    }

    private OperationSafety convertToEntity(OperationSafetyDTO dto) {
        OperationSafety operationSafety = new OperationSafety();
        operationSafety.setOperationName(dto.getOperationName());
        operationSafety.setProcedureContent(dto.getProcedureContent());
        operationSafety.setSafetyLevel(dto.getSafetyLevel());
        operationSafety.setDepartment(dto.getDepartment());
        operationSafety.setOwner(dto.getOwner());
        operationSafety.setRiskPoints(dto.getRiskPoints());
        operationSafety.setControlMeasures(dto.getControlMeasures());
        operationSafety.setStatus(dto.getStatus());
        return operationSafety;
    }
}
