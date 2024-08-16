package com.pharmacy_ofc.mode_admi.infrastructure.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.pharmacy_ofc.mode_admi.application.CreateModeAdmiUseCase;
import com.pharmacy_ofc.mode_admi.application.DeleteModeAdmiUseCase;
import com.pharmacy_ofc.mode_admi.application.FindModeAdmiUseCase;
import com.pharmacy_ofc.mode_admi.application.UpdateModeAdmiUseCase;
import com.pharmacy_ofc.mode_admi.domain.entity.ModeAdmi;
import com.pharmacy_ofc.mode_admi.domain.service.ModeAdmiService;
import com.pharmacy_ofc.mode_admi.infrastructure.repository.ModeAdmiRepository;

public class ModeAdmiController {
    private final ModeAdmiService modeAdmiService;
    private final CreateModeAdmiUseCase createModeAdmiUseCase;
    private final FindModeAdmiUseCase findModeAdmiUseCase;
    private final UpdateModeAdmiUseCase updateModeAdmiUseCase;
    private final DeleteModeAdmiUseCase deleteModeAdmiUseCase;

    public ModeAdmiController() {
        this.modeAdmiService = new ModeAdmiRepository();
        this.createModeAdmiUseCase = new CreateModeAdmiUseCase(modeAdmiService);
        this.findModeAdmiUseCase = new FindModeAdmiUseCase(modeAdmiService);
        this.updateModeAdmiUseCase = new UpdateModeAdmiUseCase(modeAdmiService);
        this.deleteModeAdmiUseCase = new DeleteModeAdmiUseCase(modeAdmiService);
    }

    public void mainMenu() {
        String options = """
                1. Add ModeAdmi
                2. Search ModeAdmi
                3. Update ModeAdmi
                4. Delete ModeAdmi
                5. Return to main menu
                """;
        int option;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");
    
        do {
            option = getMenuOption(options, customIcon);
            switch (option) {
                case 1 -> addModeAdmi();
                case 2 -> findModeAdmi();
                case 3 -> updateModeAdmi();
                case 4 -> deleteModeAdmi();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (option != 5 && option != -1);
    }

    private int getMenuOption(String options, ImageIcon icon) {
        try {
            Object selectedValue = JOptionPane.showInputDialog(
                null, options, "ModeAdmi Menu", JOptionPane.QUESTION_MESSAGE, icon, null, null
            );
            return (selectedValue != null) ? Integer.parseInt(selectedValue.toString()) : -1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid option. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }
    

    private void addModeAdmi() {
        String name = JOptionPane.showInputDialog(null, "Enter ModeAdmi name: ");
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Input cannot be empty. Operation cancelled.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ModeAdmi modeAdmi = new ModeAdmi();
        modeAdmi.setDescription(name);
        createModeAdmiUseCase.execute(modeAdmi);
        JOptionPane.showMessageDialog(null, "ModeAdmi added successfully.");
    }

    private void findModeAdmi() {
        String input = JOptionPane.showInputDialog(null, "Enter ModeAdmi ID: ");
        try {
            int id = Integer.parseInt(input);
            findModeAdmiUseCase.execute(id).ifPresentOrElse(
                modeAdmiFound -> JOptionPane.showMessageDialog(null, formatModeAdmiDetails(modeAdmiFound), "ModeAdmi Details", JOptionPane.INFORMATION_MESSAGE),
                () -> JOptionPane.showMessageDialog(null, "ModeAdmi not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateModeAdmi() {
        String input = JOptionPane.showInputDialog(null, "Enter ModeAdmi ID: ");
        try {
            int id = Integer.parseInt(input);
            findModeAdmiUseCase.execute(id).ifPresentOrElse(
                currentModeAdmi -> {
                    String name = JOptionPane.showInputDialog(null, "Enter new ModeAdmi name: ");
                    if (name != null && !name.trim().isEmpty()) {
                        currentModeAdmi.setDescription(name);
                        updateModeAdmiUseCase.execute(currentModeAdmi);
                        JOptionPane.showMessageDialog(null, "ModeAdmi updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Input cannot be empty. Operation cancelled.", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                },
                () -> JOptionPane.showMessageDialog(null, "ModeAdmi not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteModeAdmi() {
        String input = JOptionPane.showInputDialog(null, "Enter ModeAdmi ID: ");
        try {
            int id = Integer.parseInt(input);
            deleteModeAdmiUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "ModeAdmi deleted successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String formatModeAdmiDetails(ModeAdmi modeAdmi) {
        return "ModeAdmi Details:\n" +
               "ID: " + modeAdmi.getId() + "\n" +
               "Name: " + modeAdmi.getDescription();
    }
}

