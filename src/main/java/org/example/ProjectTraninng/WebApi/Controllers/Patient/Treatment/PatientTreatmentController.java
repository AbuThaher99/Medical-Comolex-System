package org.example.ProjectTraninng.WebApi.Controllers.Patient.Treatment;

import lombok.AllArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Treatment;
import org.example.ProjectTraninng.Core.Servecies.TreatmentService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/patients/treatment")
public class PatientTreatmentController {
    private final TreatmentService treatmentService;

    @GetMapping("")
    public Page<Treatment> getTreatment(@RequestParam(defaultValue = "") Long patientId,
                                        @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size) throws UserNotFoundException {
      return   treatmentService.getAllTreatmentsForPatient( patientId, page, size);
    }

}
