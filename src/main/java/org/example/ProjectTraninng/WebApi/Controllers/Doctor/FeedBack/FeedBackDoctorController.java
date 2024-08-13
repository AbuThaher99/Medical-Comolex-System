package org.example.ProjectTraninng.WebApi.Controllers.Doctor.FeedBack;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.Feedback;
import org.example.ProjectTraninng.Core.Servecies.FeedbackService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("doctor/feedback")
public class FeedBackDoctorController {
    private final FeedbackService feedBackDoctorService;

    @GetMapping("")
    public PaginationDTO<Feedback> getFeedbackByDoctor(@RequestParam(defaultValue = "",required = false) Long doctorId,
                                                       @RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return feedBackDoctorService.getFeedbackByDoctor(page, size, doctorId);
    }

}
