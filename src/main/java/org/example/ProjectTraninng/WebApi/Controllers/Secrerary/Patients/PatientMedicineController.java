package org.example.ProjectTraninng.WebApi.Controllers.Secrerary.Patients;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.PatientMedicine;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Common.Responses.PatientMedicineRespones;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.PatientMedicineService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
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
            validateLoggedInAdmin(user);
         return patientMedicineService.AddPatientMedicine(patientMedicineRequest);
     }
     @PutMapping("/{id}")
    public PatientMedicineRespones UpdatePatientMedicine(@RequestBody @Valid PatientMedicine patientMedicineRequest , @PathVariable Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
         return patientMedicineService.UpdatePatientMedicine(patientMedicineRequest , id);
     }

     @GetMapping("/{id}")
    public PatientMedicine GetPatientMedicine(@PathVariable Long id, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
         return patientMedicineService.GetPatientMedicine(id);
     }

     @GetMapping("")
    public GeneralResponse<PatientMedicine> GetAllPatientMedicine(HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        List<PatientMedicine> patientMedicines = patientMedicineService.GetAllPatientMedicines();
         GeneralResponse<PatientMedicine> response = new GeneralResponse<>();
            response.setList(patientMedicines);
            response.setCount(10);
         return response;
     }

     @GetMapping("/patientId/{patientId}")
    public GeneralResponse<PatientMedicine> GetPatientMedicineByPatientId(@PathVariable Long patientId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        List<PatientMedicine> patientMedicines = patientMedicineService.getAllPatientMedicinesByPatientId(patientId);
        GeneralResponse<PatientMedicine> response = new GeneralResponse<>();
        response.setList(patientMedicines);
        response.setCount(10);
         return response;
     }

        @GetMapping("/medicineId/{medicineId}")
    public GeneralResponse<PatientMedicine> GetPatientMedicineByMedicineId(@PathVariable Long medicineId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
            List<PatientMedicine> patientMedicines = patientMedicineService.getAllPatientMedicinesByMedicineId(medicineId);
            GeneralResponse<PatientMedicine> response = new GeneralResponse<>();
            response.setList(patientMedicines);
            response.setCount(10);
         return response;
        }

        @GetMapping("/treatmentId/{treatmentId}")
    public  GeneralResponse<PatientMedicine> GetPatientMedicineByTreatmentId(@PathVariable Long treatmentId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        List<PatientMedicine> patientMedicines = patientMedicineService.getAllPatientMedicinesByTreatmentId(treatmentId);
        GeneralResponse<PatientMedicine> response = new GeneralResponse<>();
        response.setList(patientMedicines);
        response.setCount(10);
         return response;
        }

    @Override
    public void validateLoggedInAdmin(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN && user.getRole() != Role.SECRETARY){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }
}
