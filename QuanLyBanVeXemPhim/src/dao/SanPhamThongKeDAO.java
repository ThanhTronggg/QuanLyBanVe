/*
 * @(#) SanPhamThongKeDAO.java 1.0 Nov 15, 2024
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
import entity.SanPhamThongKe;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 15, 2024
 * @version: 1.0
 */

public class SanPhamThongKeDAO {
	
	public ArrayList<SanPhamThongKe> getThongKeSanPhamTheoThang(int month, int year) {
	    ArrayList<SanPhamThongKe> dsSanPhamThongKe = new ArrayList<>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"SELECT TOP 5 sp.MaSanPham, sp.TenSanPham, SUM(cthd.SoLuong) AS SoLuongDaBan, SUM(cthd.ThanhTien) AS TongTienBanDuoc " +
	        		"FROM SanPham sp " +
	        		"JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham " +
	        		"JOIN HoaDon hd ON cthd.MaHoaDon = hd.MaHoaDon " +
	        		"WHERE MONTH(hd.NgayDat) = ? AND YEAR(hd.NgayDat) = ? " +
	        		"GROUP BY sp.MaSanPham, sp.TenSanPham " +
	        		"ORDER BY TongTienBanDuoc DESC, SoLuongDaBan DESC");

	        
	        preparedStatement.setInt(1, month);
	        preparedStatement.setInt(2, year);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String maSanPham = rs.getString("MaSanPham");
	            String tenSanPham = rs.getString("TenSanPham");
	            int soLuongDaBan = rs.getInt("SoLuongDaBan");
	            double tongTienBanDuoc = rs.getDouble("TongTienBanDuoc");
	            
	            SanPhamThongKe spThongKe = new SanPhamThongKe(maSanPham, tenSanPham, soLuongDaBan, tongTienBanDuoc);
	            dsSanPhamThongKe.add(spThongKe);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsSanPhamThongKe;
	}
	
	public ArrayList<SanPhamThongKe> getThongKeSanPhamTheoNam(int year) {
        ArrayList<SanPhamThongKe> dsSanPhamThongKe = new ArrayList<>();
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
            		"SELECT TOP 5 sp.MaSanPham, sp.TenSanPham, SUM(cthd.SoLuong) AS SoLuongDaBan, SUM(cthd.ThanhTien) AS TongTienBanDuoc " +
        	        "FROM SanPham sp " +
        	        "JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham " +
        	        "JOIN HoaDon hd ON cthd.MaHoaDon = hd.MaHoaDon " +
        	        "WHERE YEAR(hd.NgayDat) = ? " +
        	        "GROUP BY sp.MaSanPham, sp.TenSanPham " +
        	        "ORDER BY TongTienBanDuoc DESC, SoLuongDaBan DESC");

            preparedStatement.setInt(1, year);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maSanPham = rs.getString("MaSanPham");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuongDaBan = rs.getInt("SoLuongDaBan");
                double tongTienBanDuoc = rs.getDouble("TongTienBanDuoc");
                
                SanPhamThongKe spThongKe = new SanPhamThongKe(maSanPham, tenSanPham, soLuongDaBan, tongTienBanDuoc);
                dsSanPhamThongKe.add(spThongKe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsSanPhamThongKe;
    }

	public DefaultCategoryDataset getDoanhThuSanPhamTheoNamBD(int year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT top 5 sp.MaSanPham, sp.TenSanPham, SUM(cthd.SoLuong) AS SoLuongDaBan, SUM(cthd.ThanhTien) AS TongTienBanDuoc " +
                "FROM SanPham sp " +
                "JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham " +
                "JOIN HoaDon hd ON cthd.MaHoaDon = hd.MaHoaDon " +
                "WHERE YEAR(hd.NgayDat) = ? " +
                "GROUP BY sp.MaSanPham, sp.TenSanPham " +
            	"ORDER BY TongTienBanDuoc DESC, SoLuongDaBan DESC");

            preparedStatement.setInt(1, year);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String tenSanPham = rs.getString("TenSanPham");
                double tongTienBanDuoc = rs.getDouble("TongTienBanDuoc");
     
                dataset.addValue(tongTienBanDuoc, "Tổng tiền bán được", tenSanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
	
	public DefaultCategoryDataset getDoanhThuSanPhamTheoThangBD(int year, int month) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"SELECT TOP 5 sp.MaSanPham, sp.TenSanPham, SUM(cthd.SoLuong) AS SoLuongDaBan, SUM(cthd.ThanhTien) AS TongTienBanDuoc " +
	        		"FROM SanPham sp " +
	        		"JOIN ChiTietHoaDon cthd ON sp.MaSanPham = cthd.MaSanPham " +
	        		"JOIN HoaDon hd ON cthd.MaHoaDon = hd.MaHoaDon " +
	        		"WHERE MONTH(hd.NgayDat) = ? AND YEAR(hd.NgayDat) = ? " +
	        		"GROUP BY sp.MaSanPham, sp.TenSanPham " +
	        		"ORDER BY TongTienBanDuoc DESC, SoLuongDaBan DESC");

	        
	        preparedStatement.setInt(1, month);
	        preparedStatement.setInt(2, year);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String tenSanPham = rs.getString("TenSanPham");
	            double tongTienBanDuoc = rs.getDouble("TongTienBanDuoc");
	            
                dataset.addValue(tongTienBanDuoc, "Tổng tiền bán được", tenSanPham);
	        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
}
