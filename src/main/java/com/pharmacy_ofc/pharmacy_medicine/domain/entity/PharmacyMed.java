package com.pharmacy_ofc.pharmacy_medicine.domain.entity;

public class PharmacyMed {
    private int id_medicine;
    private int id_pharmacy;
    private float price;

    public PharmacyMed() {}

    public PharmacyMed(int id_medicine, int id_pharmacy, float price) {
        this.id_medicine = id_medicine;
        this.id_pharmacy = id_pharmacy;
        this.price = price;
    }

    public int getId_medicine() {
        return id_medicine;
    }

    public void setId_medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    public int getId_pharmacy() {
        return id_pharmacy;
    }

    public void setId_pharmacy(int id_pharmacy) {
        this.id_pharmacy = id_pharmacy;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
