package org.example.ProjectTraninng.WebApi.Controllers.Admin.Doctors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.DoctorResponse;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Repsitories.UserRepository;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.DoctorService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/doctors")
@RequiredArgsConstructor
public class DoctorController extends SessionManagement {
    private final DoctorService doctorService;
    private final AuthenticationService service;
    @PostMapping("")
    public ResponseEntity<AuthenticationResponse> addDoctor(@RequestBody @Valid Doctor request , HttpServletRequest httpServletRequest) throws UserNotFoundException {
       String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        return ResponseEntity.ok(doctorService.addDoctor(request));
    }

    @PutMapping ("/{doctorId}")
    public ResponseEntity<DoctorResponse> updateDoctor(@PathVariable Long doctorId,  @RequestBody @Valid Doctor request, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        doctorService.updateDoctor(request,  doctorId);
        return ResponseEntity.ok(DoctorResponse.builder().message("Doctor updated successfully").build());
    }

    @DeleteMapping ("/{doctorId}")
    public ResponseEntity<DoctorResponse> deleteDoctor(@PathVariable Long doctorId, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok(DoctorResponse.builder().message("Doctor deleted successfully").build());
    }
    @GetMapping("/{email}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable String email, HttpServletRequest httpServletRequest) throws UserNotFoundException {
        String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);

        Doctor doctor = doctorService.findDoctorByEmail(email);
       // System.out.println(doctor);
        return ResponseEntity.ok(Doctor.builder()
                .id(doctor.getId())
                .user(doctor.getUser())
                .specialization(doctor.getSpecialization())
                .endTime(doctor.getEndTime())
                .beginTime(doctor.getBeginTime())
                .build());
    }

    @GetMapping("")
    public  GeneralResponse<Doctor> getAllDoctors(HttpServletRequest httpServletRequest) throws UserNotFoundException {
       String token = service.extractToken(httpServletRequest);
        User user = service.extractUserFromToken(token);
        validateLoggedInAdmin(user);
        List<Doctor> doctors = doctorService.getAllDoctors();
        GeneralResponse<Doctor> response = new GeneralResponse<>();
        response.setList(doctors);
        response.setCount(10);
        return response;
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    @Override
    public void validateLoggedInAdmin(User user) throws UserNotFoundException {
        if(user.getRole() != Role.ADMIN){
            throw new UserNotFoundException("You are not authorized to perform this operation");
        }
    }


}
