package com.employee.management.system.Entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name="EmployeeRecords")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstName;

    private String LastName;

    private String empAge;

    private String empEmail;

    private String empPhoneNumber;
}
