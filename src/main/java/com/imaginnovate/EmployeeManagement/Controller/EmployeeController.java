package com.imaginnovate.EmployeeManagement.Controller;


import com.imaginnovate.EmployeeManagement.Entity.Employee;
import com.imaginnovate.EmployeeManagement.Entity.TaxDetails;
import com.imaginnovate.EmployeeManagement.Exception.EmployeeException;
import com.imaginnovate.EmployeeManagement.Repository.EmployeeRepository;
import com.imaginnovate.EmployeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>("Employee created successfully", HttpStatus.CREATED);
    }


    @GetMapping("/{employeeId}/tax")
    public ResponseEntity<TaxDetails> getTaxDetails(@PathVariable String employeeId) {
        TaxDetails taxDetails = employeeService.calculateTaxForEmployee(employeeId);

        if (taxDetails == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(taxDetails, HttpStatus.OK);
    }
}
