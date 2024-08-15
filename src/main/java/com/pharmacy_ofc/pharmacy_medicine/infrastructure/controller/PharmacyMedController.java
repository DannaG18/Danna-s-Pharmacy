package com.pharmacy_ofc.pharmacy_medicine.infrastructure.controller;

import javax.swing.JOptionPane;

import com.pharmacy_ofc.pharmacy_medicine.application.CreatePharmacyMedUseCase;
import com.pharmacy_ofc.pharmacy_medicine.application.DeletePharmacyMedUseCase;
import com.pharmacy_ofc.pharmacy_medicine.application.FindPharmacyMedUseCase;
import com.pharmacy_ofc.pharmacy_medicine.application.UpdatePharmacyMedUseCase;
import com.pharmacy_ofc.pharmacy_medicine.domain.entity.PharmacyMed;
import com.pharmacy_ofc.pharmacy_medicine.domain.service.PharmacyMedService;
import com.pharmacy_ofc.pharmacy_medicine.infrastructure.repository.PharmacyMedRepository;

public class PharmacyMedController {
    private final PharmacyMedService pharmacyMedService;
    private final CreatePharmacyMedUseCase createPharmacyMedUseCase;
    private final FindPharmacyMedUseCase findPharmacyMedUseCase;
    private final UpdatePharmacyMedUseCase updatePharmacyMedUseCase;
    private final DeletePharmacyMedUseCase deletePharmacyMedUseCase;

    public PharmacyMedController() {
        this.pharmacyMedService = new PharmacyMedRepository();
        this.createPharmacyMedUseCase = new CreatePharmacyMedUseCase(pharmacyMedService);
        this.findPharmacyMedUseCase = new FindPharmacyMedUseCase(pharmacyMedService);
        this.updatePharmacyMedUseCase = new UpdatePharmacyMedUseCase(pharmacyMedService);
        this.deletePharmacyMedUseCase = new DeletePharmacyMedUseCase(pharmacyMedService);
    }

    public void mainMenu() {
        String options = """
                1. Add PharmacyMed
                2. Search PharmacyMed
                3. Update PharmacyMed
                4. Delete PharmacyMed
                5. Return to Main Menu
                """;
        int op;
        do {
            op = getInputAsInteger("Select an option:\n" + options);
            switch (op) {
                case 1 -> addPharmacyMed();
                case 2 -> findPharmacyMed();
                case 3 -> updatePharmacyMed();
                case 4 -> deletePharmacyMed();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 5);
    }

    private void addPharmacyMed() {
        int id_medicine = getInputAsInteger("Enter PharmacyMed id_medicine:");
        int id_pharmacy = getInputAsInteger("Enter PharmacyMed id_pharmacy:");
        float price = getInputAsFloat("Enter PharmacyMed price:");

        PharmacyMed pharmacyMed = new PharmacyMed();
        pharmacyMed.setId_medicine(id_medicine);
        pharmacyMed.setId_pharmacy(id_pharmacy);
        pharmacyMed.setPrice(price);

        createPharmacyMedUseCase.execute(pharmacyMed);
        JOptionPane.showMessageDialog(null, "PharmacyMed added successfully.");
    }

    private void findPharmacyMed() {
        int id_medicine = getInputAsInteger("Enter PharmacyMed id_medicine:");
        int id_pharmacy = getInputAsInteger("Enter PharmacyMed id_pharmacy:");

        findPharmacyMedUseCase.execute(id_medicine, id_pharmacy).ifPresentOrElse(
                this::showPharmacyMedDetails,
                () -> JOptionPane.showMessageDialog(null, "PharmacyMed not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void updatePharmacyMed() {
        int id_medicine = getInputAsInteger("Enter PharmacyMed id_medicine:");
        int id_pharmacy = getInputAsInteger("Enter PharmacyMed id_pharmacy:");

        findPharmacyMedUseCase.execute(id_medicine, id_pharmacy).ifPresentOrElse(
                pharmacyMed -> {
                    float price = getInputAsFloat("Enter new PharmacyMed price:");

                    pharmacyMed.setPrice(price);
                    updatePharmacyMedUseCase.execute(pharmacyMed);
                    JOptionPane.showMessageDialog(null, "PharmacyMed updated successfully.");
                },
                () -> JOptionPane.showMessageDialog(null, "PharmacyMed not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void deletePharmacyMed() {
        int id_medicine = getInputAsInteger("Enter PharmacyMed id_medicine:");
        int id_pharmacy = getInputAsInteger("Enter PharmacyMed id_pharmacy:");

        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this PharmacyMed?", "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            deletePharmacyMedUseCase.execute(id_medicine, id_pharmacy);
            JOptionPane.showMessageDialog(null, "PharmacyMed deleted successfully.");
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

    private void showPharmacyMedDetails(PharmacyMed pharmacyMed) {
        String details = String.format("""
                PharmacyMed found:
                Medicine ID: %d
                Pharmacy ID: %d
                Price: %.2f
                """, pharmacyMed.getId_medicine(), pharmacyMed.getId_pharmacy(), pharmacyMed.getPrice());
        JOptionPane.showMessageDialog(null, details, "PharmacyMed Details", JOptionPane.INFORMATION_MESSAGE);
    }
}

