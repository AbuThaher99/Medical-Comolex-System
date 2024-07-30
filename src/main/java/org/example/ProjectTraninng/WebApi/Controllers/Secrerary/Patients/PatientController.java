package org.example.ProjectTraninng.WebApi.Controllers.Secrerary.Patients;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Common.Responses.PatientResponse;
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
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController extends SessionManagement {

    private final PatientService patientService;
    private final AuthenticationService service;

    @PostMapping("")
    public ResponseEntity<PatientResponse> addPatient(@RequestBody @Valid Patients request , HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        return ResponseEntity.ok(patientService.addPatient(request));
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<?> getPatient(@PathVariable String firstName, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        Optional<Patients> patientRequest = patientService.getPatient(firstName);
        if (patientRequest.isPresent()) {
            return ResponseEntity.ok(patientRequest.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
    }
    @DeleteMapping("/{firstName}")
        public ResponseEntity<?> deletePatientByEmail(@PathVariable String firstName, HttpServletRequest httpServletRequest) throws UserNotFoundException {
            String token = service.extractToken(httpServletRequest);
            User user = service.extractUserFromToken(token);
            validateLoggedInSecretary(user);
        PatientResponse isDeleted = patientService.deletePatientByFirstName(firstName);
            return ResponseEntity.ok(isDeleted);
        }

    @PutMapping("/{firstName}")
    public ResponseEntity<?> updatePatient(@RequestBody @Valid Patients request, @PathVariable String firstName, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        PatientResponse d= patientService.updatePatient(request, firstName);
          return ResponseEntity.ok(d);

    }

    @GetMapping("")
    public Page<Patients> getAllPatients(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String search , @RequestParam(required = false) List<Long> doctorIds ,HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        return patientService.getAllPatients(page, size , search , doctorIds);
    }


}
