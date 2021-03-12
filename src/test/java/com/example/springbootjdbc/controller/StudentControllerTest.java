package com.example.springbootjdbc.controller;

import com.example.springbootjdbc.model.Student;
import com.example.springbootjdbc.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private List<Student> studentList;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student().setId(1).setFirstName("aaa").setLastName("test").setEmail("aaa@gmail.com").setAge(10);
        studentList = List.of(
                student,
                new Student().setId(2).setFirstName("bbb").setLastName("test").setEmail("bbb@gmail.com").setAge(10));
    }

    @Test
    void createStudent() throws Exception {
        Mockito.when(studentService.createStudent(Mockito.any())).thenReturn(student);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(content().string("Create New Student: " + student.getFirstName()))
                .andExpect(status().isCreated());
    }

    @Test
    void createStudentMvcResult() throws Exception {
        Mockito.when(studentService.createStudent(Mockito.any())).thenReturn(student);

        MvcResult mvcResult = mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andReturn();

        assertEquals("Create New Student: " + student.getFirstName(), mvcResult.getResponse().getContentAsString());
        assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    void getAllStudents() throws Exception {
        Mockito.when(studentService.getAllStudents()).thenReturn(studentList);

        mockMvc.perform(get("/api/students"))
                .andExpect(jsonPath("studentList[0].firstName").value("aaa"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllStudentsWithNull() throws Exception {
        studentList = null;
        Mockito.when(studentService.getAllStudents()).thenReturn(studentList);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk());
    }

    @Test
    void getStudentById() throws Exception {
        Mockito.when(studentService.getStudentById(Mockito.anyLong())).thenReturn(student);
        long id = 1L;
        mockMvc.perform(get("/api/students/" + id))
                .andExpect(jsonPath("student.firstName").value("aaa"))
                .andExpect(status().isOk());
    }
}