/*
 * @(#) PhimThongKeDAO.java 1.0 Nov 8, 2024
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
import entity.PhimThongKe;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 8, 2024
 * @version: 1.0
 */

public class PhimThongKeDAO {
	
	public ArrayList<PhimThongKe> getThongKePhimTheoThang(int month, int year) {
	    ArrayList<PhimThongKe> dsPhimThongKe = new ArrayList<PhimThongKe>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"SELECT TOP 5 p.MaPhim, p.TenPhim, COUNT(v.MaVe) AS SoLuotXem, SUM(lc.GiaMotGhe) AS TongDoanhThu\r\n"
	        		+ "FROM Phim p\r\n"
	        		+ "JOIN LichChieu lc ON p.MaPhim = lc.MaPhim\r\n"
	        		+ "JOIN Ve v ON lc.MaLichChieu = v.MaLichChieu\r\n"
	        		+ "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon "
	        		+ "WHERE MONTH(hd.NgayDat) = ? AND YEAR(hd.NgayDat) = ? \r\n"
	        		+ "GROUP BY p.MaPhim, p.TenPhim\r\n"
	        		+ "ORDER BY TongDoanhThu DESC, SoLuotXem DESC;");

	        
	        preparedStatement.setInt(1, month);
	        preparedStatement.setInt(2, year);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String maPhim = rs.getString("MaPhim");
	            String tenPhim = rs.getString("TenPhim");
	            int soLuotXem = rs.getInt("SoLuotXem");
	            double tongTienVe = rs.getDouble("TongDoanhThu");
	            
	            PhimThongKe phimThongKe = new PhimThongKe(maPhim, tenPhim, soLuotXem, tongTienVe);
	            dsPhimThongKe.add(phimThongKe);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsPhimThongKe;
	}
	
	public ArrayList<PhimThongKe> getThongKePhimTheoNam(int year) {
	    ArrayList<PhimThongKe> dsPhimThongKe = new ArrayList<PhimThongKe>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"SELECT TOP 5 p.MaPhim, p.TenPhim, COUNT(v.MaVe) AS SoLuotXem, SUM(lc.GiaMotGhe) AS TongDoanhThu\r\n"
	        		+ "FROM Phim p\r\n"
	        		+ "JOIN LichChieu lc ON p.MaPhim = lc.MaPhim\r\n"
	        		+ "JOIN Ve v ON lc.MaLichChieu = v.MaLichChieu\r\n"
	        		+ "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon "
	        		+ "WHERE YEAR(hd.NgayDat) = ? \r\n"
	        		+ "GROUP BY p.MaPhim, p.TenPhim\r\n"
	        		+ "ORDER BY TongDoanhThu DESC, SoLuotXem DESC;");

	        preparedStatement.setInt(1, year);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String maPhim = rs.getString("MaPhim");
	            String tenPhim = rs.getString("TenPhim");
	            int soLuotXem = rs.getInt("SoLuotXem");
	            double tongTienVe = rs.getDouble("TongDoanhThu");
	            
	            PhimThongKe phimThongKe = new PhimThongKe(maPhim, tenPhim, soLuotXem, tongTienVe);
	            dsPhimThongKe.add(phimThongKe);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsPhimThongKe;
	}
	
	public DefaultCategoryDataset getThongKePhimTheoNamBD(int year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"SELECT TOP 5 p.MaPhim, p.TenPhim, COUNT(v.MaVe) AS SoLuotXem, SUM(lc.GiaMotGhe) AS TongDoanhThu\r\n"
	        		+ "FROM Phim p\r\n"
	        		+ "JOIN LichChieu lc ON p.MaPhim = lc.MaPhim\r\n"
	        		+ "JOIN Ve v ON lc.MaLichChieu = v.MaLichChieu\r\n"
	        		+ "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon "
	        		+ "WHERE YEAR(hd.NgayDat) = ? \r\n"
	        		+ "GROUP BY p.MaPhim, p.TenPhim\r\n"
	        		+ "ORDER BY TongDoanhThu DESC, SoLuotXem DESC;");

	        preparedStatement.setInt(1, year);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String tenPhim = rs.getString("TenPhim");
	            double tongTienVe = rs.getDouble("TongDoanhThu");
	            
	            dataset.addValue(tongTienVe, "Tổng tiền vé", tenPhim);
	        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
	
	public DefaultCategoryDataset getThongKePhimTheoThangBD(int year, int month) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"SELECT TOP 5 p.MaPhim, p.TenPhim, COUNT(v.MaVe) AS SoLuotXem, SUM(lc.GiaMotGhe) AS TongDoanhThu\r\n"
	        		+ "FROM Phim p\r\n"
	        		+ "JOIN LichChieu lc ON p.MaPhim = lc.MaPhim\r\n"
	        		+ "JOIN Ve v ON lc.MaLichChieu = v.MaLichChieu\r\n"
	        		+ "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon "
	        		+ "WHERE YEAR(hd.NgayDat) = ? and month(hd.NgayDat) = ? \r\n"
	        		+ "GROUP BY p.MaPhim, p.TenPhim\r\n"
	        		+ "ORDER BY TongDoanhThu DESC, SoLuotXem DESC;");

	        preparedStatement.setInt(1, year);
	        preparedStatement.setInt(2, month);
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String tenPhim = rs.getString("TenPhim");
	            double tongTienVe = rs.getDouble("TongDoanhThu");
	            
	            dataset.addValue(tongTienVe, "Tổng tiền vé", tenPhim);
	        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
}
