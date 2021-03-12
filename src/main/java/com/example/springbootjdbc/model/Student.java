package com.example.springbootjdbc.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class Student {
    @NotNull
    @Min(1)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    @NotNull
    @Min(1)
    private int age;
}
