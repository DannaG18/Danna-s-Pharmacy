package com.pharmacy_ofc.customer.domain.entity;

public class Customer {
    private String id;
    private String name;
    private String last_name;
    private String email;
    private String birthdate;
    private float long_customer;
    private float lat_customer;
    private String city_code;

    public Customer(){}
    public Customer(String id, String name, String last_name, String email, String birthdate, float long_customer,
            float lat_customer, String city_code) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.birthdate = birthdate;
        this.long_customer = long_customer;
        this.lat_customer = lat_customer;
        this.city_code = city_code;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
    public float getLong_customer() {
        return long_customer;
    }
    public void setLong_customer(float long_customer) {
        this.long_customer = long_customer;
    }
    public float getLat_customer() {
        return lat_customer;
    }
    public void setLat_customer(float lat_customer) {
        this.lat_customer = lat_customer;
    }
    public String getCity_code() {
        return city_code;
    }
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

}
