package org.example.ProjectTraninng.Core.Servecies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Repsitories.MedicineRepository;
import org.example.ProjectTraninng.Core.Repsitories.SupplierRepository;
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
    public GeneralResponse addMedicine(Medicine request) throws UserNotFoundException {
        boolean exists = medicineRepository.findByNameAndSupplierId(request.getName(), request.getSupplier().getId()).isPresent();
        if (exists) {
            throw new UserNotFoundException("Medicine with the same name and supplier already exists");
        }
        Medicine medicine = Medicine.builder()
                .name(request.getName())
                .buyPrice(request.getBuyPrice())
                .purchasePrice(request.getPurchasePrice())
                .expirationDate(request.getExpirationDate())
                .supplier(request.getSupplier())
                .build();
        medicineRepository.save(medicine);
        return GeneralResponse.builder().message("Medicine added successfully").build();
    }
    @Transactional
    public GeneralResponse updateMedicine(Medicine request , Long id) throws UserNotFoundException {

        var medicineOptional = medicineRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));

        Medicine medicine = medicineOptional;
        medicine.setName(request.getName());
        medicine.setBuyPrice(request.getBuyPrice());
        medicine.setPurchasePrice(request.getPurchasePrice());
        medicine.setExpirationDate(request.getExpirationDate());
        medicineRepository.save(medicine);
        return GeneralResponse.builder().message("Medicine updated successfully").build();
    }

    @Transactional
    public GeneralResponse deleteMedicine(Long id) throws UserNotFoundException {
        var medicineOptional = medicineRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));
        Medicine medicine = medicineOptional;
        medicine.setDeleted(true);
        medicineRepository.save(medicine);

        WarehouseStore warehouseStores = warehouseStoreRepository.findByMedicineId(medicine.getId());
        if(warehouseStores == null){
            return GeneralResponse.builder().message("Medicine deleted successfully").build();
        }
        warehouseStores.setDeleted(true);
        warehouseStoreRepository.save(warehouseStores);

        return GeneralResponse.builder().message("Medicine deleted successfully").build();
    }

    @Transactional
    public Medicine getMedicine(Long id) throws UserNotFoundException {

        var medicineOptional = medicineRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Medicine not found"));;
        return medicineOptional;
    }
    @Transactional
    public PaginationDTO<Medicine> getAllMedicines(int page, int size , String search) {
        if(search!=null && search.isEmpty()){
            search = null;
        }
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Medicine> medicines = medicineRepository.findAll(pageable , search);
        PaginationDTO<Medicine> paginationDTO = new PaginationDTO<>();
        paginationDTO.setTotalElements(medicines.getTotalElements());
        paginationDTO.setTotalPages(medicines.getTotalPages());
        paginationDTO.setSize(medicines.getSize());
        paginationDTO.setNumber(medicines.getNumber() + 1);
        paginationDTO.setNumberOfElements(medicines.getNumberOfElements());
        paginationDTO.setContent(medicines.getContent());
        return paginationDTO;
    }
}
