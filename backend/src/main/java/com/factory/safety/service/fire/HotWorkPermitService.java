package com.factory.safety.service.fire;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.fire.HotWorkPermitDTO;
import com.factory.safety.entity.fire.HotWorkPermit;
import com.factory.safety.repository.fire.HotWorkPermitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotWorkPermitService {
    private final HotWorkPermitRepository repository;

    public List<HotWorkPermitDTO> getAllHotWorkPermits() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public HotWorkPermitDTO getHotWorkPermitById(Long id) {
        HotWorkPermit hotWorkPermit = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hot Work Permit not found with id: " + id));
        return convertToDTO(hotWorkPermit);
    }

    public HotWorkPermitDTO createHotWorkPermit(HotWorkPermitDTO dto) {
        HotWorkPermit hotWorkPermit = convertToEntity(dto);
        HotWorkPermit saved = repository.save(hotWorkPermit);
        return convertToDTO(saved);
    }

    public HotWorkPermitDTO updateHotWorkPermit(Long id, HotWorkPermitDTO dto) {
        HotWorkPermit hotWorkPermit = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hot Work Permit not found with id: " + id));
        
        hotWorkPermit.setPermitNumber(dto.getPermitNumber());
        hotWorkPermit.setWorkDate(dto.getWorkDate());
        hotWorkPermit.setWorkType(dto.getWorkType());
        hotWorkPermit.setLocation(dto.getLocation());
        hotWorkPermit.setOperator(dto.getOperator());
        hotWorkPermit.setSupervisor(dto.getSupervisor());
        hotWorkPermit.setSafetyOfficer(dto.getSafetyOfficer());
        hotWorkPermit.setWorkDescription(dto.getWorkDescription());
        hotWorkPermit.setSafetyMeasures(dto.getSafetyMeasures());
        hotWorkPermit.setRiskAssessment(dto.getRiskAssessment());
        hotWorkPermit.setStartTime(dto.getStartTime());
        hotWorkPermit.setEndTime(dto.getEndTime());
        hotWorkPermit.setStatus(dto.getStatus());
        hotWorkPermit.setApprovedAt(dto.getApprovedAt());
        hotWorkPermit.setApprovedBy(dto.getApprovedBy());
        
        HotWorkPermit updated = repository.save(hotWorkPermit);
        return convertToDTO(updated);
    }

    public void deleteHotWorkPermit(Long id) {
        HotWorkPermit hotWorkPermit = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hot Work Permit not found with id: " + id));
        repository.delete(hotWorkPermit);
    }

    public List<HotWorkPermitDTO> searchByLocation(String location) {
        return repository.findByLocation(location).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HotWorkPermitDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HotWorkPermitDTO> searchByWorkType(String workType) {
        return repository.findByWorkType(workType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<HotWorkPermitDTO> searchByKeyword(String keyword) {
        return repository.findByPermitNumberContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private HotWorkPermitDTO convertToDTO(HotWorkPermit hotWorkPermit) {
        HotWorkPermitDTO dto = new HotWorkPermitDTO();
        dto.setId(hotWorkPermit.getId());
        dto.setPermitNumber(hotWorkPermit.getPermitNumber());
        dto.setWorkDate(hotWorkPermit.getWorkDate());
        dto.setWorkType(hotWorkPermit.getWorkType());
        dto.setLocation(hotWorkPermit.getLocation());
        dto.setOperator(hotWorkPermit.getOperator());
        dto.setSupervisor(hotWorkPermit.getSupervisor());
        dto.setSafetyOfficer(hotWorkPermit.getSafetyOfficer());
        dto.setWorkDescription(hotWorkPermit.getWorkDescription());
        dto.setSafetyMeasures(hotWorkPermit.getSafetyMeasures());
        dto.setRiskAssessment(hotWorkPermit.getRiskAssessment());
        dto.setStartTime(hotWorkPermit.getStartTime());
        dto.setEndTime(hotWorkPermit.getEndTime());
        dto.setStatus(hotWorkPermit.getStatus());
        dto.setApprovedAt(hotWorkPermit.getApprovedAt());
        dto.setApprovedBy(hotWorkPermit.getApprovedBy());
        dto.setCreatedAt(hotWorkPermit.getCreatedAt());
        dto.setUpdatedAt(hotWorkPermit.getUpdatedAt());
        return dto;
    }

    private HotWorkPermit convertToEntity(HotWorkPermitDTO dto) {
        HotWorkPermit hotWorkPermit = new HotWorkPermit();
        hotWorkPermit.setPermitNumber(dto.getPermitNumber());
        hotWorkPermit.setWorkDate(dto.getWorkDate());
        hotWorkPermit.setWorkType(dto.getWorkType());
        hotWorkPermit.setLocation(dto.getLocation());
        hotWorkPermit.setOperator(dto.getOperator());
        hotWorkPermit.setSupervisor(dto.getSupervisor());
        hotWorkPermit.setSafetyOfficer(dto.getSafetyOfficer());
        hotWorkPermit.setWorkDescription(dto.getWorkDescription());
        hotWorkPermit.setSafetyMeasures(dto.getSafetyMeasures());
        hotWorkPermit.setRiskAssessment(dto.getRiskAssessment());
        hotWorkPermit.setStartTime(dto.getStartTime());
        hotWorkPermit.setEndTime(dto.getEndTime());
        hotWorkPermit.setStatus(dto.getStatus());
        hotWorkPermit.setApprovedAt(dto.getApprovedAt());
        hotWorkPermit.setApprovedBy(dto.getApprovedBy());
        return hotWorkPermit;
    }
}
