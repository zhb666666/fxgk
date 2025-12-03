package com.factory.safety.controller.fire;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.fire.FireControlDTO;
import com.factory.safety.service.fire.FireControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/fire/fire-control")
@RequiredArgsConstructor
public class FireControlController {
    private final FireControlService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<FireControlDTO>>> getAllFireControl() {
        List<FireControlDTO> data = service.getAllFireControl();
        return ResponseEntity.ok(ApiResponse.success("Get all fire control records successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FireControlDTO>> getFireControlById(@PathVariable Long id) {
        FireControlDTO data = service.getFireControlById(id);
        return ResponseEntity.ok(ApiResponse.success("Get fire control record successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FireControlDTO>> createFireControl(@RequestBody FireControlDTO dto) {
        FireControlDTO data = service.createFireControl(dto);
        return ResponseEntity.ok(ApiResponse.success("Create fire control record successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FireControlDTO>> updateFireControl(
            @PathVariable Long id,
            @RequestBody FireControlDTO dto) {
        FireControlDTO data = service.updateFireControl(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update fire control record successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteFireControl(@PathVariable Long id) {
        service.deleteFireControl(id);
        return ResponseEntity.ok(ApiResponse.success("Delete fire control record successfully", null));
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ApiResponse<List<FireControlDTO>>> searchByLocation(@PathVariable String location) {
        List<FireControlDTO> data = service.searchByLocation(location);
        return ResponseEntity.ok(ApiResponse.success("Search by location successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<FireControlDTO>>> searchByStatus(@PathVariable String status) {
        List<FireControlDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/type/{facilityType}")
    public ResponseEntity<ApiResponse<List<FireControlDTO>>> searchByFacilityType(
            @PathVariable String facilityType) {
        List<FireControlDTO> data = service.searchByFacilityType(facilityType);
        return ResponseEntity.ok(ApiResponse.success("Search by facility type successfully", data));
    }
}
