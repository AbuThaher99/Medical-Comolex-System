package org.example.ProjectTraninng.WebApi;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.example.ProjectTraninng.Common.Responses.DoctorResponse;
import org.example.ProjectTraninng.Core.Servecies.DoctorService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Common.Responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @PostMapping("/addDoctor")
    public ResponseEntity<AuthenticationResponse> addDoctor(
            @RequestBody @Valid Doctor request
    ) throws UserNotFoundException {
        return ResponseEntity.ok(doctorService.addDoctor(request));
    }

    @PutMapping ("UpdateDoctor/{doctorId}")
    public ResponseEntity<DoctorResponse> updateDoctor(@PathVariable Long doctorId,  @RequestBody @Valid Doctor request) throws UserNotFoundException {
        doctorService.updateDoctor(request,  doctorId);
        return ResponseEntity.ok(DoctorResponse.builder().message("Doctor updated successfully").build());
    }

    @DeleteMapping ("DeleteDoctor/{doctorId}")
    public ResponseEntity<DoctorResponse> deleteDoctor(@PathVariable Long doctorId) throws UserNotFoundException {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok(DoctorResponse.builder().message("Doctor deleted successfully").build());
    }
    @GetMapping("/getDoctor/{email}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable String email) throws UserNotFoundException {
        Doctor doctor = doctorService.findDoctorByEmail(email);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("getAllDoctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }


}
