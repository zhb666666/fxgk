package com.factory.safety.controller.production;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.production.OperationSafetyDTO;
import com.factory.safety.service.production.OperationSafetyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/production/operation-safety")
@RequiredArgsConstructor
public class OperationSafetyController {
    private final OperationSafetyService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OperationSafetyDTO>>> getAllOperationSafety() {
        List<OperationSafetyDTO> data = service.getAllOperationSafety();
        return ResponseEntity.ok(ApiResponse.success("Get all operation safety records successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OperationSafetyDTO>> getOperationSafetyById(@PathVariable Long id) {
        OperationSafetyDTO data = service.getOperationSafetyById(id);
        return ResponseEntity.ok(ApiResponse.success("Get operation safety record successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OperationSafetyDTO>> createOperationSafety(
            @RequestBody OperationSafetyDTO dto) {
        OperationSafetyDTO data = service.createOperationSafety(dto);
        return ResponseEntity.ok(ApiResponse.success("Create operation safety record successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OperationSafetyDTO>> updateOperationSafety(
            @PathVariable Long id,
            @RequestBody OperationSafetyDTO dto) {
        OperationSafetyDTO data = service.updateOperationSafety(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update operation safety record successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteOperationSafety(@PathVariable Long id) {
        service.deleteOperationSafety(id);
        return ResponseEntity.ok(ApiResponse.success("Delete operation safety record successfully", null));
    }

    @GetMapping("/search/department/{department}")
    public ResponseEntity<ApiResponse<List<OperationSafetyDTO>>> searchByDepartment(
            @PathVariable String department) {
        List<OperationSafetyDTO> data = service.searchByDepartment(department);
        return ResponseEntity.ok(ApiResponse.success("Search by department successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<OperationSafetyDTO>>> searchByStatus(
            @PathVariable String status) {
        List<OperationSafetyDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<OperationSafetyDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<OperationSafetyDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
