package org.example.ProjectTraninng.WebApi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.example.ProjectTraninng.Common.Responses.WarehouseStoreResponse;
import org.example.ProjectTraninng.Core.Servecies.WarehouseStoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/warehouseStore")
public class WarehouseStoreController {
    private final WarehouseStoreService warehouseStoreService;
    @PostMapping("/addToWarehouse")
    public WarehouseStoreResponse addToWarehouse(@RequestBody @Valid WarehouseStore warehouseStoreRequest) {
        return warehouseStoreService.addMedicineToWarehouse(warehouseStoreRequest);
    }
    @PutMapping("/updateWarehouse/{medicineId}")
    public WarehouseStoreResponse updateWarehouseQuantity(@RequestBody @Valid WarehouseStore quantity , @PathVariable Long medicineId) {
        return warehouseStoreService.updateMedicineQuantity(quantity, medicineId);
    }
}
