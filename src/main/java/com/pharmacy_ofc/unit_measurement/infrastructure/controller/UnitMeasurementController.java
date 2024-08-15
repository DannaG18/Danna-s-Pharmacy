package com.pharmacy_ofc.unit_measurement.infrastructure.controller;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.pharmacy_ofc.unit_measurement.application.CreateUnitMeasurementUseCase;
import com.pharmacy_ofc.unit_measurement.application.DeleteUnitMeasurementUseCase;
import com.pharmacy_ofc.unit_measurement.application.FindUnitMeasurementUseCase;
import com.pharmacy_ofc.unit_measurement.application.UpdateUnitMeasurementUseCase;
import com.pharmacy_ofc.unit_measurement.domain.entity.UnitMeasurement;
import com.pharmacy_ofc.unit_measurement.domain.service.UnitMeasurementService;
import com.pharmacy_ofc.unit_measurement.infrastructure.repository.UnitMeasurementRepository;

public class UnitMeasurementController {
    private UnitMeasurementService unitMeasurementService;
    private CreateUnitMeasurementUseCase createUnitMeasurementUseCase;
    private FindUnitMeasurementUseCase findUnitMeasurementUseCase;
    private UpdateUnitMeasurementUseCase updateUnitMeasurementUseCase;
    private DeleteUnitMeasurementUseCase deleteUnitMeasurementUseCase;

    public UnitMeasurementController() {
        this.unitMeasurementService = new UnitMeasurementRepository();
        this.createUnitMeasurementUseCase = new CreateUnitMeasurementUseCase(unitMeasurementService);
        this.findUnitMeasurementUseCase = new FindUnitMeasurementUseCase(unitMeasurementService);
        this.updateUnitMeasurementUseCase = new UpdateUnitMeasurementUseCase(unitMeasurementService);
        this.deleteUnitMeasurementUseCase = new DeleteUnitMeasurementUseCase(unitMeasurementService);
    }

            public void mainMenu() {
        String options = "1.Add UnitMeasurement \n2. Search UnitMeasurement\n3. Update UnitMeasurement\n4. Delete UnitMeasurement\n5. Return main menu.";
        int op;
                do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, options));
            switch (op) {
                case 1:
                    addUnitMeasurement();
                    break;
                case 2:
                    findUnitMeasurement();
                    break;
                case 3:
                    updateUnitMeasurement();
                    break;
                case 4:
                    deleteUnitMeasurement();
                    break;
                case 5:
                    break;
                default:
                JOptionPane.showMessageDialog(null, "Error en la opcion ingresada","Error",JOptionPane.ERROR_MESSAGE);
            } 
        } while (op!= 5);
    }

    public void addUnitMeasurement() {
    
        String name = JOptionPane.showInputDialog(null, "UnitMeasurement name: ");
        if (name == null || name.trim().isEmpty()) {
            return;
        }
    
        UnitMeasurement unitMeasurement = new UnitMeasurement();
        unitMeasurement.setName(name);
    
        createUnitMeasurementUseCase.execute(unitMeasurement);
        JOptionPane.showMessageDialog(null, "UnitMeasurement added successfully");
    }
    

    public void findUnitMeasurement() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter UnitMeasurement id: "));

            findUnitMeasurementUseCase.execute(id).ifPresentOrElse(
                UnitMeasurementFound -> {
                    JOptionPane.showMessageDialog(null, 
                    "UnitMeasurement found:\n" + 
                    "id: " + UnitMeasurementFound.getId() + "\n" +
                    "Name: " + UnitMeasurementFound.getName(),
                    "UnitMeasurement Details", JOptionPane.INFORMATION_MESSAGE);
                },
                () -> {
                    JOptionPane.showMessageDialog(null, "UnitMeasurement not found.","Error",JOptionPane.ERROR_MESSAGE);
                });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUnitMeasurement() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter UnitMeasurement id: "));

            findUnitMeasurementUseCase.execute(id).ifPresentOrElse(
                currentUnitMeasurement -> {
                    String name = JOptionPane.showInputDialog(null, "Enter new UnitMeasurement name: ");

                    currentUnitMeasurement.setName(name);
                    updateUnitMeasurementUseCase.execute(currentUnitMeasurement);

                    JOptionPane.showMessageDialog(null, "UnitMeasurement updated successfully.");
                }, 
                () -> {
                    JOptionPane.showMessageDialog(null, "UnitMeasurement not found.","Error",JOptionPane.ERROR_MESSAGE);
                });
        }
    }

    public void deleteUnitMeasurement() {
        try (Scanner scanner = new Scanner(System.in)) {
            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter UnitMeasurement id: "));
            deleteUnitMeasurementUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "UnitMeasurement deleted successfully.");
        }
    }
}
