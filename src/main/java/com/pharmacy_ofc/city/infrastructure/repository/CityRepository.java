package com.pharmacy_ofc.city.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.city.domain.entity.City;
import com.pharmacy_ofc.city.domain.service.CityService;

public class CityRepository implements CityService{
    private Connection connection;

    public CityRepository() {
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
    public void createCity(City city) {
        String sql = "INSERT INTO city (code_city, name_city, code_reg) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, city.getCode());
            ps.setString(2, city.getName());
            ps.setString(3, city.getReg_code());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<City> findCity(String code) {
               String sql = "SELECT code_city, name_city, code_reg FROM city WHERE code_city = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    City city = new City(rs.getString("code_city"), rs.getString("name_city"), rs.getString("code_reg"));
                    return Optional.of(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCity(City city) {
        String sql = "UPDATE city SET name_city = ?, code_reg = ? WHERE code_city = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, city.getName());
            ps.setString(2, city.getReg_code());
            ps.setString(3, city.getCode());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCity(String code) {
        String sql = "DELETE FROM city WHERE code_city = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
}
