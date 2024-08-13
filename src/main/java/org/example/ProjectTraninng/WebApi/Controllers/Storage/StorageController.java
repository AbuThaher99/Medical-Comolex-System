package org.example.ProjectTraninng.WebApi.Controllers.Storage;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.FileData;
import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Core.Servecies.AuthenticationService;
import org.example.ProjectTraninng.Core.Servecies.StorageService;
import org.example.ProjectTraninng.SessionManagement;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController extends SessionManagement {
    private final StorageService service;
    private final AuthenticationService authenticationService;

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file, HttpServletRequest httpServletRequest) throws IOException, UserNotFoundException {
        String token = authenticationService.extractToken(httpServletRequest);
        User user = authenticationService.extractUserFromToken(token);
        validateLoggedInAllUser(user);
        String uploadImage = service.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(uploadImage);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName,HttpServletRequest httpServletRequest) throws IOException, UserNotFoundException {
        String token = authenticationService.extractToken(httpServletRequest);
        User user = authenticationService.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        FileData imageData = service.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(imageData.getType()))
                .body(imageData.getData());
    }

    @GetMapping("/patientExcel")
    public ResponseEntity<?> generatePatientToExcel(HttpServletRequest httpServletRequest) throws IOException, UserNotFoundException {
        String token = authenticationService.extractToken(httpServletRequest);
        User user = authenticationService.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        String filePath = service.GanratePationToExcel();
        return ResponseEntity.status(HttpStatus.OK)
                .body(filePath);
    }

    @GetMapping("/medicineExcel")
    public ResponseEntity<?> generateMedicineToExcel(HttpServletRequest httpServletRequest) throws IOException, UserNotFoundException {
        String token = authenticationService.extractToken(httpServletRequest);
        User user = authenticationService.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        String filePath = service.GanarateMedicineToExcel();
        return ResponseEntity.status(HttpStatus.OK)
                .body(filePath);
    }

    @GetMapping("/patientTreatmentExcel")
    public ResponseEntity<?> generatePatientTreatmentToExcel(HttpServletRequest httpServletRequest) throws IOException, UserNotFoundException {
        String token = authenticationService.extractToken(httpServletRequest);
        User user = authenticationService.extractUserFromToken(token);
        validateLoggedInSecretary(user);
        String filePath = service.GanaratePatientTreatmentToExcel();
        return ResponseEntity.status(HttpStatus.OK)
                .body(filePath);
    }


}
