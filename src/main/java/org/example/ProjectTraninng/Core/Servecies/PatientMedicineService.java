package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.PatientMedicineRequest;
import org.example.ProjectTraninng.Common.Entities.PatientMedicine;
import org.example.ProjectTraninng.Common.Response.PatientMedicineRespones;
import org.example.ProjectTraninng.Core.Repsitory.PatientMedicineRepository;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitory.MedicineRepository;
import org.example.ProjectTraninng.Core.Repsitory.TreatmentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientMedicineService {
    private final PatientMedicineRepository patientMedicineRepository;
    private final TreatmentRepository treatmentRepository;
    private final MedicineRepository medicineRepository;

    public PatientMedicineRespones AddPatientMedicine(PatientMedicineRequest patientMedicineRequest) throws UserNotFoundException {
      treatmentRepository.findById(patientMedicineRequest.getTreatmentId())
                .orElseThrow(() -> new UserNotFoundException("Treatment not found"));
         medicineRepository.findById(patientMedicineRequest.getMedicineId())
                .orElseThrow(() -> new UserNotFoundException("Medicine not found"));

        PatientMedicine patientMedicine = PatientMedicine.builder()
                .quantity(patientMedicineRequest.getQuantity())
                .price(patientMedicineRequest.getPrice())
                .treatmentId(patientMedicineRequest.getTreatmentId())
                .medicineId(patientMedicineRequest.getMedicineId())
                .build();
        patientMedicineRepository.save(patientMedicine);
        return PatientMedicineRespones.builder()
                .message("Patient medicine created successfully")
                .build();
    }

//    public PatientMedicineRespones delete(Long id) {
//        patientMedicineRepository.deleteById(id);
//        return PatientMedicineRespones.builder()
//                .message("Patient medicine deleted successfully")
//                .build();
//    }

    public PatientMedicineRespones UpdatePatientMedicine(PatientMedicineRequest patientMedicineRequest , Long id) throws UserNotFoundException {
        PatientMedicine patientMedicine = patientMedicineRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Patient medicine not found"));

       treatmentRepository.findById(patientMedicineRequest.getTreatmentId())
                .orElseThrow(() -> new UserNotFoundException("Treatment not found"));

        medicineRepository.findById(patientMedicineRequest.getMedicineId())
                .orElseThrow(() -> new UserNotFoundException("Medicine not found"));

        patientMedicine.setQuantity(patientMedicineRequest.getQuantity());
        patientMedicine.setPrice(patientMedicineRequest.getPrice());
        patientMedicine.setTreatmentId(patientMedicineRequest.getTreatmentId());
        patientMedicine.setMedicineId(patientMedicineRequest.getMedicineId());
        patientMedicineRepository.save(patientMedicine);
        return PatientMedicineRespones.builder()
                .message("Patient medicine updated successfully")
                .build();
    }


}
