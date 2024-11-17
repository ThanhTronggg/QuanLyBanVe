package dao;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LichChieu;
import entity.Phim;
import entity.Phong;

public class LichChieuDAO {
	
	PhongDAO daoPhong = new PhongDAO();
	PhimDAO daoPhim = new PhimDAO();
	
	public LichChieu timLichChieuTheoMa(String maLichChieu) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	            "select *\r\n" +
	            "from LichChieu\r\n" +
	            "where MaLichChieu = ?");

	        preparedStatement.setString(1, maLichChieu);
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            String maLichChieu1 = rs.getString("MaLichChieu");
	            LocalDateTime gioBatDau = rs.getTimestamp("GioBatDau").toLocalDateTime();
	            LocalDateTime gioKetThuc = rs.getTimestamp("GioKetThuc").toLocalDateTime();
	            double giaMotGhe = rs.getDouble("GiaMotGhe");
	            Phong phong = daoPhong.timPhong(rs.getString("MaPhong"));
	            Phim phim = daoPhim.getPhimByMa(rs.getString("MaPhim"));

	            LichChieu lichChieu = new LichChieu(maLichChieu1, gioBatDau, gioKetThuc, giaMotGhe, phong, phim);
	            return lichChieu;
	        }
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public ArrayList<LichChieu> getLichChieuTheoNgay(LocalDate ngay) {
        ArrayList<LichChieu> dsLichChieu = new ArrayList<>();
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT *\r\n"
                + "FROM LichChieu\r\n"
                + "WHERE CONVERT(DATE, GioBatDau) = ?;\r\n");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            preparedStatement.setString(1, ngay.format(formatter));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maLichChieu = rs.getString("MaLichChieu");
                String maPhong = rs.getString("MaPhong");
                String maPhim = rs.getString("MaPhim");
                LocalDateTime gioBatDau = rs.getTimestamp("GioBatDau").toLocalDateTime();
                LocalDateTime gioKetThuc = rs.getTimestamp("GioKetThuc").toLocalDateTime();
                double giaMotGhe = rs.getDouble("GiaMotGhe");
                
                Phong phong = daoPhong.timPhong(maPhong);
                Phim phim = daoPhim.getPhimByMa(maPhim);
                
                LichChieu lc = new LichChieu(maLichChieu, gioBatDau, gioKetThuc, giaMotGhe, phong, phim);
                dsLichChieu.add(lc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return dsLichChieu;
    }
    
    public ArrayList<LichChieu> getTatCaLichChieu() {
        ArrayList<LichChieu> dsLichChieu = new ArrayList<>();
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM [dbo].[LichChieu]");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maLichChieu = rs.getString("MaLichChieu");
                String maPhong = rs.getString("MaPhong");
                String maPhim = rs.getString("MaPhim");
                LocalDateTime gioBatDau = rs.getTimestamp("GioBatDau").toLocalDateTime();
                LocalDateTime gioKetThuc = rs.getTimestamp("GioKetThuc").toLocalDateTime();
                double giaMotGhe = rs.getDouble("GiaMotGhe");
                
                Phong phong = daoPhong.timPhong(maPhong);
                Phim phim = daoPhim.getPhimByMa(maPhim);
                
                LichChieu lc = new LichChieu(maLichChieu, gioBatDau, gioKetThuc, giaMotGhe, phong, phim);
                dsLichChieu.add(lc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return dsLichChieu;
    }
    
    public ArrayList<LichChieu> getLichChieuTheoMa(String maLichChieu) {
        ArrayList<LichChieu> dsLichChieu = new ArrayList<>();
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM [dbo].[LichChieu] WHERE MaLichChieu LIKE ?");
            preparedStatement.setString(1, "%" + maLichChieu + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MaLichChieu");
                String maPhong = rs.getString("MaPhong");
                String maPhim = rs.getString("MaPhim");
                LocalDateTime gioBatDau = rs.getTimestamp("GioBatDau").toLocalDateTime();
                LocalDateTime gioKetThuc = rs.getTimestamp("GioKetThuc").toLocalDateTime();
                double giaMotGhe = rs.getDouble("GiaMotGhe");
                
                Phong phong = daoPhong.timPhong(maPhong);
                Phim phim = daoPhim.getPhimByMa(maPhim);
                
                LichChieu lc = new LichChieu(ma, gioBatDau, gioKetThuc, giaMotGhe, phong, phim);
                dsLichChieu.add(lc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return dsLichChieu;
    }
    
    // Add more methods for CRUD operations as needed
    
    public boolean themLichChieu(LichChieu lichChieu) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO [dbo].[LichChieu] (MaPhong, MaPhim, GioBatDau, GioKetThuc, GiaMotGhe) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, lichChieu.getPhong().getMaPhong());
            stmt.setString(2, lichChieu.getPhim().getMaPhim());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
            stmt.setString(3, lichChieu.getGioBatDau().format(formatter));
            stmt.setString(4, lichChieu.getGioKetThuc().format(formatter));
            stmt.setDouble(5, lichChieu.getGiaMotGhe());
            
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
    
    public boolean capNhatLichChieu(LichChieu lichChieu) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE [dbo].[LichChieu] SET MaPhong = ?, MaPhim = ?, GioBatDau = ?, GioKetThuc = ?, GiaMotGhe = ? WHERE MaLichChieu = ?");
            stmt.setString(1, lichChieu.getPhong().getMaPhong());
            stmt.setString(2, lichChieu.getPhim().getMaPhim());
            stmt.setTimestamp(3, Timestamp.valueOf(lichChieu.getGioBatDau()));
            stmt.setTimestamp(4, Timestamp.valueOf(lichChieu.getGioKetThuc()));
            stmt.setDouble(5, lichChieu.getGiaMotGhe());
            stmt.setString(6, lichChieu.getMaLichChieu());
            
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }
    
    public boolean xoaLichChieu(String maLichChieu) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM [dbo].[LichChieu] WHERE MaLichChieu = ?");
            stmt.setString(1, maLichChieu);
            
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return n > 0;
    }

	public ArrayList<LichChieu> getLichChieuTheoTrangThai(LocalDateTime now, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<LichChieu> getLichChieuDangChieu(LocalDateTime now) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<LichChieu> getLichChieuTheoPhong(String maPhong) {
	    ArrayList<LichChieu> dsLichChieu = new ArrayList<>();
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();

	    try {
	        PreparedStatement stmt = con.prepareStatement(
	            "SELECT MaLichChieu, GioBatDau, GioKetThuc, MaPhim, MaPhong, GiaMotGhe " +
	            "FROM LichChieu WHERE MaPhong = ?"
	        );
	        stmt.setString(1, maPhong);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String maLichChieu = rs.getString("MaLichChieu");
	            LocalDateTime gioBatDau = rs.getTimestamp("GioBatDau").toLocalDateTime();
	            LocalDateTime gioKetThuc = rs.getTimestamp("GioKetThuc").toLocalDateTime();
	            String maPhim = rs.getString("MaPhim");
	            String maPhong2 = rs.getString("MaPhong");
	            double giaMotGhe = rs.getDouble("GiaMotGhe");

	            Phong phong = daoPhong.timPhong(maPhong2);
	            Phim phim = daoPhim.getPhimByMa(maPhim);

	            LichChieu lichChieu = new LichChieu(maLichChieu, gioBatDau, gioKetThuc, giaMotGhe, phong, phim);
	            dsLichChieu.add(lichChieu);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    }
	    
	    return dsLichChieu;
	}

}