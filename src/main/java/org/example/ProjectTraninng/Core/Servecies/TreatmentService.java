package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.example.ProjectTraninng.Common.Entities.Treatment;
import org.example.ProjectTraninng.Common.Responses.TreatmentResponse;
import org.example.ProjectTraninng.Core.Repsitories.DoctorRepository;
import org.example.ProjectTraninng.Core.Repsitories.TreatmentRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitories.PatientRepository;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public TreatmentResponse createTreatment(Treatment request) throws UserNotFoundException {

        Patients patients = patientRepository.findById(request.getPatient().getId()).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));

        Doctor doctor = doctorRepository.findById(request.getDoctor().getId()).orElseThrow(
                () -> new UserNotFoundException("Doctor not found"));
        if (doctor == null) {
            return TreatmentResponse.builder().message("Doctor not found").build();
        }
        Treatment treatment = Treatment.builder()
                .patient(patients)
                .doctor(doctor)
                .diseaseDescription(request.getDiseaseDescription())
                .note(request.getNote())
                .build();
        treatmentRepository.save(treatment);
        return  TreatmentResponse.builder().message("Treatment created successfully").build();
    }

    public TreatmentResponse updateTreatment(Treatment request, Long treatmentId) throws UserNotFoundException {
        var treatmentOptional = treatmentRepository.findById(treatmentId).orElseThrow(
                () -> new UserNotFoundException("Treatment not found"));

        doctorRepository.findById(request.getDoctor().getId()).orElseThrow(
                () -> new UserNotFoundException("Doctor not found"));

        patientRepository.findById(request.getPatient().getId()).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));


        Treatment treatment = treatmentOptional;
        treatment.setDiseaseDescription(request.getDiseaseDescription());
        treatment.setNote(request.getNote());
        treatmentRepository.save(treatment);
        return TreatmentResponse.builder().message("Treatment updated successfully").build();
    }

        public TreatmentResponse deleteTreatment(Long treatmentId) throws UserNotFoundException {
            var treatmentOptional = treatmentRepository.findById(treatmentId).orElseThrow(
                    () -> new UserNotFoundException("Treatment not found"));
            treatmentRepository.delete(treatmentOptional);
            return TreatmentResponse.builder().message("Treatment deleted successfully").build();
        }

    public Treatment getTreatment(Long treatmentId) throws UserNotFoundException {
        var treatmentOptional = treatmentRepository.findById(treatmentId).orElseThrow(
                () -> new UserNotFoundException("Treatment not found"));
        Treatment treatment = treatmentOptional;
        return Treatment.builder()
                .patient(treatment.getPatient())
                .doctor(treatment.getDoctor())
                .diseaseDescription(treatment.getDiseaseDescription())
                .note(treatment.getNote())
                .build();
    }

    public List<Treatment> getAllTreatments() {

        List<Treatment> treatments = treatmentRepository.findAll();
        List<Treatment> treatmentRequests = new ArrayList<>();
        for (Treatment treatment : treatments) {
            treatmentRequests.add(Treatment.builder()
                    .id(treatment.getId())
                    .patient(treatment.getPatient())
                    .doctor(treatment.getDoctor())
                    .diseaseDescription(treatment.getDiseaseDescription())
                    .note(treatment.getNote())
                    .treatmentDate(treatment.getTreatmentDate())
                    .build());
        }
        return treatmentRequests;
    }

    // make a getAllTreatments  method for specific patient

    public List<Treatment> getAllTreatmentsForPatient(Long patientId) throws UserNotFoundException {
        patientRepository.findById(patientId).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));
        List<Treatment> treatments = treatmentRepository.findAllByPatientId(patientId);
        List<Treatment> treatmentRequests = new ArrayList<>();
        for (Treatment treatment : treatments) {
            treatmentRequests.add(Treatment.builder()
                    .id(treatment.getId())
                    .patient(treatment.getPatient())
                    .doctor(treatment.getDoctor())
                    .diseaseDescription(treatment.getDiseaseDescription())
                    .note(treatment.getNote())
                    .treatmentDate(treatment.getTreatmentDate())
                    .build());
        }
        return treatmentRequests;
    }



}
