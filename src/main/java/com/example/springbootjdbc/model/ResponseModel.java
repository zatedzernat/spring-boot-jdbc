package com.example.springbootjdbc.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ResponseModel {
    private Student student;
    private List<Student> studentList;
}
