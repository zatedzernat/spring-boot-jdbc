package com.example.springbootjdbc.controller;

import com.example.springbootjdbc.model.Student;
import com.example.springbootjdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student std = studentService.createStudent(student);
        if (std == null) {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(std, HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    //    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    @GetMapping("/search/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable long id) {
//        if(std == null) {
//            return new ResponseEntity<>()
//        }
        return null;
    }
}
