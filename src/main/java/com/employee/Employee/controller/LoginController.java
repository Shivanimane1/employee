package com.employee.Employee.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.employee.Employee.entity.Employee;
import com.employee.Employee.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("employee", new Employee());
		return mav;
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {

		System.out.println("Received login request with email: " + email + " and password: " + password);

		Optional<Employee> emp = employeeRepository.findByEmailAndPassword(email, password);

		if (emp.isPresent()) {
			return ResponseEntity.ok("Login successful!");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
	public ModelAndView logoutDo(HttpServletRequest request, HttpServletResponse response) {
		// Perform logout actions (if any)
		ModelAndView mav = new ModelAndView("redirect:/login");
		return mav;
	}

}
