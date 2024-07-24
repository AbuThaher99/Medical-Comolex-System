package org.example.ProjectTraninng.WebApi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.example.ProjectTraninng.Common.Responses.MedicineResponse;
import org.example.ProjectTraninng.Core.Servecies.MedicineService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @PostMapping("/addMedicine")
    public ResponseEntity<MedicineResponse> addMedicine(@RequestBody  Medicine request) throws UserNotFoundException {
        return ResponseEntity.ok(medicineService.addMedicine(request));
    }

    @PutMapping("/updateMedicine/{medicineName}")
    public ResponseEntity<MedicineResponse> updateMedicine(@RequestBody @Valid Medicine request, @PathVariable  String medicineName) throws UserNotFoundException {
        return ResponseEntity.ok(medicineService.updateMedicine(request, medicineName));
    }

    @DeleteMapping("/deleteMedicine/{medicineName}")
    public ResponseEntity<MedicineResponse> deleteMedicine(@PathVariable  String medicineName) throws UserNotFoundException {
        return ResponseEntity.ok(medicineService.deleteMedicine(medicineName));
    }

    @GetMapping("/getMedicine/{medicineName}")
    public ResponseEntity<Medicine> getMedicine(@PathVariable String medicineName) throws UserNotFoundException {
        return ResponseEntity.ok(medicineService.getMedicine(medicineName));
    }

    @GetMapping("/getAllMedicines")
    public ResponseEntity<Iterable<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }
}
