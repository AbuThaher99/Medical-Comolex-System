package org.example.ProjectTraninng.WebApi.Controllers.AdminMedicine;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Supplier;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.CompanyNames;
import org.example.ProjectTraninng.Common.Responses.SupplierResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.SupplierService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/supplier")
public class SupplierController extends SessionManagement {
    private final SupplierService supplierService;
    private final AuthenticationService service;

    @PostMapping("/")
    public ResponseEntity<SupplierResponse> addSupplier(@RequestBody @Valid Supplier supplier , HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return ResponseEntity.ok(supplierService.addSupplier(supplier));
    }

    @PutMapping("/")
    public ResponseEntity<SupplierResponse> updateSupplier(@RequestBody Supplier supplier ,Long id , HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return ResponseEntity.ok(supplierService.updateSupplier(supplier,id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Long id , HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return ResponseEntity.ok(supplierService.getSupplier(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierResponse> deleteSupplier(@PathVariable Long id , HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }

    @GetMapping("")
    public Page<Supplier> getAllSuppliers(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(required = false) String search ,
                                          @RequestParam(required = false) CompanyNames companyName ,
                                          HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInWarehouseEmployee(user);
        return supplierService.getAllSuppliers( page, size, search, companyName);
    }

}
