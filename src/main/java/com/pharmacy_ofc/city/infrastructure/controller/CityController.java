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

    private final CityService cityService;
    private final CreateCityUseCase createCityUseCase;
    private final FindCityUseCase findCityUseCase;
    private final UpdateCityUseCase updateCityUseCase;
    private final DeleteCityUseCase deleteCityUseCase;

    public CityController() {
        this.cityService = new CityRepository();
        this.createCityUseCase = new CreateCityUseCase(cityService);
        this.findCityUseCase = new FindCityUseCase(cityService);
        this.updateCityUseCase = new UpdateCityUseCase(cityService);
        this.deleteCityUseCase = new DeleteCityUseCase(cityService);
    }

    public void mainMenu() {
        String options = """
                1. Add City
                2. Search City
                3. Update City
                4. Delete City
                5. Return to main menu
                """;
        int op;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");

        do {
            String selectedValue = getInput(options, "City Menu", customIcon);
            if (selectedValue == null) {
                break; // Exit loop if the user cancels the input
            }

            try {
                op = Integer.parseInt(selectedValue);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (op) {
                case 1 -> addCity();
                case 2 -> findCity();
                case 3 -> updateCity();
                case 4 -> deleteCity();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (false);
    }

    private void addCity() {
        String code = getInput("Enter City code:");
        if (code == null) {
            JOptionPane.showMessageDialog(null, "City code cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = getInput("Enter City name:");
        if (name == null) {
            JOptionPane.showMessageDialog(null, "City name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String countryCode = getInput("Enter City region:");
        if (countryCode == null) {
            JOptionPane.showMessageDialog(null, "City region cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        City city = new City();
        city.setCode(code);
        city.setName(name);
        city.setReg_code(countryCode);

        createCityUseCase.execute(city);
        JOptionPane.showMessageDialog(null, "City added successfully.");
    }

    private void findCity() {
        String code = getInput("Enter City code:");
        if (code == null) return;

        findCityUseCase.execute(code).ifPresentOrElse(
                city -> showCityDetails(city),
                () -> JOptionPane.showMessageDialog(null, "City not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void updateCity() {
        String code = getInput("Enter City code:");
        if (code == null) return;

        findCityUseCase.execute(code).ifPresentOrElse(
                city -> {
                    String name = getInput("Enter new City name:");
                    if (name == null) {
                        JOptionPane.showMessageDialog(null, "City name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String region = getInput("Enter new City region:");
                    if (region == null) {
                        JOptionPane.showMessageDialog(null, "City region cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    city.setName(name);
                    city.setReg_code(region);
                    updateCityUseCase.execute(city);
                    JOptionPane.showMessageDialog(null, "City updated successfully.");
                },
                () -> JOptionPane.showMessageDialog(null, "City not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void deleteCity() {
        String code = getInput("Enter City code:");
        if (code == null) return;

        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this City?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            deleteCityUseCase.execute(code);
            JOptionPane.showMessageDialog(null, "City deleted successfully.");
        }
    }

    private String getInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return (input != null && !input.trim().isEmpty()) ? input.trim() : null;
    }

    private String getInput(String message, String title, ImageIcon icon) {
        Object input = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, icon, null, null);
        return (input != null) ? input.toString().trim() : null;
    }

    private void showCityDetails(City city) {
        String details = String.format("""
                City found:
                Code: %s
                Name: %s
                Country Code: %s
                """, city.getCode(), city.getName(), city.getReg_code());
        JOptionPane.showMessageDialog(null, details, "City Details", JOptionPane.INFORMATION_MESSAGE);
    }
}

