package com.example.springbootjdbc.model;

import lombok.Data;

@Data
public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public Student(long id, String firstName, String lastName, String email, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
}
