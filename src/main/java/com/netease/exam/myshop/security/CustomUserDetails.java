package com.netease.exam.myshop.security;

import com.netease.exam.myshop.dao.PersonDao;
import com.netease.exam.myshop.meta.Person;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

@Component
public class CustomUserDetails implements UserDetailsService{

    @Resource
    private PersonDao personDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person user = personDao.findByUsername(s);
        if(user==null)
        {
            throw new UsernameNotFoundException("用户名或者密码错误");
        }
        if(user.getUserType()==0)
        {

            return new User(user.getUserName(),
                    user.getPwssword(),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_BUYER")
                    ));
        }
        else
        {
            return new User(user.getUserName(),
                    user.getPwssword(),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_SELLER")
                    ));

        }
    }
}
