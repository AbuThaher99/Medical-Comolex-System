package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.example.ProjectTraninng.Common.Responses.WarehouseStoreResponse;
import org.example.ProjectTraninng.Core.Repsitories.MedicineRepository;
import org.example.ProjectTraninng.Core.Repsitories.WarehouseStoreRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseStoreService {
    private final WarehouseStoreRepository warehouseStoreRepository;
    private final MedicineRepository medicineRepository;

    public WarehouseStoreResponse addMedicineToWarehouse(WarehouseStore warehouseStoreRequest) throws UserNotFoundException {
        if (!medicineRepository.existsById(warehouseStoreRequest.getMedicine().getId())) {
           throw new UserNotFoundException("Medicine not found");
        }
        if (warehouseStoreRepository.existsByMedicineId(warehouseStoreRequest.getMedicine().getId())) {
           throw new UserNotFoundException("Medicine already exists in the warehouse store");
        }
        WarehouseStore warehouseStore = WarehouseStore.builder()
                .medicine(warehouseStoreRequest.getMedicine())
                .quantity(warehouseStoreRequest.getQuantity())
                .build();
        warehouseStoreRepository.save(warehouseStore);
        return WarehouseStoreResponse.builder()
                .message("Medicine added successfully")
                .build();
    }

    public WarehouseStoreResponse updateMedicineQuantity(WarehouseStore quantityRequest , Long medicineId) {
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
