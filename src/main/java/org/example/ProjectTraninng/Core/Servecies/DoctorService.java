package org.example.ProjectTraninng.Core.Servecies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Enums.TokenType;
import org.example.ProjectTraninng.Core.Repsitories.DoctorRepository;
import org.example.ProjectTraninng.Core.Repsitories.UserRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitories.TokenRepository;
import org.example.ProjectTraninng.config.JwtService;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final DoctorRepository doctorRepository;
    @Transactional
    public AuthenticationResponse addDoctor(Doctor request) throws UserNotFoundException {
        User user = request.getUser();
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElse(null);
        if (existingUser != null) {
            if (existingUser.getDoctor() != null) {
                throw new UserNotFoundException("User already has an associated doctor");
            } else {
                user = existingUser;
            }
        } else {
            user.setRole(Role.DOCTOR);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userRepository.save(user);
        }

        Doctor doctor = Doctor.builder()
                .user(user)
                .specialization(request.getSpecialization())
                .beginTime(request.getBeginTime())
                .endTime(request.getEndTime())
                .build();

        doctorRepository.save(doctor);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("Doctor added successfully")
                .build();
    }


    public void updateDoctor(Doctor request, Long doctorId) throws UserNotFoundException {
        var doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found"));

        var user = doctor.getUser();
        user.setRole(Role.DOCTOR);
        user.setFirstName(request.getUser().getFirstName());
        user.setLastName(request.getUser().getLastName());
        user.setAddress(request.getUser().getAddress());
        user.setDateOfBirth(request.getUser().getDateOfBirth());
        user.setPhone(request.getUser().getPhone());
        user.setEmail(request.getUser().getEmail());
        user.getSalary().putAll(request.getUser().getSalary());

        userRepository.save(user);

        doctor.setSpecialization(request.getSpecialization());
        doctor.setBeginTime(request.getBeginTime());
        doctor.setEndTime(request.getEndTime());
        doctorRepository.save(doctor);
    }

    @Transactional
    public void deleteDoctor(Long doctorId) throws UserNotFoundException {
        var doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found"));
        var user = doctor.getUser();
        tokenRepository.deleteAllByUser(user);
        doctorRepository.deleteById(doctorId);
        userRepository.deleteById(user.getId());
    }

    public Doctor findDoctorByEmail(String email) throws UserNotFoundException {
        Doctor doctor = doctorRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found with email: " + email));
        return doctor;

    }
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
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
}
