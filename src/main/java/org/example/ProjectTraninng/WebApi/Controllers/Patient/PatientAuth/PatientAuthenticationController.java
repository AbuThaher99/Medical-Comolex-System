package org.example.ProjectTraninng.WebApi.Controllers.Patient.PatientAuth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.PatientService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/patients/auth")
public class PatientAuthenticationController extends SessionManagement {

    private final PatientService patientService;
    private final AuthenticationService service;

    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> Register(@RequestBody @Valid Patients request , HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAll(user);
        return new ResponseEntity<>(patientService.addPatient(request), HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> updatePatient(@RequestBody @Valid Patients request, @PathVariable String email, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAll(user);
        GeneralResponse d= patientService.updatePatient(request, email);
        return ResponseEntity.ok(d);

    }


}
