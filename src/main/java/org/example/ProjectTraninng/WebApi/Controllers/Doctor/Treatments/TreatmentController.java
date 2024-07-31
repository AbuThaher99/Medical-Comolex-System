package org.example.ProjectTraninng.WebApi.Controllers.Doctor.Treatments;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Treatment;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Common.Responses.TreatmentResponse;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.TreatmentService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("doctor/treatments")
public class TreatmentController extends SessionManagement {
    private final TreatmentService treatmentService;

    private final AuthenticationService service;

    @PostMapping("")
    public ResponseEntity<TreatmentResponse> addTreatment(@RequestBody @Valid Treatment request, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInDoctor(user);
        return ResponseEntity.ok(treatmentService.createTreatment(request));
    }

    @PutMapping("/{treatmentId}")
    public ResponseEntity<TreatmentResponse> updateTreatment(@RequestBody @Valid Treatment request, @PathVariable Long treatmentId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
       String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInDoctor(user);
        return ResponseEntity.ok(treatmentService.updateTreatment(request, treatmentId));
    }

    @DeleteMapping("/{treatmentId}")
    public ResponseEntity<TreatmentResponse> deleteTreatment(@PathVariable Long treatmentId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInDoctor(user);
        return ResponseEntity.ok(treatmentService.deleteTreatment(treatmentId));
    }

    @GetMapping("/{treatmentId}")
    public ResponseEntity<Treatment> getTreatment(@PathVariable Long treatmentId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInDoctor(user);
        return ResponseEntity.ok(treatmentService.getTreatment(treatmentId));
    }

    @GetMapping("")
    public Page<Treatment> getAllTreatments(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(required = false)List<Long> patientIds,
                                            @RequestParam(required = false) Long patientId,
                                            @RequestParam(required = false)String search,
                                            HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInDoctor(user);
        return treatmentService.getAllTreatments(page, size , patientIds, patientId, search);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Iterable<Treatment>> getTreatmentByPatientId(@PathVariable Long patientId,@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInDoctor(user);
        return ResponseEntity.ok(treatmentService.getAllTreatmentsForPatient(patientId, page, size));
    }




}
