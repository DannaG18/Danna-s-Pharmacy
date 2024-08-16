package com.pharmacy_ofc.mode_admi.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.mode_admi.domain.entity.ModeAdmi;
import com.pharmacy_ofc.mode_admi.domain.service.ModeAdmiService;

public class ModeAdmiRepository implements ModeAdmiService{
    private Connection connection;

    public ModeAdmiRepository() {
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
    public void createModeAdmi(ModeAdmi modeAdmi) {
        String sql = "INSERT INTO mode_admi (id_mod_admi, description_mod_admi) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, modeAdmi.getId());
            ps.setString(2, modeAdmi.getDescription());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ModeAdmi> findModeAdmi(int id) {
        String sql = "SELECT id_mod_admi, description_mod_admi FROM mode_admi WHERE id_mod_admi = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    ModeAdmi modeAdmi = new ModeAdmi(rs.getInt("id_mod_admi"), rs.getString("description_mod_admi"));
                    return Optional.of(modeAdmi);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateModeAdmi(ModeAdmi modeAdmi) {
        String sql = "UPDATE mode_admi SET description_mod_admi = ? WHERE id_mod_admi = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, modeAdmi.getDescription());
            ps.setInt(2, modeAdmi.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteModeAdmi(int id) {
        String sql = "DELETE FROM mode_admi WHERE id_mod_admi = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
