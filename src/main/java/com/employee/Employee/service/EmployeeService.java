package com.employee.Employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Employee.entity.Employee;
import com.employee.Employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id);
	}
}

	/*
	 * public Optional<Employee> findByEmailAndPassword(String email, String
	 * password) { return employeeRepository.findByEmailAndPassword(email,
	 * password); }
	 * 
	 * 
	 * public List<Employee> listAll() { return employeeRepository.findAll(); }
	 */
	/*
	 * public void save(Employee emp) { employeeRepository.save(emp); }
	 * 
	 * public Employee get(Long empid) { return
	 * employeeRepository.findById(empid).get(); }
	 * 
	 * public void delete(Long empid) { employeeRepository.deleteById(empid); }
	 */

