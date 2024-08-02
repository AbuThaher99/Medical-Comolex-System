package org.example.ProjectTraninng.Core.Servecies;

import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Core.Repsitories.SalaryPaymentRepository;
import org.example.ProjectTraninng.Core.Repsitories.UserRepository;
import org.example.ProjectTraninng.Common.Entities.SalaryPayment;
import org.example.ProjectTraninng.Common.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalaryPaymentService {

    @Autowired
    private SalaryPaymentRepository salaryPaymentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<SalaryPayment> getSalaryPaymentsByRoles(List<Role> roles) {
        List<User> users = userRepository.findByRoleIn(roles);
        List<Long> userIds = users.stream().map(User::getId).collect(Collectors.toList());
        return salaryPaymentRepository.findByUserIdIn(userIds);
    }

    public Map<String, Double> getTotalAmountByMonth(List<SalaryPayment> payments) {
        return payments.stream()
                .collect(Collectors.groupingBy(
                        payment -> {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(payment.getPaymentDate());
                            return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
                        },
                        Collectors.summingDouble(SalaryPayment::getAmount)));
    }

    public double getTotalAmount(List<SalaryPayment> payments) {
        return payments.stream()
                .mapToDouble(SalaryPayment::getAmount)
                .sum();
    }

    public List<User> getUsersByRoles(List<Role> roles) {
        return userRepository.findByRoleIn(roles);
    }



    @Scheduled(cron = "0 0 0 1 * ?") // Runs at midnight on the first day of each month
    public void processSalaries() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            Map<String, Object> salary = user.getSalary();
            String salaryType = (String) salary.get("salaryType");
            if ("MONTHLY".equals(salaryType)) {
                processMonthlySalary(user, salary);
            } else if ("HOURLY".equals(salaryType)) {
                processHourlySalary(user, salary);
            }
        }
    }

    private void processMonthlySalary(User user, Map<String, Object> salary) {
        Double salaryAmount = (Double) salary.get("salaryAmount");
        SalaryPayment payment = SalaryPayment.builder()
                .user(user)
                .paymentDate(new Date())
                .amount(salaryAmount)
                .salaryType("MONTHLY")
                .build();
        salaryPaymentRepository.save(payment);
    }

    private void processHourlySalary(User user, Map<String, Object> salary) {
        Double hourRate = (Double) salary.get("hourRate");
        Integer hourWork = (Integer) salary.get("hourWork");
        Double totalPayment = hourRate * hourWork;
        SalaryPayment payment = SalaryPayment.builder()
                .user(user)
                .paymentDate(new Date())
                .amount(totalPayment)
                .salaryType("HOURLY")
                .build();
        salaryPaymentRepository.save(payment);
    }
}