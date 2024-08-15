package com.pharmacy_ofc;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.pharmacy_ofc.laboratory.infrastructure.controller.LaboratoryController;
import com.pharmacy_ofc.customer.infrastructure.controller.CustomerController;
import com.pharmacy_ofc.medicine.infrastructure.controller.MedicineController;
import com.pharmacy_ofc.active_principle.infrastructure.controller.APController;
import com.pharmacy_ofc.city.infrastructure.controller.CityController;
import com.pharmacy_ofc.country.infrastructure.controller.CountryController;
import com.pharmacy_ofc.mode_admi.infrastructure.controller.ModeAdmiController;
import com.pharmacy_ofc.pharmacy.infrastructure.controller.PharmacyController;
import com.pharmacy_ofc.pharmacy_medicine.infrastructure.controller.PharmacyMedController;
import com.pharmacy_ofc.region.infrastructure.controller.RegionController;
import com.pharmacy_ofc.unit_measurement.infrastructure.controller.UnitMeasurementController;

public class Main {
    public static void main(String[] args) {
        String options = """
                1. Country.
                2. Active Principle.
                3. ModeAdmi.
                4. Unit Measurement.
                5. Region.
                6. City.
                7. Laboratory.  
                8. Medicine.
                9. Pharmacy.
                10. Pharmacy Medicine.
                11. Customer
                12. Exit.
                """;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");
        int op = 0;

        do {
            Object selectedValue = JOptionPane.showInputDialog(
                null, 
                options, 
                "Main Menu", 
                JOptionPane.QUESTION_MESSAGE, 
                customIcon, 
                null, 
                null
            );
        
            if (selectedValue != null) {
                try {
                    op = Integer.parseInt(selectedValue.toString());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid option. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    continue; // Si la opción no es válida, vuelve a mostrar el menú
                }
            } else {
                op = -1; // Si el usuario cancela, se asigna un valor fuera de rango para salir del bucle
            }
        
            switch (op) {
                case 1:
                    CountryController countryController = new CountryController();
                    countryController.mainMenu();
                    break;
                case 2:
                    APController apController = new APController();
                    apController.mainMenu();
                    break;
                case 3:
                    ModeAdmiController modeAdmiController = new ModeAdmiController();
                    modeAdmiController.mainMenu();
                    break;
                case 4:
                    UnitMeasurementController unitMeasurementController = new UnitMeasurementController();
                    unitMeasurementController.mainMenu();
                    break;
                case 5:
                    RegionController regionController = new RegionController();
                    regionController.mainMenu();
                    break;
                case 6:
                    CityController cityController = new CityController();
                    cityController.mainMenu();
                    break;
                case 7:
                    LaboratoryController laboratoryController = new LaboratoryController();
                    laboratoryController.mainMenu();
                    break;
                case 8:
                    MedicineController medicineController = new MedicineController();
                    medicineController.mainMenu();
                    break;
                case 9:
                    PharmacyController farmacyController = new PharmacyController();
                    farmacyController.mainMenu();
                    break;
                case 10:
                    PharmacyMedController farmacyMedController = new PharmacyMedController();
                    farmacyMedController.mainMenu();
                    break;
                case 11:
                    CustomerController customerMedController = new CustomerController();
                    customerMedController.mainMenu();
                    break;
                case 12:
                    JOptionPane.showMessageDialog(null, "El que lo cierra es gai", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    break;
            }
        } while (op != 3 && op != -1);
    }
}