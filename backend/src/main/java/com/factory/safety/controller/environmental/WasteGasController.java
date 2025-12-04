package com.factory.safety.controller.environmental;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.environmental.WasteGasDTO;
import com.factory.safety.service.environmental.WasteGasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/environmental/waste-gas")
@RequiredArgsConstructor
public class WasteGasController {
    private final WasteGasService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WasteGasDTO>>> getAllWasteGas() {
        List<WasteGasDTO> data = service.getAllWasteGas();
        return ResponseEntity.ok(ApiResponse.success("Get all waste gas records successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WasteGasDTO>> getWasteGasById(@PathVariable Long id) {
        WasteGasDTO data = service.getWasteGasById(id);
        return ResponseEntity.ok(ApiResponse.success("Get waste gas record successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WasteGasDTO>> createWasteGas(
            @RequestBody WasteGasDTO dto) {
        WasteGasDTO data = service.createWasteGas(dto);
        return ResponseEntity.ok(ApiResponse.success("Create waste gas record successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WasteGasDTO>> updateWasteGas(
            @PathVariable Long id,
            @RequestBody WasteGasDTO dto) {
        WasteGasDTO data = service.updateWasteGas(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update waste gas record successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteWasteGas(@PathVariable Long id) {
        service.deleteWasteGas(id);
        return ResponseEntity.ok(ApiResponse.success("Delete waste gas record successfully", null));
    }

    @GetMapping("/search/source/{emissionSource}")
    public ResponseEntity<ApiResponse<List<WasteGasDTO>>> searchByEmissionSource(
            @PathVariable String emissionSource) {
        List<WasteGasDTO> data = service.searchByEmissionSource(emissionSource);
        return ResponseEntity.ok(ApiResponse.success("Search by emission source successfully", data));
    }

    @GetMapping("/search/compliance/{complianceStatus}")
    public ResponseEntity<ApiResponse<List<WasteGasDTO>>> searchByComplianceStatus(
            @PathVariable String complianceStatus) {
        List<WasteGasDTO> data = service.searchByComplianceStatus(complianceStatus);
        return ResponseEntity.ok(ApiResponse.success("Search by compliance status successfully", data));
    }
}
