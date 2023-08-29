package com.imaginnovate.EmployeeManagement.Controller;

import com.imaginnovate.EmployeeManagement.Entity.Employee;
import com.imaginnovate.EmployeeManagement.Entity.TaxDetails;
import com.imaginnovate.EmployeeManagement.Repository.EmployeeRepository;
import com.imaginnovate.EmployeeManagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {


    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateEmployee() {
        Employee employee = new Employee();
        employee.setEmployeeId("123");
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john@example.com");
        employee.setPhoneNumber(Collections.singletonList("123-456-7890"));
        employee.setDoj(LocalDate.parse("2023-08-30"));
        employee.setSalary(60000);

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        ResponseEntity<String> response = employeeController.createEmployee(employee);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Employee created successfully", response.getBody());

        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    public void testGetTaxDetails() {
        String employeeId = "123";

        TaxDetails taxDetails = new TaxDetails();
        taxDetails.setEmployeeId(Long.valueOf(employeeId));
        taxDetails.setFirstName("John");
        taxDetails.setLastName("Doe");
        taxDetails.setYearlySalary(720000);
        taxDetails.setTaxAmount(36000);
        taxDetails.setCessAmount(720);

        when(employeeService.calculateTaxForEmployee(employeeId)).thenReturn(taxDetails);

        ResponseEntity<TaxDetails> response = employeeController.getTaxDetails(employeeId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(taxDetails, response.getBody());

        verify(employeeService, times(1)).calculateTaxForEmployee(employeeId);
    }

}