/*
 * @(#) DoanhThuDAO.java 1.0 Nov 7, 2024
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
import org.jfree.data.general.DefaultPieDataset;

import connectDB.ConnectDB;
import entity.DoanhThu;
import entity.SanPhamThongKe;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 7, 2024
 * @version: 1.0
 */

public class DoanhThuDAO {
	
	public DefaultPieDataset<String> getThongKeDoanhThuTheoNamBD(int year) {
		DefaultPieDataset<String> dataset = new DefaultPieDataset<String>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatementSP = con.prepareStatement(
	        		"SELECT top 1 SUM(cthd.ThanhTien) AS TongTienSP\r\n"
	        		+ "	FROM SanPham sp\r\n"
	        		+ "	JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham\r\n"
	        		+ "	JOIN HoaDon hd ON cthd.MaHoaDon = hd.MaHoaDon\r\n"
	        		+ "WHERE YEAR(hd.NgayDat) = ? \r\n"
	        		+ "	ORDER BY TongTienSP DESC"); 
	        preparedStatementSP.setInt(1, year);
	        ResultSet rs = preparedStatementSP.executeQuery();
	        rs.next();
	        double tongTienSP = rs.getDouble("TongTienSP");
	        
	        PreparedStatement preparedStatementVe = con.prepareStatement(
	        		"SELECT SUM(lc.GiaMotGhe) AS TongTienVe\r\n"
	        		+ "FROM Phim p\r\n"
	        		+ "JOIN LichChieu lc ON p.MaPhim = lc.MaPhim\r\n"
	        		+ "JOIN Ve v ON lc.MaLichChieu = v.MaLichChieu\r\n"
	        		+ "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon \r\n"
	        		+ "WHERE YEAR(hd.NgayDat) = ?\r\n"
	        		+ "ORDER BY TongTienVe DESC"); 
	        preparedStatementVe.setInt(1, year);
	        rs = preparedStatementVe.executeQuery();
	        rs.next();
	        double tongTienBanVe = rs.getDouble("TongTienVe");

	        dataset.setValue("Tổng tiền bán vé", tongTienBanVe);
	        dataset.setValue("Tổng tiền bán đồ ăn & uống", tongTienSP);
	        return dataset;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dataset;
	}
	
	public DefaultPieDataset getThongKeDoanhThuTheoThangBD(int month, int year) {
		DefaultPieDataset dataset = new DefaultPieDataset();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	    	PreparedStatement preparedStatementSP = con.prepareStatement(
	        		"SELECT top 1 SUM(cthd.ThanhTien) AS TongTienSP\r\n"
	        		+ "	FROM SanPham sp\r\n"
	        		+ "	JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham\r\n"
	        		+ "	JOIN HoaDon hd ON cthd.MaHoaDon = hd.MaHoaDon\r\n"
	        		+ "WHERE YEAR(hd.NgayDat) = ? and month(hd.NgayDat) = ? \r\n"
	        		+ "	ORDER BY TongTienSP DESC"); 
	        preparedStatementSP.setInt(1, year);
	        preparedStatementSP.setInt(2, month);
	        ResultSet rs = preparedStatementSP.executeQuery();
	        rs.next();
	        double tongTienSP = rs.getDouble("TongTienSP");
	        System.out.println(tongTienSP+ "Tổng tiền sp");
	        PreparedStatement preparedStatementVe = con.prepareStatement(
	        		"SELECT SUM(lc.GiaMotGhe) AS TongTienVe\r\n"
	        		+ "FROM Phim p\r\n"
	        		+ "JOIN LichChieu lc ON p.MaPhim = lc.MaPhim\r\n"
	        		+ "JOIN Ve v ON lc.MaLichChieu = v.MaLichChieu\r\n"
	        		+ "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon \r\n"
	        		+ "WHERE YEAR(hd.NgayDat) = ? and month(hd.NgayDat) = ?\r\n"
	        		+ "ORDER BY TongTienVe DESC"); 
	        preparedStatementVe.setInt(1, year);
	        preparedStatementVe.setInt(2, month);
	        rs = preparedStatementVe.executeQuery();
	        rs.next();
	        double tongTienBanVe = rs.getDouble("TongTienVe");
	        System.out.println(tongTienBanVe+ "TongTienVe");
	        dataset.setValue("Tổng tiền bán vé", tongTienBanVe);
	        dataset.setValue("Tổng tiền bán đồ ăn & uống", tongTienSP);
	        return dataset;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dataset;
	}
	
