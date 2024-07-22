package org.example.ProjectTraninng.WebApi;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.DTO.DoctorDTO;
import org.example.ProjectTraninng.Common.DTO.DoctorRegisterRequest;
import org.example.ProjectTraninng.Common.Response.DoctorResponse;
import org.example.ProjectTraninng.Core.Servecies.DoctorService;
import org.example.ProjectTraninng.Exceptions.UserNotFoundException;
import org.example.ProjectTraninng.Common.Response.AuthenticationResponse;
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
            @RequestBody DoctorRegisterRequest request
    ) {
        return ResponseEntity.ok(doctorService.addDoctor(request));
    }

    @PutMapping ("/{doctorId}")
    public ResponseEntity<DoctorResponse> updateDoctor(@PathVariable Long doctorId, @RequestBody DoctorRegisterRequest request) throws UserNotFoundException {
        doctorService.updateDoctor(request,  doctorId);
        return ResponseEntity.ok(DoctorResponse.builder().message("Doctor updated successfully").build());
    }

    @DeleteMapping ("/{doctorId}")
    public ResponseEntity<DoctorResponse> deleteDoctor(@PathVariable Long doctorId) throws UserNotFoundException {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.ok(DoctorResponse.builder().message("Doctor deleted successfully").build());
    }
    @GetMapping("/getDoctor/{username}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable String username) throws UserNotFoundException {
        DoctorDTO doctor = doctorService.findDoctorByUsername(username);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping("getAllDoctors")
    public ResponseEntity<List<DoctorRegisterRequest>> getAllDoctors() {
        List<DoctorRegisterRequest> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }


}
