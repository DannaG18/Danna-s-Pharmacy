package com.pharmacy_ofc.medicine.infrastructure.controller;

import javax.swing.JOptionPane;

import com.pharmacy_ofc.medicine.domain.entity.Medicine;
import com.pharmacy_ofc.medicine.application.CreateMedicineUseCase;
import com.pharmacy_ofc.medicine.application.DeleteMedicineUseCase;
import com.pharmacy_ofc.medicine.application.FindMedicineUseCase;
import com.pharmacy_ofc.medicine.application.UpdateMedicineUseCase;
import com.pharmacy_ofc.medicine.domain.service.MedicineService;
import com.pharmacy_ofc.medicine.infrastructure.repository.MedicineRepository;

public class MedicineController {
    private MedicineService medicineService;
    private CreateMedicineUseCase createMedicineUseCase;
    private FindMedicineUseCase findMedicineUseCase;
    private UpdateMedicineUseCase updateMedicineUseCase;
    private DeleteMedicineUseCase deleteMedicineUseCase;

    public MedicineController() {
        this.medicineService = new MedicineRepository();
        this.createMedicineUseCase = new CreateMedicineUseCase(medicineService);
        this.findMedicineUseCase = new FindMedicineUseCase(medicineService);
        this.updateMedicineUseCase = new UpdateMedicineUseCase(medicineService);
        this.deleteMedicineUseCase = new DeleteMedicineUseCase(medicineService);
    }

    public void mainMenu() {
        String options = """
                1. Add Medicine
                2. Search Medicine
                3. Update Medicine
                4. Delete Medicine
                5. Return to main menu
                """;
        int op;
        do {
            op = getInputAsInteger("Select an option:\n" + options);
            switch (op) {
                case 1 -> addMedicine();
                case 2 -> findMedicine();
                case 3 -> updateMedicine();
                case 4 -> deleteMedicine();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 5);
    }

    private void addMedicine() {
        Medicine medicine = new Medicine();

        medicine.setProceedings(promptInput("Medicine proceedings: "));
        medicine.setName_medicine(promptInput("Medicine name: "));
        medicine.setHealth_register(promptInput("Health register: "));
        medicine.setDescription(promptInput("Description: "));
        medicine.setDescription_short(promptInput("Short description: "));
        medicine.setName_rol(promptInput("Role name: "));

        medicine.setCode_mode_adm(getInputAsInteger("Code for mode of administration: "));
        medicine.setCode_act_p(getInputAsInteger("Code for active principle: "));
        medicine.setCode_uni_m(getInputAsInteger("Code for unit measurement: "));
        medicine.setCode_lab(getInputAsInteger("Code for laboratory: "));

        createMedicineUseCase.execute(medicine);
        JOptionPane.showMessageDialog(null, "Medicine added successfully.");
    }

    private void findMedicine() {
        try {
            int id = getInputAsInteger("Enter Medicine ID: ");
            findMedicineUseCase.execute(id).ifPresentOrElse(
                medicineFound -> JOptionPane.showMessageDialog(null, formatMedicineDetails(medicineFound), "Medicine Details", JOptionPane.INFORMATION_MESSAGE),
                () -> JOptionPane.showMessageDialog(null, "Medicine not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMedicine() {
        try {
            int id = getInputAsInteger("Enter Medicine ID: ");
            findMedicineUseCase.execute(id).ifPresentOrElse(
                currentMedicine -> {
                    currentMedicine.setProceedings(promptInput("Medicine proceedings: "));
                    currentMedicine.setName_medicine(promptInput("Medicine name: "));
                    currentMedicine.setHealth_register(promptInput("Health register: "));
                    currentMedicine.setDescription(promptInput("Description: "));
                    currentMedicine.setDescription_short(promptInput("Short description: "));
                    currentMedicine.setName_rol(promptInput("Role name: "));

                    currentMedicine.setCode_mode_adm(getInputAsInteger("Code for mode of administration: "));
                    currentMedicine.setCode_act_p(getInputAsInteger("Code for active principle: "));
                    currentMedicine.setCode_uni_m(getInputAsInteger("Code for unit measurement: "));
                    currentMedicine.setCode_lab(getInputAsInteger("Code for laboratory: "));

                    updateMedicineUseCase.execute(currentMedicine);
                    JOptionPane.showMessageDialog(null, "Medicine updated successfully.");
                },
                () -> JOptionPane.showMessageDialog(null, "Medicine not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteMedicine() {
        try {
            int id = getInputAsInteger("Enter Medicine ID: ");
            deleteMedicineUseCase.execute(id);
            JOptionPane.showMessageDialog(null, "Medicine deleted successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String promptInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Input cannot be empty. Operation cancelled.", "Warning", JOptionPane.WARNING_MESSAGE);
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        return input.trim();
    }

    private int getInputAsInteger(String message) {
        String input = promptInput(message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new NumberFormatException("Invalid integer input");
        }
    }

    private String formatMedicineDetails(Medicine medicine) {
        return "Medicine found:\n" +
               "ID: " + medicine.getId() + "\n" +
               "Proceedings: " + medicine.getProceedings() + "\n" +
               "Name: " + medicine.getName_medicine() + "\n" +
               "Health Register: " + medicine.getHealth_register() + "\n" +
               "Description: " + medicine.getDescription() + "\n" +
               "Short Description: " + medicine.getDescription_short() + "\n" +
               "Role Name: " + medicine.getName_rol() + "\n" +
               "Code Mode of Administration: " + medicine.getCode_mode_adm() + "\n" +
               "Code Active Principle: " + medicine.getCode_act_p() + "\n" +
               "Code Unit Measurement: " + medicine.getCode_uni_m() + "\n" +
               "Code Laboratory: " + medicine.getCode_lab();
    }
}



