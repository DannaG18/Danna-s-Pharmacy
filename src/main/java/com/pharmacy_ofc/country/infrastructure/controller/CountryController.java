package com.pharmacy_ofc.country.infrastructure.controller;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.pharmacy_ofc.country.application.CreateCountryUseCase;
import com.pharmacy_ofc.country.application.DeleteCountryUseCase;
import com.pharmacy_ofc.country.application.FindCountryByIdUseCase;
import com.pharmacy_ofc.country.application.UpdateCountryUseCase;
import com.pharmacy_ofc.country.domain.entity.Country;
import com.pharmacy_ofc.country.domain.service.CountryService;
import com.pharmacy_ofc.country.infrastructure.repository.CountryRepository;

public class CountryController {
    private CountryService countryService;
    private CreateCountryUseCase createCountryUseCase;
    private FindCountryByIdUseCase findCountryByIdUseCase;
    private UpdateCountryUseCase updateCountryUseCase;
    private DeleteCountryUseCase deleteCountryUseCase;

    public CountryController() {
        this.countryService = new CountryRepository();
        this.createCountryUseCase = new CreateCountryUseCase(countryService);
        this.findCountryByIdUseCase = new FindCountryByIdUseCase(countryService);
        this.updateCountryUseCase = new UpdateCountryUseCase(countryService);
        this.deleteCountryUseCase = new DeleteCountryUseCase(countryService);
    }

    public void mainMenu() {
        String options = "1.Add country\n2. Search country\n3. Update country\n4. Delete country\n5. Return main menu.";
        int op;
                do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, options));
            switch (op) {
                case 1:
                    addCountry();
                    break;
                case 2:
                    findCountry();
                    break;
                case 3:
                    updateCountry();
                    break;
                case 4:
                    deleteCountry();
                    break;
                case 5:
                    break;
                default:
                JOptionPane.showMessageDialog(null, "Error en la opcion ingresada","Error",JOptionPane.ERROR_MESSAGE);
            } 
        } while (op!= 4);
    }

    public void addCountry() {
        String code = JOptionPane.showInputDialog(null, "Country code: ");
        String name = JOptionPane.showInputDialog(null, "Country name: ");

        Country country = new Country();
        country.setCode(code);
        country.setName(name);

        createCountryUseCase.execute(country);
        JOptionPane.showMessageDialog(null, "Country added successfully");
    }

    public void findCountry() {
        try (Scanner scanner = new Scanner(System.in)) {
            String code = JOptionPane.showInputDialog(null, "Enter country code: ");

            findCountryByIdUseCase.execute(code).ifPresentOrElse(
                countryFound -> {
                    JOptionPane.showMessageDialog(null, countryFound.toString(),"Error",JOptionPane.ERROR_MESSAGE);
                },
                () -> {
                    JOptionPane.showMessageDialog(null, "Country not found.","Error",JOptionPane.ERROR_MESSAGE);
                });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCountry() {
        try (Scanner scanner = new Scanner(System.in)) {
            String code = JOptionPane.showInputDialog(null, "Enter country code: ");

            findCountryByIdUseCase.execute(code).ifPresentOrElse(
                currentCountry -> {
                    String name = JOptionPane.showInputDialog(null, "Enter new country name: ");

                    currentCountry.setName(name);
                    updateCountryUseCase.execute(currentCountry);

                    JOptionPane.showMessageDialog(null, "Country updated successfully.");
                }, 
                () -> {
                    JOptionPane.showMessageDialog(null, "Country not found.","Error",JOptionPane.ERROR_MESSAGE);
                });
        }
    }

    public void deleteCountry() {
        try (Scanner scanner = new Scanner(System.in)) {
            String code = JOptionPane.showInputDialog(null, "Enter country code: ");
            deleteCountryUseCase.execute(code);
            JOptionPane.showMessageDialog(null, "Country deleted successfully.");
        }
    }
}
