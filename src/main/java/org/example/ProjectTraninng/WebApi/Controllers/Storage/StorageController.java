package org.example.ProjectTraninng.WebApi.Controllers.Storage;

import lombok.RequiredArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.FileData;
import org.example.ProjectTraninng.Core.Servecies.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService service;

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = service.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        FileData imageData = service.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(imageData.getType()))
                .body(imageData.getData());
    }

    @GetMapping("/patientExcel")
    public ResponseEntity<?> generatePatientToExcel() throws IOException {
        String filePath = service.GanratePationToExcel();
        return ResponseEntity.status(HttpStatus.OK)
                .body(filePath);
    }

    @GetMapping("/medicineExcel")
    public ResponseEntity<?> generateMedicineToExcel() throws IOException {
        String filePath = service.GanarateMedicineToExcel();
        return ResponseEntity.status(HttpStatus.OK)
                .body(filePath);
    }

    @GetMapping("/patientTreatmentExcel")
    public ResponseEntity<?> generatePatientTreatmentToExcel() throws IOException {
        String filePath = service.GanaratePatientTreatmentToExcel();
        return ResponseEntity.status(HttpStatus.OK)
                .body(filePath);
    }


}
