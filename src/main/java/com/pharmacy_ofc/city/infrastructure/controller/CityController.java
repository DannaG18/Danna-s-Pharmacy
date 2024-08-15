package com.pharmacy_ofc.city.infrastructure.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.pharmacy_ofc.city.application.CreateCityUseCase;
import com.pharmacy_ofc.city.application.DeleteCityUseCase;
import com.pharmacy_ofc.city.application.FindCityUseCase;
import com.pharmacy_ofc.city.application.UpdateCityUseCase;
import com.pharmacy_ofc.city.domain.service.CityService;
import com.pharmacy_ofc.city.infrastructure.repository.CityRepository;
import com.pharmacy_ofc.city.domain.entity.City;

public class CityController {
    private CityService cityService;
    private CreateCityUseCase createCityUseCase;
    private FindCityUseCase findCityUseCase;
    private UpdateCityUseCase updateCityUseCase;
    private DeleteCityUseCase deleteCityUseCase;

    public CityController() {
        this.cityService = new CityRepository();
        this.createCityUseCase = new CreateCityUseCase(cityService);
        this.findCityUseCase = new FindCityUseCase(cityService);
        this.updateCityUseCase = new UpdateCityUseCase(cityService);
        this.deleteCityUseCase = new DeleteCityUseCase(cityService);
    }

        public void mainMenu() {
        String options = "1. Add City\n2. Search City\n3. Update City\n4. Delete City\n5. Return to main menu.";
        int op = 0;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");
        
        do {
            Object selectedValue = JOptionPane.showInputDialog(
                null, 
                options, 
                "City Menu", 
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
                    addCity();
                    break;
                case 2:
                    findCity();
                    break;
                case 3:
                    updateCity();
                    break;
                case 4:
                    deleteCity();
                    break;
                case 5:
                    break;
                default:
                    break;
            } 
        } while (op != 5 && op != -1);
    }

    public void addCity() {
        String code = JOptionPane.showInputDialog(null, "City code: ");
        if (code == null) {
            return;
        }
        String name = JOptionPane.showInputDialog(null, "City name: ");
        if (name == null) {
            return;
        }
        String country_code = JOptionPane.showInputDialog(null, "City Region: ");
        if (country_code == null) {
            return;
        }

        City City = new City();
        City.setCode(code);
        City.setName(name);
        City.setReg_code(country_code);

        createCityUseCase.execute(City);
        JOptionPane.showMessageDialog(null, "City added successfully.");
    }

    public void findCity() {
        String code = JOptionPane.showInputDialog(null, "Enter City code: ");

        findCityUseCase.execute(code).ifPresentOrElse(
            CityFound -> {
                JOptionPane.showMessageDialog(null, 
                    "City found:\n" + 
                    "Code: " + CityFound.getCode() + "\n" +
                    "Name: " + CityFound.getName() + "\n" +
                    "Country Code: " + CityFound.getReg_code(),
                    "City Details", JOptionPane.INFORMATION_MESSAGE);
            },
            () -> {
                JOptionPane.showMessageDialog(null, "City not found.", "Error", JOptionPane.ERROR_MESSAGE);
            });
    }

    public void updateCity() {
        String code = JOptionPane.showInputDialog(null, "Enter City code: ");

        findCityUseCase.execute(code).ifPresentOrElse(
            currentCity -> {
                String name = JOptionPane.showInputDialog(null, "Enter new City name: ");
                if (name == null) {
                    return;
                }

                String region = JOptionPane.showInputDialog(null, "Enter new City region: ");
                if (region == null) {
                    return;
                }

                currentCity.setName(name);
                currentCity.setReg_code(region);
                updateCityUseCase.execute(currentCity);

                JOptionPane.showMessageDialog(null, "City updated successfully.");
            }, 
            () -> {
                JOptionPane.showMessageDialog(null, "City not found.", "Error", JOptionPane.ERROR_MESSAGE);
            });
    }

    public void deleteCity() {
        String code = JOptionPane.showInputDialog(null, "Enter City code: ");
        if (code == null) {
            return;
        }
        deleteCityUseCase.execute(code);
        JOptionPane.showMessageDialog(null, "City deleted successfully.");
    }
}
