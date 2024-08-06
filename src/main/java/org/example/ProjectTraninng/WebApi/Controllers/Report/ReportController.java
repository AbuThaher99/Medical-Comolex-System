package org.example.ProjectTraninng.WebApi.Controllers.Report;

import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Components.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping("/generateSalary")
    public ResponseEntity<GeneralResponse> generateReport(@RequestParam List<Role> roles,
                                         @RequestParam String headerBgColor,
                                         @RequestParam String headerTextColor,
                                         @RequestParam String tableRowColor1,
                                         @RequestParam String tableRowColor2) {
        try {
            return new ResponseEntity<>(pdfGenerator.generatePdfForRoles(roles , headerBgColor, headerTextColor, tableRowColor1, tableRowColor2), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GeneralResponse.builder().message("Error generating PDF").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generateTreatmentProfits")
    public ResponseEntity<GeneralResponse> generatePdfForTreatments(@RequestParam String headerBgColor,
                                           @RequestParam String headerTextColor,
                                           @RequestParam String tableRowColor1,
                                           @RequestParam String tableRowColor2) {
        try {
            return new ResponseEntity<>( pdfGenerator.generatePdfForTreatments( headerBgColor, headerTextColor, tableRowColor1, tableRowColor2), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(GeneralResponse.builder().message("Error generating PDF").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
