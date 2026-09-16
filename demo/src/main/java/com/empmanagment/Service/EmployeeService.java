package com.empmanagment.Service;

import java.util.List;

import com.empmanagment.model.Employee;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Employee employee);

    void deleteEmployee(int id);
}