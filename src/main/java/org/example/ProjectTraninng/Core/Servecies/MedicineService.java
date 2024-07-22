package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.MedicineRequest;
import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.example.ProjectTraninng.Common.Response.MedicineResponse;
import org.example.ProjectTraninng.Core.Repsitory.MedicineRepository;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitory.WarehouseStoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService  {
    private final MedicineRepository medicineRepository;
    private final WarehouseStoreRepository warehouseStoreRepository;

    public MedicineResponse addMedicine(MedicineRequest request) throws UserNotFoundException {
       medicineRepository.findByName(request.getName()).orElseThrow(
                () -> new UserNotFoundException("Medicine already exists"));
        Medicine medicine = Medicine.builder()
                .name(request.getName())
                .buyPrice(request.getBuyPrice())
                .purchasePrice(request.getPurchasePrice())
                .expirationDate(request.getExpirationDate())
                .build();
        medicineRepository.save(medicine);
        return MedicineResponse.builder().message("Medicine added successfully").build();
    }

    public MedicineResponse updateMedicine(MedicineRequest request , String name) throws UserNotFoundException {
        var medicineOptional = medicineRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));

        Medicine medicine = medicineOptional;
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

    public MedicineRequest getMedicine(String name) throws UserNotFoundException {
        var medicineOptional = medicineRepository.findByName(name).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));;

        Medicine medicine = medicineOptional;
        return MedicineRequest.builder()
                .name(medicine.getName())
                .buyPrice(medicine.getBuyPrice())
                .purchasePrice(medicine.getPurchasePrice())
                .expirationDate(medicine.getExpirationDate())
                .build();
    }

    public List<MedicineRequest> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        List<MedicineRequest> medicineRequests = new ArrayList<>();
        for (Medicine medicine : medicines) {
            medicineRequests.add(MedicineRequest.builder()
                    .name(medicine.getName())
                    .buyPrice(medicine.getBuyPrice())
                    .purchasePrice(medicine.getPurchasePrice())
                    .expirationDate(medicine.getExpirationDate())
                    .build());
        }
        return medicineRequests;
    }
}
