package com.pharmacy_ofc.customer.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.customer.domain.entity.Customer;
import com.pharmacy_ofc.customer.domain.service.CustomerService;

public class CustomerRepository implements CustomerService{
    private Connection connection;

    public CustomerRepository() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("configdb.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (id_customer, name_customer, last_name_customer, email_customer, birthdate_customer, long_customer, lat_customer, code_city_cm) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getId());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getLast_name());
            ps.setString(4, customer.getEmail());
            ps.setString(5, customer.getBirthdate());
            ps.setFloat(6, customer.getLong_customer());
            ps.setFloat(7, customer.getLat_customer());
            ps.setString(8, customer.getCity_code());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findCustomer(String id) {
        String sql = "SELECT id_customer, name_customer, last_name_customer, email_customer, birthdate_customer, long_customer, lat_customer, code_city_cm FROM customer WHERE id_customer = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Customer customer = new Customer(rs.getString("id_customer"), rs.getString("name_customer"), rs.getString("last_name_customer"), rs.getString("email_customer"), rs.getString("birthdate_customer"), rs.getFloat("long_customer"), rs.getFloat("lat_customer"), rs.getString("code_city_cm"));
                    return Optional.of(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET name_customer = ?, last_name_customer = ?, email_customer = ?, birthdate_customer = ?, long_customer = ?, lat_customer = ?, code_city_cm = ? FROM customer WHERE id_customer = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getLast_name());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getBirthdate());
            ps.setFloat(5, customer.getLong_customer());
            ps.setFloat(6, customer.getLat_customer());
            ps.setString(7, customer.getCity_code());
            ps.setString(8, customer.getId());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteCustomer(String id) {
        String sql = "DELETE FROM customer WHERE id_customer = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
}
