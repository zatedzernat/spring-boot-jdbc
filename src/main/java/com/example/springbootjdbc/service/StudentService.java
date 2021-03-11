package com.example.springbootjdbc.service;

import com.example.springbootjdbc.exception.StudentException;
import com.example.springbootjdbc.model.Student;
import com.example.springbootjdbc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new StudentException("Duplicate Email: " + student.getEmail(), 400);
        } else {
            studentRepository.create(student);
            return student;
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentException("Not Found Student ID: " + id));
    }
}
