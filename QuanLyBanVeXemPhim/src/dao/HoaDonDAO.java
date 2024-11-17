/*
 * @(#) HoaDonDAO.java 1.0 Nov 11, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import connectDB.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 11, 2024
 * @version: 1.0
 */

public class HoaDonDAO {
	
	KhachHangDAO kh_dao;
	NhanVienDAO nv_dao;
	KhuyenMaiDAO km_dao;
	
	
	public String taoHoaDonMoi(HoaDon hoaDon) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    String maHoaDon = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "INSERT INTO HoaDon (NgayDat, SoGhe, GhiChu, MaKhachHang, MaNhanVien, MaKhuyenMai, VAT, TongTien) " +
	            "OUTPUT inserted.MaHoaDon " +
	            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
	        );

	        preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(hoaDon.getNgayDat()));
	        preparedStatement.setInt(2, hoaDon.getSoGhe());
	        preparedStatement.setString(3, hoaDon.getGhiChu());
	        preparedStatement.setString(4, hoaDon.getKh().getMaKhachHang());
	        preparedStatement.setString(5, hoaDon.getNv().getMaNhanVien());
	        preparedStatement.setString(6, hoaDon.getKm().getMaKhuyenMai());
	        preparedStatement.setDouble(7, hoaDon.getVAT());
	        preparedStatement.setDouble(8, hoaDon.getTongTien());

	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            maHoaDon = rs.getString(1);
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
	    return maHoaDon;
	}


	public HoaDon timHoaDonTheoMa(String maHoaDon) {
		kh_dao = new KhachHangDAO();
		nv_dao = new NhanVienDAO();
		km_dao = new KhuyenMaiDAO();
		ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "select *\r\n" +
	            "from HoaDon\r\n" +
	            "where MaHoaDon = ?");

	        preparedStatement.setString(1, maHoaDon);
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            String maHoaDon1 = rs.getString("MaHoaDon");
	            LocalDateTime ngayDat = rs.getTimestamp("NgayDat").toLocalDateTime();
	            int soGhe = rs.getInt("SoGhe");
	            String ghiChu = rs.getString("GhiChu");
	            String maKhachHang = rs.getString("MaKhachHang");
	            String maNhanVien = rs.getString("MaNhanVien");
	            String maKhuyenMai = rs.getString("MaKhuyenMai");
	            
	            double vat = rs.getDouble("VAT");
	            double tongTien = rs.getDouble("TongTien");
	            
	            KhachHang kh = kh_dao.timKhachHangTheoMa(maKhachHang);
	            NhanVien nv = nv_dao.getNhanVienByID(maNhanVien);
	            KhuyenMai km = km_dao.tim1KhuyenMaiTheoMa(maKhuyenMai);

	            HoaDon hoaDon = new HoaDon(maHoaDon1, ngayDat, soGhe, ghiChu, kh, nv, km, vat, tongTien);
	            return hoaDon;
	        }
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}
