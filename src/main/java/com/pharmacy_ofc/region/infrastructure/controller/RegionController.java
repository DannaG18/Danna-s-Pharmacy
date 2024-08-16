package com.pharmacy_ofc.region.infrastructure.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Optional;

import com.pharmacy_ofc.region.domain.entity.Region;
import com.pharmacy_ofc.region.application.CreateRegionUseCase;
import com.pharmacy_ofc.region.application.DeleteRegionUseCase;
import com.pharmacy_ofc.region.application.FindRegionUseCase;
import com.pharmacy_ofc.region.application.UpdateRegionUseCase;
import com.pharmacy_ofc.region.domain.service.RegionService;
import com.pharmacy_ofc.region.infrastructure.repository.RegionRepository;

public class RegionController {

    private final CreateRegionUseCase createRegionUseCase;
    private final FindRegionUseCase findRegionUseCase;
    private final UpdateRegionUseCase updateRegionUseCase;
    private final DeleteRegionUseCase deleteRegionUseCase;

    public RegionController() {
        RegionService regionService = new RegionRepository();
        this.createRegionUseCase = new CreateRegionUseCase(regionService);
        this.findRegionUseCase = new FindRegionUseCase(regionService);
        this.updateRegionUseCase = new UpdateRegionUseCase(regionService);
        this.deleteRegionUseCase = new DeleteRegionUseCase(regionService);
    }

    public void mainMenu() {
        String options = """
                1. Add Region
                2. Search Region
                3. Update Region
                4. Delete Region
                5. Return to main menu
                """;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");
        int option;

        do {
            option = getMenuOption(options, customIcon);
            switch (option) {
                case 1 -> addRegion();
                case 2 -> findRegion();
                case 3 -> updateRegion();
                case 4 -> deleteRegion();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (option != 5 && option != -1);
    }

    private int getMenuOption(String options, ImageIcon icon) {
        try {
            Object selectedValue = JOptionPane.showInputDialog(
                null, options, "Region Menu", JOptionPane.QUESTION_MESSAGE, icon, null, null
            );
            return (selectedValue != null) ? Integer.parseInt(selectedValue.toString()) : -1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid option. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    private void addRegion() {
        String code = getValidatedInput("Region code: ");
        String name = getValidatedInput("Region name: ");
        String countryCode = getValidatedInput("Region country code: ");

        if (code != null && name != null && countryCode != null) {
            Region region = new Region();
            region.setCode(code);
            region.setName(name);
            region.setCountry_code(countryCode);

            createRegionUseCase.execute(region);
            JOptionPane.showMessageDialog(null, "Region added successfully.");
        }
    }

    private void findRegion() {
        String code = getValidatedInput("Enter Region code: ");

        if (code != null) {
            Optional<Region> regionFound = findRegionUseCase.execute(code);
            regionFound.ifPresentOrElse(
                this::showRegionDetails,
                () -> JOptionPane.showMessageDialog(null, "Region not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        }
    }

    private void updateRegion() {
        String code = getValidatedInput("Enter Region code: ");

        if (code != null) {
            findRegionUseCase.execute(code).ifPresentOrElse(
                currentRegion -> {
                    String name = getValidatedInput("Enter new Region name: ");
                    if (name != null) {
                        currentRegion.setName(name);
                        updateRegionUseCase.execute(currentRegion);
                        JOptionPane.showMessageDialog(null, "Region updated successfully.");
                    }
                },
                () -> JOptionPane.showMessageDialog(null, "Region not found.", "Error", JOptionPane.ERROR_MESSAGE)
            );
        }
    }

    private void deleteRegion() {
        String code = getValidatedInput("Enter Region code: ");

        if (code != null) {
            deleteRegionUseCase.execute(code);
            JOptionPane.showMessageDialog(null, "Region deleted successfully.");
        }
    }

    private String getValidatedInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return (input != null && !input.trim().isEmpty()) ? input.trim() : null;
    }

    private void showRegionDetails(Region region) {
        String details = String.format("""
                Region found:
                Code: %s
                Name: %s
                Country Code: %s
                """, region.getCode(), region.getName(), region.getCountry_code());
        JOptionPane.showMessageDialog(null, details, "Region Details", JOptionPane.INFORMATION_MESSAGE);
    }
}

