package org.example.ProjectTraninng.Core.Repsitories;




import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.user.email = :email AND d.user.isDeleted = false")
    Optional<Doctor> findByUserEmail(@Param("email") String email);

    @Query("SELECT d FROM Doctor d WHERE d.user.id = :id AND d.user.isDeleted = false")
    Optional<Doctor> findById(@Param("id") Long id);

    @Query("SELECT d FROM Doctor d WHERE d.user.isDeleted = false")
    Page<Doctor> findAll(Pageable pageable);
}
