package org.example.ProjectTraninng.Common.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ProjectTraninng.Common.Entities.Role;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "Password is required")
    private String password;
    @NotNull(message = "First Name is required")
    private String firstName;
    @NotNull(message = "Last Name is required")
    private String lastName;
    @NotNull(message = "Date of Birth is required")
    private Date dateOfBirth;
    @NotNull(message = "Address is required")
    private String address;
    @Pattern(regexp = "^(\\+\\d{1,3}[-]?)?\\d{10}$", message = "Invalid phone number") // +123-1234567890
    private String phone;
    @Email(message = "Invalid email")
    private String email;
    @NotNull(message = "Role is required")
    private Role role;
    @NotNull(message = "Salary is required")
    private Map<String, Object> salary;
}
