package com.factory.safety.controller.environmental;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.environmental.WasteWaterDTO;
import com.factory.safety.service.environmental.WasteWaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/environmental/waste-water")
@RequiredArgsConstructor
public class WasteWaterController {
    private final WasteWaterService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WasteWaterDTO>>> getAllWasteWater() {
        List<WasteWaterDTO> data = service.getAllWasteWater();
        return ResponseEntity.ok(ApiResponse.success("Get all waste water records successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WasteWaterDTO>> getWasteWaterById(@PathVariable Long id) {
        WasteWaterDTO data = service.getWasteWaterById(id);
        return ResponseEntity.ok(ApiResponse.success("Get waste water record successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WasteWaterDTO>> createWasteWater(@RequestBody WasteWaterDTO dto) {
        WasteWaterDTO data = service.createWasteWater(dto);
        return ResponseEntity.ok(ApiResponse.success("Create waste water record successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WasteWaterDTO>> updateWasteWater(
            @PathVariable Long id,
            @RequestBody WasteWaterDTO dto) {
        WasteWaterDTO data = service.updateWasteWater(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update waste water record successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteWasteWater(@PathVariable Long id) {
        service.deleteWasteWater(id);
        return ResponseEntity.ok(ApiResponse.success("Delete waste water record successfully", null));
    }

    @GetMapping("/search/source/{source}")
    public ResponseEntity<ApiResponse<List<WasteWaterDTO>>> searchBySource(@PathVariable String source) {
        List<WasteWaterDTO> data = service.searchBySource(source);
        return ResponseEntity.ok(ApiResponse.success("Search by source successfully", data));
    }

    @GetMapping("/search/compliance/{complianceStatus}")
    public ResponseEntity<ApiResponse<List<WasteWaterDTO>>> searchByComplianceStatus(
            @PathVariable String complianceStatus) {
        List<WasteWaterDTO> data = service.searchByComplianceStatus(complianceStatus);
        return ResponseEntity.ok(ApiResponse.success("Search by compliance status successfully", data));
    }
}
