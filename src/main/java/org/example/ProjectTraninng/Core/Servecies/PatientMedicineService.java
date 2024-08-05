package org.example.ProjectTraninng.Core.Servecies;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Common.Responses.PatientMedicineRespones;
import org.example.ProjectTraninng.Core.Repsitories.*;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientMedicineService {
    private final PatientMedicineRepository patientMedicineRepository;
    private final TreatmentRepository treatmentRepository;
    private final MedicineRepository medicineRepository;
    private final PatientRepository patientsService;
    private final DeletedPatientMedicineRepository deletedPatientMedicineRepository;
    @Transactional
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
    @Transactional
    public PatientMedicineRespones delete(Long id) throws UserNotFoundException {
        PatientMedicine patientMedicine = patientMedicineRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Patient medicine not found"));

        // save the patient medicine in a deleted_patient_medicine table
        DeletedPatientMedicine deletedPatientMedicine = DeletedPatientMedicine.builder()
                .quantity(patientMedicine.getQuantity())
                .price(patientMedicine.getPrice())
                .medicine(patientMedicine.getMedicine())
                .treatmentDeleted(null)
                .build();
        deletedPatientMedicineRepository.save(deletedPatientMedicine);

        patientMedicineRepository.deleteById(patientMedicine.getId());
        return PatientMedicineRespones.builder()
                .message("Patient medicine deleted successfully")
                .build();
    }
    @Transactional
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
    @Transactional
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
    @Transactional
    public Page<PatientMedicine> GetAllPatientMedicines(int page , int size,String search ,List<Long> treatmentIds,List<Long> medicineIds,List<Long> patientIds) throws UserNotFoundException {
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        if (patientIds != null && patientIds.isEmpty()) {
            patientIds = null;
        }
        if (treatmentIds != null && treatmentIds.isEmpty()) {
            treatmentIds = null;
        }

        if (medicineIds != null && medicineIds.isEmpty()) {
            medicineIds = null;
        }
        return patientMedicineRepository.findAll(pageable,search, treatmentIds,medicineIds,patientIds);
    }
    @Transactional
    public Page<PatientMedicine> getAllPatientMedicinesByTreatmentId(Long treatmentId,int size , int page) throws UserNotFoundException {
        boolean exists = treatmentRepository.findById(treatmentId).isPresent();
        if (!exists) {
            throw new UserNotFoundException("Treatment not found");
        }
        Pageable pageable = PageRequest.of(page, size);
        return patientMedicineRepository.findAllByTreatmentId(treatmentId,pageable);
    }
    @Transactional
    public Page<PatientMedicine> getAllPatientMedicinesByMedicineId(Long medicineId,int size , int page) throws UserNotFoundException {
            boolean exists = medicineRepository.findById(medicineId).isPresent();
            if (!exists) {
                throw new UserNotFoundException("Medicine not found");
            }
            Pageable pageable = PageRequest.of(page, size);
            return patientMedicineRepository.findAllByMedicineId(medicineId,pageable);
        }

    @Transactional
        public Page<PatientMedicine> getAllPatientMedicinesByPatientId(Long patientId,int size ,int page) throws UserNotFoundException {
            boolean exists = patientsService.findById(patientId).isPresent();
            if (!exists) {
                throw new UserNotFoundException("Patient not found");
            }
            Pageable pageable = PageRequest.of(page, size);
            return patientMedicineRepository.findAllByPatientId(patientId,pageable);
        }
}
