package org.example.ProjectTraninng.WebApi;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.QuantityRequest;
import org.example.ProjectTraninng.Common.DTO.WarehouseStoreRequest;
import org.example.ProjectTraninng.Common.Response.WarehouseStoreResponse;
import org.example.ProjectTraninng.Core.Servecies.WarehouseStoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/warehouseStore")
public class WarehouseStoreController {
    private final WarehouseStoreService warehouseStoreService;
    @PostMapping("/addToWarehouse")
    public WarehouseStoreResponse addToWarehouse(@RequestBody WarehouseStoreRequest warehouseStoreRequest) {
        return warehouseStoreService.addMedicineToWarehouse(warehouseStoreRequest);
    }
    @PutMapping("/updateWarehouse/{medicineId}")
    public WarehouseStoreResponse updateWarehouseQuantity(@RequestBody QuantityRequest quantity , @PathVariable Long medicineId) {
        return warehouseStoreService.updateMedicineQuantity(quantity, medicineId);
    }
}
