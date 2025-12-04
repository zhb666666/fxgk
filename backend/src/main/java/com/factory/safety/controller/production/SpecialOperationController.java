package com.factory.safety.controller.production;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.production.SpecialOperationDTO;
import com.factory.safety.service.production.SpecialOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/production/special-operation")
@RequiredArgsConstructor
public class SpecialOperationController {
    private final SpecialOperationService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SpecialOperationDTO>>> getAllSpecialOperations() {
        List<SpecialOperationDTO> data = service.getAllSpecialOperations();
        return ResponseEntity.ok(ApiResponse.success("Get all special operations successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SpecialOperationDTO>> getSpecialOperationById(@PathVariable Long id) {
        SpecialOperationDTO data = service.getSpecialOperationById(id);
        return ResponseEntity.ok(ApiResponse.success("Get special operation successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SpecialOperationDTO>> createSpecialOperation(
            @RequestBody SpecialOperationDTO dto) {
        SpecialOperationDTO data = service.createSpecialOperation(dto);
        return ResponseEntity.ok(ApiResponse.success("Create special operation successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SpecialOperationDTO>> updateSpecialOperation(
            @PathVariable Long id,
            @RequestBody SpecialOperationDTO dto) {
        SpecialOperationDTO data = service.updateSpecialOperation(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update special operation successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteSpecialOperation(@PathVariable Long id) {
        service.deleteSpecialOperation(id);
        return ResponseEntity.ok(ApiResponse.success("Delete special operation successfully", null));
    }

    @GetMapping("/search/type/{operationType}")
    public ResponseEntity<ApiResponse<List<SpecialOperationDTO>>> searchByOperationType(
            @PathVariable String operationType) {
        List<SpecialOperationDTO> data = service.searchByOperationType(operationType);
        return ResponseEntity.ok(ApiResponse.success("Search by operation type successfully", data));
    }

    @GetMapping("/search/department/{department}")
    public ResponseEntity<ApiResponse<List<SpecialOperationDTO>>> searchByDepartment(
            @PathVariable String department) {
        List<SpecialOperationDTO> data = service.searchByDepartment(department);
        return ResponseEntity.ok(ApiResponse.success("Search by department successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<SpecialOperationDTO>>> searchByStatus(
            @PathVariable String status) {
        List<SpecialOperationDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<SpecialOperationDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<SpecialOperationDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
