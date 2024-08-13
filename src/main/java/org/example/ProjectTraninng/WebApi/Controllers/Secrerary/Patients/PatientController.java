package org.example.ProjectTraninng.WebApi.Controllers.Secrerary.Patients;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.PatientService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("secretary/patient")
@RequiredArgsConstructor
public class PatientController extends SessionManagement {

    private final PatientService patientService;
    private final AuthenticationService service;


    @GetMapping("/{email}")
    public ResponseEntity<?> getPatient(@PathVariable String email, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        Optional<Patients> patientRequest = patientService.getPatient(email);
        if (patientRequest.isPresent()) {
            return ResponseEntity.ok(patientRequest.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
    }
    @DeleteMapping("/{email}")
        public ResponseEntity<?> deletePatientByEmail(@PathVariable String email, HttpServletRequest httpServletRequest) throws UserNotFoundException {
            String token = service.extractToken(httpServletRequest);
            User user = service.extractUserFromToken(token);
            validateLoggedInSecretary(user);
        GeneralResponse isDeleted = patientService.deletePatientByFirstName(email);
            return ResponseEntity.ok(isDeleted);
        }

    @GetMapping("")
    public PaginationDTO<Patients> getAllPatients(@RequestParam(defaultValue = "1",required = false) int page,
                                                  @RequestParam(defaultValue = "10",required = false) int size,
                                                  @RequestParam(defaultValue = "",required = false) String search ,
                                                  @RequestParam(defaultValue = "",required = false) List<Long> doctorIds ,
                                                  HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        if (doctorIds != null && doctorIds.isEmpty()) {
            doctorIds = null;
        }
        return patientService.getAllPatients(page, size , search , doctorIds);
    }

}
