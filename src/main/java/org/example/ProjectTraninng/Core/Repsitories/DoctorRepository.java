package org.example.ProjectTraninng.Core.Repsitories;




import org.example.ProjectTraninng.Common.Entities.Doctor;
import org.example.ProjectTraninng.Common.Enums.Specialization;
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

    @Query("SELECT d FROM Doctor d WHERE d.id = :id AND d.user.isDeleted = false")
    Optional<Doctor> findById(@Param("id") Long id);

    @Query("select d from Doctor d where d.user.isDeleted = false and " +
            "(:search IS NULL OR :search = '' OR d.user.firstName LIKE %:search% OR d.user.lastName LIKE %:search% OR " +
            "d.user.email LIKE %:search% OR d.user.address LIKE %:search% OR d.user.phone LIKE %:search%) and " +
            "(:specialization IS NULL OR d.specialization = :specialization)")
    Page<Doctor> findAll(Pageable pageable, @Param("search") String search, @Param("specialization") Specialization specialization);
    @Query("SELECT d FROM Doctor d WHERE d.user.isDeleted = false")
    Page<Doctor> findAll(Pageable pageable);
}
