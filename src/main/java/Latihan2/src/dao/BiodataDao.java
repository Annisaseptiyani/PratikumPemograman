package dao;
import db.MySqlConnection;
import model.Biodata;

import java.lang.reflect.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BiodataDao {


    public void insertBiodata(Biodata biodata) {
        String query = "INSERT INTO biodata (nama, nomor_hp, jenis_kelamin, warga_negara_asing) VALUES (?, ?, ?, ?)";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, biodata.getNama());
            stmt.setString(2, biodata.getNomorhp());
            stmt.setString(3, biodata.getJeniskelamin());
            stmt.setBoolean(4, biodata.getWna());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Biodata> getAllBiodata() {
        List<Biodata> biodataList = new ArrayList<>();
        String query = "SELECT * FROM biodata";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Biodata biodata = new Biodata();
                biodataList.add(biodata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return biodataList;
    }

    public void updateBiodata(Biodata biodata) {
        String query = "UPDATE biodata SET nama = ?, nomor_hp = ?, jenis_kelamin = ?, warga_negara_asing = ? WHERE id = ?";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, biodata.getNama());
            stmt.setString(2, biodata.getNomorhp());
            stmt.setString(3, biodata.getJeniskelamin());
            stmt.setBoolean(4, biodata.getWna());
            stmt.setInt(5,Integer.parseInt(stmt.executeQuery("SELECT * FROM biodata WHERE id ").getString("id")));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBiodata(int id) {
        String query = "DELETE FROM biodata WHERE id = ?";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Biodata> findAll() {
        List<Biodata> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("select * FROM biodata");) {
                // Mengambil data
                while (resultSet.next()) {
                    Biodata biodata = new Biodata();
                    biodata.setNama(resultSet.getString("nama"));
                    biodata.setJeniskelamin(resultSet.getString("jenis_kelamin"));
                    biodata.setNomorhp(resultSet.getString("nomor_hp"));
                    biodata.setWna(resultSet.getBoolean("warga_negara_asing"));
                    list.add(biodata);

                 

                   
                }
            } catch (SQLException e) {
                e.getMessage();
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return list;
    }
}
