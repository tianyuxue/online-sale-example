package com.netease.exam.myshop.dao;


import com.netease.exam.myshop.meta.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonDao extends GenericDao<Person, Integer> {
    Person findByUsername(String username);
}
