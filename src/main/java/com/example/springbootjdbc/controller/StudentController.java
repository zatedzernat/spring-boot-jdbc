package com.example.springbootjdbc.controller;

import com.example.springbootjdbc.model.Student;
import com.example.springbootjdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student std = studentService.createStudent(student);
        return new ResponseEntity<>("Create New Student: " + std.getFirstName(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable long id) {
        Student std = studentService.getStudentById(id);
        return new ResponseEntity<>(std, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateAge(@RequestBody Student student) {
        studentService.updateAge(student);
        return new ResponseEntity<>("Update Age for Student ID: " + student.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Delete Student ID: " + id, HttpStatus.OK);
    }
}
