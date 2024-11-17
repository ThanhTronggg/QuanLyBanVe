/*
 * @(#) TaiKhoanDAO.java 1.0 Nov 11, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.TaiKhoan;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 11, 2024
 * @version: 1.0
 */

public class TaiKhoanDAO {

	public NhanVien getNhanVienTheoTaiKhoan(String username, boolean authentication) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			// Kiểm tra tài khoản trước khi thực hiện truy vấn
			if (getTaiKhoanTheoUsername(username) != null && authentication) {
				String sql = "SELECT tk.ID, nv.MaNhanVien, nv.HoTen, nv.VaiTro, nv.SoDienThoai, nv.Email, nv.GioiTinh, nv.NgaySinh, nv.NgayBatDau, nv.Luong, nv.TrangThai "
						+ "FROM NhanVien nv "
						+ "JOIN TaiKhoan tk ON nv.MaTaiKhoan = tk.ID "
						+ "WHERE tk.Username = ?";

				PreparedStatement preparedStatement = con.prepareStatement(sql);
				preparedStatement.setString(1, username);

				ResultSet rs = preparedStatement.executeQuery();

				// Kiểm tra nếu có dữ liệu trả về
				if (rs.next()) {
					String id = rs.getString("ID");
					String maNhanVien = rs.getString("MaNhanVien");
					String tenNhanVien = rs.getString("HoTen");
					String chucVu = rs.getString("VaiTro");
					String soDienThoai = rs.getString("SoDienThoai");
					String email = rs.getString("Email");
					boolean gioiTinh = rs.getBoolean("GioiTinh");

					// Kiểm tra trường có giá trị null hay không trước khi chuyển đổi
					LocalDate ngaySinh = rs.getDate("NgaySinh") != null ? rs.getDate("NgaySinh").toLocalDate() : null;
					LocalDate ngayVaoLam = rs.getDate("NgayBatDau") != null ? rs.getDate("NgayBatDau").toLocalDate()
							: null;
					double luong = rs.getDouble("Luong");
					String trangThai = rs.getString("TrangThai");

					// Khởi tạo đối tượng NhanVien
					return new NhanVien(maNhanVien, tenNhanVien, gioiTinh, ngaySinh, email, soDienThoai, chucVu,
							ngayVaoLam, luong, getTaiKhoanTheoID(id), trangThai);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public TaiKhoan getTaiKhoanTheoID(String id) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM TaiKhoan WHERE ID = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String taiKhoanID = rs.getString("ID");
				String tenDangNhap = rs.getString("Username");
				String matKhau = rs.getString("MatKhau");
				return new TaiKhoan(taiKhoanID, tenDangNhap, matKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public TaiKhoan getTaiKhoanTheoUsername(String username) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM TaiKhoan WHERE Username = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String id = rs.getString("ID");
				String tenDangNhap = rs.getString("Username");
				String matKhau = rs.getString("MatKhau");
				return new TaiKhoan(id, tenDangNhap, matKhau);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public TaiKhoan timTaiKhoanTheoID(String id) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"select *\r\n" +
							"from TaiKhoan\r\n" +
							"where ID = ?");

			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String id1 = rs.getString("ID");
				String taiKhoan = rs.getString("Username");
				String matKhau = rs.getString("MatKhau");

				TaiKhoan tk = new TaiKhoan(id1, taiKhoan, matKhau);
				return tk;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addTaiKhoan(TaiKhoan taiKhoan) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			// Băm mật khẩu trước khi lưu vào cơ sở dữ liệu
			String hashedPassword = BCrypt.hashpw(taiKhoan.getMatKhau(), BCrypt.gensalt());

			// Chuẩn bị câu lệnh SQL để chèn dữ liệu vào bảng TaiKhoan
			String sql = "INSERT INTO TaiKhoan (Username, MatKhau) VALUES (?, ?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// Thiết lập các tham số cho câu truy vấn
			preparedStatement.setString(1, taiKhoan.getTaiKhoan());
			preparedStatement.setString(2, hashedPassword);

			// Thực thi câu truy vấn
			int rowsInserted = preparedStatement.executeUpdate();
			if (rowsInserted > 0) {
				// Lấy ID tự động sinh ra cho tài khoản vừa thêm
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (generatedKeys.next()) {
					String generatedID = generatedKeys.getString(1); // Lấy ID từ cột đầu tiên trong ResultSet
					taiKhoan.setID(generatedID); // Gán ID mới cho đối tượng TaiKhoan
					return true; // Thêm thành công
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false; // Nếu thêm không thành công, trả về false
	}

	public boolean doiMatKhau(String username, String currentPassword, String newPassword) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			// Tìm tài khoản theo tên đăng nhập
			TaiKhoan taiKhoan = getTaiKhoanTheoUsername(username);
			if (taiKhoan == null) {
				System.out.println("Tài khoản không tồn tại.");
				return false;
			}

			// Kiểm tra mật khẩu hiện tại
			if (!BCrypt.checkpw(currentPassword, taiKhoan.getMatKhau())) {
				System.out.println("Mật khẩu hiện tại không đúng.");
				return false;
			}

			// Băm mật khẩu mới
			String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

			// Cập nhật mật khẩu mới trong cơ sở dữ liệu
			String sql = "UPDATE TaiKhoan SET MatKhau = ? WHERE Username = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, hashedNewPassword);
			preparedStatement.setString(2, username);

			int rowsUpdated = preparedStatement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Đổi mật khẩu thành công!");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean deleteTaiKhoan(String username) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			// Xóa tài khoản theo tên đăng nhập
			String sql = "DELETE FROM TaiKhoan WHERE Username = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, username);

			int rowsDeleted = preparedStatement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Xóa tài khoản thành công!");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
