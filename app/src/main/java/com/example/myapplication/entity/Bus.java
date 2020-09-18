package com.example.myapplication.entity;

public class Bus {
    private String id;
    private String code;
    private String capacity;
    private String make;
    private String agencyId;

    public Bus() {
    }

    public Bus(String id, String code, String capacity, String make, String agencyId) {
        this.id = id;
        this.code = code;
        this.capacity = capacity;
        this.make = make;
        this.agencyId = agencyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
