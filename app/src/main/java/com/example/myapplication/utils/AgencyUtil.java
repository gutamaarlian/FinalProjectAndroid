package com.example.myapplication.utils;

import com.example.myapplication.service.AgencyService;
import com.example.myapplication.service.ClientService;
import com.example.myapplication.service.UserService;

public class AgencyUtil extends UtilHelper {
    public AgencyService getAgency(){
        return ClientService.getClient().create(AgencyService.class);
    }

    public AgencyUtil() {
    }
}
