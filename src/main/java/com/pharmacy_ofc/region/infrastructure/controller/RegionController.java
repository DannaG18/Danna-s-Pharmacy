package com.pharmacy_ofc.region.infrastructure.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.pharmacy_ofc.region.domain.entity.Region;
import com.pharmacy_ofc.region.application.CreateRegionUseCase;
import com.pharmacy_ofc.region.application.DeleteRegionUseCase;
import com.pharmacy_ofc.region.application.FindRegionUseCase;
import com.pharmacy_ofc.region.application.UpdateRegionUseCase;
import com.pharmacy_ofc.region.domain.service.RegionService;
import com.pharmacy_ofc.region.infrastructure.repository.RegionRepository;

public class RegionController {
    private RegionService regionService;
    private CreateRegionUseCase createRegionUseCase;
    private FindRegionUseCase findRegionUseCase;
    private UpdateRegionUseCase updateRegionUseCase;
    private DeleteRegionUseCase deleteRegionUseCase;

    public RegionController() {
        this.regionService = new RegionRepository();
        this.createRegionUseCase = new CreateRegionUseCase(regionService);
        this.findRegionUseCase = new FindRegionUseCase(regionService);
        this.updateRegionUseCase = new UpdateRegionUseCase(regionService);
        this.deleteRegionUseCase = new DeleteRegionUseCase(regionService);
    }

    public void mainMenu() {
        String options = "1. Add Region\n2. Search Region\n3. Update Region\n4. Delete Region\n5. Return to main menu.";
        int op = 0;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");
        
        do {
            Object selectedValue = JOptionPane.showInputDialog(
                null, 
                options, 
                "Region Menu", 
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
                    addRegion();
                    break;
                case 2:
                    findRegion();
                    break;
                case 3:
                    updateRegion();
                    break;
                case 4:
                    deleteRegion();
                    break;
                case 5:
                    break;
                default:
                    break;
            } 
        } while (op != 5 && op != -1);
    }

    public void addRegion() {
        String code = JOptionPane.showInputDialog(null, "Region code: ");
        if (code == null) {
            return;
        }
        String name = JOptionPane.showInputDialog(null, "Region name: ");
        if (name == null) {
            return;
        }
        String country_code = JOptionPane.showInputDialog(null, "Region country: ");
        if (country_code == null) {
            return;
        }

        Region region = new Region();
        region.setCode(code);
        region.setName(name);
        region.setCountry_code(country_code);

        createRegionUseCase.execute(region);
        JOptionPane.showMessageDialog(null, "Region added successfully.");
    }

    public void findRegion() {
        String code = JOptionPane.showInputDialog(null, "Enter Region code: ");

        findRegionUseCase.execute(code).ifPresentOrElse(
            regionFound -> {
                JOptionPane.showMessageDialog(null, 
                    "Region found:\n" + 
                    "Code: " + regionFound.getCode() + "\n" +
                    "Name: " + regionFound.getName() + "\n" +
                    "Country Code: " + regionFound.getCountry_code(),
                    "Region Details", JOptionPane.INFORMATION_MESSAGE);
            },
            () -> {
                JOptionPane.showMessageDialog(null, "Region not found.", "Error", JOptionPane.ERROR_MESSAGE);
            });
    }

    public void updateRegion() {
        String code = JOptionPane.showInputDialog(null, "Enter Region code: ");

        findRegionUseCase.execute(code).ifPresentOrElse(
            currentRegion -> {
                String name = JOptionPane.showInputDialog(null, "Enter new Region name: ");
                if (name == null) {
                    return;
                }

                currentRegion.setName(name);
                updateRegionUseCase.execute(currentRegion);

                JOptionPane.showMessageDialog(null, "Region updated successfully.");
            }, 
            () -> {
                JOptionPane.showMessageDialog(null, "Region not found.", "Error", JOptionPane.ERROR_MESSAGE);
            });
    }

    public void deleteRegion() {
        String code = JOptionPane.showInputDialog(null, "Enter Region code: ");
        if (code == null) {
            return;
        }
        deleteRegionUseCase.execute(code);
        JOptionPane.showMessageDialog(null, "Region deleted successfully.");
    }
}

