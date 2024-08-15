package com.pharmacy_ofc.region.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.region.domain.entity.Region;
import com.pharmacy_ofc.region.domain.service.RegionService;

public class RegionRepository implements RegionService {
    private Connection connection;

    public RegionRepository() {
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
    public void createRegion(Region region) {
        String sql = "INSERT INTO region (code_reg, name_reg, country_code) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, region.getCode());
            ps.setString(2, region.getName());
            ps.setString(3, region.getCountry_code());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Region> findRegionById(String code) {
        String sql = "SELECT code_reg, name_reg FROM region WHERE code_reg = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Region region = new Region(rs.getString("code_reg"), rs.getString("name_reg"), rs.getString("country_code"));
                    return Optional.of(region);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateRegion(Region region) {
        String sql = "UPDATE region SET name_reg = ?, country_code = ? WHERE code_reg = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, region.getName());
            ps.setString(2, region.getCountry_code());
            ps.setString(3, region.getCode());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteRegion(String code) {
        String sql = "DELETE FROM region WHERE code_reg = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, code);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
    
