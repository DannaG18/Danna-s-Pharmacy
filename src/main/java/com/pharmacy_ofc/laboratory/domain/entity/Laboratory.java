package com.pharmacy_ofc.laboratory.domain.entity;

public class Laboratory {
    private int id;
    private String name;
    private String city_code;

    public Laboratory() {}
    public Laboratory(int id, String name, String city_code) {
        this.id = id;
        this.name = name;
        this.city_code = city_code;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity_code() {
        return city_code;
    }
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    
}
