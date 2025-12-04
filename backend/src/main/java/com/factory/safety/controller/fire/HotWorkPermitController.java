package com.factory.safety.controller.fire;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.fire.HotWorkPermitDTO;
import com.factory.safety.service.fire.HotWorkPermitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/fire/hot-work-permit")
@RequiredArgsConstructor
public class HotWorkPermitController {
    private final HotWorkPermitService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<HotWorkPermitDTO>>> getAllHotWorkPermits() {
        List<HotWorkPermitDTO> data = service.getAllHotWorkPermits();
        return ResponseEntity.ok(ApiResponse.success("Get all hot work permits successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HotWorkPermitDTO>> getHotWorkPermitById(@PathVariable Long id) {
        HotWorkPermitDTO data = service.getHotWorkPermitById(id);
        return ResponseEntity.ok(ApiResponse.success("Get hot work permit successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HotWorkPermitDTO>> createHotWorkPermit(
            @RequestBody HotWorkPermitDTO dto) {
        HotWorkPermitDTO data = service.createHotWorkPermit(dto);
        return ResponseEntity.ok(ApiResponse.success("Create hot work permit successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HotWorkPermitDTO>> updateHotWorkPermit(
            @PathVariable Long id,
            @RequestBody HotWorkPermitDTO dto) {
        HotWorkPermitDTO data = service.updateHotWorkPermit(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update hot work permit successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteHotWorkPermit(@PathVariable Long id) {
        service.deleteHotWorkPermit(id);
        return ResponseEntity.ok(ApiResponse.success("Delete hot work permit successfully", null));
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ApiResponse<List<HotWorkPermitDTO>>> searchByLocation(
            @PathVariable String location) {
        List<HotWorkPermitDTO> data = service.searchByLocation(location);
        return ResponseEntity.ok(ApiResponse.success("Search by location successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<HotWorkPermitDTO>>> searchByStatus(
            @PathVariable String status) {
        List<HotWorkPermitDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/type/{workType}")
    public ResponseEntity<ApiResponse<List<HotWorkPermitDTO>>> searchByWorkType(
            @PathVariable String workType) {
        List<HotWorkPermitDTO> data = service.searchByWorkType(workType);
        return ResponseEntity.ok(ApiResponse.success("Search by work type successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<HotWorkPermitDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<HotWorkPermitDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
