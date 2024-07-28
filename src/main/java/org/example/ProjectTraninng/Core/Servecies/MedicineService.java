package org.example.ProjectTraninng.Core.Servecies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.example.ProjectTraninng.Common.Responses.MedicineResponse;
import org.example.ProjectTraninng.Core.Repsitories.MedicineRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitories.WarehouseStoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService  {
    private final MedicineRepository medicineRepository;
    private final WarehouseStoreRepository warehouseStoreRepository;

    @Transactional
    public MedicineResponse addMedicine(Medicine request) throws UserNotFoundException {
        boolean exists = medicineRepository.findByName(request.getName()).isPresent();
        if (exists) {
            throw new UserNotFoundException("Medicine already exists");
        }
        Medicine medicine = Medicine.builder()
                .name(request.getName())
                .buyPrice(request.getBuyPrice())
                .purchasePrice(request.getPurchasePrice())
                .expirationDate(request.getExpirationDate())
                .build();
        medicineRepository.save(medicine);
        return MedicineResponse.builder().message("Medicine added successfully").build();
    }
    @Transactional
    public MedicineResponse updateMedicine(Medicine request , String name) throws UserNotFoundException {

        var medicineOptional = medicineRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));

        Medicine medicine = medicineOptional;
        medicine.setName(request.getName());
        medicine.setBuyPrice(request.getBuyPrice());
        medicine.setPurchasePrice(request.getPurchasePrice());
        medicine.setExpirationDate(request.getExpirationDate());
        medicineRepository.save(medicine);
        return MedicineResponse.builder().message("Medicine updated successfully").build();
    }

    @Transactional
    public MedicineResponse deleteMedicine(String name) throws UserNotFoundException {
        var medicineOptional = medicineRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));
        Medicine medicine = medicineOptional;
        medicine.setDeleted(true);
        medicineRepository.save(medicine);

        WarehouseStore warehouseStores = warehouseStoreRepository.findByMedicineId(medicine.getId());
        if(warehouseStores == null){
            return MedicineResponse.builder().message("Medicine deleted successfully").build();
        }
        warehouseStores.setDeleted(true);
        warehouseStoreRepository.save(warehouseStores);

        return MedicineResponse.builder().message("Medicine deleted successfully").build();
    }

    @Transactional
    public Medicine getMedicine(String name) throws UserNotFoundException {

        var medicineOptional = medicineRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));;
        return medicineOptional;
    }
    @Transactional
    public Page<Medicine> getAllMedicines(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return medicineRepository.findAll(pageable);
    }
}
