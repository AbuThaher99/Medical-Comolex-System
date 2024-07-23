package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.TreatmentRequest;
import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.example.ProjectTraninng.Common.Entities.Treatment;
import org.example.ProjectTraninng.Common.Response.TreatmentResponse;
import org.example.ProjectTraninng.Core.Repsitory.DoctorRepository;
import org.example.ProjectTraninng.Core.Repsitory.TreatmentRepository;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitory.PatientRepository;
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

    public TreatmentResponse createTreatment(TreatmentRequest request) throws UserNotFoundException {
        if (request.getDescription().equals("") ||
                request.getDoctorId().equals("") || request.getPatientId().equals("")) {
            throw new UserNotFoundException("Please fill all the fields");
        }
        if (request.getDescription() == null ||
                request.getDoctorId() == null || request.getPatientId() == null) {
            throw new UserNotFoundException("Please fill all the fields");
        }
        Patients patients = patientRepository.findById(request.getPatientId()).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));

        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElseThrow(
                () -> new UserNotFoundException("Doctor not found"));
        if (doctor == null) {
            return TreatmentResponse.builder().message("Doctor not found").build();
        }
        Treatment treatment = Treatment.builder()
                .patient(patients)
                .doctor(doctor)
                .diseaseDescription(request.getDescription())
                .note(request.getNote())
                .build();
        treatmentRepository.save(treatment);
        return  null;
    }

    public TreatmentResponse updateTreatment(TreatmentRequest request, Long treatmentId) throws UserNotFoundException {
        var treatmentOptional = treatmentRepository.findById(treatmentId).orElseThrow(
                () -> new UserNotFoundException("Treatment not found"));

        doctorRepository.findById(request.getDoctorId()).orElseThrow(
                () -> new UserNotFoundException("Doctor not found"));

        patientRepository.findById(request.getPatientId()).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));


        Treatment treatment = treatmentOptional;
        treatment.setDiseaseDescription(request.getDescription());
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

    public TreatmentRequest getTreatment(Long treatmentId) throws UserNotFoundException {

        var treatmentOptional = treatmentRepository.findById(treatmentId).orElseThrow(
                () -> new UserNotFoundException("Treatment not found"));
        Treatment treatment = treatmentOptional;
        return TreatmentRequest.builder()
                .patientId(treatment.getPatient().getId())
                .doctorId(treatment.getDoctor().getId())
                .description(treatment.getDiseaseDescription())
                .note(treatment.getNote())
                .build();
    }

    public List<TreatmentRequest> getAllTreatments() {

        List<Treatment> treatments = treatmentRepository.findAll();
        List<TreatmentRequest> treatmentRequests = new ArrayList<>();
        for (Treatment treatment : treatments) {
            treatmentRequests.add(TreatmentRequest.builder()
                    .patientId(treatment.getPatient().getId())
                    .doctorId(treatment.getDoctor().getId())
                    .description(treatment.getDiseaseDescription())
                    .note(treatment.getNote())
                    .build());
        }
        return treatmentRequests;
    }

    // make a getAllTreatments  method for specific patient

    public List<TreatmentRequest> getAllTreatmentsForPatient(Long patientId) throws UserNotFoundException {
        patientRepository.findById(patientId).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));
        List<Treatment> treatments = treatmentRepository.findAllByPatientId(patientId);
        List<TreatmentRequest> treatmentRequests = new ArrayList<>();
        for (Treatment treatment : treatments) {
            treatmentRequests.add(TreatmentRequest.builder()
                    .patientId(treatment.getPatient().getId())
                    .doctorId(treatment.getDoctor().getId())
                    .description(treatment.getDiseaseDescription())
                    .note(treatment.getNote())
                    .build());
        }
        return treatmentRequests;
    }



}
