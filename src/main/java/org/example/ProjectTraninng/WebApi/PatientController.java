package org.example.ProjectTraninng.WebApi;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.PatientRequest;
import org.example.ProjectTraninng.Common.Response.PatientResponse;
import org.example.ProjectTraninng.Core.Servecies.PatientService;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
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
    public ResponseEntity<PatientResponse> addPatient(@RequestBody PatientRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(patientService.addPatient(request));
    }

    @GetMapping("/getPatient/{firstName}")
    public ResponseEntity<?> getPatient(@PathVariable String firstName) throws UserNotFoundException {
        Optional<PatientRequest> patientRequest = patientService.getPatient(firstName);
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
    public ResponseEntity<?> updatePatient(@RequestBody PatientRequest request, @PathVariable String firstName) throws UserNotFoundException {
         PatientResponse d= patientService.updatePatient(request, firstName);
          return ResponseEntity.ok(d);

    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<Iterable<PatientRequest>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}
