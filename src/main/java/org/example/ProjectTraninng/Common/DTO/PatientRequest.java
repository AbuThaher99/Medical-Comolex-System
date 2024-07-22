package org.example.ProjectTraninng.Common.DTO;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
    private String phone;

}