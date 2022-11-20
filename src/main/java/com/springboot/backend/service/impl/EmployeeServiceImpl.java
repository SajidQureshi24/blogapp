package com.springboot.backend.service.impl;

import java.util.List;

import com.springboot.backend.exception.EmptyInputException;
import com.springboot.backend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.backend.model.Employee;
import com.springboot.backend.repository.EmployeeRespository;
import com.springboot.backend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {


	private EmployeeRespository employeeRespository;


	public EmployeeServiceImpl(EmployeeRespository employeeRespository) {
		super();
		this.employeeRespository = employeeRespository;
	}


	@Override
	public Employee saveEmployee(Employee employee) {

		if (employee.getFirstName().isEmpty() || employee.getLastName().isEmpty() || employee.getEmail().isEmpty())

			throw new EmptyInputException("Employee", "Input fields are emplty", employee.getId());

		return employeeRespository.save(employee);
	}


	//get all

	@Override
	public List<Employee> getAllEmployees() {

		List<Employee> allEmployee = employeeRespository.findAll();
		if(allEmployee.size()==0 || allEmployee.isEmpty()){

			throw new ResourceNotFoundException("Employee","No Data present In Db,Please Insert First.","601");

		}
		return allEmployee;

	}
	//get employee by id

	@Override
	public Employee getEmployeeById(long id) {

		return employeeRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Record not available in the db releted to this id " + id, id));

	}
}




