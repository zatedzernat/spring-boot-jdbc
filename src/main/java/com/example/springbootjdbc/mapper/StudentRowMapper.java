package com.example.springbootjdbc.mapper;

import com.example.springbootjdbc.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student()
                .setId(rs.getLong("id"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .setEmail(rs.getString("email"))
                .setAge(rs.getInt("age"));
    }
}
