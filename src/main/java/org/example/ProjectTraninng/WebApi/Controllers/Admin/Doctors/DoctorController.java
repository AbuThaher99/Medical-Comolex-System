package org.example.ProjectTraninng.WebApi.Controllers.Admin.Doctors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Specialization;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.DoctorService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/admin/doctors")
@RequiredArgsConstructor
public class DoctorController extends SessionManagement {
    private final DoctorService doctorService;
    private final AuthenticationService service;
    @PostMapping("/")
    public ResponseEntity<AuthenticationResponse> addDoctor(@RequestBody @Valid Doctor request , HttpServletRequest httpServletRequest) throws UserNotFoundException {
       String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return ResponseEntity.ok(doctorService.addDoctor(request));
    }

    @PutMapping ("/{doctorId}")
    public ResponseEntity<GeneralResponse> updateDoctor(@PathVariable Long doctorId,  @RequestBody @Valid Doctor request, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        doctorService.updateDoctor(request,  doctorId);
        return ResponseEntity.ok(GeneralResponse.builder().message("Doctor updated successfully").build());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable String email, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);

        Doctor doctor = doctorService.findDoctorByEmail(email);
       // System.out.println(doctor);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("")
    public PaginationDTO<Doctor> getAllDoctors(@RequestParam(defaultValue = "1") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "",required = false) String search ,
                                               @RequestParam(defaultValue = "",required = false) Specialization specialization,
                                               HttpServletRequest httpServletRequest) throws UserNotFoundException {
       String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return doctorService.getAllDoctors(page, size, search, specialization);
    }


}
