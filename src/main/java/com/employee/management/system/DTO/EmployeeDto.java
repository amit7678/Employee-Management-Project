package com.employee.management.system.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class EmployeeDto {

    private String id;

    @NotEmpty(message="first name cannot be blank or null")
    @Size(min=2 , max=5, message = "min 2 and max 5 character is allowed for First Name")
    private String firstName;

    @NotEmpty(message="Last name cannot be blank or null")
    @Size(min=2 , max=5, message = "min 2 and max 5 character is allowed for Last Name")
    private String LastName;

    @NotEmpty(message="Age cannot be blank or null")
    @Size(max=64)
    private String empAge;

    @NotEmpty(message="Email cannot be blank or null")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String empEmail;

    @NotEmpty(message="ContactNumber cannot be blank or null")
    @Size(min=10 , max=10, message = "Please entered your 10 digit mobile number")
    private String empPhoneNumber;
}
