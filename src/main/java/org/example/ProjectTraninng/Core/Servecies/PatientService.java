package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Common.Responses.PatientResponse;
import org.example.ProjectTraninng.Core.Repsitories.*;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
    @Transactional
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
    @Transactional
    public PatientResponse deletePatientByFirstName(String firstName) throws UserNotFoundException {
        Patients patient = patientRepository.findByFirstName(firstName)
                .orElseThrow(() -> new UserNotFoundException("Patient not found"));
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
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .age(patient.getAge())
                .address(patient.getAddress())
                .phone(patient.getPhone())
                .build();

        patientDeletedRepository.save(patientsDeleted);
        patientRepository.delete(patient);
        return PatientResponse.builder().message("Patient deleted successfully").build();
    }


    @Transactional
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

    @Transactional
    public Page<Patients> getAllPatients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return patientRepository.findAll(pageable);
    }

}
