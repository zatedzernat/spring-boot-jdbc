package com.example.springbootjdbc.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

}
