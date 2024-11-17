/*
 * @(#) GheTheoLichChieuDAO.java 1.0 Nov 11, 2024
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
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ghe;
import entity.HoaDon;
import entity.KhachHang;
import entity.LichChieu;
import entity.Ve;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 11, 2024
 * @version: 1.0
 */

public class VeDAO {
	
	ArrayList<Ve> dsGhe;
	GheDAO ghe_dao;
	LichChieuDAO lichChieu_dao;
	HoaDonDAO hoaDon_dao;
	
	public ArrayList<Ve> getVeTheoLichChieu(LichChieu lc) {
	    ArrayList<Ve> danhSachVe = new ArrayList<>();
	    hoaDon_dao = new HoaDonDAO();
	    lichChieu_dao = new LichChieuDAO();
	    ghe_dao = new GheDAO();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "SELECT * " +
	            "FROM Ve " +
	            "WHERE MaLichChieu = ?"
	        );

	        preparedStatement.setString(1, lc.getMaLichChieu());
	        rs = preparedStatement.executeQuery();
	        
	        while (rs.next()) {
	            String maVe = rs.getString("MaVe");
	            LocalDate ngayPhatHanh = rs.getDate("NgayPhatHanh").toLocalDate();
	            String maGhe = rs.getString("MaGhe");
	            String maHoaDon = rs.getString("MaHoaDon");

	            Ghe ghe = ghe_dao.timGheTheoMa(maGhe);
	            HoaDon hd = hoaDon_dao.timHoaDonTheoMa(maHoaDon);
	            Ve ve = new Ve(maVe, ngayPhatHanh, ghe, lc, hd);

	            danhSachVe.add(ve);
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
	    return danhSachVe;
	}

	
	public String taoVeMoi(Ve ve) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    String maVe = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "INSERT INTO Ve (NgayPhatHanh, MaGhe, MaLichChieu, MaHoaDon) OUTPUT inserted.MaVe  VALUES (?, ?, ?, ?)"
	        );
	        preparedStatement.setDate(1, java.sql.Date.valueOf(ve.getNgayPhatHanh()));
	        preparedStatement.setString(2, ve.getGhe().getMaGhe());
	        preparedStatement.setString(3, ve.getLc().getMaLichChieu());
	        preparedStatement.setString(4, ve.getHd().getMaHoaDon());
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            maVe = rs.getString(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return maVe;
	}


	
	public ArrayList<Ve> getGheTheoLichChieu(LichChieu lc) {
		dsGhe = new ArrayList<Ve>();
		hoaDon_dao = new HoaDonDAO();
		lichChieu_dao = new LichChieuDAO();
		ghe_dao = new GheDAO();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(
		            "select * \r\n"
		            + "from Ve\r\n"
		            + "where MaLichChieu = ?");

		        preparedStatement.setString(1, lc.getMaLichChieu());
		        ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maVe = rs.getString("MaVe");
				LocalDate ngayPhatHanh = rs.getDate("NgayPhatHanh").toLocalDate();
				String maGhe = rs.getString("MaGhe");
				String maLichChieu = rs.getString("MaLichChieu");
				String maHoaDon = rs.getString("MaHoaDon");
				
				Ghe ghe = ghe_dao.timGheTheoMa(maGhe);
				HoaDon hd = hoaDon_dao.timHoaDonTheoMa(maHoaDon);
				LichChieu lc1 = lichChieu_dao.timLichChieuTheoMa(maLichChieu);
				Ve ve = new Ve(maVe, ngayPhatHanh, ghe, lc1, hd);
				
				
				dsGhe.add(ve);
			}
			return dsGhe;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
