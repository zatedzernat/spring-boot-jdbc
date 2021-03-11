package com.example.springbootjdbc.service;

import com.example.springbootjdbc.exception.StudentException;
import com.example.springbootjdbc.model.Student;
import com.example.springbootjdbc.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    private Student student;

    private List<Student> studentList;

    @BeforeEach
    void setUp() {
        student = new Student().setId(1).setFirstName("aaa").setLastName("test").setEmail("aaa@gmail.com").setAge(10);
        studentList = List.of(
                student,
                new Student().setId(2).setFirstName("bbb").setLastName("test").setEmail("bbb@gmail.com").setAge(10));
    }

    @Test
    void createStudent() {
        Mockito.when(studentRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(studentRepository.create(Mockito.any())).thenReturn(student);
        Student result = studentService.createStudent(student);

        assertEquals(student, result);

    }

    @Test
    void createStudentWithDuplicateEmail() {
        Mockito.when(studentRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(student));
        StudentException ex = assertThrows(StudentException.class, () -> {
            studentService.createStudent(student);
        });

        assertEquals("Duplicate Email: " + student.getEmail(), ex.getCustomMessage());
        assertEquals(400, ex.getCode());
    }

    @Test
    void getAllStudents() {
        Mockito.when(studentRepository.getAllStudents()).thenReturn(studentList);
        List<Student> result = studentService.getAllStudents();

        assertEquals(studentList, result);
    }

    @Test
    void getStudentById() {
        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(student));
        long id = 1L;
        Student result = studentService.getStudentById(id);

        assertEquals(student, result);
    }

    @Test
    void getStudentByIdNotFound() {
        Mockito.when(studentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        long id = 1L;
        StudentException ex = assertThrows(StudentException.class, () -> {
            studentService.getStudentById(id);
        });

        assertEquals("Not Found Student ID: " + id, ex.getCustomMessage());
        assertEquals(404, ex.getCode());
    }
}