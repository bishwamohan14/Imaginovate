package com.imaginnovate.EmployeeManagement.service;

import com.imaginnovate.EmployeeManagement.Entity.Employee;
import com.imaginnovate.EmployeeManagement.Entity.TaxCalculator;
import com.imaginnovate.EmployeeManagement.Entity.TaxDetails;
import com.imaginnovate.EmployeeManagement.Exception.EmployeeException;
import com.imaginnovate.EmployeeManagement.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public TaxDetails calculateTaxForEmployee(String employeeId) {

        Long employeeIdLong = Long.parseLong(employeeId);

        Employee employee = employeeRepository.findByEmployeeId(String.valueOf(employeeIdLong));

        if (employee == null) {
            throw new EmployeeException("Employee not found with ID: " + employeeId);
        }

        double yearlySalary = calculateYearlySalary(employee);
        double taxAmount = TaxCalculator.calculateTax(yearlySalary);
        double cessAmount = TaxCalculator.calculateCess(yearlySalary);

        TaxDetails taxDetails = new TaxDetails();
        taxDetails.setEmployeeId(Long.valueOf(employee.getEmployeeId()));
        taxDetails.setFirstName(employee.getFirstName());
        taxDetails.setLastName(employee.getLastName());
        taxDetails.setYearlySalary(yearlySalary);
        taxDetails.setTaxAmount(taxAmount);
        taxDetails.setCessAmount(cessAmount);

        return taxDetails;
    }

    private double calculateYearlySalary(Employee employee) {
        return employee.getSalary() * 12;
    }
}
