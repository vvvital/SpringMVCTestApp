package org.example.config.DAO;

import org.example.config.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;


@Component
public class PersonDAO {

    private static int PEOPLE_COUNT = 5;
    private static Connection connectionDB;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }


    static {
        try {//store.person
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionDB = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person",new PersonMapper());
    }

    public Person show(int id) {

        return jdbcTemplate.query("SELECT * from person where id=?",new Object[]{id},new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
       jdbcTemplate.update("INSERT INTO person values (?,?,?,?)",person.getId(),
               person.getName(),person.getAge(),person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE person SET name=?,age=?,email=? where id=?",
                updatedPerson.getName(),updatedPerson.getAge(),updatedPerson.getEmail(),id);
    }

    public void delete(int id) {
       jdbcTemplate.update("DELETE from person where id=?",id);

    }


}