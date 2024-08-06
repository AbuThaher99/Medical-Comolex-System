package org.example.ProjectTraninng.Core.Servecies;

import jakarta.mail.MessagingException;
import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Core.Repsitories.PatientRepository;
import org.example.ProjectTraninng.Core.Repsitories.TreatmentRepository;
import org.example.ProjectTraninng.Core.Repsitories.UserRepository;
import org.example.ProjectTraninng.Core.Repsitories.WarehouseStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseStoreRepository warehouseStoreRepository;


    @Scheduled(cron = "0 43 10 * * ?")
    public void sendTreatmentNotifications() {
        List<Treatment> todayTreatments = treatmentRepository.findTodayTreatments();
        System.out.println("Today's Treatments: " + todayTreatments.size());
        for (Treatment treatment : todayTreatments) {
            Doctor doctor = treatment.getDoctor();
            User user = doctor.getUser();
            String doctorEmail = user.getEmail();
            Long patient = treatment.getPatient().getId();
            Patients patients = patientRepository.findById(patient).get();
            String subject = "Today's Treatment Notification";
            String message = "Dear Dr. " + user.getFirstName() + " " + user.getLastName() + ",<br>"
                    + "You have a treatment scheduled for today.<br>"
                    + "Patient: " + patients.getFirstName() + " " + patients.getLastName() + "<br>"
                    + "Disease Description: " + treatment.getDiseaseDescription() + "<br>"
                    + "Note: " + treatment.getNote() + "<br>"
                    + "Please be prepared.";

            try {
                emailService.sentNotificationEmail(doctorEmail, subject, message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 20 11 * * ?")
    public void sendLowStockNotifications() {
        List<WarehouseStore> lowStockMedicines = warehouseStoreRepository.findLowStockMedicines();

        if (!lowStockMedicines.isEmpty()) {
            List<User> warehouseEmployees = userRepository.findAllByRole(Role.WAREHOUSE_EMPLOYEE);
            String subject = "Low Stock Alert";
            StringBuilder message = new StringBuilder("The following medicines are low in stock:\n");

            for (WarehouseStore warehouseStore : lowStockMedicines) {
                message.append("Medicine: ").append(warehouseStore.getMedicine().getName())
                        .append(", Quantity: ").append(warehouseStore.getQuantity()).append("\n");
            }

            for (User employee : warehouseEmployees) {
                try {
                    emailService.sentNotificationEmail(employee.getEmail(), subject, message.toString());
                } catch (MessagingException e) {
                    // Handle the exception (e.g., log the error)
                    e.printStackTrace();
                }
            }
        }
    }

}
