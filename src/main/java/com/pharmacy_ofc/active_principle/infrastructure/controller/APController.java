package com.pharmacy_ofc.active_principle.infrastructure.controller;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.pharmacy_ofc.active_principle.application.CreateAPUseCase;
import com.pharmacy_ofc.active_principle.application.DeleteAPUseCase;
import com.pharmacy_ofc.active_principle.application.FindAPByIdUseCase;
import com.pharmacy_ofc.active_principle.application.UpdateAPUseCase;
import com.pharmacy_ofc.active_principle.domain.entity.ActivePrinciple;
import com.pharmacy_ofc.active_principle.domain.service.APService;
import com.pharmacy_ofc.active_principle.infrastructure.repository.APRepository;

public class APController {
    private APService apService;
    private CreateAPUseCase createAPUseCase;
    private FindAPByIdUseCase findAPByIdUseCase;
    private UpdateAPUseCase updateAPUseCase;
    private DeleteAPUseCase deleteAPUseCase;

    public APController() {
        this.apService = new APRepository();
        this.createAPUseCase = new CreateAPUseCase(apService);
        this.findAPByIdUseCase = new FindAPByIdUseCase(apService);
        this.updateAPUseCase = new UpdateAPUseCase(apService);
        this.deleteAPUseCase = new DeleteAPUseCase(apService);
    }

        public void mainMenu() {
        String options = "1.Add AP \n2. Search AP\n3. Update AP\n4. Delete AP\n5. Return main menu.";
        int op;
                do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, options));
            switch (op) {
                case 1:
                    addAP();
                    break;
                case 2:
                    findAP();
                    break;
                case 3:
                    updateAP();
                    break;
                case 4:
                    deleteAP();
                    break;
                case 5:
                    break;
                default:
                JOptionPane.showMessageDialog(null, "Error en la opcion ingresada","Error",JOptionPane.ERROR_MESSAGE);
            } 
        } while (op!= 5);
    }

    public void addAP() {
    
        String name = JOptionPane.showInputDialog(null, "AP name: ");
        if (name == null || name.trim().isEmpty()) {
            return;
        }
    
        ActivePrinciple actP = new ActivePrinciple();
        actP.setName(name);
    
        createAPUseCase.execute(actP);
        JOptionPane.showMessageDialog(null, "AP added successfully");
    }
    

    public void findAP() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter AP id: "));

            findAPByIdUseCase.execute(id).ifPresentOrElse(
                APFound -> {
                    JOptionPane.showMessageDialog(null, 
                    "AP found:\n" + 
                    "id: " + APFound.getId() + "\n" +
                    "Name: " + APFound.getName(),
                    "AP Details", JOptionPane.INFORMATION_MESSAGE);
                },
                () -> {
                    JOptionPane.showMessageDialog(null, "AP not found.","Error",JOptionPane.ERROR_MESSAGE);
                });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAP() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter AP id: "));

            findAPByIdUseCase.execute(id).ifPresentOrElse(
                currentAP -> {
                    String name = JOptionPane.showInputDialog(null, "Enter new AP name: ");

                    currentAP.setName(name);
                    updateAPUseCase.execute(currentAP);

                    JOptionPane.showMessageDialog(null, "AP updated successfully.");
                }, 
                () -> {
                    JOptionPane.showMessageDialog(null, "AP not found.","Error",JOptionPane.ERROR_MESSAGE);
                });
        }
    }

    public void deleteAP() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter AP id: "));
            deleteAPUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "AP deleted successfully.");
        }
    }
}
