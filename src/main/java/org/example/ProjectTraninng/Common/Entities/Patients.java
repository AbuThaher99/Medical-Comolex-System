package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ProjectTraninng.Common.Enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patients extends BaseEntity {



    @Column(name = "age", nullable = false)
    @NotNull(message = "Age is required")
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",nullable = false)
    @JsonBackReference("patientUser")
    private User user;


    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY ,orphanRemoval = true)
    @JoinColumn(name = "patientId" , referencedColumnName = "id")
    @JsonManagedReference("patient-treatment")
    private List<Treatment> treatments; // done

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY ,orphanRemoval = true)
    @JoinColumn(name = "patientId" , referencedColumnName = "id")
    @JsonManagedReference("patient-feedback")
    private List<Feedback> feedbacks;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY ,orphanRemoval = true)
    @JoinColumn(name = "patientId" , referencedColumnName = "id")
    @JsonManagedReference("patient-notification")
    private List<Notification> notifications;


}
