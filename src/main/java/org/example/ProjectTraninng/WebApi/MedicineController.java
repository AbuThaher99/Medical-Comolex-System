package org.example.ProjectTraninng.WebApi;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.MedicineRequest;
import org.example.ProjectTraninng.Common.Response.MedicineResponse;
import org.example.ProjectTraninng.Core.Servecies.MedicineService;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @PostMapping("/addMedicine")
    public ResponseEntity<MedicineResponse> addMedicine(@RequestBody MedicineRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(medicineService.addMedicine(request));
    }

    @PutMapping("/updateMedicine/{medicineName}")
    public ResponseEntity<MedicineResponse> updateMedicine(@RequestBody MedicineRequest request, @PathVariable String medicineName) throws UserNotFoundException {
        return ResponseEntity.ok(medicineService.updateMedicine(request, medicineName));
    }

    @DeleteMapping("/deleteMedicine/{medicineName}")
    public ResponseEntity<MedicineResponse> deleteMedicine(@PathVariable String medicineName) throws UserNotFoundException {
        return ResponseEntity.ok(medicineService.deleteMedicine(medicineName));
    }

    @GetMapping("/getMedicine/{medicineName}")
    public ResponseEntity<MedicineRequest> getMedicine(@PathVariable String medicineName) throws UserNotFoundException {
        return ResponseEntity.ok(medicineService.getMedicine(medicineName));
    }

    @GetMapping("/getAllMedicines")
    public ResponseEntity<Iterable<MedicineRequest>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }
}
