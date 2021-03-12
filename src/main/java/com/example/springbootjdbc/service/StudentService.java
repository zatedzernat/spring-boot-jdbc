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
        return studentRepository.findById(id).orElseThrow(() -> new StudentException("Not Found Student ID: " + id, 404));
    }

    public void updateAge(Student student) {
        long id = student.getId();
        if (this.getStudentById(id) != null) {
            int update = studentRepository.update(student);
            if (update == 0) {
                throw new StudentException("Something Went Wrong (Update)", 500);
            }
        }
    }

    public void deleteStudentById(long id) {
        if (this.getStudentById(id) != null) {
            int delete = studentRepository.delete(id);
            if (delete == 0) {
                throw new StudentException("Something Went Wrong (Delete)", 500);
            }
        }
    }
}
