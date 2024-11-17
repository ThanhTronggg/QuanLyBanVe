package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVienDAO {

    private ConnectDB connectDB;

    public NhanVienDAO() {
        connectDB = ConnectDB.getInstance();
    }

    // Get all NhanVien
    public List<NhanVien> getAllNhanVien() {
        connectDB.connect();
        String querySQL = "SELECT maNhanVien, hoTen, gioiTinh, ngaySinh, email, soDienThoai, vaiTro, ngayBatDau, luong, trangThai FROM NhanVien";
        List<NhanVien> nhanVienList = new ArrayList<>();

        try (Connection connection = connectDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String maNhanVien = resultSet.getString("maNhanVien");
                String hoTen = resultSet.getString("hoTen");
                boolean gioiTinh = resultSet.getBoolean("gioiTinh");
                LocalDate ngaySinh = resultSet.getDate("ngaySinh").toLocalDate();
                String email = resultSet.getString("email");
                String soDienThoai = resultSet.getString("soDienThoai");
                String vaiTro = resultSet.getString("vaiTro");
                LocalDate ngayBatDau = resultSet.getDate("ngayBatDau").toLocalDate();
                double luong = resultSet.getDouble("luong");
                String trangThai = resultSet.getString("trangThai");

                NhanVien nv = new NhanVien(maNhanVien, hoTen, gioiTinh, ngaySinh, email, soDienThoai, vaiTro,
                        ngayBatDau, luong, null, trangThai);
                nhanVienList.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectDB.disconnect();
        }

        return nhanVienList;
    }

    public boolean removeNhanVienByID(String maNhanVien) {
        connectDB.connect();
        String deleteSQL = "DELETE FROM NhanVien WHERE maNhanVien = ?";
        try (Connection connection = connectDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setString(1, maNhanVien);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0; // Trả về true nếu có ít nhất một dòng bị xóa

        } catch (SQLException e) {
            // Ghi log lỗi hoặc hiển thị thông báo chi tiết hơn
            System.err.println("Lỗi khi xóa nhân viên: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            connectDB.disconnect(); // Đảm bảo kết nối luôn được đóng
        }
    }

    // Add a new NhanVien
    public boolean addNewNhanVien(NhanVien newNhanVien) {
        connectDB.connect();

        String insertSQL = "INSERT INTO NhanVien (maTaiKhoan, hoTen, gioiTinh, ngaySinh, email, soDienThoai, vaiTro, ngayBatDau, luong, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS)) {

            // Thiết lập các tham số cho câu truy vấn
            preparedStatement.setString(1, newNhanVien.getTk().getID()); // Thiết lập mã tài khoản từ
                                                                         // newNhanVien.getTk().getMaTaiKhoan()
            preparedStatement.setString(2, newNhanVien.getHoTen());
            preparedStatement.setBoolean(3, newNhanVien.isGioiTinh());
            preparedStatement.setDate(4, java.sql.Date.valueOf(newNhanVien.getNgaySinh()));
            preparedStatement.setString(5, newNhanVien.getEmail());
            preparedStatement.setString(6, newNhanVien.getSoDienThoai());
            preparedStatement.setString(7, newNhanVien.getVaiTro());
            preparedStatement.setDate(8, java.sql.Date.valueOf(newNhanVien.getNgayBatDauLam()));
            preparedStatement.setDouble(9, newNhanVien.getLuong());
            preparedStatement.setString(10, "Còn làm");

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        String generatedID = rs.getString(1); // Lấy ID tự động sinh ra (nếu có)
                        newNhanVien.setMaNhanVien(generatedID); // Gán ID mới cho đối tượng NhanVien
                        System.out.println("Thêm nhân viên thành công với ID: " + generatedID);
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectDB.disconnect();
        }

        return false;
    }

    // Get NhanVien by ID
    public NhanVien getNhanVienByID(String maNhanVien) {
        connectDB.connect();

        String querySQL = "SELECT maNhanVien, hoTen, gioiTinh, ngaySinh, email, soDienThoai, vaiTro, ngayBatDau, luong, trangThai FROM NhanVien WHERE maNhanVien = ?";

        try (Connection connection = connectDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(querySQL)) {

            preparedStatement.setString(1, maNhanVien);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hoTen = resultSet.getString("hoTen");
                    boolean gioiTinh = resultSet.getBoolean("gioiTinh");
                    LocalDate ngaySinh = resultSet.getDate("ngaySinh").toLocalDate();
                    String email = resultSet.getString("email");
                    String soDienThoai = resultSet.getString("soDienThoai");
                    String vaiTro = resultSet.getString("vaiTro");
                    LocalDate ngayBatDau = resultSet.getDate("ngayBatDau").toLocalDate();
                    double luong = resultSet.getDouble("luong");
                    String trangThai = resultSet.getString("trangThai");

                    return new NhanVien(maNhanVien, hoTen, gioiTinh, ngaySinh, email, soDienThoai, vaiTro,
                            ngayBatDau, luong, null, trangThai);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectDB.disconnect();
        }

        return null;
    }

    public boolean updateNhanVien(NhanVien updatedNhanVien) {
        connectDB.connect();
        String updateSQL = "UPDATE NhanVien SET hoTen = ?, gioiTinh = ?, ngaySinh = ?, email = ?, soDienThoai = ?, vaiTro = ?, ngayBatDau = ?, luong = ?, TrangThai = ? WHERE maNhanVien = ?";

        try (Connection connection = connectDB.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {

            preparedStatement.setString(1, updatedNhanVien.getHoTen());
            preparedStatement.setBoolean(2, updatedNhanVien.isGioiTinh());
            preparedStatement.setDate(3, java.sql.Date.valueOf(updatedNhanVien.getNgaySinh()));
            preparedStatement.setString(4, updatedNhanVien.getEmail());
            preparedStatement.setString(5, updatedNhanVien.getSoDienThoai());
            preparedStatement.setString(6, updatedNhanVien.getVaiTro());
            preparedStatement.setDate(7, java.sql.Date.valueOf(updatedNhanVien.getNgayBatDauLam()));
            preparedStatement.setDouble(8, updatedNhanVien.getLuong());
            preparedStatement.setString(9, updatedNhanVien.getTrangThai()); // Cập nhật trạng thái nếu cần
            preparedStatement.setString(10, updatedNhanVien.getMaNhanVien()); // Dùng mã nhân viên để xác định bản ghi
                                                                              // cần cập nhật

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectDB.disconnect();
        }

        return false;
    }

    // Placeholder for search functionality
    public List<NhanVien> searchNhanVien(String keyword) {
        return new ArrayList<>();
    }
}
