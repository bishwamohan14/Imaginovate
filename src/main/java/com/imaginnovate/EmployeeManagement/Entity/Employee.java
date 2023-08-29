package com.imaginnovate.EmployeeManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String employeeId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @ElementCollection
    private List<String> phoneNumbers;

    @NotNull
    private LocalDate doj;

    @NotNull
    private double salary;


    public <T> void setPhoneNumber(List<T> phoneNumber) {
        this.phoneNumbers = (List<String>) phoneNumber;
    }
}
