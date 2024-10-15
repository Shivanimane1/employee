package com.employee.Employee.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.employee.Employee.entity.Employee;

import com.employee.Employee.repository.EmployeeRepository;
	
import com.employee.Employee.service.EmployeeService;



@Controller
public class EmployeeController {
	
	

	@Autowired
	private EmployeeService employeeService;
	
	private CrudRepository<Employee, Long> employeeRepository;

	@GetMapping("/")
    public String home() {
        return "home"; // This should match the name of your HTML file (home.html)
    }

	@GetMapping("/employee_register")
	public String employeeRegister() {
		return "employeeRegister";
	}

	@GetMapping("/employeeList")
	public String employeeList() {
		return "employeeList";
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<String> addEmployee(@ModelAttribute Employee e) {
		employeeService.save(e);
		return new ResponseEntity<>("Employee added successfully", HttpStatus.OK);
	}

	@GetMapping("/existing_employees")
	@ResponseBody
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	@GetMapping("/get_employee/{id}")
	@ResponseBody
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> emp = employeeService.findById(id);
		if (emp.isPresent()) {
			return new ResponseEntity<>(emp.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
//	@PutMapping("/editData")
//	public ResponseEntity<String> updateEmployeeById(@RequestBody Employee employee) {
//	    try {
//	        Optional<Employee> emp = employeeRepository.findById(employee.getId());
//	        if (emp.isPresent()) {
//	            Employee existingEmployee = emp.get();
//	            existingEmployee.setName(employee.getName());
//	            existingEmployee.setSalary(employee.getSalary());
//	            existingEmployee.setEmail(employee.getEmail());
//	            existingEmployee.setPassword(employee.getPassword());
//	            
//	            employeeRepository.save(existingEmployee);
//	            return ResponseEntity.ok("Employee details against id " + employee.getId() + " updated");
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee details do not exist for id " + employee.getId());
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee details for id " + employee.getId());
//	    }
//	}

	
	
//	@PutMapping("/editData")
//    public ResponseEntity<String> updateEmployeeById(@RequestBody Employee employee) {
//        try {
//            Optional<Employee> emp = employeeRepository.findById(employee.getId());
//            if (emp.isPresent()) {
//                Employee existingEmployee = emp.get();
//                existingEmployee.setName(employee.getName());
//                existingEmployee.setSalary(employee.getSalary());
//                existingEmployee.setEmail(employee.getEmail());
//                existingEmployee.setPassword(employee.getPassword());
//                
//                employeeRepository.save(existingEmployee);
//                return ResponseEntity.ok("Employee details against id " + employee.getId() + " updated");
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee details do not exist for id " + employee.getId());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee details for id " + employee.getId());
//        }
//	}

	@PutMapping("/editData")
	public ResponseEntity<String> updateEmployeeById(@RequestBody Employee employee) {
	    try {
	        Optional<Employee> emp = employeeRepository.findById(employee.getId());
	        if (emp.isPresent()) {
	            Employee existingEmployee = emp.get();
	            existingEmployee.setName(employee.getName());
	            existingEmployee.setSalary(employee.getSalary());
	            existingEmployee.setEmail(employee.getEmail());
	            existingEmployee.setPassword(employee.getPassword());

	            employeeRepository.save(existingEmployee);
	            return ResponseEntity.ok("Employee details updated successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with id: " + employee.getId());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee details: " + e.getMessage());
	    }
	}


	
	/*
	 * @DeleteMapping("/deleteData/{id}")
	 * 
	 * @ResponseBody public String deleteEmployeeById(@PathVariable Long id) {
	 * employeeRepository.deleteById(id); return
	 * "Product Details Deleted Successfully"; }
	 */
	
//	@DeleteMapping("/deleteData/{id}")
//    public String deleteEmployeeById(@PathVariable Long id) {
//        if (employeeRepository.existsById(id)) {
//            employeeRepository.deleteById(id);
//            return "Employee Details Deleted Successfully";
//        } else {
//            return "Employee Not Found";
//        }
//    }
	
	@DeleteMapping("/deleteData/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
	    if (employeeRepository.existsById(id)) {
	        employeeRepository.deleteById(id);
	        return ResponseEntity.ok("Employee Details Deleted Successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
	    }
	}

}


