package org.example.ProjectTraninng.Core.Servecies;

import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.example.ProjectTraninng.Common.Entities.Feedback;
import org.example.ProjectTraninng.Common.Entities.Patients;
import org.example.ProjectTraninng.Core.Repsitories.DoctorRepository;
import org.example.ProjectTraninng.Core.Repsitories.FeedbackRepository;
import org.example.ProjectTraninng.Core.Repsitories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Feedback addFeedback(Feedback feedback) {
        Patients patients = patientRepository.findById(feedback.getPatient().getId()).orElseThrow(
                () -> new RuntimeException("Patient not found with id: " + feedback.getPatient().getId())
        );
        feedback.setPatient(patients);
        Doctor doctor = doctorRepository.findById(feedback.getDoctor().getId()).orElseThrow(
                () -> new RuntimeException("Doctor not found with id: " + feedback.getDoctor().getId())
        );
        feedback.setDoctor(doctor);

        return feedbackRepository.save(feedback);
    }

    public PaginationDTO<Feedback> getFeedbackByDoctor(int page , int size, Long doctorId) {
        if(doctorId!=null && doctorId == 0){
            doctorId = null;

        }
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Feedback> feedbacks = feedbackRepository.findAllByDoctorId(pageable,doctorId);
        PaginationDTO<Feedback> feedbackPaginationDTO = new PaginationDTO<>();
        feedbackPaginationDTO.setTotalElements(feedbacks.getTotalElements());
        feedbackPaginationDTO.setTotalPages(feedbacks.getTotalPages());
        feedbackPaginationDTO.setSize(feedbacks.getSize());
        feedbackPaginationDTO.setNumber(feedbacks.getNumber() + 1);
        feedbackPaginationDTO.setNumberOfElements(feedbacks.getNumberOfElements());
        feedbackPaginationDTO.setContent(feedbacks.getContent());
        return feedbackPaginationDTO;
    }



}