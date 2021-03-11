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
public class StudentRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Student create(Student student) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("first_name", student.getFirstName());
        mapSqlParameterSource.addValue("last_name", student.getLastName());
        mapSqlParameterSource.addValue("email", student.getEmail());
        mapSqlParameterSource.addValue("age", student.getAge());

        namedParameterJdbcTemplate.update(
                "insert into students (first_name, last_name, email, age) values (:first_name, :last_name, :email, :age)",
                mapSqlParameterSource
        );
        return student;
    }

    public List<Student> getAllStudents() {
        return namedParameterJdbcTemplate.query(
                "select * from students",
                new BeanPropertyRowMapper<>(Student.class)
        );
    }

    public Optional<Student> findById(long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);

        List<Student> studentList = namedParameterJdbcTemplate.query(
                "select * from students where id = :id",
                mapSqlParameterSource,
                new BeanPropertyRowMapper<>(Student.class)
        );

        return studentList.stream().findFirst();
    }

    public Optional<Student> findByEmail(String email) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email);

        List<Student> studentList = namedParameterJdbcTemplate.query(
                "select * from students where email = :email",
                mapSqlParameterSource,
                new BeanPropertyRowMapper<>(Student.class)
        );

        return studentList.stream().findFirst();
    }
}
