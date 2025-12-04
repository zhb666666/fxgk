package com.factory.safety.controller.campus;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.campus.SurveillanceLogDTO;
import com.factory.safety.service.campus.SurveillanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/campus/surveillance-log")
@RequiredArgsConstructor
public class SurveillanceLogController {
    private final SurveillanceLogService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SurveillanceLogDTO>>> getAllSurveillanceLogs() {
        List<SurveillanceLogDTO> data = service.getAllSurveillanceLogs();
        return ResponseEntity.ok(ApiResponse.success("Get all surveillance logs successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveillanceLogDTO>> getSurveillanceLogById(@PathVariable Long id) {
        SurveillanceLogDTO data = service.getSurveillanceLogById(id);
        return ResponseEntity.ok(ApiResponse.success("Get surveillance log successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SurveillanceLogDTO>> createSurveillanceLog(
            @RequestBody SurveillanceLogDTO dto) {
        SurveillanceLogDTO data = service.createSurveillanceLog(dto);
        return ResponseEntity.ok(ApiResponse.success("Create surveillance log successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveillanceLogDTO>> updateSurveillanceLog(
            @PathVariable Long id,
            @RequestBody SurveillanceLogDTO dto) {
        SurveillanceLogDTO data = service.updateSurveillanceLog(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update surveillance log successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteSurveillanceLog(@PathVariable Long id) {
        service.deleteSurveillanceLog(id);
        return ResponseEntity.ok(ApiResponse.success("Delete surveillance log successfully", null));
    }

    @GetMapping("/search/camera/{cameraName}")
    public ResponseEntity<ApiResponse<List<SurveillanceLogDTO>>> searchByCameraName(
            @PathVariable String cameraName) {
        List<SurveillanceLogDTO> data = service.searchByCameraName(cameraName);
        return ResponseEntity.ok(ApiResponse.success("Search by camera name successfully", data));
    }

    @GetMapping("/search/type/{eventType}")
    public ResponseEntity<ApiResponse<List<SurveillanceLogDTO>>> searchByEventType(
            @PathVariable String eventType) {
        List<SurveillanceLogDTO> data = service.searchByEventType(eventType);
        return ResponseEntity.ok(ApiResponse.success("Search by event type successfully", data));
    }

    @GetMapping("/search/severity/{severity}")
    public ResponseEntity<ApiResponse<List<SurveillanceLogDTO>>> searchBySeverity(
            @PathVariable String severity) {
        List<SurveillanceLogDTO> data = service.searchBySeverity(severity);
        return ResponseEntity.ok(ApiResponse.success("Search by severity successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<SurveillanceLogDTO>>> searchByStatus(
            @PathVariable String status) {
        List<SurveillanceLogDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }
}
