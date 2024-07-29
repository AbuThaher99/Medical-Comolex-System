package org.example.ProjectTraninng.Core.Servecies;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.LoginDTO;
import org.example.ProjectTraninng.Common.Entities.Token;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.example.ProjectTraninng.Core.Repsitories.DepartmentRepsitory;
import org.example.ProjectTraninng.Core.Repsitories.TokenRepository;
import org.example.ProjectTraninng.Common.Enums.TokenType;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Core.Repsitories.UserRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.config.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DepartmentRepsitory departmentRepsitory;

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
    public AuthenticationResponse UpdateUser(User userRequest ,Long id) throws UserNotFoundException {
        var user = repository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found") );
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("User updated successfully")
                .build();
    }
    @Transactional
    public AuthenticationResponse DeleteUser(Long id) throws UserNotFoundException {
        var user = repository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found"));
        user.setDeleted(true);
        departmentRepsitory.setHeadIdToNull(id);
        departmentRepsitory.setSecretaryIdToNull(id);
        repository.save(user);
        return AuthenticationResponse.builder()
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
        return repository.findAllByRole(role, PageRequest.of(page, size));
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




    public String uploadImageToFileSystem(MultipartFile file,Long userId) throws IOException, UserNotFoundException {
       User user = repository.findById(userId).orElseThrow(()->new UserNotFoundException("User not found"));
        String imageFolder = "C:\\Users\\AbuThaher\\Desktop\\Traning Project\\ProjectTraninng\\src\\main\\resources\\Images\\";
        String filePath=imageFolder+file.getOriginalFilename();
        user.setImage(filePath);
        repository.save(user);
        file.transferTo(new File(filePath));
        return filePath;
    }

}
