package org.example.ProjectTraninng.Core.Servecies;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Medicine;
import org.example.ProjectTraninng.Common.Entities.PatientMedicine;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Entities.Treatment;
import org.example.ProjectTraninng.Common.Responses.PatientMedicineRespones;
import org.example.ProjectTraninng.Core.Repsitories.PatientMedicineRepository;
import org.example.ProjectTraninng.Core.Repsitories.PatientRepository;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Core.Repsitories.MedicineRepository;
import org.example.ProjectTraninng.Core.Repsitories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientMedicineService {
    private final PatientMedicineRepository patientMedicineRepository;
    private final TreatmentRepository treatmentRepository;
    private final MedicineRepository medicineRepository;
    private final PatientRepository patientsService;

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

    public PatientMedicine GetPatientMedicine(Long id) throws UserNotFoundException {
        PatientMedicine patientMedicine = patientMedicineRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Patient medicine not found"));
        return patientMedicine.builder()
                .quantity(patientMedicine.getQuantity())
                .price(patientMedicine.getPrice())
                .treatment(patientMedicine.getTreatment())
                .medicine(patientMedicine.getMedicine())
                .build();
    }

    public List<PatientMedicine> GetAllPatientMedicines() throws UserNotFoundException {
        return patientMedicineRepository.findAll();
    }
    public List<PatientMedicine> getAllPatientMedicinesByTreatmentId(Long treatmentId) throws UserNotFoundException {
        boolean exists = treatmentRepository.findById(treatmentId).isPresent();
        if (!exists) {
            throw new UserNotFoundException("Treatment not found");
        }
        return patientMedicineRepository.findAllByTreatmentId(treatmentId);
    }

    public List<PatientMedicine> getAllPatientMedicinesByMedicineId(Long medicineId) throws UserNotFoundException {
            boolean exists = medicineRepository.findById(medicineId).isPresent();
            if (!exists) {
                throw new UserNotFoundException("Medicine not found");
            }
            return patientMedicineRepository.findAllByMedicineId(medicineId);
        }


        public List<PatientMedicine> getAllPatientMedicinesByPatientId(Long patientId) throws UserNotFoundException {
            boolean exists = patientsService.findById(patientId).isPresent();
            if (!exists) {
                throw new UserNotFoundException("Patient not found");
            }
            return patientMedicineRepository.findAllByPatientId(patientId);
        }
}
