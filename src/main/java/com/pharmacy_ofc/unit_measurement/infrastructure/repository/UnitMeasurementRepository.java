package com.pharmacy_ofc.unit_measurement.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.unit_measurement.domain.entity.UnitMeasurement;
import com.pharmacy_ofc.unit_measurement.domain.service.UnitMeasurementService;

public class UnitMeasurementRepository implements UnitMeasurementService{
    private Connection connection;

    public UnitMeasurementRepository() {
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
    public void createUnitMeasurement(UnitMeasurement unitMeasurement) {
        String sql = "INSERT INTO unit_measurement (id_uni_m, name_uni_m) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, unitMeasurement.getId());
            ps.setString(2, unitMeasurement.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<UnitMeasurement> findUnitMeasurement(int id) {
        String sql = "SELECT id_uni_m, name_uni_m FROM unit_measurement WHERE id_uni_m = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    UnitMeasurement unitMeasurement = new UnitMeasurement(rs.getInt("id_uni_m"), rs.getString("name_uni_m"));
                    return Optional.of(unitMeasurement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateUnitMeasurement(UnitMeasurement unitMesurement) {
        String sql = "UPDATE unit_measurement SET name_uni_m = ? WHERE id_uni_m = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, unitMesurement.getName());
            ps.setInt(2, unitMesurement.getId());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteUnitMeasurement(int id) {
        String sql = "DELETE FROM unit_measurement WHERE id_uni_m = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
