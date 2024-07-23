package org.example.ProjectTraninng.Core.Servecies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.DoctorDTO;
import org.example.ProjectTraninng.Common.DTO.DoctorRegisterRequest;
import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Enums.TokenType;
import org.example.ProjectTraninng.Core.Repsitory.DoctorRepository;
import org.example.ProjectTraninng.Core.Repsitory.UserRepository;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitory.TokenRepository;
import org.example.ProjectTraninng.config.JwtService;
import org.example.ProjectTraninng.Common.Response.AuthenticationResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final DoctorRepository doctorRepository;

    public AuthenticationResponse addDoctor(DoctorRegisterRequest request) throws UserNotFoundException {
        String salaryJson = convertSalaryToJson(request.getSalary());

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.DOCTOR)
                .address(request.getAddress())
                .phone(request.getPhone())
                .dateOfBirth(request.getDateOfBirth())
                .salary(salaryJson)
                .build();

        var savedUser = userRepository.save(user);
        Doctor doctor = Doctor.builder()
                .user(savedUser)
                .specialization(request.getSpecialization())
                .beginTime(request.getBeginTime())
                .endTime(request.getEndTime())
                .build();

        doctorRepository.save(doctor);

        var jwtToken = jwtService.generateToken(savedUser);
        var refreshToken = jwtService.generateRefreshToken(savedUser);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void updateDoctor(DoctorRegisterRequest request, Long doctorId) throws UserNotFoundException {
        var doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found"));

        var user = doctor.getUser();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setSalary(convertSalaryToJson(request.getSalary()));
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

    public DoctorDTO findDoctorByEmail(String email) throws UserNotFoundException {
        Doctor doctor = doctorRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Doctor not found with Email: " + email));
        String salaryJson = doctor.getUser().getSalary();
        Map<String, Object> salary = convertJsonToSalary(salaryJson);

        return DoctorDTO.builder()
                .specialization(doctor.getSpecialization())
                .beginTime(doctor.getBeginTime())
                .endTime(doctor.getEndTime())
                .firstName(doctor.getUser().getFirstName())
                .lastName(doctor.getUser().getLastName())
                .dateOfBirth(doctor.getUser().getDateOfBirth())
                .address(doctor.getUser().getAddress())
                .phone(doctor.getUser().getPhone())
                .email(doctor.getUser().getEmail())
                .salary(salary) // Add salary as Map
                .build();
    }

    private String convertSalaryToJson(Map<String, Object> salaryDetails) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(salaryDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing salary JSON", e);
        }
    }

    private Map<String, Object> convertJsonToSalary(String salaryJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(salaryJson, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting salary JSON", e);
        }
    }

    // make a getAllDoctors method

    public List<DoctorRegisterRequest> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorRegisterRequest> doctorRequests = new ArrayList<>();

        for (Doctor doctor : doctors) {
            doctorRequests.add(DoctorRegisterRequest.builder()
                    .firstName(doctor.getUser().getFirstName())
                    .lastName(doctor.getUser().getLastName())
                    .email(doctor.getUser().getEmail())
                    .password(doctor.getUser().getPassword())
                    .address(doctor.getUser().getAddress())
                    .phone(doctor.getUser().getPhone())
                    .dateOfBirth(doctor.getUser().getDateOfBirth())
                    .salary(convertJsonToSalary(doctor.getUser().getSalary()))
                    .specialization(doctor.getSpecialization())
                    .beginTime(doctor.getBeginTime())
                    .endTime(doctor.getEndTime())
                    .build());
        }

        return doctorRequests;
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
