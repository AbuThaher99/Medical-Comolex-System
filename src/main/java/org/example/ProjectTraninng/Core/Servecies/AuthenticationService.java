package org.example.ProjectTraninng.Core.Servecies;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.LoginDTO;
import org.example.ProjectTraninng.Common.Entities.Email;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Entities.Token;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Repsitories.*;
import org.example.ProjectTraninng.Common.Enums.TokenType;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.WebApi.config.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DepartmentRepsitory departmentRepsitory;
    private final EmailService emailService;
    private final EmailRepository emailRepository;

    @Transactional
    public AuthenticationResponse adduser(User user ) throws UserNotFoundException, IOException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setDeleted(false);
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("User " +user.getRole()+ " added successfully")
                .build();
    }
    @Transactional
    public GeneralResponse UpdateUser(User userRequest ,Long id) throws UserNotFoundException {
        var user = repository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found") );
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        repository.save(user);
        return GeneralResponse.builder()
                .message("User updated successfully")
                .build();
    }
    @Transactional
    public GeneralResponse DeleteUser(Long id) throws UserNotFoundException {
        var user = repository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found"));
        user.setDeleted(true);
        departmentRepsitory.setHeadIdToNull(id);
        departmentRepsitory.setSecretaryIdToNull(id);
        repository.save(user);
        return GeneralResponse.builder()
                .message("User deleted successfully")
                 .build();
    }
    @Transactional
    public User GetUser(Long id) throws UserNotFoundException {
        var user = repository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found") );
        if(user.isDeleted()){
            throw new UserNotFoundException("User not found");
        }

        return user;
    }
    @Transactional
    public Page<User> GetAllUsers( int page, int size ,String search ,Role role) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest,search,role);
    }
    @Transactional
    public Page<User> getAllUsersByRole(Role role, int page, int size) {
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findAllByRole(role, pageable);
    }
    @Transactional
    public AuthenticationResponse authenticate(LoginDTO request) throws UserNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                );
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("User LoggedIn successfully")
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }



    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
    public User extractUserFromToken(String token) {
        String username = jwtService.extractUsername(token);
        return repository.findByEmail(username).orElse(null);
    }


    @Transactional
    public AuthenticationResponse resetPassword(String email, String password) throws UserNotFoundException {
        var user = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(password));
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("Password reset successfully")
                .build();
    }

    @Transactional
    public void sendVerificationCode(String email) throws UserNotFoundException, MessagingException {
        var userEmail = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String verificationCode = UUID.randomUUID().toString();
        Email emailEntity = Email.builder()
                .email(email)
                .verificationCode(verificationCode)
                .verified(false)
                .build();
        emailRepository.save(emailEntity);
        String verificationUrl = "http://localhost:8080/Login/resetPassword?verificationCode=" + verificationCode + "&email=" + email;
        emailService.sendVerificationEmail(email, "Email Verification", verificationUrl);
    }

    @Transactional
    public AuthenticationResponse verifyCodeAndResetPassword(String email, String verificationCode, String newPassword) throws UserNotFoundException {
        Email emailEntity = emailRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email not found"));
        if(emailEntity.getVerificationCode().equals(verificationCode)) {
            emailEntity.setVerified(true);
            emailRepository.save(emailEntity);
        }else{
            throw new UserNotFoundException("Invalid verification code ");
        }
           return resetPassword(email, newPassword);
    }


    @Transactional
    public AuthenticationResponse ChangePassword(String email, String oldPassword, String newPassword) throws UserNotFoundException {
        var user = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            var savedUser = repository.save(user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(savedUser, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .message("Password changed successfully")
                    .build();
        } else {
            throw new UserNotFoundException("Invalid old password");
        }
    }
}
