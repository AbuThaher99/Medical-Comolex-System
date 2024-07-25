package org.example.ProjectTraninng.Core.Servecies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.example.ProjectTraninng.Common.Responses.MedicineResponse;
import org.example.ProjectTraninng.Core.Repsitories.MedicineRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitories.WarehouseStoreRepository;
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

    public MedicineResponse deleteMedicine(String name) throws UserNotFoundException {
        var medicineOptional = medicineRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));

        warehouseStoreRepository.deleteByMedicineId(medicineOptional.getId());
        medicineRepository.delete(medicineOptional);

        return MedicineResponse.builder().message("Medicine deleted successfully").build();
    }

    public Medicine getMedicine(String name) throws UserNotFoundException {

        var medicineOptional = medicineRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));;
        return medicineOptional;
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();

        return medicines;
    }
}
