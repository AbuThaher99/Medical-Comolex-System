package org.example.ProjectTraninng.WebApi;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.DepartmentDTO;
import org.example.ProjectTraninng.Common.DTO.DepartmentRequest;
import org.example.ProjectTraninng.Common.Response.DepartmentResponse;
import org.example.ProjectTraninng.Core.Servecies.DepartmentService;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
@Validated
public class DepartmentController {

    @Autowired
    private final DepartmentService departmentService;

    @PostMapping("/addDepartment")
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody @Valid DepartmentRequest request) throws UserNotFoundException {
       DepartmentResponse res = departmentService.addDepartment(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/updateDepartment/{departmentId}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody  DepartmentRequest request, @PathVariable Long departmentId) throws UserNotFoundException {
        departmentService.updateDepartment(request, departmentId);
        return new ResponseEntity<>(new DepartmentResponse("Department updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteDepartment/{departmentId}")
    public ResponseEntity<DepartmentResponse> deleteDepartment(@PathVariable Long departmentId) throws UserNotFoundException {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(new DepartmentResponse("Department deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/getDepartment/{departmentId}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long departmentId) throws UserNotFoundException {
        DepartmentDTO department = departmentService.findDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<Iterable<DepartmentRequest>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }



}