package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.Student;
import in.ashokit.repository.StudentRepository;
import in.ashokit.service.StudentService;
import org.springframework.web.bind.annotation.RequestParam;


@org.springframework.stereotype.Controller

public class Controller {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("/home")
    public String home() {
    	
    	return "home";  //view page html file
    }
	
	
	@GetMapping("/students")
	public String getAllStudents(Model model) {
	     model.addAttribute("students", service.getAllStudents());
	     List<Student> allStudents = service.getAllStudents();
	     
	     System.out.println(allStudents);
	
	    return "students"; //view
	}	
	
	@GetMapping("students/new")
	public String createStudentForm(Model model) {
		
		Student student = new Student(); //to hold the student object
		
		model.addAttribute("student", student);
		
		
		return"create-student";
	}
	
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		service.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("students/edit/{id}")
	public String editStudentForm(@PathVariable int id  ,Model model) {
		
		model.addAttribute("student", service.getById(id));
		
		return "edit_student";
		
		
	}
	
	
	@PostMapping("/students/edit/{id}")
	public String updateStudent(@PathVariable int id, @ModelAttribute("student") Student student) {
		
		Student existingStudent=service.getById(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
	
	    service.saveStudent(existingStudent);
	    return "redirect:/students";
	}
	
	
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	
	@GetMapping("/students/{id}")
	public String deleteById(@PathVariable int id) {
		
		service.deleteById(id);
		
		return "redirect:/students";
	}
	
	
}
