package org.example.ProjectTraninng.WebApi;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.PatientMedicineRequest;
import org.example.ProjectTraninng.Common.Response.PatientMedicineRespones;
import org.example.ProjectTraninng.Core.Servecies.PatientMedicineService;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patient_medicine")
public class PatientMedicineController {
    private final PatientMedicineService patientMedicineService;

     @PostMapping("/AddPatientMedicine")
    public PatientMedicineRespones AddPatientMedicine(@RequestBody PatientMedicineRequest patientMedicineRequest) throws UserNotFoundException {
         return patientMedicineService.AddPatientMedicine(patientMedicineRequest);
     }
     @PutMapping("/UpdatePatientMedicine/{id}")
    public PatientMedicineRespones UpdatePatientMedicine(@RequestBody PatientMedicineRequest patientMedicineRequest , @PathVariable Long id) throws UserNotFoundException {
         return patientMedicineService.UpdatePatientMedicine(patientMedicineRequest , id);
     }
}
