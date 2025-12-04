package com.factory.safety.controller.production;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.production.WorkInjuryDTO;
import com.factory.safety.service.production.WorkInjuryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/production/work-injury")
@RequiredArgsConstructor
public class WorkInjuryController {
    private final WorkInjuryService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WorkInjuryDTO>>> getAllWorkInjuries() {
        List<WorkInjuryDTO> data = service.getAllWorkInjuries();
        return ResponseEntity.ok(ApiResponse.success("Get all work injuries successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkInjuryDTO>> getWorkInjuryById(@PathVariable Long id) {
        WorkInjuryDTO data = service.getWorkInjuryById(id);
        return ResponseEntity.ok(ApiResponse.success("Get work injury successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkInjuryDTO>> createWorkInjury(
            @RequestBody WorkInjuryDTO dto) {
        WorkInjuryDTO data = service.createWorkInjury(dto);
        return ResponseEntity.ok(ApiResponse.success("Create work injury successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkInjuryDTO>> updateWorkInjury(
            @PathVariable Long id,
            @RequestBody WorkInjuryDTO dto) {
        WorkInjuryDTO data = service.updateWorkInjury(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update work injury successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteWorkInjury(@PathVariable Long id) {
        service.deleteWorkInjury(id);
        return ResponseEntity.ok(ApiResponse.success("Delete work injury successfully", null));
    }

    @GetMapping("/search/department/{department}")
    public ResponseEntity<ApiResponse<List<WorkInjuryDTO>>> searchByDepartment(
            @PathVariable String department) {
        List<WorkInjuryDTO> data = service.searchByDepartment(department);
        return ResponseEntity.ok(ApiResponse.success("Search by department successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<WorkInjuryDTO>>> searchByStatus(
            @PathVariable String status) {
        List<WorkInjuryDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/severity/{severity}")
    public ResponseEntity<ApiResponse<List<WorkInjuryDTO>>> searchBySeverity(
            @PathVariable String severity) {
        List<WorkInjuryDTO> data = service.searchBySeverity(severity);
        return ResponseEntity.ok(ApiResponse.success("Search by severity successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<WorkInjuryDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<WorkInjuryDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
