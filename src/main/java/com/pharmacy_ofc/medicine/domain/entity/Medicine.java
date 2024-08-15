package com.pharmacy_ofc.medicine.domain.entity;

public class Medicine {
    private int id;
    private String proceedings;
    private String name_medicine;
    private String health_register;
    private String description;
    private String description_short;
    private String name_rol;
    private int code_mode_adm;
    private int code_act_p;
    private int code_uni_m;
    private int code_lab;

    public Medicine() {
        
    }
    public Medicine(int id, String proceedings, String name_medicine, String health_register, String description,
            String description_short, String name_rol, int code_mode_adm, int code_act_p, int code_uni_m,
            int code_lab) {
        this.id = id;
        this.proceedings = proceedings;
        this.name_medicine = name_medicine;
        this.health_register = health_register;
        this.description = description;
        this.description_short = description_short;
        this.name_rol = name_rol;
        this.code_mode_adm = code_mode_adm;
        this.code_act_p = code_act_p;
        this.code_uni_m = code_uni_m;
        this.code_lab = code_lab;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProceedings() {
        return proceedings;
    }
    public void setProceedings(String proceedings) {
        this.proceedings = proceedings;
    }
    public String getName_medicine() {
        return name_medicine;
    }
    public void setName_medicine(String name_medicine) {
        this.name_medicine = name_medicine;
    }
    public String getHealth_register() {
        return health_register;
    }
    public void setHealth_register(String health_register) {
        this.health_register = health_register;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription_short() {
        return description_short;
    }
    public void setDescription_short(String description_short) {
        this.description_short = description_short;
    }
    public String getName_rol() {
        return name_rol;
    }
    public void setName_rol(String name_rol) {
        this.name_rol = name_rol;
    }
    public int getCode_mode_adm() {
        return code_mode_adm;
    }
    public void setCode_mode_adm(int code_mode_adm) {
        this.code_mode_adm = code_mode_adm;
    }
    public int getCode_act_p() {
        return code_act_p;
    }
    public void setCode_act_p(int code_act_p) {
        this.code_act_p = code_act_p;
    }
    public int getCode_uni_m() {
        return code_uni_m;
    }
    public void setCode_uni_m(int code_uni_m) {
        this.code_uni_m = code_uni_m;
    }
    public int getCode_lab() {
        return code_lab;
    }
    public void setCode_lab(int code_lab) {
        this.code_lab = code_lab;
    }

    
}
