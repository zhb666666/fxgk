package com.factory.safety.controller.environmental;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.environmental.HazardousWasteDTO;
import com.factory.safety.service.environmental.HazardousWasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/environmental/hazardous-waste")
@RequiredArgsConstructor
public class HazardousWasteController {
    private final HazardousWasteService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<HazardousWasteDTO>>> getAllHazardousWastes() {
        List<HazardousWasteDTO> data = service.getAllHazardousWastes();
        return ResponseEntity.ok(ApiResponse.success("Get all hazardous wastes successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HazardousWasteDTO>> getHazardousWasteById(@PathVariable Long id) {
        HazardousWasteDTO data = service.getHazardousWasteById(id);
        return ResponseEntity.ok(ApiResponse.success("Get hazardous waste successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HazardousWasteDTO>> createHazardousWaste(
            @RequestBody HazardousWasteDTO dto) {
        HazardousWasteDTO data = service.createHazardousWaste(dto);
        return ResponseEntity.ok(ApiResponse.success("Create hazardous waste successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HazardousWasteDTO>> updateHazardousWaste(
            @PathVariable Long id,
            @RequestBody HazardousWasteDTO dto) {
        HazardousWasteDTO data = service.updateHazardousWaste(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update hazardous waste successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteHazardousWaste(@PathVariable Long id) {
        service.deleteHazardousWaste(id);
        return ResponseEntity.ok(ApiResponse.success("Delete hazardous waste successfully", null));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<HazardousWasteDTO>>> searchByStatus(
            @PathVariable String status) {
        List<HazardousWasteDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ApiResponse<List<HazardousWasteDTO>>> searchByStorageLocation(
            @PathVariable String location) {
        List<HazardousWasteDTO> data = service.searchByStorageLocation(location);
        return ResponseEntity.ok(ApiResponse.success("Search by storage location successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<HazardousWasteDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<HazardousWasteDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
