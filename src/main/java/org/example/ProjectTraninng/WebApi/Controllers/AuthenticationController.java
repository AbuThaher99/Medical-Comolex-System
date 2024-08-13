package org.example.ProjectTraninng.WebApi.Controllers;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.LoginDTO;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.LogoutService;
import org.example.ProjectTraninng.Core.Servecies.PatientService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final PatientService patientService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> Login(@RequestBody @Valid LoginDTO request) throws UserNotFoundException {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    @PostMapping("/send-verification-code")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) throws UserNotFoundException, MessagingException, MessagingException {
        service.sendVerificationCode(email);
        return ResponseEntity.ok("Verification code sent to email");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<AuthenticationResponse> verifyCodeAndResetPassword(@RequestParam String email,
                                                                             @RequestParam String verificationCode,
                                                                             @RequestParam String newPassword
    ) throws UserNotFoundException {
        AuthenticationResponse response = service.verifyCodeAndResetPassword(
                email, verificationCode, newPassword);
        return ResponseEntity.ok(response);
    }


    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> RegisterPatient(@RequestBody @Valid Patients request , HttpServletRequest httpServletRequest) throws UserNotFoundException {
        return new ResponseEntity<>(patientService.addPatient(request), HttpStatus.CREATED);
    }

}
