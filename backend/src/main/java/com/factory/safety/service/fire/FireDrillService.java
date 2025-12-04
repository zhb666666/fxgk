package com.factory.safety.service.fire;

import com.factory.safety.common.ResourceNotFoundException;
import com.factory.safety.dto.fire.FireDrillDTO;
import com.factory.safety.entity.fire.FireDrill;
import com.factory.safety.repository.fire.FireDrillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FireDrillService {
    private final FireDrillRepository repository;

    public List<FireDrillDTO> getAllFireDrills() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FireDrillDTO getFireDrillById(Long id) {
        FireDrill fireDrill = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fire Drill not found with id: " + id));
        return convertToDTO(fireDrill);
    }

    public FireDrillDTO createFireDrill(FireDrillDTO dto) {
        FireDrill fireDrill = convertToEntity(dto);
        FireDrill saved = repository.save(fireDrill);
        return convertToDTO(saved);
    }

    public FireDrillDTO updateFireDrill(Long id, FireDrillDTO dto) {
        FireDrill fireDrill = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fire Drill not found with id: " + id));
        
        fireDrill.setDrillName(dto.getDrillName());
        fireDrill.setDrillDate(dto.getDrillDate());
        fireDrill.setDrillType(dto.getDrillType());
        fireDrill.setDrillContent(dto.getDrillContent());
        fireDrill.setParticipantCount(dto.getParticipantCount());
        fireDrill.setOrganizer(dto.getOrganizer());
        fireDrill.setObjectives(dto.getObjectives());
        fireDrill.setExecutionProcess(dto.getExecutionProcess());
        fireDrill.setFindingsAndIssues(dto.getFindingsAndIssues());
        fireDrill.setImprovementMeasures(dto.getImprovementMeasures());
        fireDrill.setStatus(dto.getStatus());
        
        FireDrill updated = repository.save(fireDrill);
        return convertToDTO(updated);
    }

    public void deleteFireDrill(Long id) {
        FireDrill fireDrill = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fire Drill not found with id: " + id));
        repository.delete(fireDrill);
    }

    public List<FireDrillDTO> searchByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FireDrillDTO> searchByDrillType(String drillType) {
        return repository.findByDrillType(drillType).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FireDrillDTO> searchByKeyword(String keyword) {
        return repository.findByDrillNameContaining(keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FireDrillDTO convertToDTO(FireDrill fireDrill) {
        FireDrillDTO dto = new FireDrillDTO();
        dto.setId(fireDrill.getId());
        dto.setDrillName(fireDrill.getDrillName());
        dto.setDrillDate(fireDrill.getDrillDate());
        dto.setDrillType(fireDrill.getDrillType());
        dto.setDrillContent(fireDrill.getDrillContent());
        dto.setParticipantCount(fireDrill.getParticipantCount());
        dto.setOrganizer(fireDrill.getOrganizer());
        dto.setObjectives(fireDrill.getObjectives());
        dto.setExecutionProcess(fireDrill.getExecutionProcess());
        dto.setFindingsAndIssues(fireDrill.getFindingsAndIssues());
        dto.setImprovementMeasures(fireDrill.getImprovementMeasures());
        dto.setStatus(fireDrill.getStatus());
        dto.setCreatedAt(fireDrill.getCreatedAt());
        dto.setUpdatedAt(fireDrill.getUpdatedAt());
        return dto;
    }

    private FireDrill convertToEntity(FireDrillDTO dto) {
        FireDrill fireDrill = new FireDrill();
        fireDrill.setDrillName(dto.getDrillName());
        fireDrill.setDrillDate(dto.getDrillDate());
        fireDrill.setDrillType(dto.getDrillType());
        fireDrill.setDrillContent(dto.getDrillContent());
        fireDrill.setParticipantCount(dto.getParticipantCount());
        fireDrill.setOrganizer(dto.getOrganizer());
        fireDrill.setObjectives(dto.getObjectives());
        fireDrill.setExecutionProcess(dto.getExecutionProcess());
        fireDrill.setFindingsAndIssues(dto.getFindingsAndIssues());
        fireDrill.setImprovementMeasures(dto.getImprovementMeasures());
        fireDrill.setStatus(dto.getStatus());
        return fireDrill;
    }
}
