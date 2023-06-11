package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private EmployeeService employeeService;
	EmployeeController(EmployeeService theemployeeService){
		employeeService=theemployeeService;
	}

	// load employee data




/*  creating seed data
	@PostConstruct
	private void loadData() {

		// create employees
		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");

		// create the list
		theEmployees = new ArrayList<>();

		// add to the list
		theEmployees.add(emp1);
		theEmployees.add(emp2);
		theEmployees.add(emp3);
	}
*/
	// add mapping for "/list"



	@GetMapping("/list")
	public String listEmployees(Model theModel){
		List<Employee> theEmployees= employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return  "List";
	}


	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee){
		if(employee.getFirstName()!="" && employee.getLastName()!=""){
			System.out.println(employee.getFirstName());
			System.out.println("hello");
			employeeService.save(employee);
		}


		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam int id){
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}
	@GetMapping("/update")
	public String update(@RequestParam(required = false) int id,Model model){
          Employee employee=employeeService.findById(id);
		  model.addAttribute("employee",employee);
		  return "Form";
	}

	@GetMapping("/newEmp")
	public String saveNewEmplooye(Model model){
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "Form";

	}

}









