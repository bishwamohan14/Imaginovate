package com.imaginnovate.EmployeeManagement.Repository;

import com.imaginnovate.EmployeeManagement.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmployeeId(String employeeId);
}
