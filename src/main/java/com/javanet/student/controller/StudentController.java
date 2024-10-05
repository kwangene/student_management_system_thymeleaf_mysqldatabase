package com.javanet.student.controller;

import com.javanet.student.model.Student;
import com.javanet.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;
     // Handler method to handle  list students and return model and view
    @GetMapping("/students")
    public String listStudent(Model model){
        model.addAttribute("students",service.getAllStudents());
        return "students";
    }
    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        //Create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student",student);
        return "create_student";

    }
     @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student){
        service.saveStudent(student);
        return "redirect:/students";

//         @GetMapping("/students")
//         public String listStudent(Model model){
//             model.addAttribute("students",service.getAllStudents());
//             return "students";
//         }


    }
     @GetMapping("/students/edit/{id}")
    public  String updateForrm(@PathVariable long id,Model model){
        model.addAttribute("student",service.getStudentById(id));
        return "edit_student";

    }
    @PostMapping("/students/{id}")
    public  String updateStudent(@PathVariable long id,
                                 @ModelAttribute("student") Student student,
                                 Model model){
             Student existingStudent = service.getStudentById(id);
             existingStudent.setId(id);
              existingStudent.setFirstName(student.getFirstName());
              existingStudent.setFirstName(student.getLastName());
              existingStudent.setEmail(student.getEmail());
              //Save updated student
              service.saveStudent(existingStudent);

              return "redirect:/students";
    }
    @GetMapping("/students/{id}")
    public  String deleteStudent(@PathVariable Long id){
        service.deleteStudentById(id);
        return "redirect:/students";

    }
}
