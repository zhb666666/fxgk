package com.factory.safety.controller.campus;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.campus.AccessControlDTO;
import com.factory.safety.service.campus.AccessControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/campus/access-control")
@RequiredArgsConstructor
public class AccessControlController {
    private final AccessControlService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccessControlDTO>>> getAllAccessControl() {
        List<AccessControlDTO> data = service.getAllAccessControl();
        return ResponseEntity.ok(ApiResponse.success("Get all access control records successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccessControlDTO>> getAccessControlById(@PathVariable Long id) {
        AccessControlDTO data = service.getAccessControlById(id);
        return ResponseEntity.ok(ApiResponse.success("Get access control record successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccessControlDTO>> createAccessControl(@RequestBody AccessControlDTO dto) {
        AccessControlDTO data = service.createAccessControl(dto);
        return ResponseEntity.ok(ApiResponse.success("Create access control record successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccessControlDTO>> updateAccessControl(
            @PathVariable Long id,
            @RequestBody AccessControlDTO dto) {
        AccessControlDTO data = service.updateAccessControl(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update access control record successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteAccessControl(@PathVariable Long id) {
        service.deleteAccessControl(id);
        return ResponseEntity.ok(ApiResponse.success("Delete access control record successfully", null));
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ApiResponse<List<AccessControlDTO>>> searchByLocation(@PathVariable String location) {
        List<AccessControlDTO> data = service.searchByLocation(location);
        return ResponseEntity.ok(ApiResponse.success("Search by location successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<AccessControlDTO>>> searchByStatus(@PathVariable String status) {
        List<AccessControlDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<AccessControlDTO>>> searchByKeyword(@PathVariable String keyword) {
        List<AccessControlDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
