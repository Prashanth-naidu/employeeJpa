package com.example.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeJpaRepository;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeJpaService implements EmployeeRepository {

    @Autowired
    private EmployeeJpaRepository db;

    @Override
    public ArrayList<Employee> getEmployees() {
        return (ArrayList<Employee>) db.findAll();
    }

    @Override

    public Employee getEmployeeById(int employeeId) {
        try {
            Employee e = db.findById(employeeId).get();
            return e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Employee addEmployee(Employee employee) {
        db.save(employee);

        return employee;
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee employee) {
        try {
            Employee e = db.findById(employeeId).get();
            if (e.getEmployeeName() != null) {
                e.setEmployeeName(employee.getEmployeeName());
            }

            if (e.getEmail() != null) {
                e.setEmail(employee.getEmail());
            }

            if (e.getDepartment() != null) {
                e.setDepartment(employee.getDepartment());
            }

            db.save(e);
            return e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        try {
            db.deleteById(employeeId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