	public ArrayList<DoanhThu> getThongKeDoanhThuTheoThang(int month, int year) {
	    ArrayList<DoanhThu> dsDoanhThu = new ArrayList<DoanhThu>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatementSP = con.prepareStatement(
	        		"SELECT top 1 SUM(cthd.ThanhTien) AS TongTienSP\r\n"
	        		+ "	FROM SanPham sp\r\n"
	        		+ "	JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham\r\n"
	        		+ "	JOIN HoaDon hd ON cthd.MaHoaDon = hd.MaHoaDon\r\n"
	        		+ "WHERE YEAR(hd.NgayDat) = ? and month(hd.NgayDat) = ? \r\n"
	        		+ "	ORDER BY TongTienSP DESC"); 
	        preparedStatementSP.setInt(1, year);
	        preparedStatementSP.setInt(2, month);
	        ResultSet rs = preparedStatementSP.executeQuery();
	        rs.next();
	        double tongTienSP = rs.getDouble("TongTienSP");
	        System.out.println(tongTienSP+ "Tổng tiền sp");
	        PreparedStatement preparedStatementVe = con.prepareStatement(
	        		"SELECT SUM(lc.GiaMotGhe) AS TongTienVe\r\n"
	        		+ "FROM Phim p\r\n"
	        		+ "JOIN LichChieu lc ON p.MaPhim = lc.MaPhim\r\n"
	        		+ "JOIN Ve v ON lc.MaLichChieu = v.MaLichChieu\r\n"
	        		+ "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon \r\n"
	        		+ "WHERE YEAR(hd.NgayDat) = ? and month(hd.NgayDat) = ?\r\n"
	        		+ "ORDER BY TongTienVe DESC"); 
	        preparedStatementVe.setInt(1, year);
	        preparedStatementVe.setInt(2, month);
	        rs = preparedStatementVe.executeQuery();
	        rs.next();
	        double tongTienBanVe = rs.getDouble("TongTienVe");
	        System.out.println(tongTienBanVe+ "TongTienVe");
//	        PreparedStatement preparedStatementDT = con.prepareStatement(
//	        		"SELECT SUM(hd.TongTien) AS TongDoanhThu\r\n"
//	        		+ "FROM HoaDon hd\r\n"
//	        		+ "WHERE YEAR(hd.NgayDat) = ? and month(hd.NgayDat) = ?\r\n"
//	        		+ "ORDER BY TongDoanhThu DESC"); 
//	        preparedStatementDT.setInt(2, month);
//	        preparedStatementDT.setInt(1, year);
//	        rs = preparedStatementDT.executeQuery();
//	        rs.next();
//	        double tongDoanhThu = rs.getDouble("TongDoanhThu");
	        
	        double tongDoanhThu = tongTienBanVe + tongTienSP;
	        
	        DoanhThu dt = new DoanhThu(tongTienSP, tongTienBanVe, tongDoanhThu);
            dsDoanhThu.add(dt);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsDoanhThu;
	}
	
	public ArrayList<DoanhThu> getThongKeDoanhThuTheoNam(int year) {
	    ArrayList<DoanhThu> dsDoanhThu = new ArrayList<DoanhThu>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatementSP = con.prepareStatement(
	        		"SELECT top 1 SUM(cthd.ThanhTien) AS TongTienSP\r\n"
	        		+ "	FROM SanPham sp\r\n"
	        		+ "	JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham\r\n"
	        		+ "	JOIN HoaDon hd ON cthd.MaHoaDon = hd.MaHoaDon\r\n"
	        		+ "WHERE YEAR(hd.NgayDat) = ? \r\n"
	        		+ "	ORDER BY TongTienSP DESC"); 
	        preparedStatementSP.setInt(1, year);
	        ResultSet rs = preparedStatementSP.executeQuery();
	        rs.next();
	        double tongTienSP = rs.getDouble("TongTienSP");
	        
	        PreparedStatement preparedStatementVe = con.prepareStatement(
	        		"SELECT SUM(lc.GiaMotGhe) AS TongTienVe\r\n"
	        		+ "FROM Phim p\r\n"
	        		+ "JOIN LichChieu lc ON p.MaPhim = lc.MaPhim\r\n"
	        		+ "JOIN Ve v ON lc.MaLichChieu = v.MaLichChieu\r\n"
	        		+ "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon \r\n"
	        		+ "WHERE YEAR(hd.NgayDat) = ?\r\n"
	        		+ "ORDER BY TongTienVe DESC"); 
	        preparedStatementVe.setInt(1, year);
	        rs = preparedStatementVe.executeQuery();
	        rs.next();
	        double tongTienBanVe = rs.getDouble("TongTienVe");
	        
//	        PreparedStatement preparedStatementDT = con.prepareStatement(
//	        		"SELECT SUM(hd.TongTien) AS TongDoanhThu\r\n"
//	        		+ "FROM HoaDon hd\r\n"
//	        		+ "WHERE YEAR(hd.NgayDat) = ? \r\n"
//	        		+ "ORDER BY TongDoanhThu DESC"); 
//	        preparedStatementDT.setInt(1, year);
//	        rs = preparedStatementDT.executeQuery();
//	        rs.next();
//	        double tongDoanhThu = rs.getDouble("TongDoanhThu");
	        
	        double tongDoanhThu = tongTienBanVe + tongTienSP;
	        
	        DoanhThu dt = new DoanhThu(tongTienSP, tongTienBanVe, tongDoanhThu);
            dsDoanhThu.add(dt);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsDoanhThu;
	}
}
