package org.example.ProjectTraninng.WebApi.Controllers.Secrerary.Blood;

import lombok.AllArgsConstructor;
import org.example.ProjectTraninng.Common.DTOs.PaginationDTO;
import org.example.ProjectTraninng.Common.Entities.Donation;
import org.example.ProjectTraninng.Common.Entities.Donor;
import org.example.ProjectTraninng.Common.Entities.PatientsBlood;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;
import org.example.ProjectTraninng.Common.Enums.Gender;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Servecies.BloodTypeService;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/blood")
public class BloodController {
    @Autowired
    private final BloodTypeService bloodService;

    @PostMapping("/donor")
    public GeneralResponse addDonor(@RequestBody Donor donor) {
        return bloodService.addDonor(donor);
    }


    @PutMapping("/donor/{donorId}")
    public GeneralResponse updateDonor(@RequestBody Donor donor, @PathVariable Long donorId) throws UserNotFoundException {
        return bloodService.updateDonor(donor, donorId);
    }


    @DeleteMapping("/donor/{donorId}")
    public GeneralResponse deleteDonor(@PathVariable Long donorId) throws UserNotFoundException {
        return bloodService.deleteDonor(donorId);
    }


    @GetMapping("/donor/{donorId}")
    public Donor getDonor(@PathVariable Long donorId) throws UserNotFoundException {
        return bloodService.getDonor(donorId);
    }


    @GetMapping("/donors")
    public PaginationDTO<Donor> getDonors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false ,defaultValue = "") String search,
            @RequestParam(required = false ,defaultValue = "") BloodTypes bloodType,
            @RequestParam(required = false ,defaultValue = "") List<Long> donorIds,
            @RequestParam(required = false ,defaultValue = "") Gender gender) {
        return bloodService.getDonors(page, size, search, bloodType, donorIds, gender);
    }

    @PostMapping("/donation/{donorId}")
    public GeneralResponse addDonation(@PathVariable Long donorId, @RequestBody Donation donation) throws UserNotFoundException {
        return bloodService.addDonation(donorId, donation);
    }


    @GetMapping("/donation/{donationId}")
    public Donation getDonation(@PathVariable Long donationId) throws UserNotFoundException {
        return bloodService.getDonation(donationId);
    }

    @GetMapping("/donations")
    public PaginationDTO<Donation> getDonations(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false ,defaultValue = "") String search,
            @RequestParam(required = false ,defaultValue = "") BloodTypes bloodType,
            @RequestParam(required = false ,defaultValue = "") List<Long> donorIds,
            @RequestParam(required = false ,defaultValue = "") Integer quantity) {
        return bloodService.getDonations(page, size, search, bloodType, donorIds, quantity);
    }

    @PostMapping("/takeBlood")
    public GeneralResponse takeBlood(@RequestBody PatientsBlood patientsBlood) throws UserNotFoundException {
        return bloodService.takeBlood(patientsBlood);
    }

    @GetMapping("/patientsBlood")
    public PaginationDTO<PatientsBlood> getPatientsBlood(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false ,defaultValue = "") String search,
            @RequestParam(required = false ,defaultValue = "") BloodTypes bloodType,
            @RequestParam(required = false ,defaultValue = "") List<Long> patientIds,
            @RequestParam(required = false ,defaultValue = "") Integer quantity) {
        return bloodService.getPatientsBlood(page, size, search, patientIds ,bloodType, quantity);
    }


}
