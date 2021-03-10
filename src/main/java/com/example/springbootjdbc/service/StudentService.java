package com.example.springbootjdbc.service;

import com.example.springbootjdbc.model.Student;
import com.example.springbootjdbc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(student.getEmail());
        if (optionalStudent.isPresent()) {
            return null;
        } else {
            studentRepository.create(student);
            return student;
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }
}
