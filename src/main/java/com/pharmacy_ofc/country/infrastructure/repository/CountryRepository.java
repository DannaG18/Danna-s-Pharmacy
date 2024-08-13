package com.pharmacy_ofc.country.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.country.domain.entity.Country;
import com.pharmacy_ofc.country.domain.service.CountryService;

public class CountryRepository implements CountryService{
    private Connection connection;

    public CountryRepository() {
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
    public void createCountry(Country country) {
        String sql = "INSERT INTO country (country_code, country_name) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, country.getCode());
            ps.setString(2, country.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Country> findCountryById(String code) {
        String sql = "SELECT country_code, country_name FROM country WHERE country_code = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Country country = new Country(rs.getString("country_code"), rs.getString("country_name"));
                    return Optional.of(country);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCountry(Country country) {
        String sql = "UPDATE country SET country_name = ? WHERE country_code = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, country.getName());
            ps.setString(2, country.getCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCountry(String code) {
        String sql = "DELETE FROM country WHERE country_code = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
