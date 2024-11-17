package dao;

/*
 * @(#) GheDAO.java 1.0 Nov 11, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ghe;
import entity.KhachHang;
import entity.LoaiGhe;
import entity.Phong;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 11, 2024
 * @version: 1.0
 */

public class GheDAO {
	ArrayList<Ghe> dsGhe;
	LoaiGheDAO loaiGhe_dao;
	PhongDAO phong_dao;
	
	public Ghe timGheTheoMa(String maGhe) {
		loaiGhe_dao = new LoaiGheDAO();
		phong_dao = new PhongDAO();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
		            "select *\r\n"
		            + "from Ghe\r\n"
		            + "where MaGhe = ?");

		        preparedStatement.setString(1, maGhe);
		        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maGhe1 = rs.getString("MaGhe");
				String viTri = rs.getString("ViTri");
				String maLoaiGhe = rs.getString("MaLoaiGhe");
				String maPhong = rs.getString("MaPhong");
				
				LoaiGhe loaiGhe = loaiGhe_dao.timLoaiGheTheoMa(maLoaiGhe);
				Phong phong = phong_dao.timPhong(maPhong);
				
				Ghe ghe = new Ghe(maGhe1, viTri, phong, loaiGhe);
				return ghe;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public ArrayList<Ghe> getTatCaGheTheoPhong(String maPhong) {
        ArrayList<Ghe> dsGhe = new ArrayList<>();
        loaiGhe_dao = new LoaiGheDAO();
		phong_dao = new PhongDAO();
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "select *\n" +
                    "from Ghe\n" +
                    "where MaPhong = ?");

            preparedStatement.setString(1, maPhong);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maGhe = rs.getString("MaGhe");
                String viTri = rs.getString("ViTri");
                String maLoaiGhe = rs.getString("MaLoaiGhe");

                LoaiGhe loaiGhe = loaiGhe_dao.timLoaiGheTheoMa(maLoaiGhe);
                Phong phong = phong_dao.timPhong(maPhong);

                Ghe ghe = new Ghe(maGhe, viTri, phong, loaiGhe);
                dsGhe.add(ghe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsGhe;
    }
}
