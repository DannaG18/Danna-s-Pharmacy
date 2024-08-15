package com.pharmacy_ofc.city.domain.entity;

public class City {
    private String code;
    private String name;
    private String reg_code;

    public City() {
        
    }
    public City(String code, String name, String reg_code) {
        this.code = code;
        this.name = name;
        this.reg_code = reg_code;
    }
    
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getReg_code() {
        return reg_code;
    }
    public void setReg_code(String reg_code) {
        this.reg_code = reg_code;
    }

    
}
