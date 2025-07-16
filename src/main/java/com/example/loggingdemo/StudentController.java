package com.example.loggingdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/add")
    public String addStudent(@RequestParam String name) {
        studentService.addStudent(name);
        return "Student added: " + name;
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteStudent(id);
        return "Student deleted with ID: " + id;
    }

    @GetMapping("/error")
    public String errorTest() {
        studentService.causeError();
        return "Error test done";
    }
}
