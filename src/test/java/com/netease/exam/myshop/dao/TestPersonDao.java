package com.netease.exam.myshop.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import com.netease.exam.myshop.meta.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPersonDao
{

    @Resource
    private PersonDao dao;
    
    @Test
    public void testFindById(){
       Person person = dao.findByUsername("buyer");
       System.out.println(person);
       assertNotNull(person);
       Person buyer = dao.findById(1);
       assertEquals("buyer", buyer.getUserName());
    }
    
    @Test
    public void testFindAll(){
        List<Person> persons = dao.findAll();
        for(Person p : persons){
            System.out.println(p);
        }
        assertEquals(persons.size(), 2);
    }

    @Test
    @Transactional
    public void testInsert(){
        Person newPerson = new Person();
        newPerson.setUserName("jitianyu");
        newPerson.setPwssword("alkd");
        newPerson.setNickName("ty");
        newPerson.setUserType(0);

        
        dao.addEntity(newPerson);
        List<Person> persons = dao.findAll();
        for(Person p : persons){
            System.out.println(p);
        }
        assertEquals(persons.size(), 3);
        
        newPerson.setNickName("tianyuxue");
        dao.updateEntity(newPerson);
        assertEquals("tianyuxue",dao.findByUsername("jitianyu").getNickName());

        dao.deleteEntity(newPerson);
        persons = dao.findAll();
        assertEquals(persons.size(), 2);
    }
}
