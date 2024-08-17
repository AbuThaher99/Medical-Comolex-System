package org.example.ProjectTraninng.Core.Servecies;

import jakarta.mail.MessagingException;
import org.example.ProjectTraninng.Common.Entities.*;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Core.Repsitories.*;
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

    @Autowired
    private NotificationRepository notificationRepository;


    @Scheduled(cron = "0 43 10 * * ?")
    public void sendTreatmentNotificationsDoctor() {
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
                    + "Patient: " + patients.getUser().getFirstName() + " " + patients.getUser().getLastName() + "<br>"
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
                    e.printStackTrace();
                }
            }
        }
    }


    public void createNotification(Patients patient, String message) {
        Notification notification = Notification.builder()
                .patient(patient)
                .message(message)
                .build();
        notificationRepository.save(notification);
    }

    public List<Notification> getUnreadNotifications(Long patientId) {
        return notificationRepository.findByPatientIdAndIsReadFalse(patientId);
    }

    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setRead(true);
        notificationRepository.save(notification);
    }


    @Scheduled(cron = "0 53 16 * * ?")
    public void sendTreatmentNotifications() {
        List<Treatment> todayTreatments = treatmentRepository.findTodayTreatments();

        for (Treatment treatment : todayTreatments) {
            Patients patient = treatment.getPatient();
            String message = "You have a treatment scheduled for today with Dr. " +
                    treatment.getDoctor().getUser().getFirstName() + " " +
                    treatment.getDoctor().getUser().getLastName() + ".\n" +
                    "Disease Description: " + treatment.getDiseaseDescription() + "\n" +
                    "Note: " + treatment.getNote();
                    createNotification(patient, message);
                    treatment.setNotificationSent(true);
                    treatmentRepository.save(treatment);

        }
    }

}
