package com.pharmacy_ofc;

import javax.swing.JOptionPane;

import com.pharmacy_ofc.country.infrastructure.controller.CountryController;

public class Main {
    public static void main(String[] args) {

        String options = "1. Country\n2. Exit.";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, options));
            switch (op) {
                case 1:
                    CountryController countryController = new CountryController();
                    countryController.mainMenu();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Suerte nos vemos....");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error opcion invalida");
                    break;
            } 
        } while (op!= 2);
    }
}