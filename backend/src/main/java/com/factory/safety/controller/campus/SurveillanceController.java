package com.factory.safety.controller.campus;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.campus.SurveillanceDTO;
import com.factory.safety.service.campus.SurveillanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/campus/surveillance")
@RequiredArgsConstructor
public class SurveillanceController {
    private final SurveillanceService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SurveillanceDTO>>> getAllSurveillances() {
        List<SurveillanceDTO> data = service.getAllSurveillances();
        return ResponseEntity.ok(ApiResponse.success("Get all surveillance systems successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveillanceDTO>> getSurveillanceById(@PathVariable Long id) {
        SurveillanceDTO data = service.getSurveillanceById(id);
        return ResponseEntity.ok(ApiResponse.success("Get surveillance system successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SurveillanceDTO>> createSurveillance(
            @RequestBody SurveillanceDTO dto) {
        SurveillanceDTO data = service.createSurveillance(dto);
        return ResponseEntity.ok(ApiResponse.success("Create surveillance system successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveillanceDTO>> updateSurveillance(
            @PathVariable Long id,
            @RequestBody SurveillanceDTO dto) {
        SurveillanceDTO data = service.updateSurveillance(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update surveillance system successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteSurveillance(@PathVariable Long id) {
        service.deleteSurveillance(id);
        return ResponseEntity.ok(ApiResponse.success("Delete surveillance system successfully", null));
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ApiResponse<List<SurveillanceDTO>>> searchByLocation(
            @PathVariable String location) {
        List<SurveillanceDTO> data = service.searchByLocation(location);
        return ResponseEntity.ok(ApiResponse.success("Search by location successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<SurveillanceDTO>>> searchByStatus(
            @PathVariable String status) {
        List<SurveillanceDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<SurveillanceDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<SurveillanceDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
