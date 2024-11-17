/*
 * @(#) ChiTietHoaDonDAO.java 1.0 Nov 12, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 12, 2024
 * @version: 1.0
 */

public class ChiTietHoaDonDAO {
	public boolean themChiTietHoaDonMoi(ChiTietHoaDon chiTietHoaDon) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "INSERT INTO ChiTietHoaDon (MaHoaDon, MaSanPham, SoLuong, ThanhTien) " +
	            "VALUES (?, ?, ?, ?)"
	        );

	        preparedStatement.setString(1, chiTietHoaDon.getHd().getMaHoaDon());
	        preparedStatement.setString(2, chiTietHoaDon.getSp().getMaSanPham());
	        preparedStatement.setInt(3, chiTietHoaDon.getSoLuong());
	        preparedStatement.setDouble(4, chiTietHoaDon.getTongTienSP());

	        int rowsInserted = preparedStatement.executeUpdate();

	        return rowsInserted > 0;
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


}
