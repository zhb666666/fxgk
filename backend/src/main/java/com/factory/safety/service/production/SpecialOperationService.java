package com.factory.safety.service.production;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.production.SpecialOperationDTO;
import com.factory.safety.entity.production.SpecialOperation;
import com.factory.safety.repository.production.SpecialOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialOperationService {
    private final SpecialOperationRepository repository;

    public List<SpecialOperationDTO> getAllSpecialOperations() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SpecialOperationDTO getSpecialOperationById(Long id) {
        SpecialOperation specialOperation = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Special Operation not found with id: " + id));
        return convertToDTO(specialOperation);
    }

    public SpecialOperationDTO createSpecialOperation(SpecialOperationDTO dto) {
        SpecialOperation specialOperation = convertToEntity(dto);
        SpecialOperation saved = repository.save(specialOperation);
        return convertToDTO(saved);
    }

    public SpecialOperationDTO updateSpecialOperation(Long id, SpecialOperationDTO dto) {
        SpecialOperation specialOperation = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Special Operation not found with id: " + id));
        
        specialOperation.setOperationType(dto.getOperationType());
        specialOperation.setOperatorName(dto.getOperatorName());
        specialOperation.setCertificateNumber(dto.getCertificateNumber());
        specialOperation.setCertificateIssuedDate(dto.getCertificateIssuedDate());
        specialOperation.setCertificateExpiryDate(dto.getCertificateExpiryDate());
        specialOperation.setIssuingAuthority(dto.getIssuingAuthority());
        specialOperation.setLastTrainingDate(dto.getLastTrainingDate());
        specialOperation.setNextTrainingDate(dto.getNextTrainingDate());
        specialOperation.setDepartment(dto.getDepartment());
        specialOperation.setWorkLocation(dto.getWorkLocation());
        specialOperation.setNotes(dto.getNotes());
        specialOperation.setStatus(dto.getStatus());
        
        SpecialOperation updated = repository.save(specialOperation);
        return convertToDTO(updated);
    }

    public void deleteSpecialOperation(Long id) {
        SpecialOperation specialOperation = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Special Operation not found with id: " + id));
        repository.delete(specialOperation);
    }

    public List<SpecialOperationDTO> searchByOperationType(String operationType) {
        return repository.findByOperationType(operationType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SpecialOperationDTO> searchByDepartment(String department) {
        return repository.findByDepartment(department).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SpecialOperationDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SpecialOperationDTO> searchByKeyword(String keyword) {
        return repository.findByOperatorNameContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SpecialOperationDTO convertToDTO(SpecialOperation specialOperation) {
        SpecialOperationDTO dto = new SpecialOperationDTO();
        dto.setId(specialOperation.getId());
        dto.setOperationType(specialOperation.getOperationType());
        dto.setOperatorName(specialOperation.getOperatorName());
        dto.setCertificateNumber(specialOperation.getCertificateNumber());
        dto.setCertificateIssuedDate(specialOperation.getCertificateIssuedDate());
        dto.setCertificateExpiryDate(specialOperation.getCertificateExpiryDate());
        dto.setIssuingAuthority(specialOperation.getIssuingAuthority());
        dto.setLastTrainingDate(specialOperation.getLastTrainingDate());
        dto.setNextTrainingDate(specialOperation.getNextTrainingDate());
        dto.setDepartment(specialOperation.getDepartment());
        dto.setWorkLocation(specialOperation.getWorkLocation());
        dto.setNotes(specialOperation.getNotes());
        dto.setStatus(specialOperation.getStatus());
        dto.setCreatedAt(specialOperation.getCreatedAt());
        dto.setUpdatedAt(specialOperation.getUpdatedAt());
        return dto;
    }

    private SpecialOperation convertToEntity(SpecialOperationDTO dto) {
        SpecialOperation specialOperation = new SpecialOperation();
        specialOperation.setOperationType(dto.getOperationType());
        specialOperation.setOperatorName(dto.getOperatorName());
        specialOperation.setCertificateNumber(dto.getCertificateNumber());
        specialOperation.setCertificateIssuedDate(dto.getCertificateIssuedDate());
        specialOperation.setCertificateExpiryDate(dto.getCertificateExpiryDate());
        specialOperation.setIssuingAuthority(dto.getIssuingAuthority());
        specialOperation.setLastTrainingDate(dto.getLastTrainingDate());
        specialOperation.setNextTrainingDate(dto.getNextTrainingDate());
        specialOperation.setDepartment(dto.getDepartment());
        specialOperation.setWorkLocation(dto.getWorkLocation());
        specialOperation.setNotes(dto.getNotes());
        specialOperation.setStatus(dto.getStatus());
        return specialOperation;
    }
}
