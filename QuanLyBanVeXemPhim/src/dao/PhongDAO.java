/*
 * @(#) PhongDAO.java 1.0 Nov 8, 2024
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
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import entity.Phong;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 8, 2024
 * @version: 1.0
 */

public class PhongDAO {
	
	private ArrayList<Phong> dsPhong;

	public Phong timPhong(String maPhong) {
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		Phong phong = null;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
					"select * from Phong\r\n"
					+ "where MaPhong = ?\r\n");
			preparedStatement.setString(1, maPhong);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String ma = rs.getString("MaPhong");
				String ten = rs.getString("TenPhong");
				int soGhe = rs.getInt("SoLuongGhe");
				
				phong = new Phong(ma, ten, soGhe);
				return phong;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return phong;
		}
		return phong;
	}
	
	public ArrayList<Phong> getAllPhong() {
		dsPhong = new ArrayList<Phong>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from Phong");
			while (rs.next()) {
				String ma = rs.getString("MaPhong");
				String ten = rs.getString("TenPhong");
				int soGhe = rs.getInt("SoLuongGhe");
				
				Phong phong = new Phong(ma, ten, soGhe);
				
				dsPhong.add(phong);
			}
			return dsPhong;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
