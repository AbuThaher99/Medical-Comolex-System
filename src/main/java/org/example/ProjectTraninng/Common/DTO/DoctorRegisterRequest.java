package org.example.ProjectTraninng.Common.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Specialization;

import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegisterRequest {
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private Specialization specialization;
    private LocalTime beginTime;
    private LocalTime endTime;
    private Map<String, Object> salary;


}
