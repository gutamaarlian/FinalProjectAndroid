package com.example.myapplication.utils;

import com.example.myapplication.service.ClientService;
import com.example.myapplication.service.UserService;

public class UserUtil extends UtilHelper{
    public UserUtil() {
    }



    public UserService getUser(){
        return ClientService.getClient().create(UserService.class);
    }
}
