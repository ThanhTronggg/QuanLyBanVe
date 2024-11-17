/*
 * @(#) SanPhamDAO.java 1.0 Nov 6, 2024
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
import entity.SanPham;
import entity.SanPhamThongKe;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 6, 2024
 * @version: 1.0
 */

public class SanPhamDAO {
	
	public boolean capNhatSanPham(SanPham sp) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    try {
	        String queryUpdate = "UPDATE SanPham SET TenSanPham = ?, SoLuong = ?, GiaMua = ?, LoaiSanPham = ?  WHERE MaSanPham = ?";
	        preparedStatement = con.prepareStatement(queryUpdate);
	        preparedStatement.setString(1, sp.getTenSanPham());
	        preparedStatement.setInt(2, sp.getSoLuong());
	        preparedStatement.setDouble(3, sp.getGiaMua());
	        preparedStatement.setString(4, sp.getLoaiSanPham());
	        preparedStatement.setString(5, sp.getMaSanPham());

	        int rowsUpdated = preparedStatement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public boolean xoaSanPham(String maSanPham) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    try {
	        String queryUpdate = "Delete From SanPham WHERE MaSanPham = ?";
	        preparedStatement = con.prepareStatement(queryUpdate);
	        preparedStatement.setString(1, maSanPham);
	        int rowsUpdated = preparedStatement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public boolean themSanPhamMoi(SanPham sp) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    try {
	        String queryUpdate = "INSERT [dbo].[SanPham] ([TenSanPham], [SoLuong], [GiaMua], [GiaBan], [LoaiSanPham], [Anh]) \r\n"
	        		+ "VALUES (? , ?, ?, ?, ?, ?);";
	        preparedStatement = con.prepareStatement(queryUpdate);
	        preparedStatement.setString(1, sp.getTenSanPham());
	        preparedStatement.setInt(2, sp.getSoLuong());
	        preparedStatement.setDouble(3, sp.getGiaMua());
	        preparedStatement.setDouble(4, sp.getGiaBan());
	        preparedStatement.setString(5, sp.getLoaiSanPham());
	        preparedStatement.setString(6, sp.getAnh());

	        int rowsUpdated = preparedStatement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public boolean tangSoLuongSanPham(String maSanPham, int soLuong) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    try {
	        String queryUpdate = "UPDATE SanPham SET SoLuong = SoLuong + ? WHERE MaSanPham = ?";
	        preparedStatement = con.prepareStatement(queryUpdate);
	        preparedStatement.setInt(1, soLuong);
	        preparedStatement.setString(2, maSanPham);

	        int rowsUpdated = preparedStatement.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public boolean giamSoLuongSanPham(String maSanPham, int soLuongCanGiam) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    try {
	        String queryUpdate = "UPDATE SanPham SET SoLuong = SoLuong - ? WHERE MaSanPham = ?";
	        preparedStatement = con.prepareStatement(queryUpdate);
	        preparedStatement.setInt(1, soLuongCanGiam);
	        preparedStatement.setString(2, maSanPham);

	        int rowsUpdated = preparedStatement.executeUpdate();
	        System.out.println("aaaa"+ rowsUpdated);
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public ArrayList<SanPham> getSanPhamTheoLoaiSP(String loaiSanPham) {
	    ArrayList<SanPham> dsSanPham = new ArrayList<>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "select *\r\n" +
	            "from SanPham\r\n" +
	            "where LoaiSanPham = ?");

	        preparedStatement.setString(1, loaiSanPham);
	        rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String maSanPham = rs.getString("MaSanPham");
	            String tenSanPham = rs.getString("TenSanPham");
	            int soLuong = rs.getInt("SoLuong");
	            double giaMua = rs.getDouble("GiaMua");
	            String loaiSP = rs.getString("LoaiSanPham");
	            String anh = rs.getString("Anh");

	            SanPham sanPham = new SanPham(maSanPham, tenSanPham, soLuong, giaMua, loaiSP, anh);
	            dsSanPham.add(sanPham);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return dsSanPham;
	}

	
	public ArrayList<SanPham> getTatCaSanPham() {
	    ArrayList<SanPham> dsSanPham = new ArrayList<>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "select *\r\n" +
	            "from SanPham");

	        rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	            String maSanPham = rs.getString("MaSanPham");
	            String tenSanPham = rs.getString("TenSanPham");
	            int soLuong = rs.getInt("SoLuong");
	            double giaMua = rs.getDouble("GiaMua");
	            String loaiSanPham = rs.getString("LoaiSanPham");
	            String anh = rs.getString("Anh");

	            SanPham sanPham = new SanPham(maSanPham, tenSanPham, soLuong, giaMua, loaiSanPham, anh);
	            dsSanPham.add(sanPham);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return dsSanPham;
	}
}
