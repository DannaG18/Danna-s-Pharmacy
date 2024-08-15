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
        String options = "1. Add Customer\n2. Search Customer\n3. Update Customer\n4. Delete Customer\n5. Return to main menu.";
        int op = 0;
        ImageIcon customIcon = new ImageIcon("src/main/resources/img/logou.png");
        
        do {
            Object selectedValue = JOptionPane.showInputDialog(
                null, 
                options, 
                "Customer Menu", 
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
                    addCustomer();
                    break;
                case 2:
                    findCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    break;
                default:
                    break;
            } 
        } while (op != 5 && op != -1);
    }

    public void addCustomer() {
        String id = JOptionPane.showInputDialog(null, "Customer id: ");
        if (id == null) {
            return;
        }
        String name = JOptionPane.showInputDialog(null, "Customer name: ");
        if (name == null) {
            return;
        }
        String last_name = JOptionPane.showInputDialog(null, "Customer Las Name: ");
        if (last_name == null) {
            return;
        }
        String email = JOptionPane.showInputDialog(null, "Customer Email: ");
        if (email == null) {
            return;
        }
        String birthdate = JOptionPane.showInputDialog(null, "Customer BirthDate: ");
        if (birthdate == null) {
            return;
        }
        float long_customer = getInputAsFloat("Enter Customer Longitude:");

        float lat_customer = getInputAsFloat("Enter Customer Latitude:");

        String city_code = JOptionPane.showInputDialog(null, "Customer City: ");
        if (city_code == null) {
            return;
        }

        Customer Customer = new Customer();
        Customer.setId(id);
        Customer.setName(name);
        Customer.setLast_name(last_name);
        Customer.setEmail(email);
        Customer.setBirthdate(birthdate);
        Customer.setLong_customer(long_customer);
        Customer.setLat_customer(lat_customer);
        Customer.setCity_code(city_code);

        createCustomerUseCase.execute(Customer);
        JOptionPane.showMessageDialog(null, "Customer added successfully.");
    }

    public void findCustomer() {
        String id = JOptionPane.showInputDialog(null, "Enter Customer id: ");

        findCustomerUseCase.execute(id).ifPresentOrElse(
            CustomerFound -> {
                JOptionPane.showMessageDialog(null, 
                    "Customer found:\n" + 
                    "Id: " + CustomerFound.getId() + "\n" +
                    "Name: " + CustomerFound.getName() + "\n" +
                    "Lat Name: " + CustomerFound.getLast_name() + "\n" +
                    "Email: " + CustomerFound.getEmail() + "\n" +
                    "BirthDate: " + CustomerFound.getBirthdate() + "\n" +
                    "Longitude: " + CustomerFound.getLong_customer() + "\n" +
                    "Latitude: " + CustomerFound.getLat_customer() + "\n" +
                    "City Code: " + CustomerFound.getCity_code(),
                    "Customer Details", JOptionPane.INFORMATION_MESSAGE);
            },
            () -> {
                JOptionPane.showMessageDialog(null, "Customer not found.", "Error", JOptionPane.ERROR_MESSAGE);
            });
    }

    public void updateCustomer() {
        String id = JOptionPane.showInputDialog(null, "Enter Customer id: ");

        findCustomerUseCase.execute(id).ifPresentOrElse(
            currentCustomer -> {

                String name = JOptionPane.showInputDialog(null, "Customer name: ");
                if (name == null) {
                    return;
                }
                String last_name = JOptionPane.showInputDialog(null, "Customer Last Name: ");
                if (last_name == null) {
                    return;
                }
                String email = JOptionPane.showInputDialog(null, "Customer Email: ");
                if (email == null) {
                    return;
                }
                String birthdate = JOptionPane.showInputDialog(null, "Customer BirthDate: ");
                if (birthdate == null) {
                    return;
                }
                float long_customer = getInputAsFloat("Enter Customer Longitude:");
        
                float lat_customer = getInputAsFloat("Enter Customer Latitude:");
        
                String city_code = JOptionPane.showInputDialog(null, "Customer City: ");
                if (city_code == null) {
                    return;
                }
        
                Customer Customer = new Customer();
                Customer.setId(id);
                Customer.setName(name);
                Customer.setLast_name(last_name);
                Customer.setEmail(email);
                Customer.setBirthdate(birthdate);
                Customer.setLong_customer(long_customer);
                Customer.setLat_customer(lat_customer);
                Customer.setCity_code(city_code);
        
                updateCustomerUseCase.execute(Customer);

                JOptionPane.showMessageDialog(null, "Customer updated successfully.");
            }, 
            () -> {
                JOptionPane.showMessageDialog(null, "Customer not found.", "Error", JOptionPane.ERROR_MESSAGE);
            });
    }

    public void deleteCustomer() {
        String id = JOptionPane.showInputDialog(null, "Enter Customer id: ");
        if (id == null) {
            return;
        }
        deleteCustomerUseCase.execute(id);
        JOptionPane.showMessageDialog(null, "Customer deleted successfully.");
    }

    private String getInput(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return (input != null && !input.trim().isEmpty()) ? input : null;
    }

    private float getInputAsFloat(String message) {
        try {
            return Float.parseFloat(getInput(message));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1f; // Returning a default invalid value
        }
    }
}
