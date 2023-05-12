package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeServiceImpl;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data

	EmployeeServiceImpl employeeServiceImpl;

	@Autowired // inject
	public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl = employeeServiceImpl;
	} 

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// add to the spring model
		List<Employee> theEmployees = this.employeeServiceImpl.findAll();
		theModel.addAttribute("employees", theEmployees);
		return "list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String save(Employee employee) {
		this.employeeServiceImpl.save(employee);
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int employeeId, Model model) {
		Employee employee = this.employeeServiceImpl.findById(employeeId);
		model.addAttribute("employee", employee);
		return "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int employeeId, Model model) {
		this.employeeServiceImpl.deleteById(employeeId);
		return "redirect:/employees/list";
	}

}









