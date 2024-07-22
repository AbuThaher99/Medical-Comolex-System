package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.WarehouseStoreRequest;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.example.ProjectTraninng.Common.Response.WarehouseStoreResponse;
import org.example.ProjectTraninng.Core.Repsitory.MedicineRepository;
import org.example.ProjectTraninng.Core.Repsitory.WarehouseStoreRepository;
import org.example.ProjectTraninng.Common.DTO.QuantityRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseStoreService {
    private final WarehouseStoreRepository warehouseStoreRepository;
    private final MedicineRepository medicineRepository;

    public WarehouseStoreResponse addMedicineToWarehouse(WarehouseStoreRequest warehouseStoreRequest) {
        if (!medicineRepository.existsById(warehouseStoreRequest.getMedicineId())) {
            return WarehouseStoreResponse.builder()
                    .message("Medicine not found")
                    .build();
        }
        if (warehouseStoreRepository.existsByMedicineId(warehouseStoreRequest.getMedicineId())) {
            return WarehouseStoreResponse.builder()
                    .message("Medicine already exists in the warehouse store")
                    .build();
        }
        WarehouseStore warehouseStore = WarehouseStore.builder()
                .medicineId(warehouseStoreRequest.getMedicineId())
                .quantity(warehouseStoreRequest.getQuantity())
                .build();
        warehouseStoreRepository.save(warehouseStore);
        return WarehouseStoreResponse.builder()
                .message("Medicine added successfully")
                .build();
    }

    public WarehouseStoreResponse updateMedicineQuantity(QuantityRequest quantityRequest , Long medicineId) {
        if (!warehouseStoreRepository.existsByMedicineId(medicineId)) {
            return WarehouseStoreResponse.builder()
                    .message("Medicine not found in the warehouse store")
                    .build();
        }
        WarehouseStore warehouseStore = warehouseStoreRepository.findByMedicineId(medicineId);
        warehouseStore.setQuantity(warehouseStore.getQuantity() + quantityRequest.getQuantity());
        warehouseStoreRepository.save(warehouseStore);
        return WarehouseStoreResponse.builder()
                .message("Medicine quantity updated successfully")
                .build();
    }


}
