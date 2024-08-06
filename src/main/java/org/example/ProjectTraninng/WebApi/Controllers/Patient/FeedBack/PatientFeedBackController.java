package org.example.ProjectTraninng.WebApi.Controllers.Patient.FeedBack;

import lombok.AllArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Feedback;
import org.example.ProjectTraninng.Core.Servecies.FeedbackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/patients/feedback")
public class PatientFeedBackController {
    private final FeedbackService feedBackDoctorService;

    @PostMapping("/")
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return feedBackDoctorService.addFeedback(feedback);
    }
}
