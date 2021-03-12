package com.example.springbootjdbc.controller;

import com.example.springbootjdbc.model.ResponseModel;
import com.example.springbootjdbc.model.Student;
import com.example.springbootjdbc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        Student std = studentService.createStudent(student);
        return new ResponseEntity<>("Create New Student: " + std.getFirstName(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseModel> getAllStudents() {
        return new ResponseEntity<>(new ResponseModel().setStudentList(studentService.getAllStudents()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel> getStudentById(@PathVariable long id) {
        Student std = studentService.getStudentById(id);
        ResponseModel responseModel = new ResponseModel().setStudent(std);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateAge(@Valid @RequestBody Student student) {
        studentService.updateAge(student);
        return new ResponseEntity<>("Update Age for Student ID: " + student.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Delete Student ID: " + id, HttpStatus.OK);
    }
}
