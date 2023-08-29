package com.imaginnovate.EmployeeManagement.Entity;

public class TaxCalculator {
    public static double calculateTax(double yearlySalary) {
        if (yearlySalary <= 250000) {
            return 0;
        } else if (yearlySalary <= 500000) {
            return (yearlySalary - 250000) * 0.05;
        } else if (yearlySalary <= 1000000) {
            return 250000 * 0.05 + (yearlySalary - 500000) * 0.10;
        } else {
            return 250000 * 0.05 + 500000 * 0.10 + (yearlySalary - 1000000) * 0.20;
        }
    }

    public static double calculateCess(double yearlySalary) {
        // Implement cess calculation logic
        // Return the calculated cess amount
        // Example logic: apply 2% cess on income over 2500000
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        } else {
            return 0;
        }
    }
}
