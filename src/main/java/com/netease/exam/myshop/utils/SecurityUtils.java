package com.netease.exam.myshop.utils;

import com.netease.exam.myshop.dao.PersonDao;
import com.netease.exam.myshop.meta.Person;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Component
public class SecurityUtils {

    @Resource
    private PersonDao _personDao;

    private static PersonDao personDao;

    /**
     * 用于注入静态变量
     */
    @PostConstruct
    public void initStaticVars()
    {
        personDao=_personDao;
    }

    /**
     * 返回当前登录的用户
     */
    public static Person getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        /*
        if(name==null)
        {
            //如果用户在已经通过验证的情况下调用这个方法返回null,说明某个环节出了错误,
            //正常情况下不应该发生这个异常
            throw new RuntimeException("在已经登录的页面，却没能检测到登录用户");
        }
        */
        return personDao.findByUsername(name);
    }
}
