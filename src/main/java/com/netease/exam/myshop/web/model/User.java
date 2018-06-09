package com.netease.exam.myshop.web.model;

public class User {

    private String username;

    private int usertype;

    public User() {
    }

    public User(String username, int userType)
    {
        this.username = username;
        this.usertype = userType;
    }

    public String getUsername()
    {
        return username;
    }

    public int getUsertype()
    {
        return usertype;
    }

    public void setUserName(String username)
    {
        this.username = username;
    }

    public void setUsertype(int userType)
    {
        this.usertype = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", usertype=" + usertype +
                '}';
    }
}
