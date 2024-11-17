/*
 * @(#) KhachHangThongKeDAO.java 1.0 Nov 15, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jfree.data.category.DefaultCategoryDataset;

import connectDB.ConnectDB;
import entity.KhachHangThongKe;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 15, 2024
 * @version: 1.0
 */

public class KhachHangThongKeDAO {
	
	public ArrayList<KhachHangThongKe> getThongKeKhachHangTheoNam(int year) {
		ArrayList<KhachHangThongKe> dsKhachHang = new ArrayList<KhachHangThongKe>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "SELECT TOP 5 kh.MaKhachHang, kh.TenKhachHang, SUM(hd.TongTien) AS tongTienChi " +
	            "FROM KhachHang kh " +
	            "JOIN HoaDon hd ON kh.maKhachHang = hd.MaKhachHang " +
	            "WHERE YEAR(hd.NgayDat) = ? " + 
	            "GROUP BY kh.MaKhachHang, kh.TenKhachHang " +
	            "ORDER BY tongTienChi DESC"
	        );

	        preparedStatement.setInt(1, year);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	String maKhachHang = rs.getString("MaKhachHang");
	            String tenKhachHang = rs.getString("TenKhachHang");
	            double tongTienChi = rs.getDouble("tongTienChi");
	            dsKhachHang.add(new KhachHangThongKe(maKhachHang, tenKhachHang, tongTienChi));
	        }
	        return dsKhachHang;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public ArrayList<KhachHangThongKe> getThongKeKhachHangTheoThang(int month, int year) {
		ArrayList<KhachHangThongKe> dsKhachHang = new ArrayList<KhachHangThongKe>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "SELECT TOP 5 kh.MaKhachHang, kh.TenKhachHang, SUM(hd.TongTien) AS tongTienChi " +
	            "FROM KhachHang kh " +
	            "JOIN HoaDon hd ON kh.maKhachHang = hd.MaKhachHang " +
	            "WHERE MONTH(hd.NgayDat) = ? AND YEAR(hd.NgayDat) = ? " + 
	            "GROUP BY kh.MaKhachHang, kh.TenKhachHang " +
	            "ORDER BY tongTienChi DESC"
	        );

	        preparedStatement.setInt(1, month);
	        preparedStatement.setInt(2, year);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	String maKhachHang = rs.getString("MaKhachHang");
	            String tenKhachHang = rs.getString("TenKhachHang");
	            double tongTienChi = rs.getDouble("tongTienChi");
	            dsKhachHang.add(new KhachHangThongKe(maKhachHang, tenKhachHang, tongTienChi));
	        }
	        return dsKhachHang;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public DefaultCategoryDataset getTop5KhachHangTheoChiTieu(int month, int year) {
	    DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "SELECT TOP 5 kh.TenKhachHang, SUM(hd.TongTien) AS tongTienChi " +
	            "FROM KhachHang kh " +
	            "JOIN HoaDon hd ON kh.maKhachHang = hd.MaKhachHang " +
	            "WHERE MONTH(hd.NgayDat) = ? AND YEAR(hd.NgayDat) = ? " + 
	            "GROUP BY kh.TenKhachHang " +
	            "ORDER BY tongTienChi DESC"
	        );

	        preparedStatement.setInt(1, month);
	        preparedStatement.setInt(2, year);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String tenKhachHang = rs.getString("TenKhachHang");
	            double tongTienChi = rs.getDouble("tongTienChi");

	            dsKhachHang.addValue(tongTienChi, "Tổng tiền đã chi", tenKhachHang);
	        }
	        return dsKhachHang;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public DefaultCategoryDataset getTop5KhachHangTheoChiTieu(int year) {
	    DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "SELECT TOP 5 kh.TenKhachHang, SUM(hd.TongTien) AS tongTienChi " +
	            "FROM KhachHang kh " +
	            "JOIN HoaDon hd ON kh.maKhachHang = hd.MaKhachHang " +
	            "WHERE YEAR(hd.NgayDat) = ? " + 
	            "GROUP BY kh.TenKhachHang " +
	            "ORDER BY tongTienChi DESC"
	        );

	        preparedStatement.setInt(1, year);

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String tenKhachHang = rs.getString("TenKhachHang");
	            double tongTienChi = rs.getDouble("tongTienChi");

	            dsKhachHang.addValue(tongTienChi, "Tổng tiền đã chi", tenKhachHang);
	        }
	        return dsKhachHang;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoThang() {
		DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	    	PreparedStatement preparedStatement = con.prepareStatement(
	    			"SELECT MONTH(hd.NgayDat) AS month1, YEAR(hd.NgayDat) AS year1, COUNT(DISTINCT kh.MaKhachHang) AS SoKhachHang\r\n"
	    			+ "FROM HoaDon hd\r\n"
	    			+ "JOIN KhachHang kh ON kh.MaKhachHang = hd.MaKhachHang\r\n"
	    			+ "WHERE hd.NgayDat >= DATEADD(YEAR, -1, GETDATE()) and hd.NgayDat < CURRENT_TIMESTAMP \r\n"
	    			+ "GROUP BY MONTH(hd.NgayDat), YEAR(hd.NgayDat)\r\n"
	    			+ "ORDER BY year1 DESC, month1;\r\n");
			ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            int thang = rs.getInt("month1");
	            int nam = rs.getInt("year1");
	            int soKhachHang = rs.getInt("SoKhachHang");
	            
	            dsKhachHang.addValue(soKhachHang, "Số lượng khách hàng", thang+"/"+nam);
	        }
	        return dsKhachHang;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoQuy() {
	    DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "SELECT DATEPART(QUARTER, hd.NgayDat) AS quarter, YEAR(hd.NgayDat) AS year1, COUNT(DISTINCT kh.MaKhachHang) AS SoKhachHang " +
	            "FROM HoaDon hd " +
	            "JOIN KhachHang kh ON kh.MaKhachHang = hd.MaKhachHang " +
	            "WHERE hd.NgayDat >= DATEADD(YEAR, -2, GETDATE()) AND hd.NgayDat < CURRENT_TIMESTAMP " +
	            "GROUP BY DATEPART(QUARTER, hd.NgayDat), YEAR(hd.NgayDat) " +
	            "ORDER BY year1 DESC, quarter;"
	        );

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            int quy = rs.getInt("quarter");
	            int nam = rs.getInt("year1");
	            int soKhachHang = rs.getInt("SoKhachHang");
	            dsKhachHang.addValue(soKhachHang, "Số lượng khách hàng", "Q" + quy + "/" + nam);
	        }
	        return dsKhachHang;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public DefaultCategoryDataset getSoLuongKhachHangPhanBietTheoNam() {
	    DefaultCategoryDataset dsKhachHang = new DefaultCategoryDataset();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "SELECT YEAR(hd.NgayDat) AS year1, COUNT(DISTINCT kh.MaKhachHang) AS SoKhachHang " +
	            "FROM HoaDon hd " +
	            "JOIN KhachHang kh ON kh.MaKhachHang = hd.MaKhachHang " +
	            "WHERE hd.NgayDat >= DATEADD(YEAR, -8, GETDATE()) AND hd.NgayDat < CURRENT_TIMESTAMP " +
	            "GROUP BY YEAR(hd.NgayDat) " +
	            "ORDER BY year1 DESC;"
	        );

	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            int nam = rs.getInt("year1");
	            int soKhachHang = rs.getInt("SoKhachHang");

	            dsKhachHang.addValue(soKhachHang, "Số lượng khách hàng", String.valueOf(nam));
	        }
	        return dsKhachHang;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
