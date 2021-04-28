package com.example.qjh.cloudrecognition.Interfaces;

import com.example.qjh.cloudrecognition.logins.Person;

import cn.bmob.v3.BmobUser;

public interface Current_user   {
    //获取当前用户信息
    public BmobUser currentUser=BmobUser.getCurrentUser(Person.class);
    //获取当前用户的用户名
    public String User_name=currentUser.getUsername();

}
