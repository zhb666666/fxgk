package com.factory.safety.controller.campus;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.entity.campus.AccessControl;
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
    public ResponseEntity<ApiResponse<List<AccessControl>>> getAllAccessControl() {
        List<AccessControl> data = service.getAllAccessControl();
        return ResponseEntity.ok(ApiResponse.success("Get all access control records successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccessControl>> getAccessControlById(@PathVariable Long id) {
        AccessControl data = service.getAccessControlById(id);
        return ResponseEntity.ok(ApiResponse.success("Get access control record successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccessControl>> createAccessControl(@RequestBody AccessControl accessControl) {
        AccessControl data = service.createAccessControl(accessControl);
        return ResponseEntity.ok(ApiResponse.success("Create access control record successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AccessControl>> updateAccessControl(
            @PathVariable Long id,
            @RequestBody AccessControl accessControl) {
        AccessControl data = service.updateAccessControl(id, accessControl);
        return ResponseEntity.ok(ApiResponse.success("Update access control record successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteAccessControl(@PathVariable Long id) {
        service.deleteAccessControl(id);
        return ResponseEntity.ok(ApiResponse.success("Delete access control record successfully", null));
    }

    @GetMapping("/search/location/{location}")
    public ResponseEntity<ApiResponse<List<AccessControl>>> searchByLocation(@PathVariable String location) {
        List<AccessControl> data = service.searchByLocation(location);
        return ResponseEntity.ok(ApiResponse.success("Search by location successfully", data));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<AccessControl>>> searchByStatus(@PathVariable String status) {
        List<AccessControl> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<AccessControl>>> searchByKeyword(@PathVariable String keyword) {
        List<AccessControl> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
