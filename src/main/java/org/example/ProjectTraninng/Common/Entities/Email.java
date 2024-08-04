package org.example.ProjectTraninng.Common.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emails")
public class Email extends BaseEntity {

    @Column(name = "email", nullable = false)
    private String email;


    @Column(name = "verified", nullable = false)
    private boolean verified;


    @Column(name = "verificationCode", nullable = false)
    private String verificationCode;




}
