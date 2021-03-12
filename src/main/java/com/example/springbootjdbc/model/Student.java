package com.example.springbootjdbc.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class Student {
    @NotNull
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
