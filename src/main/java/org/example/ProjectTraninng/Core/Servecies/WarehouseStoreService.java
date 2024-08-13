package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Repsitories.MedicineRepository;
import org.example.ProjectTraninng.Core.Repsitories.WarehouseStoreRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WarehouseStoreService {
    private final WarehouseStoreRepository warehouseStoreRepository;
    private final MedicineRepository medicineRepository;
    @Transactional
    public GeneralResponse addMedicineToWarehouse(WarehouseStore warehouseStoreRequest) throws UserNotFoundException {
        medicineRepository.findById(warehouseStoreRequest.getMedicine().getId())
                .orElseThrow(() -> new UserNotFoundException("Medicine not found"));
        if (warehouseStoreRepository.existsByMedicineId(warehouseStoreRequest.getMedicine().getId())) {
           throw new UserNotFoundException("Medicine already exists in the warehouse store");
        }
        WarehouseStore warehouseStore = WarehouseStore.builder()
                .medicine(warehouseStoreRequest.getMedicine())
                .quantity(warehouseStoreRequest.getQuantity())
                .build();
        warehouseStoreRepository.save(warehouseStore);
        return GeneralResponse.builder()
                .message("Medicine added successfully")
                .build();
    }
    @Transactional
    public GeneralResponse updateMedicineQuantity(WarehouseStore quantityRequest , Long medicineId) {
        if (!warehouseStoreRepository.existsByMedicineId(medicineId)) {
            return GeneralResponse.builder()
                    .message("Medicine not found in the warehouse store")
                    .build();
        }
        WarehouseStore warehouseStore = warehouseStoreRepository.findByMedicineId(medicineId);
        warehouseStore.setQuantity(warehouseStore.getQuantity() + quantityRequest.getQuantity());
        warehouseStoreRepository.save(warehouseStore);
        return GeneralResponse.builder()
                .message("Medicine quantity updated successfully")
                .build();
    }
    @Transactional
    public PaginationDTO<WarehouseStore> getWarehouseStore(int page, int size) {
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<WarehouseStore> warehouseStores = warehouseStoreRepository.findAll(pageable);
        PaginationDTO<WarehouseStore> paginationDTO = new PaginationDTO<>();
        paginationDTO.setTotalElements(warehouseStores.getTotalElements());
        paginationDTO.setTotalPages(warehouseStores.getTotalPages());
        paginationDTO.setSize(warehouseStores.getSize());
        paginationDTO.setNumber(warehouseStores.getNumber() + 1);
        paginationDTO.setNumberOfElements(warehouseStores.getNumberOfElements());
        paginationDTO.setContent(warehouseStores.getContent());
        return paginationDTO;

    }


}
