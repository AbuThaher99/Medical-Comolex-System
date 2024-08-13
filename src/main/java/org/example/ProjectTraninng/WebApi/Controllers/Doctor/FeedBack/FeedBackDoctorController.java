package org.example.ProjectTraninng.WebApi.Controllers.Doctor.FeedBack;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.Feedback;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.FeedbackService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("doctor/feedback")
public class FeedBackDoctorController extends SessionManagement {
    private final FeedbackService feedBackDoctorService;
    private final AuthenticationService authenticationService;

    @GetMapping("")
    public PaginationDTO<Feedback> getFeedbackByDoctor(@RequestParam(defaultValue = "",required = false) Long doctorId,
                                                       @RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = authenticationService.extractToken(httpServletRequest);
        User user = authenticationService.extractUserFromToken(token);
        validateLoggedInDoctor(user);
        return feedBackDoctorService.getFeedbackByDoctor(page, size, doctorId);
    }

}
