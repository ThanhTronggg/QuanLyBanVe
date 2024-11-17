/*
 * @(#) LoaiGheDAO.java 1.0 Nov 11, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDB.ConnectDB;
import entity.LoaiGhe;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 11, 2024
 * @version: 1.0
 */

public class LoaiGheDAO {
	public LoaiGhe timLoaiGheTheoMa(String maLoaiGhe) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "select *\r\n" +
	            "from LoaiGhe\r\n" +
	            "where MaLoaiGhe = ?");

	        preparedStatement.setString(1, maLoaiGhe);
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            String maLoaiGhe1 = rs.getString("MaLoaiGhe");
	            String tenLoaiGhe = rs.getString("TenLoaiGhe");
	            String moTaLoaiGhe = rs.getString("MoTaLoaiGhe");

	            LoaiGhe loaiGhe = new LoaiGhe(maLoaiGhe1, tenLoaiGhe, moTaLoaiGhe);
	            return loaiGhe;
	        }
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
