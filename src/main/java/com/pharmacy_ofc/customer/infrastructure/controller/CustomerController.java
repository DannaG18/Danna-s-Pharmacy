package com.pharmacy_ofc.customer.infrastructure.controller;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.pharmacy_ofc.customer.domain.entity.Customer;
import com.pharmacy_ofc.customer.application.CreateCustomerUseCase;
import com.pharmacy_ofc.customer.application.DeleteCustomerUseCase;
import com.pharmacy_ofc.customer.application.FindCustomerUseCase;
import com.pharmacy_ofc.customer.application.UpdateCustomerUseCase;
import com.pharmacy_ofc.customer.domain.service.CustomerService;
import com.pharmacy_ofc.customer.infrastructure.repository.CustomerRepository;

public class CustomerController {

    private final CustomerService customerService;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final FindCustomerUseCase findCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public CustomerController() {
        this.customerService = new CustomerRepository();
        this.createCustomerUseCase = new CreateCustomerUseCase(customerService);
        this.findCustomerUseCase = new FindCustomerUseCase(customerService);
        this.updateCustomerUseCase = new UpdateCustomerUseCase(customerService);
        this.deleteCustomerUseCase = new DeleteCustomerUseCase(customerService);
    }

    public void mainMenu() {
        String options = """
                1. Add Customer
                2. Search Customer
                3. Update Customer
                4. Delete Customer
                5. Return to main menu
                """;
        int op;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");

        do {
            String selectedValue = getInput(options, "Customer Menu", customIcon);
            if (selectedValue == null) {
                break; // Salir del bucle si el usuario cancela la entrada
            }

            try {
                op = Integer.parseInt(selectedValue);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid option. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (op) {
                case 1 -> addCustomer();
                case 2 -> findCustomer();
                case 3 -> updateCustomer();
                case 4 -> deleteCustomer();
                case 5 -> JOptionPane.showMessageDialog(null, "Exiting the menu.");
                default -> JOptionPane.showMessageDialog(null, "Invalid option selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (true);
    }

    private void addCustomer() {
        String id = getInput("Customer ID:");
        if (id == null || id.isEmpty()) {
            showMessage("Customer ID cannot be empty.", "Error");
            return;
        }

        String name = getInput("Customer Name:");
        if (name == null || name.isEmpty()) {
            showMessage("Customer Name cannot be empty.", "Error");
            return;
        }

        String lastName = getInput("Customer Last Name:");
        if (lastName == null || lastName.isEmpty()) {
            showMessage("Customer Last Name cannot be empty.", "Error");
            return;
        }

        String email = getInput("Customer Email:");
        if (email == null || email.isEmpty()) {
            showMessage("Customer Email cannot be empty.", "Error");
            return;
        }

        String birthdate = getInput("Customer BirthDate:");
        if (birthdate == null || birthdate.isEmpty()) {
            showMessage("Customer BirthDate cannot be empty.", "Error");
            return;
        }

        float longitude = getInputAsFloat("Enter Customer Longitude:");
        float latitude = getInputAsFloat("Enter Customer Latitude:");

        String cityCode = getInput("Customer City:");
        if (cityCode == null || cityCode.isEmpty()) {
            showMessage("Customer City cannot be empty.", "Error");
            return;
        }

        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setLast_name(lastName);
        customer.setEmail(email);
        customer.setBirthdate(birthdate);
        customer.setLong_customer(longitude);
        customer.setLat_customer(latitude);
        customer.setCity_code(cityCode);

        createCustomerUseCase.execute(customer);
        showMessage("Customer added successfully.");
    }

    private void findCustomer() {
        String id = getInput("Enter Customer ID:");
        if (id == null || id.isEmpty()) {
            showMessage("Customer ID cannot be empty.", "Error");
            return;
        }

        findCustomerUseCase.execute(id).ifPresentOrElse(
                this::showCustomerDetails,
                () -> showMessage("Customer not found.", "Error")
        );
    }

    private void updateCustomer() {
        String id = getInput("Enter Customer ID:");
        if (id == null || id.isEmpty()) {
            showMessage("Customer ID cannot be empty.", "Error");
            return;
        }

        findCustomerUseCase.execute(id).ifPresentOrElse(
                currentCustomer -> {
                    String name = getInput("Customer Name:");
                    if (name == null || name.isEmpty()) {
                        showMessage("Customer Name cannot be empty.", "Error");
                        return;
                    }

                    String lastName = getInput("Customer Last Name:");
                    if (lastName == null || lastName.isEmpty()) {
                        showMessage("Customer Last Name cannot be empty.", "Error");
                        return;
                    }

                    String email = getInput("Customer Email:");
                    if (email == null || email.isEmpty()) {
                        showMessage("Customer Email cannot be empty.", "Error");
                        return;
                    }

                    String birthdate = getInput("Customer BirthDate:");
                    if (birthdate == null || birthdate.isEmpty()) {
                        showMessage("Customer BirthDate cannot be empty.", "Error");
                        return;
                    }

                    float longitude = getInputAsFloat("Enter Customer Longitude:");
                    float latitude = getInputAsFloat("Enter Customer Latitude:");

                    String cityCode = getInput("Customer City:");
                    if (cityCode == null || cityCode.isEmpty()) {
                        showMessage("Customer City cannot be empty.", "Error");
                        return;
                    }

                    currentCustomer.setName(name);
                    currentCustomer.setLast_name(lastName);
                    currentCustomer.setEmail(email);
                    currentCustomer.setBirthdate(birthdate);
                    currentCustomer.setLong_customer(longitude);
                    currentCustomer.setLat_customer(latitude);
                    currentCustomer.setCity_code(cityCode);

                    updateCustomerUseCase.execute(currentCustomer);
                    showMessage("Customer updated successfully.");
                },
                () -> showMessage("Customer not found.", "Error")
        );
    }

    private void deleteCustomer() {
        String id = getInput("Enter Customer ID:");
        if (id == null || id.isEmpty()) {
            showMessage("Customer ID cannot be empty.", "Error");
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            deleteCustomerUseCase.execute(id);
            showMessage("Customer deleted successfully.");
        }
    }

    private String getInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return (input != null && !input.trim().isEmpty()) ? input.trim() : null;
    }

    private String getInput(String message, String title, ImageIcon icon) {
        Object input = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, icon, null, null);
        return (input != null) ? input.toString().trim() : null;
    }

    private float getInputAsFloat(String message) {
        while (true) {
            String input = getInput(message);
            if (input == null) {
                return -1f; // Return a default invalid value if the input is null
            }
            try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                showMessage("Invalid input. Please enter a valid number.", "Error");
            }
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void showCustomerDetails(Customer customer) {
        String details = String.format("""
                Customer found:
                ID: %s
                Name: %s
                Last Name: %s
                Email: %s
                BirthDate: %s
                Longitude: %.2f
                Latitude: %.2f
                City Code: %s
                """, customer.getId(), customer.getName(), customer.getLast_name(), customer.getEmail(),
                customer.getBirthdate(), customer.getLong_customer(), customer.getLat_customer(), customer.getCity_code());
        showMessage(details, "Customer Details");
    }
}

