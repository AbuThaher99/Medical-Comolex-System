package org.example.ProjectTraninng.Core.Repsitories;

import org.example.ProjectTraninng.Common.Entities.User;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = false")
    Optional<User> findByEmail(String email);
    // make a query to get the user when the user is deleted equal to false
    @Query("SELECT u FROM User u WHERE u.isDeleted = false AND u.id = :id")
    Optional<User> findById(@Param("id") Long id);
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.isDeleted = false")
    Page<User> findAllByRole(@Param("role") Role role, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.isDeleted = false ")
    Page<User> findAll(Pageable pageable );
    @Query("SELECT u FROM User u WHERE u.isDeleted = false AND (u.firstName LIKE %:search% or u.lastName LIKE %:search% or u.email LIKE %:search% or u.phone LIKE %:search% or u.address LIKE %:search% ) and u.role = :role")
    Page<User> findAll(Pageable pageable , @Param("search") String search , @Param("role") Role role);
}
