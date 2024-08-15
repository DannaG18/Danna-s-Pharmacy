package com.pharmacy_ofc.active_principle.domain.entity;

public class ActivePrinciple {
    private int id;
    private String name;

    public ActivePrinciple() {

    }
    public ActivePrinciple(int id, String name) {
        this.id = id;
        this.name = name;
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
    
}
