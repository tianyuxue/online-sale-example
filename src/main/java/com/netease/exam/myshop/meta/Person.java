package com.netease.exam.myshop.meta;

import java.io.Serializable;

public class Person implements Serializable
{
    private Integer userId;

    private String userName;

    private String password;

    private String nickName;

    private int userType;

    public Integer getUserId()
    {
        return userId;
    }
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getPwssword()
    {
        return password;
    }
    public void setPwssword(String pwssword)
    {
        this.password = pwssword;
    }
    public String getNickName()
    {
        return nickName;
    }
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    public int getUserType()
    {
        return userType;
    }
    public void setUserType(int userType)
    {
        this.userType = userType;
    } 
    
    @Override
    public String toString()
    {
        return "Person [userId=" + userId + ", userName=" + userName + ", pwssword=" + password + ", nickName="
                + nickName + ", userType=" + userType + "]";
    }
    
    

}
