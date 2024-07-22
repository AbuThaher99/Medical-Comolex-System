package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.PatientRequest;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Response.PatientResponse;
import org.example.ProjectTraninng.Core.Repsitory.PatientRepository;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientResponse addPatient(PatientRequest request) throws UserNotFoundException {
        patientRepository.findByFirstName(request.getFirstName()).orElseThrow(
                () -> new UserNotFoundException("Patient already exists"));

        Patients patient = Patients.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .address(request.getAddress())
                .phone(request.getPhone())
                .build();
        patientRepository.save(patient);
        return PatientResponse.builder().message("Patient added successfully").build();
    }

    public Optional<PatientRequest> getPatient(String firstName) throws UserNotFoundException {
        patientRepository.findByFirstName(firstName).orElseThrow(
                () -> new UserNotFoundException("Patient already exists"));

        return patientRepository.findByFirstName(firstName)
                .map(patient -> PatientRequest.builder()
                        .firstName(patient.getFirstName())
                        .lastName(patient.getLastName())
                        .age(patient.getAge())
                        .address(patient.getAddress())
                        .phone(patient.getPhone())
                        .build());
    }

    public PatientResponse deletePatientByFirstName(String firstName) throws UserNotFoundException {
         patientRepository.findByFirstName(firstName).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));
      return PatientResponse.builder().message("Patient deleted successfully").build();
    }

    public PatientResponse updatePatient(PatientRequest request, String firstName) throws UserNotFoundException {
       var patientOptional = patientRepository.findByFirstName(firstName).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));

            Patients patient = patientOptional;
            patient.setFirstName(request.getFirstName());
            patient.setLastName(request.getLastName());
            patient.setAge(request.getAge());
            patient.setAddress(request.getAddress());
            patient.setPhone(request.getPhone());
            patientRepository.save(patient);

        return PatientResponse.builder().message("Patient updated successfully").build();
    }


    public List<PatientRequest> getAllPatients() {
        List<Patients> patients = patientRepository.findAll();
        List<PatientRequest> patientRequests = new ArrayList<>();
        for (Patients patient : patients) {
            patientRequests.add(PatientRequest.builder()
                    .firstName(patient.getFirstName())
                    .lastName(patient.getLastName())
                    .age(patient.getAge())
                    .address(patient.getAddress())
                    .phone(patient.getPhone())
                    .build());
        }
        return patientRequests;
    }

}
