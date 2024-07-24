package org.example.ProjectTraninng.WebApi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.PatientMedicine;
import org.example.ProjectTraninng.Common.Responses.PatientMedicineRespones;
import org.example.ProjectTraninng.Core.Servecies.PatientMedicineService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient_medicine")
public class PatientMedicineController {
    private final PatientMedicineService patientMedicineService;

     @PostMapping("/AddPatientMedicine")
    public PatientMedicineRespones AddPatientMedicine(@RequestBody @Valid PatientMedicine patientMedicineRequest) throws UserNotFoundException {
         return patientMedicineService.AddPatientMedicine(patientMedicineRequest);
     }
     @PutMapping("/UpdatePatientMedicine/{id}")
    public PatientMedicineRespones UpdatePatientMedicine(@RequestBody @Valid PatientMedicine patientMedicineRequest , @PathVariable Long id) throws UserNotFoundException {
         return patientMedicineService.UpdatePatientMedicine(patientMedicineRequest , id);
     }
}
