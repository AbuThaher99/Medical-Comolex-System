package org.example.ProjectTraninng.WebApi.Controllers.Secrerary.Patients;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.PatientMedicine;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Common.Responses.PatientMedicineRespones;
import org.example.ProjectTraninng.Common.Responses.PatientResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.PatientMedicineService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient/medicine")
public class PatientMedicineController extends SessionManagement {
    private final PatientMedicineService patientMedicineService;
    private final AuthenticationService service;

     @PostMapping("")
    public PatientMedicineRespones AddPatientMedicine(@RequestBody @Valid PatientMedicine patientMedicineRequest, HttpServletRequest httpServletRequest) throws UserNotFoundException {
         String token = service.extractToken(httpServletRequest);
            User user = service.extractUserFromToken(token);
         validateLoggedInSecretary(user);
         return patientMedicineService.AddPatientMedicine(patientMedicineRequest);
     }
     @PutMapping("/{id}")
    public PatientMedicineRespones UpdatePatientMedicine(@RequestBody @Valid PatientMedicine patientMedicineRequest , @PathVariable Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
         validateLoggedInSecretary(user);
         return patientMedicineService.UpdatePatientMedicine(patientMedicineRequest , id);
     }

     @GetMapping("/{id}")
    public PatientMedicine GetPatientMedicine(@PathVariable Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
         validateLoggedInSecretary(user);
         return patientMedicineService.GetPatientMedicine(id);
     }

     @GetMapping("")
    public Page<PatientMedicine> GetAllPatientMedicine(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size,HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
         validateLoggedInSecretary(user);
         return patientMedicineService.GetAllPatientMedicines(page, size);
     }

     @GetMapping("/patientId/{patientId}")
    public Page<PatientMedicine> GetPatientMedicineByPatientId(@PathVariable Long patientId, @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size,HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
         validateLoggedInSecretary(user);

         return patientMedicineService.getAllPatientMedicinesByPatientId(patientId,page,size);
     }

        @GetMapping("/medicineId/{medicineId}")
    public Page<PatientMedicine> GetPatientMedicineByMedicineId(@PathVariable Long medicineId,@RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
            validateLoggedInSecretary(user);
        return patientMedicineService.getAllPatientMedicinesByMedicineId(medicineId,page,size);
        }

        @GetMapping("/treatmentId/{treatmentId}")
    public  Page<PatientMedicine> GetPatientMedicineByTreatmentId(@PathVariable Long treatmentId, @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size,HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
            validateLoggedInSecretary(user);
            return patientMedicineService.getAllPatientMedicinesByTreatmentId(treatmentId,page,size);

        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> DeletePatientMedicine(@PathVariable Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
            validateLoggedInSecretary(user);
            PatientMedicineRespones isDeleted = patientMedicineService.delete(id);
            return ResponseEntity.ok(isDeleted);
        }



//        @DeleteMapping("/{firstName}")
//        public ResponseEntity<?> deletePatientByEmail(@PathVariable String firstName, HttpServletRequest httpServletRequest) throws UserNotFoundException {
//            String token = service.extractToken(httpServletRequest);
//            User user = service.extractUserFromToken(token);
//            validateLoggedInSecretary(user);
//            PatientResponse isDeleted = patientService.deletePatientByFirstName(firstName);
//            return ResponseEntity.ok(isDeleted);
//        }



}
