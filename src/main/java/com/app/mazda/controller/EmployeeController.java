package com.app.mazda.controller;

import com.app.mazda.model.Employee;
import com.app.mazda.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String listEmployees(Model model) {
        //logger are used to log the information about the employees
        return findPaginated(1, model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        //Model is used to pass the data from controller to view
        model.addAttribute("employee", new Employee());
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        logger.info(" Saving employee: " + employee.getFirstName() + " " + employee.getLastName() + " with email: "
                + employee.getEmail() + " and department: " + employee.getEmail() + "");
        employeeService.saveEmployee(employee);
        return "redirect:/employee/list";
    }
    @GetMapping("/showFormUpdate/{id}")
    public String showFormUpdate(@PathVariable (value="id") long id,Model model) {
        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable (value="id") long id) {
        // call delete employee method from the service
        employeeService.deleteEmployeeById(id);
        return "redirect:/employee/list";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page< Employee > page = employeeService.findPaginated(pageNo, pageSize);
        List< Employee > listEmployees = new ArrayList<>(page.getContent());

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }

}
