package com.pharmacy_ofc.unit_measurement.infrastructure.controller;

import javax.swing.JOptionPane;
import java.util.Optional;

import com.pharmacy_ofc.unit_measurement.application.CreateUnitMeasurementUseCase;
import com.pharmacy_ofc.unit_measurement.application.DeleteUnitMeasurementUseCase;
import com.pharmacy_ofc.unit_measurement.application.FindUnitMeasurementUseCase;
import com.pharmacy_ofc.unit_measurement.application.UpdateUnitMeasurementUseCase;
import com.pharmacy_ofc.unit_measurement.domain.entity.UnitMeasurement;
import com.pharmacy_ofc.unit_measurement.domain.service.UnitMeasurementService;
import com.pharmacy_ofc.unit_measurement.infrastructure.repository.UnitMeasurementRepository;

public class UnitMeasurementController {

    private final CreateUnitMeasurementUseCase createUnitMeasurementUseCase;
    private final FindUnitMeasurementUseCase findUnitMeasurementUseCase;
    private final UpdateUnitMeasurementUseCase updateUnitMeasurementUseCase;
    private final DeleteUnitMeasurementUseCase deleteUnitMeasurementUseCase;

    public UnitMeasurementController() {
        UnitMeasurementService unitMeasurementService = new UnitMeasurementRepository();
        this.createUnitMeasurementUseCase = new CreateUnitMeasurementUseCase(unitMeasurementService);
        this.findUnitMeasurementUseCase = new FindUnitMeasurementUseCase(unitMeasurementService);
        this.updateUnitMeasurementUseCase = new UpdateUnitMeasurementUseCase(unitMeasurementService);
        this.deleteUnitMeasurementUseCase = new DeleteUnitMeasurementUseCase(unitMeasurementService);
    }

    public void mainMenu() {
        String options = """
                1. Add Unit Measurement
                2. Search Unit Measurement
                3. Update Unit Measurement
                4. Delete Unit Measurement
                5. Return to main menu
                """;
        int option;

        do {
            option = getMenuOption(options);
            switch (option) {
                case 1 -> addUnitMeasurement();
                case 2 -> findUnitMeasurement();
                case 3 -> updateUnitMeasurement();
                case 4 -> deleteUnitMeasurement();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (option != 5);
    }

    private int getMenuOption(String options) {
        try {
            String selectedValue = JOptionPane.showInputDialog(null, options);
            return selectedValue != null ? Integer.parseInt(selectedValue.trim()) : 5; // Exit on cancel
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid option. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public void addUnitMeasurement() {
        String name = getValidatedInput("Enter Unit Measurement name: ");
        if (name == null) return;

        UnitMeasurement unitMeasurement = new UnitMeasurement();
        unitMeasurement.setName(name);

        createUnitMeasurementUseCase.execute(unitMeasurement);
        JOptionPane.showMessageDialog(null, "Unit Measurement added successfully.");
    }

    public void findUnitMeasurement() {
        try {
            int id = getValidatedId("Enter Unit Measurement ID: ");
            if (id == -1) return;

            Optional<UnitMeasurement> unitMeasurementFound = findUnitMeasurementUseCase.execute(id);
            unitMeasurementFound.ifPresentOrElse(
                this::showUnitMeasurementDetails,
                () -> JOptionPane.showMessageDialog(null, "Unit Measurement not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateUnitMeasurement() {
        try {
            int id = getValidatedId("Enter Unit Measurement ID: ");
            if (id == -1) return;

            findUnitMeasurementUseCase.execute(id).ifPresentOrElse(
                currentUnitMeasurement -> {
                    String name = getValidatedInput("Enter new Unit Measurement name: ");
                    if (name != null) {
                        currentUnitMeasurement.setName(name);
                        updateUnitMeasurementUseCase.execute(currentUnitMeasurement);
                        JOptionPane.showMessageDialog(null, "Unit Measurement updated successfully.");
                    }
                },
                () -> JOptionPane.showMessageDialog(null, "Unit Measurement not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteUnitMeasurement() {
        try {
            int id = getValidatedId("Enter Unit Measurement ID: ");
            if (id == -1) return;

            deleteUnitMeasurementUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "Unit Measurement deleted successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getValidatedInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return input != null && !input.trim().isEmpty() ? input.trim() : null;
    }

    private int getValidatedId(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        if (input == null || input.trim().isEmpty()) return -1;

        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private void showUnitMeasurementDetails(UnitMeasurement unitMeasurement) {
        String details = String.format("""
                Unit Measurement found:
                ID: %d
                Name: %s
                """, unitMeasurement.getId(), unitMeasurement.getName());
        JOptionPane.showMessageDialog(null, details, "Unit Measurement Details", JOptionPane.INFORMATION_MESSAGE);
    }
}

