package com.netease.exam.myshop.dao;


import java.util.List;

public interface GenericDao<T, ID> {
    /**
     * 接口中的方法是默认由 public abstract修饰
     */
    T findById(ID id);

    List<T> findAll();

    int addEntity(T t);

    int updateEntity(T t);

    int deleteEntity(T t);
}
