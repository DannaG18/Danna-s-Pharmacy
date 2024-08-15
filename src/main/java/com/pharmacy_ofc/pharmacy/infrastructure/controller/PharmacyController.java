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
    private final CreatePharmacyUseCase createPharmacyUseCase; // Cambiado a PascalCase
    private final FindPharmacyUseCase findPharmacyUseCase;     // Cambiado a PascalCase
    private final UpdatePharmacyUseCase updatePharmacyUseCase; // Cambiado a PascalCase
    private final DeletePharmacyUseCase deletePharmacyUseCase; // Cambiado a PascalCase

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
        } while (op != 5);
    }

    private void addPharmacy() {
        String name = getInput("Enter pharmacy name:");
        String address = getInput("Enter pharmacy address:");
        float longitude = getInputAsFloat("Enter pharmacy longitude:");
        float latitude = getInputAsFloat("Enter pharmacy latitude:");
        String logo = getInput("Enter pharmacy logo URL:");
        String cityCode = getInput("Enter City Code:");

        if (name != null && cityCode != null) {
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

        findPharmacyUseCase.execute(id).ifPresentOrElse(
                pharmacy -> showPharmacyDetails(pharmacy),
                () -> JOptionPane.showMessageDialog(null, "Pharmacy not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void updatePharmacy() {
        int id = getInputAsInteger("Enter pharmacy ID:");

        findPharmacyUseCase.execute(id).ifPresentOrElse(
                pharmacy -> {
                    String name = getInput("Enter new pharmacy name:");
                    String address = getInput("Enter new pharmacy address:");
                    float longitude = getInputAsFloat("Enter new pharmacy longitude:");
                    float latitude = getInputAsFloat("Enter new pharmacy latitude:");
                    String logo = getInput("Enter new pharmacy logo URL:");
                    String cityCode = getInput("Enter new City Code:");

                    if (name != null && cityCode != null) {
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

        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this pharmacy?", "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            deletePharmacyUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "Pharmacy deleted successfully.");
        }
    }

    private String getInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return (input != null && !input.trim().isEmpty()) ? input : null;
    }

    private int getInputAsInteger(String message) {
        try {
            return Integer.parseInt(getInput(message));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1; // Returning a default invalid value
        }
    }

    private float getInputAsFloat(String message) {
        try {
            return Float.parseFloat(getInput(message));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1f; // Returning a default invalid value
        }
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

