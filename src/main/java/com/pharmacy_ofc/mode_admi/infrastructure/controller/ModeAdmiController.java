package com.pharmacy_ofc.mode_admi.infrastructure.controller;

import java.util.Scanner;

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
    private ModeAdmiService modeAdmiService;
    private CreateModeAdmiUseCase createModeAdmiUseCase;
    private FindModeAdmiUseCase findModeAdmiUseCase;
    private UpdateModeAdmiUseCase updateModeAdmiUseCase;
    private DeleteModeAdmiUseCase deleteModeAdmiUseCase;

    public ModeAdmiController() {
        this.modeAdmiService = new ModeAdmiRepository();
        this.createModeAdmiUseCase = new CreateModeAdmiUseCase(modeAdmiService);
        this.findModeAdmiUseCase = new FindModeAdmiUseCase(modeAdmiService);
        this.updateModeAdmiUseCase = new UpdateModeAdmiUseCase(modeAdmiService);
        this.deleteModeAdmiUseCase = new DeleteModeAdmiUseCase(modeAdmiService);
    }

        public void mainMenu() {
        String options = "1.Add ModeAdmi\n2. Search ModeAdmi\n3. Update ModeAdmi\n4. Delete ModeAdmi\n5. Return main menu.";
        int op = 0;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");
                do {
                    Object selectedValue = JOptionPane.showInputDialog(
                        null, 
                        options, 
                        "ModeAdmi Menu", 
                        JOptionPane.QUESTION_MESSAGE, 
                        customIcon, 
                        null, 
                        null
                    );

                    if (selectedValue != null) {
                        try {
                            op = Integer.parseInt(selectedValue.toString());
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid option. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                            continue; // Si la opción no es válida, vuelve a mostrar el menú
                        }
                    } else {
                        op = -1; // Si el usuario cancela, se asigna un valor fuera de rango para salir del bucle
                    }

            switch (op) {
                case 1:
                    addModeAdmi();
                    break;
                case 2:
                    findModeAdmi();
                    break;
                case 3:
                    updateModeAdmi();
                    break;
                case 4:
                    deleteModeAdmi();
                    break;
                case 5:
                    break;
                default:
                    break;
            } 
        } while (op!= 5 && op != -1);
    }

    public void addModeAdmi() {
    
        String name = JOptionPane.showInputDialog(null, "ModeAdmi name: ");
        if (name == null || name.trim().isEmpty()) {
            return;
        }
            ModeAdmi ModeAdmi = new ModeAdmi();
            ModeAdmi.setDescription(name);
    
            createModeAdmiUseCase.execute(ModeAdmi);
            JOptionPane.showMessageDialog(null, "ModeAdmi added successfully");
    }

    public void findModeAdmi() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ModeAdmi id: "));

            findModeAdmiUseCase.execute(id).ifPresentOrElse(
                ModeAdmiFound -> {
                    JOptionPane.showMessageDialog(null, 
                    "ModeAdmi found:\n" + 
                    "id: " + ModeAdmiFound.getId() + "\n" +
                    "Name: " + ModeAdmiFound.getDescription(),
                    "ModeAdmi Details", JOptionPane.INFORMATION_MESSAGE);
                },
                () -> {
                    JOptionPane.showMessageDialog(null, "ModeAdmi not found.","Error",JOptionPane.ERROR_MESSAGE);
                });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateModeAdmi() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ModeAdmi id: "));

            findModeAdmiUseCase.execute(id).ifPresentOrElse(
                currentModeAdmi -> {
                    String name = JOptionPane.showInputDialog(null, "Enter new ModeAdmi name: ");

                    currentModeAdmi.setDescription(name);
                    updateModeAdmiUseCase.execute(currentModeAdmi);

                    JOptionPane.showMessageDialog(null, "ModeAdmi updated successfully.");
                }, 
                () -> {
                    JOptionPane.showMessageDialog(null, "ModeAdmi not found.","Error",JOptionPane.ERROR_MESSAGE);
                });
        }
    }

    public void deleteModeAdmi() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter ModeAdmi id: "));
            deleteModeAdmiUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "ModeAdmi deleted successfully.");
        }
    }
}
