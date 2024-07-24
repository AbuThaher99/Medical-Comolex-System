package org.example.ProjectTraninng.WebApi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Treatment;
import org.example.ProjectTraninng.Common.Responses.TreatmentResponse;
import org.example.ProjectTraninng.Core.Servecies.TreatmentService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/treatment")
public class TreatmentController {
    private final TreatmentService treatmentService;

    @PostMapping("/addTreatment")
    public ResponseEntity<TreatmentResponse> addTreatment(@RequestBody @Valid Treatment request) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.createTreatment(request));
    }

    @PutMapping("/updateTreatment/{treatmentId}")
    public ResponseEntity<TreatmentResponse> updateTreatment(@RequestBody @Valid Treatment request, @PathVariable Long treatmentId) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.updateTreatment(request, treatmentId));
    }

    @DeleteMapping("/deleteTreatment/{treatmentId}")
    public ResponseEntity<TreatmentResponse> deleteTreatment(@PathVariable Long treatmentId) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.deleteTreatment(treatmentId));
    }

    @GetMapping("/getTreatment/{treatmentId}")
    public ResponseEntity<Treatment> getTreatment(@PathVariable Long treatmentId) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.getTreatment(treatmentId));
    }

    @GetMapping("/getAllTreatments")
    public ResponseEntity<Iterable<Treatment>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }

    @GetMapping("/getTreatmentByPatientId/{patientId}")
    public ResponseEntity<Iterable<Treatment>> getTreatmentByPatientId(@PathVariable Long patientId) throws UserNotFoundException {
        return ResponseEntity.ok(treatmentService.getAllTreatmentsForPatient(patientId));
    }


}
