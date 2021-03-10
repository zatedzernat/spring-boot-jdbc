package com.example.springbootjdbc.repository;

import com.example.springbootjdbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NameParameterStudent implements StudentRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Student createStudent(Student std) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("first_name", std.getFirstName());
        mapSqlParameterSource.addValue("last_name", std.getLastName());
        mapSqlParameterSource.addValue("email", std.getEmail());
        mapSqlParameterSource.addValue("age", std.getAge());

        namedParameterJdbcTemplate.update(
                "insert into students (first_name, last_name, email, age) values (:first_name, :last_name, :email, :age)",
                mapSqlParameterSource
        );
        return std;
    }

    @Override
    public List<Student> getStudents() {
        return namedParameterJdbcTemplate.query(
                "select * from students",
                new BeanPropertyRowMapper<>(Student.class)
        );
    }

    @Override
    public Optional<Student> findStudentByID(long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);

        List<Student> studentList = namedParameterJdbcTemplate.query(
                "select * from students where id = :id",
                new BeanPropertyRowMapper<>(Student.class)
        );

        return studentList.stream().findFirst();
    }

    @Override
    public Optional<Student> findStudentByEmail(String email) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email);

        List<Student> studentList = namedParameterJdbcTemplate.query(
                "select * from students where email = :email",
                new BeanPropertyRowMapper<>(Student.class)
        );

        return studentList.stream().findFirst();
    }
}
