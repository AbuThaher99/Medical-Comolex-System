package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.example.ProjectTraninng.Common.Entities.PatientMedicine;
import org.example.ProjectTraninng.Common.Entities.Treatment;
import org.example.ProjectTraninng.Common.Responses.PatientMedicineRespones;
import org.example.ProjectTraninng.Core.Repsitories.PatientMedicineRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitories.MedicineRepository;
import org.example.ProjectTraninng.Core.Repsitories.TreatmentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientMedicineService {
    private final PatientMedicineRepository patientMedicineRepository;
    private final TreatmentRepository treatmentRepository;
    private final MedicineRepository medicineRepository;

    public PatientMedicineRespones AddPatientMedicine(PatientMedicine patientMedicineRequest) throws UserNotFoundException {
  Treatment treatment = treatmentRepository.findById(patientMedicineRequest.getTreatment().getId())
                .orElseThrow(() -> new UserNotFoundException("Treatment not found"));
    Medicine medicine = medicineRepository.findById(patientMedicineRequest.getMedicine().getId())
                .orElseThrow(() -> new UserNotFoundException("Medicine not found"));

        PatientMedicine patientMedicine = PatientMedicine.builder()
                .quantity(patientMedicineRequest.getQuantity())
                .price(patientMedicineRequest.getPrice())
                .treatment(treatment)
                .medicine(medicine)
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

    public PatientMedicineRespones UpdatePatientMedicine(PatientMedicine patientMedicineRequest , Long id) throws UserNotFoundException {
        PatientMedicine patientMedicine = patientMedicineRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Patient medicine not found"));

       treatmentRepository.findById(patientMedicineRequest.getTreatment().getId())
                .orElseThrow(() -> new UserNotFoundException("Treatment not found"));

        medicineRepository.findById(patientMedicineRequest.getMedicine().getId())
                .orElseThrow(() -> new UserNotFoundException("Medicine not found"));

        patientMedicine.setQuantity(patientMedicineRequest.getQuantity());
        patientMedicine.setPrice(patientMedicineRequest.getPrice());
        patientMedicine.setTreatment(patientMedicineRequest.getTreatment());
        patientMedicine.setMedicine(patientMedicineRequest.getMedicine());
        patientMedicineRepository.save(patientMedicine);
        return PatientMedicineRespones.builder()
                .message("Patient medicine updated successfully")
                .build();
    }


}
