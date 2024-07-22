package org.example.ProjectTraninng.Common.DTO;

import lombok.*;
import org.example.ProjectTraninng.Common.Entities.Specialization;

import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private Specialization specialization;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private LocalTime beginTime;
    private LocalTime endTime;
    private Map<String, Object> salary; // Add salary as a Map to handle JSON
}