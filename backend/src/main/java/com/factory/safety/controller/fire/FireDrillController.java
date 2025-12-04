package com.factory.safety.controller.fire;

import com.factory.safety.common.ApiResponse;
import com.factory.safety.dto.fire.FireDrillDTO;
import com.factory.safety.service.fire.FireDrillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/fire/fire-drill")
@RequiredArgsConstructor
public class FireDrillController {
    private final FireDrillService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<FireDrillDTO>>> getAllFireDrills() {
        List<FireDrillDTO> data = service.getAllFireDrills();
        return ResponseEntity.ok(ApiResponse.success("Get all fire drills successfully", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FireDrillDTO>> getFireDrillById(@PathVariable Long id) {
        FireDrillDTO data = service.getFireDrillById(id);
        return ResponseEntity.ok(ApiResponse.success("Get fire drill successfully", data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FireDrillDTO>> createFireDrill(
            @RequestBody FireDrillDTO dto) {
        FireDrillDTO data = service.createFireDrill(dto);
        return ResponseEntity.ok(ApiResponse.success("Create fire drill successfully", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FireDrillDTO>> updateFireDrill(
            @PathVariable Long id,
            @RequestBody FireDrillDTO dto) {
        FireDrillDTO data = service.updateFireDrill(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Update fire drill successfully", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteFireDrill(@PathVariable Long id) {
        service.deleteFireDrill(id);
        return ResponseEntity.ok(ApiResponse.success("Delete fire drill successfully", null));
    }

    @GetMapping("/search/status/{status}")
    public ResponseEntity<ApiResponse<List<FireDrillDTO>>> searchByStatus(
            @PathVariable String status) {
        List<FireDrillDTO> data = service.searchByStatus(status);
        return ResponseEntity.ok(ApiResponse.success("Search by status successfully", data));
    }

    @GetMapping("/search/type/{drillType}")
    public ResponseEntity<ApiResponse<List<FireDrillDTO>>> searchByDrillType(
            @PathVariable String drillType) {
        List<FireDrillDTO> data = service.searchByDrillType(drillType);
        return ResponseEntity.ok(ApiResponse.success("Search by drill type successfully", data));
    }

    @GetMapping("/search/keyword/{keyword}")
    public ResponseEntity<ApiResponse<List<FireDrillDTO>>> searchByKeyword(
            @PathVariable String keyword) {
        List<FireDrillDTO> data = service.searchByKeyword(keyword);
        return ResponseEntity.ok(ApiResponse.success("Search by keyword successfully", data));
    }
}
