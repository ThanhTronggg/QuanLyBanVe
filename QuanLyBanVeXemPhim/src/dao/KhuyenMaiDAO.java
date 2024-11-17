/*
 * @(#) KhuyenMaiDAO.java 1.0 Nov 1, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhuyenMai;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 1, 2024
 * @version: 1.0
 */

public class KhuyenMaiDAO {
	ArrayList<KhuyenMai> dsKhuyenMai;
	
	public KhuyenMai getKhuyenMaiConHanTheoTongTienToiThieu(double tongTien) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    KhuyenMai khuyenMai = null;
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"SELECT TOP 1 * " +
	                "FROM KhuyenMai " +
	                "WHERE CURRENT_TIMESTAMP BETWEEN NgayBatDau AND NgayKetThuc " +
	                "AND TongTienToiThieu <= ? " +
	                "ORDER BY PhanTramKhuyenMai DESC");

	        preparedStatement.setDouble(1, tongTien);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            String ma = rs.getString("MaKhuyenMai");
	            String ten = rs.getString("TenKhuyenMai");
	            LocalDate ngayBD = rs.getDate("NgayBatDau").toLocalDate();
	            LocalDate ngayKT = rs.getDate("NgayKetThuc").toLocalDate();
	            double tongTienToiThieu = rs.getDouble("TongTienToiThieu");
	            double phanTram = rs.getDouble("PhanTramKhuyenMai");

	            khuyenMai = new KhuyenMai(ma, ten, ngayBD, ngayKT, tongTienToiThieu, phanTram);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return khuyenMai;
	}

	
	public KhuyenMai tim1KhuyenMaiTheoMa(String maKhuyenMai) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "select *\r\n" +
	            "from KhuyenMai\r\n" +
	            "where MaKhuyenMai = ?");

	        preparedStatement.setString(1, maKhuyenMai);
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            String maKhuyenMai1 = rs.getString("MaKhuyenMai");
	            String tenKhuyenMai = rs.getString("TenKhuyenMai");
	            LocalDate ngayBatDau = rs.getDate("NgayBatDau").toLocalDate();
	            LocalDate ngayKetThuc = rs.getDate("NgayKetThuc").toLocalDate();
	            double tongTienToiThieu = rs.getDouble("TongTienToiThieu");
	            double phanTramKhuyenMai = rs.getDouble("PhanTramKhuyenMai");

	            KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai1, tenKhuyenMai, ngayBatDau, ngayKetThuc, tongTienToiThieu, phanTramKhuyenMai);
	            return khuyenMai;
	        }
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//Lấy 5 khuyến mãi sắp hết hạn
	public ArrayList<KhuyenMai> getNamKhuyenMaiSapHetHan(String maKM) {
		dsKhuyenMai = new ArrayList<KhuyenMai>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"select top 5 * from [dbo].[KhuyenMai]\r\n"
							+ "where CURRENT_TIMESTAMP < [dbo].[KhuyenMai].[NgayKetThuc] and MaKhuyenMai LIKE ?\r\n"
							+ "order by DATEDIFF(second, CURRENT_TIMESTAMP,[dbo].[KhuyenMai].[NgayKetThuc])");
			preparedStatement.setString(1, "%" + maKM + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				LocalDate ngayBD = rs.getDate(3).toLocalDate();
				LocalDate ngayKT = rs.getDate(4).toLocalDate();
				double tongTienToiThieu = rs.getDouble(5);
				double phanTram = rs.getDouble(6);
				
				KhuyenMai km1 = new KhuyenMai(ma, ten, ngayBD, ngayKT, tongTienToiThieu, phanTram);
				
				dsKhuyenMai.add(km1);
			}
			return dsKhuyenMai;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//Lấy tất cả khuyến mãi
	public ArrayList<KhuyenMai> getTatCaKhuyenMai(String maKM) {
		dsKhuyenMai = new ArrayList<KhuyenMai>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"select * from [dbo].[KhuyenMai]\r\n"
							+ "where MaKhuyenMai LIKE ?\r\n");
			preparedStatement.setString(1, "%" + maKM + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				LocalDate ngayBD = rs.getDate(3).toLocalDate();
				LocalDate ngayKT = rs.getDate(4).toLocalDate();
				double tongTienToiThieu = rs.getDouble(5);
				double phanTram = rs.getDouble(6);
				
				KhuyenMai km1 = new KhuyenMai(ma, ten, ngayBD, ngayKT, tongTienToiThieu, phanTram);
				
				dsKhuyenMai.add(km1);
			}
			return dsKhuyenMai;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//Lấy những khuyến mãi còn hạn
	public ArrayList<KhuyenMai> getKhuyenMaiConHan(String maKM) {
		dsKhuyenMai = new ArrayList<KhuyenMai>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					 "select * from [dbo].[KhuyenMai]\r\n"
					+ "where CURRENT_TIMESTAMP BETWEEN NgayBatDau AND NgayKetThuc and MaKhuyenMai like ? ");
			preparedStatement.setString(1, "%" + maKM + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				LocalDate ngayBD = rs.getDate(3).toLocalDate();
				LocalDate ngayKT = rs.getDate(4).toLocalDate();
				double tongTienToiThieu = rs.getDouble(5);
				double phanTram = rs.getDouble(6);
				
				KhuyenMai km1 = new KhuyenMai(ma, ten, ngayBD, ngayKT, tongTienToiThieu, phanTram);
				
				dsKhuyenMai.add(km1);
			}
			return dsKhuyenMai;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//Lấy những khuyến mãi hết hạn
	public ArrayList<KhuyenMai> getKhuyenMaiHetHan(String maKM) {
		dsKhuyenMai = new ArrayList<KhuyenMai>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"select * from [dbo].[KhuyenMai]\r\n"
					+ "where CURRENT_TIMESTAMP > [dbo].[KhuyenMai].[NgayKetThuc] and MaKhuyenMai like ? ");
			preparedStatement.setString(1, "%" + maKM + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				LocalDate ngayBD = rs.getDate(3).toLocalDate();
				LocalDate ngayKT = rs.getDate(4).toLocalDate();
				double tongTienToiThieu = rs.getDouble(5);
				double phanTram = rs.getDouble(6);
				
				KhuyenMai km1 = new KhuyenMai(ma, ten, ngayBD, ngayKT, tongTienToiThieu, phanTram);
				
				dsKhuyenMai.add(km1);
			}
			return dsKhuyenMai;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	public boolean themKhuyenMai(KhuyenMai km1) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"INSERT INTO KhuyenMai ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], TongTienToiThieu, [PhanTramKhuyenMai])\r\n"
					+ "VALUES\r\n"
					+ "(?, ?, ?, ?, ?)");
			preparedStatement.setString(1, km1.getTenKhuyenMai());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			preparedStatement.setString(2, km1.getNgayBatDau().format(formatter));
			preparedStatement.setString(3, km1.getNgayKetThuc().format(formatter));
			preparedStatement.setDouble(4, km1.getTongTienToiThieu());
			preparedStatement.setDouble(5, km1.getPhanTramKhuyenMai());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean xoaKhuyenMai(String maKhuyenMai) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"DELETE FROM KhuyenMai WHERE MaKhuyenMai = ?");
	        preparedStatement.setString(1, maKhuyenMai);
	        int soDongDaXoa = preparedStatement.executeUpdate();
	        return soDongDaXoa > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean suaKhuyenMai(KhuyenMai km1) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "UPDATE KhuyenMai SET TenKhuyenMai = ?, NgayBatDau = ?, NgayKetThuc = ?, TongTienToiThieu = ?, PhanTramKhuyenMai = ? WHERE MaKhuyenMai = ?");
	        preparedStatement.setString(1, km1.getTenKhuyenMai());
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        preparedStatement.setString(2, km1.getNgayBatDau().format(formatter));
	        preparedStatement.setString(3, km1.getNgayKetThuc().format(formatter));
	        preparedStatement.setDouble(4, km1.getTongTienToiThieu());
	        preparedStatement.setDouble(5, km1.getPhanTramKhuyenMai());
	        preparedStatement.setString(6, km1.getMaKhuyenMai());
	        int soDongDaCapNhat = preparedStatement.executeUpdate();
	        System.out.println(soDongDaCapNhat);
	        return soDongDaCapNhat > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public KhuyenMai timKhuyenMai(String maKM) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		KhuyenMai km1 = null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"select * from [dbo].[KhuyenMai]\r\n"
					+ "where MaKhuyenMai = ?");
			preparedStatement.setString(1, maKM);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				LocalDate ngayBD = rs.getDate(3).toLocalDate();
				LocalDate ngayKT = rs.getDate(4).toLocalDate();
				double tongTienToiThieu = rs.getDouble(5);
				double phanTram = rs.getDouble(6);
				
				km1 = new KhuyenMai(ma, ten, ngayBD, ngayKT, tongTienToiThieu, phanTram);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return km1;
	}
	
	public KhuyenMai timKhuyenMaiTheoMa(String maKM) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		KhuyenMai km1 = null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"select * from [dbo].[KhuyenMai]\r\n"
					+ "where MaKhuyenMai = %?%");
			preparedStatement.setString(1, maKM);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString(1);
				String ten = rs.getString(2);
				LocalDate ngayBD = rs.getDate(3).toLocalDate();
				LocalDate ngayKT = rs.getDate(4).toLocalDate();
				double tongTienToiThieu = rs.getDouble(5);
				double phanTram = rs.getDouble(6);
				
				km1 = new KhuyenMai(ma, ten, ngayBD, ngayKT, tongTienToiThieu, phanTram);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return km1;
	}

	
	
	
	//test connect
//	public static void main(String[] args) {
//		KhuyenMaiDAO dao = new KhuyenMaiDAO();
//		ArrayList<KhuyenMai> listKM = dao.getKhuyenMaiHetHan();
//		for (KhuyenMai km1: listKM) {
//			System.out.println(km1);
//		}
//	}
}
