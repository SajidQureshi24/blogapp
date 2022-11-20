package com.springboot.backend.controller;

import java.util.List;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.springboot.backend.model.Employee;
import com.springboot.backend.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		System.out.println("hello");
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED); 
	}
	@GetMapping
	
	public List<Employee> getAllEmployee(){
		System.out.println("ok");
		List<Employee> allEmployees = employeeService.getAllEmployees();
		for(Employee e :allEmployees){
			System.out.println(e.getFirstName());
			System.out.println(e.getLastName());
			System.out.println(e.getEmail());
		}
		return  allEmployees;

	}
	@GetMapping("{id}")
	public Employee getEmployeeById(@PathVariable("id") long id){

		return employeeService.getEmployeeById(id);
	}

	/*public Employee updateEmployee(@RequestBody Employee employee,long id){



		employeeService.updateEmployee(employee,id);
	}*/

}
