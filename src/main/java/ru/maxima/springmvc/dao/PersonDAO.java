package ru.maxima.springmvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.maxima.springmvc.models.Person;

import java.sql.*;
import java.util.*;

@Component
public class PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("select * from library_person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("select * from library_person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("insert into library_person (name, date_of_birth) values ( ?, ?)", person.getName(),
                person.getDateOfBirth());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("update library_person  set date_of_birth = ? , name = ? where id = ?",
                updatedPerson.getDateOfBirth(), updatedPerson.getName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from library_person where id = ?" , id);
    }

}