package com.pharmacy_ofc.pharmacy_medicine.infrastructure.controller;

import javax.swing.JOptionPane;
import java.util.Optional;

import com.pharmacy_ofc.pharmacy_medicine.application.CreatePharmacyMedUseCase;
import com.pharmacy_ofc.pharmacy_medicine.application.DeletePharmacyMedUseCase;
import com.pharmacy_ofc.pharmacy_medicine.application.FindPharmacyMedUseCase;
import com.pharmacy_ofc.pharmacy_medicine.application.UpdatePharmacyMedUseCase;
import com.pharmacy_ofc.pharmacy_medicine.domain.entity.PharmacyMed;
import com.pharmacy_ofc.pharmacy_medicine.domain.service.PharmacyMedService;
import com.pharmacy_ofc.pharmacy_medicine.infrastructure.repository.PharmacyMedRepository;

public class PharmacyMedController {

    private final CreatePharmacyMedUseCase createPharmacyMedUseCase;
    private final FindPharmacyMedUseCase findPharmacyMedUseCase;
    private final UpdatePharmacyMedUseCase updatePharmacyMedUseCase;
    private final DeletePharmacyMedUseCase deletePharmacyMedUseCase;

    public PharmacyMedController() {
        PharmacyMedService pharmacyMedService = new PharmacyMedRepository();
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
        int idMedicine = getValidatedInputAsInteger("Enter PharmacyMed Medicine ID:");
        int idPharmacy = getValidatedInputAsInteger("Enter PharmacyMed Pharmacy ID:");
        float price = getValidatedInputAsFloat("Enter PharmacyMed price:");

        if (idMedicine >= 0 && idPharmacy >= 0 && price >= 0) {
            PharmacyMed pharmacyMed = new PharmacyMed();
            pharmacyMed.setId_medicine(idMedicine);
            pharmacyMed.setId_pharmacy(idPharmacy);
            pharmacyMed.setPrice(price);

            createPharmacyMedUseCase.execute(pharmacyMed);
            JOptionPane.showMessageDialog(null, "PharmacyMed added successfully.");
        }
    }

    private void findPharmacyMed() {
        int idMedicine = getValidatedInputAsInteger("Enter PharmacyMed Medicine ID:");
        int idPharmacy = getValidatedInputAsInteger("Enter PharmacyMed Pharmacy ID:");

        if (idMedicine >= 0 && idPharmacy >= 0) {
            Optional<PharmacyMed> pharmacyMed = findPharmacyMedUseCase.execute(idMedicine, idPharmacy);
            pharmacyMed.ifPresentOrElse(
                    this::showPharmacyMedDetails,
                    () -> JOptionPane.showMessageDialog(null, "PharmacyMed not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        }
    }

    private void updatePharmacyMed() {
        int idMedicine = getValidatedInputAsInteger("Enter PharmacyMed Medicine ID:");
        int idPharmacy = getValidatedInputAsInteger("Enter PharmacyMed Pharmacy ID:");

        if (idMedicine >= 0 && idPharmacy >= 0) {
            findPharmacyMedUseCase.execute(idMedicine, idPharmacy).ifPresentOrElse(
                    pharmacyMed -> {
                        float price = getValidatedInputAsFloat("Enter new PharmacyMed price:");
                        if (price >= 0) {
                            pharmacyMed.setPrice(price);
                            updatePharmacyMedUseCase.execute(pharmacyMed);
                            JOptionPane.showMessageDialog(null, "PharmacyMed updated successfully.");
                        }
                    },
                    () -> JOptionPane.showMessageDialog(null, "PharmacyMed not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        }
    }

    private void deletePharmacyMed() {
        int idMedicine = getValidatedInputAsInteger("Enter PharmacyMed Medicine ID:");
        int idPharmacy = getValidatedInputAsInteger("Enter PharmacyMed Pharmacy ID:");

        if (idMedicine >= 0 && idPharmacy >= 0) {
            if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this PharmacyMed?", "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                deletePharmacyMedUseCase.execute(idMedicine, idPharmacy);
                JOptionPane.showMessageDialog(null, "PharmacyMed deleted successfully.");
            }
        }
    }

    private int getValidatedInputAsInteger(String message) {
        int value;
        do {
            value = getInputAsInteger(message);
            if (value < 0) {
                JOptionPane.showMessageDialog(null, "Please enter a valid positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (value < 0);
        return value;
    }

    private float getValidatedInputAsFloat(String message) {
        float value;
        do {
            value = getInputAsFloat(message);
            if (value < 0) {
                JOptionPane.showMessageDialog(null, "Please enter a valid positive number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (value < 0);
        return value;
    }

    private String getInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return (input != null && !input.trim().isEmpty()) ? input : null;
    }

    private int getInputAsInteger(String message) {
        try {
            String input = getInput(message);
            return (input != null) ? Integer.parseInt(input) : -1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private float getInputAsFloat(String message) {
        try {
            String input = getInput(message);
            return (input != null) ? Float.parseFloat(input) : -1f;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1f;
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


