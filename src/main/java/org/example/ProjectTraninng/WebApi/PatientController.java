package org.example.ProjectTraninng.WebApi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Common.Responses.PatientResponse;
import org.example.ProjectTraninng.Core.Servecies.PatientService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/addPatient")
    public ResponseEntity<PatientResponse> addPatient(@RequestBody @Valid Patients request) throws UserNotFoundException {
        return ResponseEntity.ok(patientService.addPatient(request));
    }

    @GetMapping("/getPatient/{firstName}")
    public ResponseEntity<?> getPatient(@PathVariable String firstName) throws UserNotFoundException {
        Optional<Patients> patientRequest = patientService.getPatient(firstName);
        if (patientRequest.isPresent()) {
            return ResponseEntity.ok(patientRequest.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
    }
@DeleteMapping("/deletePatient/{firstName}")
    public ResponseEntity<?> deletePatientByEmail(@PathVariable String firstName) throws UserNotFoundException {
        PatientResponse isDeleted = patientService.deletePatientByFirstName(firstName);
        return ResponseEntity.ok(isDeleted);
    }

    @PutMapping("/updatePatient/{firstName}")
    public ResponseEntity<?> updatePatient(@RequestBody @Valid Patients request, @PathVariable String firstName) throws UserNotFoundException {
         PatientResponse d= patientService.updatePatient(request, firstName);
          return ResponseEntity.ok(d);

    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<Iterable<Patients>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}
