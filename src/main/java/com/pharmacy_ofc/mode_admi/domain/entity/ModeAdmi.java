package com.pharmacy_ofc.mode_admi.domain.entity;

public class ModeAdmi {
    private int id;
    private String description;

    public ModeAdmi(){
        
    }
    public ModeAdmi(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
