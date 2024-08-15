package com.pharmacy_ofc.pharmacy_medicine.infrastructure.repository;

import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.pharmacy_ofc.pharmacy_medicine.domain.entity.PharmacyMed;
import com.pharmacy_ofc.pharmacy_medicine.domain.service.PharmacyMedService;

public class PharmacyMedRepository implements PharmacyMedService{
    private Connection connection;

    public PharmacyMedRepository() {
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
    public void createPharmacyMed(PharmacyMed pharmacyMed) {
        String sql = "INSERT INTO  pharmacy_medicine  (id_medicine, id_fm, price) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pharmacyMed.getId_medicine());
            ps.setInt(2, pharmacyMed.getId_pharmacy());
            ps.setFloat(3, pharmacyMed.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<PharmacyMed> findPharmacyMed(int id_medicine, int id_pharmacy) {
        String sql = "SELECT id_medicine, id_fm, price FROM  pharmacy_medicine  WHERE id_medicine = ? AND id_fm = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_medicine);
            ps.setInt(2, id_pharmacy);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    PharmacyMed pharmacyMed = new PharmacyMed(rs.getInt("id_medicine"), rs.getInt("id_fm"), rs.getFloat("price"));
                    return Optional.of(pharmacyMed);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updatePharmacyMed(PharmacyMed pharmacyMed) {
        String sql = "UPDATE  pharmacy_medicine  SET price = ? WHERE id_medicine = ? AND id_fm = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setFloat(1, pharmacyMed.getPrice());
            ps.setInt(2, pharmacyMed.getId_medicine());
            ps.setInt(3, pharmacyMed.getId_pharmacy());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deletePharmacyMed(int id_medicine, int id_pharmacy) {
        String sql = "DELETE FROM  pharmacy_medicine  WHERE id_medicine = ? AND id_fm = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_medicine);
            ps.setInt(2, id_pharmacy);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
