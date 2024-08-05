package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Supplier;
import org.example.ProjectTraninng.Common.Enums.CompanyNames;
import org.example.ProjectTraninng.Common.Responses.SupplierResponse;
import org.example.ProjectTraninng.Core.Repsitories.SupplierRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierResponse addSupplier(Supplier request) {
        Supplier supplier = Supplier.builder()
                .name(request.getName())
                .companyName(request.getCompanyName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();
        supplierRepository.save(supplier);
        return SupplierResponse.builder().message("Supplier added successfully").build();
    }

    public SupplierResponse updateSupplier(Supplier request, Long id) throws UserNotFoundException {
        var supplierOptional = supplierRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Supplier not found"));

        Supplier supplier = supplierOptional;
        supplier.setName(request.getName());
        supplier.setCompanyName(request.getCompanyName());
        supplier.setPhone(request.getPhone());
        supplier.setAddress(request.getAddress());
        supplierRepository.save(supplier);
        return SupplierResponse.builder().message("Supplier updated successfully").build();
    }


    public SupplierResponse deleteSupplier(Long id) throws UserNotFoundException {
        var supplierOptional = supplierRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Supplier not found"));
        Supplier supplier = supplierOptional;
        supplier.setDeleted(true);
        supplierRepository.save(supplier);
        return SupplierResponse.builder().message("Supplier deleted successfully").build();
    }

    public Supplier getSupplier(Long id) throws UserNotFoundException {
        var supplierOptional = supplierRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Supplier not found"));
        Supplier supplier = supplierOptional;
        return supplier;
    }

    public Page<Supplier> getAllSuppliers(int page, int size, String search, CompanyNames companyName) {
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        return supplierRepository.findAll(pageable , search , companyName);
    }
}
