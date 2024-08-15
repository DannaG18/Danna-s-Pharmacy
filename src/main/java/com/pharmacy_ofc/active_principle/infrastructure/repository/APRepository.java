package com.pharmacy_ofc.active_principle.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.active_principle.domain.entity.ActivePrinciple;
import com.pharmacy_ofc.active_principle.domain.service.APService;

public class APRepository implements APService {
    private Connection connection;
    
    public APRepository() {
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
    public void createAP(ActivePrinciple actP) {
        String sql = "INSERT INTO active_principle (id_act_p, name_act_p) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, actP.getId());
            ps.setString(2, actP.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<ActivePrinciple> findAPById(int id) {
        String sql = "SELECT id_act_p, name_act_p FROM active_principle WHERE id_act_p = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    ActivePrinciple actP = new ActivePrinciple(rs.getInt("id_act_p"), rs.getString("name_act_p"));
                    return Optional.of(actP);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateAP(ActivePrinciple actP) {
        String sql = "UPDATE active_principle SET name_act_p = ? WHERE id_act_p = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, actP.getName());
            ps.setInt(2, actP.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAP(int id) {
        String sql = "DELETE FROM active_principle WHERE id_act_p = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
