package com.pharmacy_ofc.medicine.infrastructure.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import com.pharmacy_ofc.medicine.domain.entity.Medicine;
import com.pharmacy_ofc.medicine.domain.service.MedicineService;

public class MedicineRepository implements MedicineService{
    private Connection connection;

    public MedicineRepository() {
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
    public void createMedicine(Medicine medicine) {
        String sql = "INSERT medicine (id_medicine, proceedings, name_medicine, health_register,  description, description_short, name_rol, code_mode_adm, code_act_p, code_uni_m, code_lab) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, medicine.getId());
            ps.setString(2, medicine.getProceedings());
            ps.setString(3, medicine.getName_medicine());
            ps.setString(4, medicine.getHealth_register());
            ps.setString(5, medicine.getDescription());
            ps.setString(6, medicine.getDescription_short());
            ps.setString(7, medicine.getName_rol());
            ps.setInt(8, medicine.getCode_mode_adm());
            ps.setInt(9, medicine.getCode_act_p());
            ps.setInt(10, medicine.getCode_uni_m());
            ps.setInt(11, medicine.getCode_lab());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Medicine> findMedicine(int id) {
        String sql = "SELECT id_medicine, proceedings, name_medicine, health_register,  description, description_short, name_rol, code_mode_adm, code_act_p, code_uni_m, code_lab FROM medicine WHERE id_medicine = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Medicine medicine = new Medicine(rs.getInt("id_medicine"), rs.getString("proceedings"), rs.getString("name_medicine"), rs.getString("health_register"), rs.getString("description"), rs.getString("description_short"), rs.getString("name_rol"), rs.getInt("code_mode_adm"), rs.getInt("code_act_p"), rs.getInt("code_uni_m"), rs.getInt("code_lab"));
                    return Optional.of(medicine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateMedicine(Medicine medicine) {
        String sql = "UPDATE medicine SET proceedings = ?, name_medicine = ?, health_register = ?, description = ?, description_short = ?, name_rol = ?, code_mode_adm = ?, code_act_p = ?, code_uni_m = ?, code_lab = ? FROM medicine WHERE id_medicine = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, medicine.getId());
            ps.setString(2, medicine.getProceedings());
            ps.setString(3, medicine.getName_medicine());
            ps.setString(4, medicine.getHealth_register());
            ps.setString(5, medicine.getDescription());
            ps.setString(6, medicine.getDescription_short());
            ps.setString(7, medicine.getName_rol());
            ps.setInt(8, medicine.getCode_mode_adm());
            ps.setInt(9, medicine.getCode_act_p());
            ps.setInt(10, medicine.getCode_uni_m());
            ps.setInt(11, medicine.getCode_lab());
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deleteMedicine(int id) {
        String sql = "DELETE FROM medicine WHERE id_medicine = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
