package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Responses.PatientResponse;
import org.example.ProjectTraninng.Core.Repsitories.PatientRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientResponse addPatient(Patients request) throws UserNotFoundException {


        boolean exists =   patientRepository.findByFirstName(request.getFirstName()).isPresent();
        if (exists) {
            throw new UserNotFoundException("Patient already exists");
        }

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

    public Optional<Patients> getPatient(String firstName) throws UserNotFoundException {

        patientRepository.findByFirstName(firstName).orElseThrow(
                () -> new UserNotFoundException("Patient Not Found"));

        return patientRepository.findByFirstName(firstName)
                .map(patient -> Patients.builder()
                        .id(patient.getId())
                        .firstName(patient.getFirstName())
                        .lastName(patient.getLastName())
                        .age(patient.getAge())
                        .address(patient.getAddress())
                        .phone(patient.getPhone())
                        .createdDate(patient.getCreatedDate())
                        .treatments(patient.getTreatments())
                        .build());
    }

    public PatientResponse deletePatientByFirstName(String firstName) throws UserNotFoundException {
         patientRepository.findByFirstName(firstName).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));
      return PatientResponse.builder().message("Patient deleted successfully").build();
    }

    public PatientResponse updatePatient(Patients request, String firstName) throws UserNotFoundException {
        Patients patientOptional = patientRepository.findByFirstName(firstName).orElseThrow(
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


    public List<Patients> getAllPatients() {
        List<Patients> patients = patientRepository.findAll();
        List<Patients> patientRequests = new ArrayList<>();
        for (Patients patient : patients) {
            patientRequests.add(Patients.builder()
                    .id(patient.getId())
                    .firstName(patient.getFirstName())
                    .lastName(patient.getLastName())
                    .age(patient.getAge())
                    .address(patient.getAddress())
                    .phone(patient.getPhone())
                    .createdDate(patient.getCreatedDate())
                    .treatments(patient.getTreatments())
                    .build());
        }
        return patientRequests;
    }

}
