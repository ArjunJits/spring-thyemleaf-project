package com.app.mazda.service;

import com.app.mazda.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    //add this changes

    public Employee getEmployeeById(long id);

    void deleteEmployeeById(long id);

    Page<Employee> findPaginated(int pageNo, int pageSize);
}
