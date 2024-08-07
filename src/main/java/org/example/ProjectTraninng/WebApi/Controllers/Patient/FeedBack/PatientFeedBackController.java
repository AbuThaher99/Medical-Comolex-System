package org.example.ProjectTraninng.WebApi.Controllers.Patient.FeedBack;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Feedback;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.FeedbackService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/patients/feedback")
public class PatientFeedBackController extends SessionManagement {
    private final FeedbackService feedBackDoctorService;
    private final AuthenticationService service;

    @PostMapping("/")
    public Feedback addFeedback(@RequestBody Feedback feedback, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInPatient(user);
        return feedBackDoctorService.addFeedback(feedback);
    }
}
