package com.pharmacy_ofc.active_principle.infrastructure.controller;

import javax.swing.JOptionPane;
import com.pharmacy_ofc.active_principle.application.CreateAPUseCase;
import com.pharmacy_ofc.active_principle.application.DeleteAPUseCase;
import com.pharmacy_ofc.active_principle.application.FindAPByIdUseCase;
import com.pharmacy_ofc.active_principle.application.UpdateAPUseCase;
import com.pharmacy_ofc.active_principle.domain.entity.ActivePrinciple;
import com.pharmacy_ofc.active_principle.domain.service.APService;
import com.pharmacy_ofc.active_principle.infrastructure.repository.APRepository;

public class APController {

    private final APService apService;
    private final CreateAPUseCase createAPUseCase;
    private final FindAPByIdUseCase findAPByIdUseCase;
    private final UpdateAPUseCase updateAPUseCase;
    private final DeleteAPUseCase deleteAPUseCase;

    public APController() {
        this.apService = new APRepository();
        this.createAPUseCase = new CreateAPUseCase(apService);
        this.findAPByIdUseCase = new FindAPByIdUseCase(apService);
        this.updateAPUseCase = new UpdateAPUseCase(apService);
        this.deleteAPUseCase = new DeleteAPUseCase(apService);
    }

    public void mainMenu() {
        String options = """
                1. Add AP
                2. Search AP
                3. Update AP
                4. Delete AP
                5. Return to main menu
                """;

        int op;
        do {
            op = getInputAsInteger("Select an option:\n" + options);
            switch (op) {
                case 1 -> addAP();
                case 2 -> findAP();
                case 3 -> updateAP();
                case 4 -> deleteAP();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 5);
    }

    private void addAP() {
        String name = getInput("Enter AP name:");

        if (name == null) {
            JOptionPane.showMessageDialog(null, "AP name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ActivePrinciple activePrinciple = new ActivePrinciple();
        activePrinciple.setName(name);

        createAPUseCase.execute(activePrinciple);
        JOptionPane.showMessageDialog(null, "AP added successfully.");
    }

    private void findAP() {
        int id = getInputAsInteger("Enter AP id:");

        if (id < 0) return;

        findAPByIdUseCase.execute(id).ifPresentOrElse(
                ap -> showAPDetails(ap),
                () -> JOptionPane.showMessageDialog(null, "AP not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void updateAP() {
        int id = getInputAsInteger("Enter AP id:");

        if (id < 0) return;

        findAPByIdUseCase.execute(id).ifPresentOrElse(
                ap -> {
                    String name = getInput("Enter new AP name:");

                    if (name == null) {
                        JOptionPane.showMessageDialog(null, "AP name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    ap.setName(name);
                    updateAPUseCase.execute(ap);
                    JOptionPane.showMessageDialog(null, "AP updated successfully.");
                },
                () -> JOptionPane.showMessageDialog(null, "AP not found.", "Error", JOptionPane.ERROR_MESSAGE)
        );
    }

    private void deleteAP() {
        int id = getInputAsInteger("Enter AP id:");

        if (id < 0) return;

        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this AP?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            deleteAPUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "AP deleted successfully.");
        }
    }

    private String getInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return (input != null && !input.trim().isEmpty()) ? input.trim() : null;
    }

    private int getInputAsInteger(String message) {
        String input = getInput(message);
        if (input == null) {
            return -1; // Indica que se canceló la entrada
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1; // Valor inválido
        }
    }

    private void showAPDetails(ActivePrinciple ap) {
        String details = String.format("""
                AP found:
                ID: %d
                Name: %s
                """, ap.getId(), ap.getName());
        JOptionPane.showMessageDialog(null, details, "AP Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
