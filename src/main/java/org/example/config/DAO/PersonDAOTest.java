package org.example.config.DAO;

import org.example.config.Models.Person;
import org.junit.jupiter.api.Test;

import java.util.List;

class PersonDAOTest {
    public void test() {
        PersonDAO personDAO = new PersonDAO();
        List<Person> people = personDAO.index();
        for (Person person:people
             ) {
            System.out.println(person);
        }


    }

    @Test
    void index() {

        PersonDAO personDAO=new PersonDAO();
        List<Person> people=personDAO.index();
        for (Person p:people
             ) {
            System.out.println(p.getName());
        }
    }
}