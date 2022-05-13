package org.example.config.DAO;

import org.example.config.Models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {

    private static int PEOPLE_COUNT = 5;
    private static Connection connectionDB;

    public PersonDAO() {
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
        List<Person> people = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connectionDB.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "select * from person";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt(1));
                person.setName(resultSet.getString(2));
                person.setAge(resultSet.getInt(3));
                person.setEmail(resultSet.getString(4));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        ResultSet resultSet = null;
        try {
            PreparedStatement statement = connectionDB.prepareStatement("SELECT * from person where id=?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Person person = new Person();
        try {
            resultSet.next();
            person.setId(resultSet.getInt(1));
            person.setName(resultSet.getString(2));
            person.setAge(resultSet.getInt(3));
            person.setEmail(resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void save(Person person) {
        try {
            PreparedStatement statement = (PreparedStatement) connectionDB.prepareStatement("INSERT INTO person values (?,?,?,?)");
            statement.setInt(1, ++PEOPLE_COUNT);
            statement.setString(2, person.getName());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement statement = connectionDB.prepareStatement("UPDATE person SET name=?,age=?,email=? where id=?");
            statement.setString(1, updatedPerson.getName());
            statement.setInt(2, updatedPerson.getAge());
            statement.setString(3, updatedPerson.getEmail());
            statement.setInt(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement=connectionDB.prepareStatement("DELETE from person where id=?");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}