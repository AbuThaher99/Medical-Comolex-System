package org.example.ProjectTraninng.Core.Servecies;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Enums.TokenType;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Repsitories.*;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.WebApi.config.JwtService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientsDeletedRepository patientDeletedRepository;
    private final TreatmentRepository treatmentRepository;
    private final DeletedPatientMedicineRepository deletedPatientMedicineRepository;
    private final TreatmentDeletedRepository treatmentDeletedRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final BloodTypeRepository bloodTypeRepository;
    @Transactional
    public AuthenticationResponse addPatient(Patients request) throws UserNotFoundException {
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
            user.setRole(Role.PATIENT);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setSalary(null);
            user.setImage(null);
            user = userRepository.save(user);
        }
        BloodType bloodType = bloodTypeRepository.findById(request.getBloodType().getId())
                .orElseThrow();

        Patients patient = Patients.builder()
                .user(user)
                .bloodType(bloodType)
                .age(request.getAge())
                .build();
        patientRepository.save(patient);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("Doctor added successfully")
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
            var user = this.userRepository.findByEmail(userEmail)
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


    @Transactional
    public Optional<Patients> getPatient(String email) throws UserNotFoundException {

     Optional<Patients> patients = patientRepository.findByUserEmail(email);
        if (patients.isEmpty()) {
            throw new UserNotFoundException("Patient not found");
        }
        return patients;
    }
    @Transactional
    public GeneralResponse deletePatientByFirstName(String email) throws UserNotFoundException {
        Patients patient = patientRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
        User user = patient.getUser();
        for (Treatment treatment : new ArrayList<>(patient.getTreatments())) {
            TreatmentDeleted treatmentDeleted = TreatmentDeleted.builder()
                    .treatmentDeletedId(treatment.getId())
                    .doctor(treatment.getDoctor())
                    .treatmentDate(treatment.getTreatmentDate())
                    .diseaseDescription(treatment.getDiseaseDescription())
                    .note(treatment.getNote())
                    .build();

            treatmentDeletedRepository.save(treatmentDeleted);
            for (PatientMedicine patientMedicine : new ArrayList<>(treatment.getPatientMedicines())) {
                DeletedPatientMedicine deletedPatientMedicine = DeletedPatientMedicine.builder()
                        .quantity(patientMedicine.getQuantity())
                        .price(patientMedicine.getPrice())
                        .treatmentDeleted(treatmentDeleted)
                        .medicine(patientMedicine.getMedicine())
                        .build();

                deletedPatientMedicineRepository.save(deletedPatientMedicine);
            }

            treatmentRepository.delete(treatment);
        }

        PatientsDeleted patientsDeleted = PatientsDeleted.builder()
                .patientDeletedId(patient.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(patient.getAge())
                .address(user.getAddress())
                .phone(user.getPhone())
                .build();

        patientDeletedRepository.save(patientsDeleted);
        user.setDeleted(true);
        patientRepository.delete(patient);
        return GeneralResponse.builder().message("Patient deleted successfully").build();
    }


    @Transactional
    public GeneralResponse updatePatient(Patients request, String email) throws UserNotFoundException {
        Patients patient = patientRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
        User user = patient.getUser();



            user.setFirstName(request.getUser().getFirstName());
            user.setLastName(request.getUser().getLastName());
            patient.setAge(request.getAge());
            user.setAddress(request.getUser().getAddress());
            user.setPhone(request.getUser().getPhone());
            user.setDateOfBirth(request.getUser().getDateOfBirth());
            user.setEmail(request.getUser().getEmail());
            patientRepository.save(patient);

        return GeneralResponse.builder().message("Patient updated successfully").build();
    }

    @Transactional
    public PaginationDTO<Patients> getAllPatients(int page, int size , String search , List<Long> doctorIds) {
        if(doctorIds != null && doctorIds.isEmpty()){
            doctorIds = null;
        }
        if(search!=null && search.isEmpty()){
            search = null;
        }
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Patients> patients = patientRepository.findAll(pageable , search , doctorIds);
        PaginationDTO<Patients> paginationDTO = new PaginationDTO<>();
        paginationDTO.setTotalElements(patients.getTotalElements());
        paginationDTO.setTotalPages(patients.getTotalPages());
        paginationDTO.setSize(patients.getSize());
        paginationDTO.setNumber(patients.getNumber() + 1);
        paginationDTO.setNumberOfElements(patients.getNumberOfElements());
        paginationDTO.setContent(patients.getContent());

        return paginationDTO;
    }



}
