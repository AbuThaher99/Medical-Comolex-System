package org.example.ProjectTraninng.WebApi;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.TreatmentRequest;
import org.example.ProjectTraninng.Common.Response.TreatmentResponse;
import org.example.ProjectTraninng.Core.Servecies.TreatmentService;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/treatment")
public class TreatmentController {
    private final TreatmentService treatmentService;

    @PostMapping("/addTreatment")
    public ResponseEntity<TreatmentResponse> addTreatment(@RequestBody TreatmentRequest request) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.createTreatment(request));
    }

    @PutMapping("/updateTreatment/{treatmentId}")
    public ResponseEntity<TreatmentResponse> updateTreatment(@RequestBody TreatmentRequest request, @PathVariable Long treatmentId) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.updateTreatment(request, treatmentId));
    }

    @DeleteMapping("/deleteTreatment/{treatmentId}")
    public ResponseEntity<TreatmentResponse> deleteTreatment(@PathVariable Long treatmentId) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.deleteTreatment(treatmentId));
    }

    @GetMapping("/getTreatment/{treatmentId}")
    public ResponseEntity<TreatmentRequest> getTreatment(@PathVariable Long treatmentId) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.getTreatment(treatmentId));
    }

    @GetMapping("/getAllTreatments")
    public ResponseEntity<Iterable<TreatmentRequest>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }

    @GetMapping("/getTreatmentByPatientId/{patientId}")
    public ResponseEntity<Iterable<TreatmentRequest>> getTreatmentByPatientId(@PathVariable Long patientId) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.getAllTreatmentsForPatient(patientId));
    }


}
