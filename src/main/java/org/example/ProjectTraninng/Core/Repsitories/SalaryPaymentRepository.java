package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.SalaryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryPaymentRepository extends JpaRepository<SalaryPayment, Long> {
    @Query("SELECT sp FROM SalaryPayment sp WHERE sp.user.id IN :userIds")
    List<SalaryPayment> findByUserIdIn(@Param("userIds") List<Long> userIds);
}