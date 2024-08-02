package org.example.ProjectTraninng.Common.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDTO {
    private String email;
    private String verificationCode;
    private String newPassword;
}