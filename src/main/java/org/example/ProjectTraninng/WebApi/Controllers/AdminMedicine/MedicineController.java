package org.example.ProjectTraninng.WebApi.Controllers.AdminMedicine;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Common.Responses.MedicineResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.MedicineService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Entities.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/medicine")
public class MedicineController extends SessionManagement {
    private final MedicineService medicineService;
    private final AuthenticationService service;

    @PostMapping("/")
    public ResponseEntity<MedicineResponse> addMedicine(@RequestBody  Medicine request, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return ResponseEntity.ok(medicineService.addMedicine(request));
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity<MedicineResponse> updateMedicine(@RequestBody @Valid Medicine request, @PathVariable  Long medicineId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return ResponseEntity.ok(medicineService.updateMedicine(request, medicineId));
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<MedicineResponse> deleteMedicine(@PathVariable  Long medicineId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
       String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return ResponseEntity.ok(medicineService.deleteMedicine(medicineId));
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<Medicine> getMedicine(@PathVariable Long medicineId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return ResponseEntity.ok(medicineService.getMedicine(medicineId));
    }

    @GetMapping("")
    public Page<Medicine> getAllMedicines(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size, @RequestParam(required = false ) String search , HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return medicineService.getAllMedicines(page, size,search);
    }

}
