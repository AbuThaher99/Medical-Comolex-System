package org.example.ProjectTraninng.WebApi.Controllers.Admin.Departments;

import javax.validation.Valid;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Department;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.DepartmentResponse;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.DepartmentService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/departments")
public class DepartmentController extends SessionManagement {

    @Autowired
    private final DepartmentService departmentService;
    private final AuthenticationService service;

    @PostMapping("/")
    public ResponseEntity<DepartmentResponse> addDepartment(@RequestBody @Valid Department request, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
       DepartmentResponse res = departmentService.addDepartment(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@RequestBody @Valid  Department request, @PathVariable Long departmentId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        departmentService.updateDepartment(request, departmentId);
        return new ResponseEntity<>(new DepartmentResponse("Department updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<DepartmentResponse> deleteDepartment(@PathVariable Long departmentId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(new DepartmentResponse("Department deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long departmentId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        Department department = departmentService.findDepartmentById(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping("")
    public Page<Department> getAllDepartments(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return departmentService.getAllDepartment(page, size);

    }


}