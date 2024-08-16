package com.pharmacy_ofc.country.infrastructure.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.pharmacy_ofc.country.application.CreateCountryUseCase;
import com.pharmacy_ofc.country.application.DeleteCountryUseCase;
import com.pharmacy_ofc.country.application.FindCountryByIdUseCase;
import com.pharmacy_ofc.country.application.UpdateCountryUseCase;
import com.pharmacy_ofc.country.domain.entity.Country;
import com.pharmacy_ofc.country.domain.service.CountryService;
import com.pharmacy_ofc.country.infrastructure.repository.CountryRepository;

public class CountryController {

    private final CountryService countryService;
    private final CreateCountryUseCase createCountryUseCase;
    private final FindCountryByIdUseCase findCountryByIdUseCase;
    private final UpdateCountryUseCase updateCountryUseCase;
    private final DeleteCountryUseCase deleteCountryUseCase;

    public CountryController() {
        this.countryService = new CountryRepository();
        this.createCountryUseCase = new CreateCountryUseCase(countryService);
        this.findCountryByIdUseCase = new FindCountryByIdUseCase(countryService);
        this.updateCountryUseCase = new UpdateCountryUseCase(countryService);
        this.deleteCountryUseCase = new DeleteCountryUseCase(countryService);
    }

    public void mainMenu() {
        String options = """
                1. Add country
                2. Search country
                3. Update country
                4. Delete country
                5. Return to main menu
                """;
        int op;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");

        do {
            String selectedValue = getInput(options, "Country Menu", customIcon);
            if (selectedValue == null) {
                break; // Salir del bucle si el usuario cancela la entrada
            }

            try {
                op = Integer.parseInt(selectedValue);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (op) {
                case 1 -> addCountry();
                case 2 -> findCountry();
                case 3 -> updateCountry();
                case 4 -> deleteCountry();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (false);
    }

    private void addCountry() {
        String code = getInput("Enter country code:");
        if (code == null || code.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Country code cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = getInput("Enter country name:");
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Country name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Country country = new Country();
        country.setCode(code);
        country.setName(name);

        createCountryUseCase.execute(country);
        JOptionPane.showMessageDialog(null, "Country added successfully.");
    }

    private void findCountry() {
        String code = getInput("Enter country code:");
        if (code == null || code.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Country code cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        findCountryByIdUseCase.execute(code).ifPresentOrElse(
                this::showCountryDetails,
                () -> JOptionPane.showMessageDialog(null, "Country not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void updateCountry() {
        String code = getInput("Enter country code:");
        if (code == null || code.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Country code cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        findCountryByIdUseCase.execute(code).ifPresentOrElse(
                country -> {
                    String name = getInput("Enter new country name:");
                    if (name == null || name.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Country name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    country.setName(name);
                    updateCountryUseCase.execute(country);
                    JOptionPane.showMessageDialog(null, "Country updated successfully.");
                },
                () -> JOptionPane.showMessageDialog(null, "Country not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void deleteCountry() {
        String code = getInput("Enter country code:");
        if (code == null || code.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Country code cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this country?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            deleteCountryUseCase.execute(code);
            JOptionPane.showMessageDialog(null, "Country deleted successfully.");
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

    private void showCountryDetails(Country country) {
        String details = String.format("""
                Country found:
                Code: %s
                Name: %s
                """, country.getCode(), country.getName());
        JOptionPane.showMessageDialog(null, details, "Country Details", JOptionPane.INFORMATION_MESSAGE);
    }
}

