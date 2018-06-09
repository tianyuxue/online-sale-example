package com.netease.exam.myshop.dao;


import com.netease.exam.myshop.meta.Content;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentDao extends GenericDao<Content, Integer> {

}
