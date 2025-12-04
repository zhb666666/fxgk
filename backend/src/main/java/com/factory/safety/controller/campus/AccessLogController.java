package com.factory.safety.controller.campus;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.campus.AccessLogDTO;
import com.factory.safety.service.campus.AccessLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/campus/access-log")
@RequiredArgsConstructor
public class AccessLogController {
    private final AccessLogService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccessLogDTO>>> getAllAccessLogs() {
        List<AccessLogDTO> data = service.getAllAccessLogs();
        return ResponseEntity.ok(ApiResponse.success("Get all access logs successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccessLogDTO>> getAccessLogById(@PathVariable Long id) {
        AccessLogDTO data = service.getAccessLogById(id);
        return ResponseEntity.ok(ApiResponse.success("Get access log successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccessLogDTO>> createAccessLog(
            @RequestBody AccessLogDTO dto) {
        AccessLogDTO data = service.createAccessLog(dto);
        return ResponseEntity.ok(ApiResponse.success("Create access log successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccessLogDTO>> updateAccessLog(
            @PathVariable Long id,
            @RequestBody AccessLogDTO dto) {
        AccessLogDTO data = service.updateAccessLog(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update access log successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteAccessLog(@PathVariable Long id) {
        service.deleteAccessLog(id);
        return ResponseEntity.ok(ApiResponse.success("Delete access log successfully", null));
    }

    @GetMapping("/search/point/{accessPoint}")
    public ResponseEntity<ApiResponse<List<AccessLogDTO>>> searchByAccessPoint(
            @PathVariable String accessPoint) {
        List<AccessLogDTO> data = service.searchByAccessPoint(accessPoint);
        return ResponseEntity.ok(ApiResponse.success("Search by access point successfully", data));
    }

    @GetMapping("/search/type/{accessType}")
    public ResponseEntity<ApiResponse<List<AccessLogDTO>>> searchByAccessType(
            @PathVariable String accessType) {
        List<AccessLogDTO> data = service.searchByAccessType(accessType);
        return ResponseEntity.ok(ApiResponse.success("Search by access type successfully", data));
    }

    @GetMapping("/search/department/{department}")
    public ResponseEntity<ApiResponse<List<AccessLogDTO>>> searchByDepartment(
            @PathVariable String department) {
        List<AccessLogDTO> data = service.searchByDepartment(department);
        return ResponseEntity.ok(ApiResponse.success("Search by department successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<AccessLogDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<AccessLogDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
