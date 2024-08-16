package com.pharmacy_ofc.pharmacy.infrastructure.controller;

import javax.swing.JOptionPane;

import com.pharmacy_ofc.pharmacy.application.CreatePharmacyUseCase;
import com.pharmacy_ofc.pharmacy.application.DeletePharmacyUseCase;
import com.pharmacy_ofc.pharmacy.application.FindPharmacyUseCase;
import com.pharmacy_ofc.pharmacy.application.UpdatePharmacyUseCase;
import com.pharmacy_ofc.pharmacy.domain.entity.Pharmacy;
import com.pharmacy_ofc.pharmacy.domain.service.PharmacyService;
import com.pharmacy_ofc.pharmacy.infrastructure.repository.PharmacyRepository;

public class PharmacyController {

    private final PharmacyService pharmacyService;
    private final CreatePharmacyUseCase createPharmacyUseCase;
    private final FindPharmacyUseCase findPharmacyUseCase;
    private final UpdatePharmacyUseCase updatePharmacyUseCase;
    private final DeletePharmacyUseCase deletePharmacyUseCase;

    public PharmacyController() {
        this.pharmacyService = new PharmacyRepository();
        this.createPharmacyUseCase = new CreatePharmacyUseCase(pharmacyService);
        this.findPharmacyUseCase = new FindPharmacyUseCase(pharmacyService);
        this.updatePharmacyUseCase = new UpdatePharmacyUseCase(pharmacyService);
        this.deletePharmacyUseCase = new DeletePharmacyUseCase(pharmacyService);
    }

    public void mainMenu() {
        String options = """
                1. Add pharmacy
                2. Search pharmacy
                3. Update pharmacy
                4. Delete pharmacy
                5. Return to main menu
                """;
        int op;
        do {
            op = getInputAsInteger("Select an option:\n" + options);
            switch (op) {
                case 1 -> addPharmacy();
                case 2 -> findPharmacy();
                case 3 -> updatePharmacy();
                case 4 -> deletePharmacy();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 5 && op != -1); // Salir si se selecciona la opción 5 o una opción no válida
    }

    private void addPharmacy() {
        String name = getInput("Enter pharmacy name:");
        String address = getInput("Enter pharmacy address:");
        Float longitude = getInputAsFloat("Enter pharmacy longitude:");
        Float latitude = getInputAsFloat("Enter pharmacy latitude:");
        String logo = getInput("Enter pharmacy logo URL:");
        String cityCode = getInput("Enter City Code:");

        if (validatePharmacyFields(name, address, longitude, latitude, cityCode)) {
            Pharmacy pharmacy = new Pharmacy();
            pharmacy.setName(name);
            pharmacy.setAddress(address);
            pharmacy.setLongitude(longitude);
            pharmacy.setLatitude(latitude);
            pharmacy.setLogo(logo);
            pharmacy.setCity_code(cityCode);

            createPharmacyUseCase.execute(pharmacy);
            JOptionPane.showMessageDialog(null, "Pharmacy added successfully.");
        }
    }

    private void findPharmacy() {
        int id = getInputAsInteger("Enter pharmacy ID:");
        if (id < 0) return; // Verifica si el ID es válido

        findPharmacyUseCase.execute(id).ifPresentOrElse(
                this::showPharmacyDetails,
                () -> JOptionPane.showMessageDialog(null, "Pharmacy not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void updatePharmacy() {
        int id = getInputAsInteger("Enter pharmacy ID:");
        if (id < 0) return; // Verifica si el ID es válido

        findPharmacyUseCase.execute(id).ifPresentOrElse(
                pharmacy -> {
                    String name = getInput("Enter new pharmacy name:");
                    String address = getInput("Enter new pharmacy address:");
                    Float longitude = getInputAsFloat("Enter new pharmacy longitude:");
                    Float latitude = getInputAsFloat("Enter new pharmacy latitude:");
                    String logo = getInput("Enter new pharmacy logo URL:");
                    String cityCode = getInput("Enter new City Code:");

                    if (validatePharmacyFields(name, address, longitude, latitude, cityCode)) {
                        pharmacy.setName(name);
                        pharmacy.setAddress(address);
                        pharmacy.setLongitude(longitude);
                        pharmacy.setLatitude(latitude);
                        pharmacy.setLogo(logo);
                        pharmacy.setCity_code(cityCode);
                        updatePharmacyUseCase.execute(pharmacy);
                        JOptionPane.showMessageDialog(null, "Pharmacy updated successfully.");
                    }
                },
                () -> JOptionPane.showMessageDialog(null, "Pharmacy not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void deletePharmacy() {
        int id = getInputAsInteger("Enter pharmacy ID:");
        if (id < 0) return; // Verifica si el ID es válido

        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this pharmacy?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            deletePharmacyUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "Pharmacy deleted successfully.");
        }
    }

    private String getInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return (input != null && !input.trim().isEmpty()) ? input.trim() : null;
    }

    private int getInputAsInteger(String message) {
        try {
            String input = getInput(message);
            return (input != null) ? Integer.parseInt(input) : -1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1; // Valor predeterminado para una entrada inválida
        }
    }

    private Float getInputAsFloat(String message) {
        try {
            String input = getInput(message);
            return (input != null) ? Float.parseFloat(input) : null;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Retorna null para una entrada inválida
        }
    }

    private boolean validatePharmacyFields(String name, String address, Float longitude, Float latitude, String cityCode) {
        if (name == null || address == null || longitude == null || latitude == null || cityCode == null) {
            JOptionPane.showMessageDialog(null, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void showPharmacyDetails(Pharmacy pharmacy) {
        String details = String.format("""
                Pharmacy found:
                ID: %d
                Name: %s
                Address: %s
                Longitude: %f
                Latitude: %f
                Logo: %s
                City Code: %s
                """, pharmacy.getId(), pharmacy.getName(), pharmacy.getAddress(), pharmacy.getLongitude(), pharmacy.getLatitude(), pharmacy.getLogo(), pharmacy.getCity_code());
        JOptionPane.showMessageDialog(null, details, "Pharmacy Details", JOptionPane.INFORMATION_MESSAGE);
    }
}


