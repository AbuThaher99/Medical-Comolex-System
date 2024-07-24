package org.example.ProjectTraninng.WebApi;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Department;
import org.example.ProjectTraninng.Common.Responses.DepartmentResponse;
import org.example.ProjectTraninng.Core.Servecies.DepartmentService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private final DepartmentService departmentService;

    @PostMapping("/addDepartment")
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody @Valid Department request) throws UserNotFoundException {
       DepartmentResponse res = departmentService.addDepartment(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/updateDepartment/{departmentId}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody @Valid  Department request, @PathVariable Long departmentId) throws UserNotFoundException {
        departmentService.updateDepartment(request, departmentId);
        return new ResponseEntity<>(new DepartmentResponse("Department updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteDepartment/{departmentId}")
    public ResponseEntity<DepartmentResponse> deleteDepartment(@PathVariable Long departmentId) throws UserNotFoundException {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(new DepartmentResponse("Department deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/getDepartment/{departmentId}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long departmentId) throws UserNotFoundException {
        Department department = departmentService.findDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);

    }

}