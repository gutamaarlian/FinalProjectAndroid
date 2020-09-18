package com.example.myapplication.utils;

import com.example.myapplication.service.AgencyService;
import com.example.myapplication.service.BusService;
import com.example.myapplication.service.ClientService;

public class BusUtil extends UtilHelper {

    public BusService getBus(){
        return ClientService.getClient().create(BusService.class);
    }

    public BusUtil() {
    }
}
