package com.pharmacy_ofc.laboratory.infrastructure.controller;

import javax.swing.JOptionPane;

import com.pharmacy_ofc.laboratory.application.CreateLaboratoryUseCase;
import com.pharmacy_ofc.laboratory.application.DeleteLaboratoryUseCase;
import com.pharmacy_ofc.laboratory.application.FindLaboratoryUseCase;
import com.pharmacy_ofc.laboratory.application.UpdateLaboratoryUseCase;
import com.pharmacy_ofc.laboratory.domain.entity.Laboratory;
import com.pharmacy_ofc.laboratory.domain.service.LaboratoryService;
import com.pharmacy_ofc.laboratory.infrastructure.repository.LaboratoryRepository;

public class LaboratoryController {
    private final LaboratoryService laboratoryService;
    private final CreateLaboratoryUseCase createLaboratoryUseCase;
    private final FindLaboratoryUseCase findLaboratoryUseCase;
    private final UpdateLaboratoryUseCase updateLaboratoryUseCase;
    private final DeleteLaboratoryUseCase deleteLaboratoryUseCase;

    public LaboratoryController() {
        this.laboratoryService = new LaboratoryRepository();
        this.createLaboratoryUseCase = new CreateLaboratoryUseCase(laboratoryService);
        this.findLaboratoryUseCase = new FindLaboratoryUseCase(laboratoryService);
        this.updateLaboratoryUseCase = new UpdateLaboratoryUseCase(laboratoryService);
        this.deleteLaboratoryUseCase = new DeleteLaboratoryUseCase(laboratoryService);
    }

    public void mainMenu() {
        String options = """
                1. Add Laboratory
                2. Search Laboratory
                3. Update Laboratory
                4. Delete Laboratory
                5. Return to main menu
                """;
        int op;
        do {
            op = getInputAsInteger("Select an option:\n" + options);
            switch (op) {
                case 1 -> addLaboratory();
                case 2 -> findLaboratory();
                case 3 -> updateLaboratory();
                case 4 -> deleteLaboratory();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 5);
    }

    private void addLaboratory() {
        String name = getInput("Enter Laboratory name:");
        String cityCode = getInput("Enter City Code:");

        if (name != null && cityCode != null) {
            Laboratory laboratory = new Laboratory();
            laboratory.setName(name);
            laboratory.setCity_code(cityCode);

            createLaboratoryUseCase.execute(laboratory);
            JOptionPane.showMessageDialog(null, "Laboratory added successfully.");
        }
    }

    private void findLaboratory() {
        int id = getInputAsInteger("Enter Laboratory ID:");

        findLaboratoryUseCase.execute(id).ifPresentOrElse(
                laboratory -> showLaboratoryDetails(laboratory),
                () -> JOptionPane.showMessageDialog(null, "Laboratory not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void updateLaboratory() {
        int id = getInputAsInteger("Enter Laboratory ID:");

        findLaboratoryUseCase.execute(id).ifPresentOrElse(
                laboratory -> {
                    String name = getInput("Enter new Laboratory name:");
                    String cityCode = getInput("Enter new City Code:");

                    if (name != null && cityCode != null) {
                        laboratory.setName(name);
                        laboratory.setCity_code(cityCode);
                        updateLaboratoryUseCase.execute(laboratory);
                        JOptionPane.showMessageDialog(null, "Laboratory updated successfully.");
                    }
                },
                () -> JOptionPane.showMessageDialog(null, "Laboratory not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void deleteLaboratory() {
        int id = getInputAsInteger("Enter Laboratory ID:");

        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this laboratory?", "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            deleteLaboratoryUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "Laboratory deleted successfully.");
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

    private void showLaboratoryDetails(Laboratory laboratory) {
        String details = String.format("""
                Laboratory found:
                ID: %d
                Name: %s
                City Code: %s
                """, laboratory.getId(), laboratory.getName(), laboratory.getCity_code());
        JOptionPane.showMessageDialog(null, details, "Laboratory Details", JOptionPane.INFORMATION_MESSAGE);
    }
}



