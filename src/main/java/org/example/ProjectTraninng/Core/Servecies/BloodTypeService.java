package org.example.ProjectTraninng.Core.Servecies;


import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Common.Enums.BloodTypes;

import org.example.ProjectTraninng.Common.Enums.Gender;
import org.example.ProjectTraninng.Common.Responses.GeneralResponse;
import org.example.ProjectTraninng.Core.Repsitories.*;
import org.example.ProjectTraninng.WebApi.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

@Service
public class BloodTypeService {

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private PatientRepository patientsRepository;

    @Autowired
    private PatientsBloodRepository patientsBloodRepository;

    public void initializeBloodTypes() {
        if (bloodTypeRepository.count() == 0) {
            Arrays.stream(BloodTypes.values()).forEach(bloodTypeEnum -> {
                BloodType bloodType = BloodType.builder()
                        .type(bloodTypeEnum)
                        .quantity(0)
                        .build();
                bloodTypeRepository.save(bloodType);
            });
        }
    }

    public GeneralResponse addDonor(Donor donor) {
        donorRepository.save(donor);
        return GeneralResponse.builder().message("Donor added successfully").build();
    }

    public GeneralResponse deleteDonor(Long donorId) throws UserNotFoundException {
        Donor donor = donorRepository.findById(donorId).orElseThrow(
                () -> new UserNotFoundException("Donor not found"));
        donor.setDeleted(true);
        donorRepository.save(donor);
        return GeneralResponse.builder().message("Donor deleted successfully").build();
    }

    public GeneralResponse updateDonor(Donor donor, Long donorId) throws UserNotFoundException {
        Donor donorOptional = donorRepository.findById(donorId).orElseThrow(
                () -> new UserNotFoundException("Donor not found"));
        donorOptional.setName(donor.getName());
        donorOptional.setPhone(donor.getPhone());
        donorOptional.setBloodType(donor.getBloodType());
        donorOptional.setGender(donor.getGender());
        donorRepository.save(donorOptional);
        return GeneralResponse.builder().message("Donor updated successfully").build();
    }

    public GeneralResponse addDonation(Long donorId, Donation donation) throws UserNotFoundException {
        Donor donor = donorRepository.findById(donorId).orElseThrow(
                () -> new UserNotFoundException("Donor not found"));

        BloodType bloodType = bloodTypeRepository.findByType(donor.getBloodType());
        donation.setDonor(donor);
        donation.setBloodType(bloodType);
        bloodType.setQuantity(bloodType.getQuantity() + donation.getQuantity());
        donationRepository.save(donation);
        bloodTypeRepository.save(bloodType);
        return GeneralResponse.builder().message("Donation added successfully").build();

    }
    public Donor getDonor(Long donorId) throws UserNotFoundException {
        return donorRepository.findById(donorId).orElseThrow(
                () -> new UserNotFoundException("Donor not found"));
    }
    public Page<Donor> getDonors(int page, int size , String search, BloodTypes bloodType, List<Long> donorIds, Gender gender) {
        if(search!=null && search.isEmpty()){
            search = null;
        }

        if(bloodType!=null && !EnumSet.allOf(BloodTypes.class).contains(bloodType)){
            bloodType = null;
        }

        if(donorIds!=null && donorIds.isEmpty()){
            donorIds = null;
        }

        if(gender!=null && !EnumSet.allOf(Gender.class).contains(gender)){
            gender = null;
        }

        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        return donorRepository.findAll(pageable, search, bloodType, donorIds,gender);
    }


    public Donation getDonation(Long donationId) throws UserNotFoundException {
        return donationRepository.findById(donationId).orElseThrow(
                () -> new UserNotFoundException("Donation not found"));

    }

//    public GeneralResponse deleteDonation(Long donationId) throws UserNotFoundException {
//        Donation donation = donationRepository.findById(donationId).orElseThrow(
//                () -> new UserNotFoundException("Donation not found"));
//        BloodType bloodType = donation.getBloodType();
//        bloodType.setQuantity(bloodType.getQuantity() - donation.getQuantity());
//        donation.setDeleted(true);
//        donationRepository.save(donation);
//        bloodTypeRepository.save(bloodType);
//        return GeneralResponse.builder().message("Donation deleted successfully").build();
//    }


    public Page<Donation> getDonations(int page, int size , String search, BloodTypes bloodType, List<Long> donorIds,Integer quantity) {
        if(search!=null && search.isEmpty()){
            search = null;
        }

        if(bloodType!=null && !EnumSet.allOf(BloodTypes.class).contains(bloodType)){
            bloodType = null;
        }

        if(donorIds!=null && donorIds.isEmpty()){
            donorIds = null;
        }

        if(quantity!=null && quantity==0){
            quantity = null;
        }
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        return donationRepository.findAll(pageable, search, bloodType, donorIds , quantity);
    }


    public GeneralResponse takeBlood(PatientsBlood patientsBlood) throws UserNotFoundException {
        System.out.println("Patients ID: " + patientsBlood.getPatients().getId());
        System.out.println("BloodType ID: " + patientsBlood.getPatientsBlood().getId());

        BloodType bloodTypeEntity = bloodTypeRepository.findById(patientsBlood.getPatientsBlood().getId()).orElseThrow(
                () -> new UserNotFoundException("Blood type not found")
        );
        if (bloodTypeEntity.getQuantity() == 0) {
            return GeneralResponse.builder().message("Blood not available").build();
        }
        Patients patients1 = patientsRepository.findById(patientsBlood.getPatients().getId()).orElseThrow(
                () -> new UserNotFoundException("Patient not found"));
        if(patientsBlood.getQuantity() > bloodTypeEntity.getQuantity()){
            return GeneralResponse.builder().message("Blood not available").build();
        }
        System.out.println("Patients Blood Type: " + patients1.getBloodType().getType());
        System.out.println("Blood Type: " + bloodTypeEntity.getType());
        if(bloodTypeEntity.getType() != patients1.getBloodType().getType()){
            return GeneralResponse.builder().message("Blood type not matched").build();
        }

        bloodTypeEntity.setQuantity(bloodTypeEntity.getQuantity() - patientsBlood.getQuantity());
        bloodTypeRepository.save(bloodTypeEntity);

        PatientsBlood patientsBlood1 = PatientsBlood.builder()
                .patients(patients1)
                .patientsBlood(bloodTypeEntity)
                .quantity(patientsBlood.getQuantity())
                .build();
        patientsBloodRepository.save(patientsBlood1);

        return GeneralResponse.builder().message("Blood taken successfully").build();
    }

    public Page<PatientsBlood> getPatientsBlood(int page, int size , String search, List<Long> patientIds, BloodTypes bloodType, Integer quantity) {
        if(search!=null && search.isEmpty()){
            search = null;
        }

        if(bloodType!=null && !EnumSet.allOf(BloodTypes.class).contains(bloodType)){
            bloodType = null;
        }

        if(patientIds!=null && patientIds.isEmpty()){
            patientIds = null;
        }

        if(quantity!=null && quantity==0){
            quantity = null;
        }
        if (page < 1) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        return patientsBloodRepository.findAll(pageable, search, patientIds, bloodType, quantity);
    }


}
