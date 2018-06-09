package com.netease.exam.myshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(path="/login", method= RequestMethod.GET)
    public String getLoginPage()
    {
        return "login";
    }

}
