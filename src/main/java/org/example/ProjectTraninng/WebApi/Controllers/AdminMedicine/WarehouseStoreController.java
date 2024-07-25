package org.example.ProjectTraninng.WebApi.Controllers.AdminMedicine;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Entities.WarehouseStore;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.WarehouseStoreResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.WarehouseStoreService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse-store")
public class WarehouseStoreController extends SessionManagement {
    private final WarehouseStoreService warehouseStoreService;
    private final AuthenticationService service;
    @PostMapping("")
    public WarehouseStoreResponse addToWarehouse(@RequestBody @Valid WarehouseStore warehouseStoreRequest, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return warehouseStoreService.addMedicineToWarehouse(warehouseStoreRequest);
    }
    @PutMapping("/{medicineId}")
    public WarehouseStoreResponse updateWarehouseQuantity(@RequestBody @Valid WarehouseStore quantity , @PathVariable Long medicineId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return warehouseStoreService.updateMedicineQuantity(quantity, medicineId);
    }
    @Override
    public void validateLoggedInAdmin(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN && user.getRole() != Role.WAREHOUSE_EMPLOYEE){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }
}
