package com.pharmacy_ofc.pharmacy.infrastructure.repository;

import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.pharmacy_ofc.pharmacy.domain.entity.Pharmacy;
import com.pharmacy_ofc.pharmacy.domain.service.PharmacyService;

public class PharmacyRepository implements PharmacyService{
    private Connection connection;

    public PharmacyRepository() {
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
    public void createPharmacy(Pharmacy pharmacy) {
        String sql = "INSERT INTO pharmacy (id_fm, name_fm, adress_fm, long_fm, lat_fm, logo_fm, code_city_fm) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pharmacy.getId());
            ps.setString(2, pharmacy.getName());
            ps.setString(3, pharmacy.getAddress());
            ps.setFloat(4, pharmacy.getLongitude());
            ps.setFloat(5, pharmacy.getLatitude());
            ps.setString(6, pharmacy.getLogo());
            ps.setString(7, pharmacy.getCity_code());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Pharmacy> findPharmacy(int id) {
        String sql = "SELECT id_fm, name_fm, adress_fm, long_fm, lat_fm, logo_fm, code_city_fm FROM pharmacy WHERE id_fm = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Pharmacy pharmacy = new Pharmacy(rs.getInt("id_fm"), rs.getString("name_fm"), rs.getString("adress_fm"), rs.getFloat("long_fm"), rs.getFloat("lat_fm"), rs.getString("logo_fm"),rs.getString("code_city_fm"));
                    return Optional.of(pharmacy);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updatePharmacy(Pharmacy pharmacy) {
       String sql = "UPDATE pharmacy SET name_FM = ?, adress_fm = ?, long_fm = ?, lat_fm = ?, logo_fm = ?, code_city_fm = ?";
       try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, pharmacy.getName());
        ps.setString(2, pharmacy.getAddress());
        ps.setFloat(3, pharmacy.getLongitude());
        ps.setFloat(4, pharmacy.getLatitude());
        ps.setString(5, pharmacy.getLogo());
        ps.setString(6, pharmacy.getCity_code());
        ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePharmacy(int id) {
        String sql = "DELETE FROM pharmacy WHERE id_fm = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
