package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface DepartmentRepsitory extends JpaRepository<Department, Long>{
    @Query("SELECT d FROM Department d WHERE d.id=:id and d.isDeleted = false")
    Optional<Department> findById(@Param("id") Long departmentId);
    @Modifying
    @Transactional
    @Query("UPDATE Department d SET d.headId = null WHERE d.headId.id = :id")
   void setHeadIdToNull(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Department d SET d.secretaryId = null WHERE d.secretaryId.id = :id")
   void setSecretaryIdToNull(@Param("id")Long id);
    @Query("SELECT d FROM Department d WHERE d.isDeleted = false and (:search IS NULL or :search = '' or" +
            " d.name like %:search% or d.headId.firstName like %:search% or d.headId.lastName like %:search% or " +
            "d.secretaryId.firstName like %:search% or d.secretaryId.lastName like %:search%)")
    Page<Department> findAll(Pageable pageable , @Param("search") String name);

}
