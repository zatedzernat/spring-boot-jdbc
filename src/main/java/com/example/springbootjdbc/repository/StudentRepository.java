package com.example.springbootjdbc.repository;

import com.example.springbootjdbc.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository {
    Student createStudent(Student std);

    List<Student> getStudents();

    Optional<Student> findStudentByID(long id);

    Optional<Student> findStudentByEmail(String email);
}
