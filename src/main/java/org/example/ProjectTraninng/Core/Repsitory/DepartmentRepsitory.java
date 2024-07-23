package org.example.ProjectTraninng.Core.Repsitory;

import org.example.ProjectTraninng.Common.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DepartmentRepsitory extends JpaRepository<Department, Long>{
    Optional<Department> findById(Long departmentId);

}
