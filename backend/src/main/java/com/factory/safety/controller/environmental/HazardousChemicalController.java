package com.factory.safety.controller.environmental;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.environmental.HazardousChemicalDTO;
import com.factory.safety.service.environmental.HazardousChemicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/environmental/hazardous-chemical")
@RequiredArgsConstructor
public class HazardousChemicalController {
    private final HazardousChemicalService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<HazardousChemicalDTO>>> getAllHazardousChemicals() {
        List<HazardousChemicalDTO> data = service.getAllHazardousChemicals();
        return ResponseEntity.ok(ApiResponse.success("Get all hazardous chemicals successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HazardousChemicalDTO>> getHazardousChemicalById(@PathVariable Long id) {
        HazardousChemicalDTO data = service.getHazardousChemicalById(id);
        return ResponseEntity.ok(ApiResponse.success("Get hazardous chemical successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HazardousChemicalDTO>> createHazardousChemical(
            @RequestBody HazardousChemicalDTO dto) {
        HazardousChemicalDTO data = service.createHazardousChemical(dto);
        return ResponseEntity.ok(ApiResponse.success("Create hazardous chemical successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HazardousChemicalDTO>> updateHazardousChemical(
            @PathVariable Long id,
            @RequestBody HazardousChemicalDTO dto) {
        HazardousChemicalDTO data = service.updateHazardousChemical(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update hazardous chemical successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteHazardousChemical(@PathVariable Long id) {
        service.deleteHazardousChemical(id);
        return ResponseEntity.ok(ApiResponse.success("Delete hazardous chemical successfully", null));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<HazardousChemicalDTO>>> searchByStatus(
            @PathVariable String status) {
        List<HazardousChemicalDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ApiResponse<List<HazardousChemicalDTO>>> searchByStorageLocation(
            @PathVariable String location) {
        List<HazardousChemicalDTO> data = service.searchByStorageLocation(location);
        return ResponseEntity.ok(ApiResponse.success("Search by storage location successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<HazardousChemicalDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<HazardousChemicalDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
