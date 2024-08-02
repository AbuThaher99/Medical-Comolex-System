package org.example.ProjectTraninng.Common.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import org.example.ProjectTraninng.Common.Enums.Role;
import org.example.ProjectTraninng.Common.Converters.MapToJsonConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image")
    private String image;


    @Column(name = "password", nullable = false)
    @NotNull(message = "Password cannot be blank")
    private String password;

    @Column(name = "firstName", nullable = false)
    @NotNull(message = "First name cannot be blank")
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotNull(message = "Last name cannot be blank")
    private String lastName;

    @Column(name = "address" , nullable = false)
    @NotNull(message = "Address cannot be blank")
    private String address;

    @Column(name = "phone" , nullable = false)
    @NotNull(message = "Phone cannot be blank")
    private String phone;

    @Column(name = "dateOfBirth" , nullable = false)
    @NotNull(message = "Date of birth cannot be blank")
    private Date dateOfBirth;

    @Column(name = "email", unique = true, nullable = false)
    @NotNull(message = "Email cannot be blank")
    private String email;

    @Column(name = "salary", columnDefinition = "JSON")
    @Convert(converter = MapToJsonConverter.class)
    @NotNull(message = "Salary cannot be blank")
    private Map<String, Object> salary;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Token> tokens;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonBackReference("doctorUser")
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "headId" , referencedColumnName = "id")
    @JsonManagedReference("headUser")
    private List<Department> headId;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "secretaryId" , referencedColumnName = "id")
    @JsonManagedReference("secretaryUser")
    private List<Department> secretaryId;

    @Column(name = "createdDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date createdDate;

    @Column(name = "isDeleted" , nullable = false )
    @JsonIgnore
    private boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "salaryId" , referencedColumnName = "id")
    @JsonManagedReference("salaryUser")
    private List<SalaryPayment> salaryId;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email; // Use email as the username
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
