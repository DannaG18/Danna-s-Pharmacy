package com.pharmacy_ofc.laboratory.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.laboratory.domain.entity.Laboratory;
import com.pharmacy_ofc.laboratory.domain.service.LaboratoryService;

public class LaboratoryRepository implements LaboratoryService{
    private Connection connection;

    public LaboratoryRepository() {
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
    public void createLaboratory(Laboratory laboratory) {
        String sql = "INSERT INTO laboratory (id_lab, name_lab, code_city) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, laboratory.getId());
            ps.setString(2, laboratory.getName());
            ps.setString(3, laboratory.getCity_code());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Laboratory> findLaboratory(int id) {
        String sql = "SELECT id_lab, name_lab, code_city FROM laboratory WHERE id_lab = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Laboratory laboratory = new Laboratory(rs.getInt("id_lab"), rs.getString("name_lab"), rs.getString("code_city"));
                    return Optional.of(laboratory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateLaboratory(Laboratory laboratory) {
        String sql = "UPDATE laboratory SET name_lab = ?, code_city = ? WHERE id_lab = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, laboratory.getName());
            ps.setString(2, laboratory.getCity_code());
            ps.setInt(3, laboratory.getId());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteLaboratory(int id) {
        String sql = "DELETE FROM laboratory WHERE id_lab = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
}
