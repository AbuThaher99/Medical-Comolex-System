package org.example.ProjectTraninng.Core.Repsitories;




import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.user.email = :email")
    Optional<Doctor> findByUserEmail(@Param("email") String email);
}
