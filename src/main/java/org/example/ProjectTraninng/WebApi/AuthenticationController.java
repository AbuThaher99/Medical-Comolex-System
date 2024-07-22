package org.example.ProjectTraninng.WebApi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.ProjectTraninng.Common.DTO.AuthenticationRequest;
import org.example.ProjectTraninng.Common.DTO.RegisterRequest;
import org.example.ProjectTraninng.Common.Response.AuthenticationResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.Common.Entities.Role;
import org.example.ProjectTraninng.Common.Entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController extends SessionManagement {

    private final AuthenticationService service;

    @PostMapping("/adduser")
    public ResponseEntity<AuthenticationResponse> adduser(@RequestBody @Valid RegisterRequest request, HttpServletRequest httpServletRequest) {
        String token = extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return ResponseEntity.ok(service.adduser(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    public void validateLoggedInAdmin(User user){
       if(user.getRole() != Role.ADMIN){
           throw new RuntimeException("You are not authorized to perform this operation");
       }
    }

}
