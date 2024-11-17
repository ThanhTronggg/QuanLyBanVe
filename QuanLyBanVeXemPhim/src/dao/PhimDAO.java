package dao;

import entity.Phim;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PhimDAO {
    private Connection connection;
    private ArrayList<Phim> dsPhim;

    public PhimDAO() {
    	dsPhim = new ArrayList<Phim>();
      //  this.connection = ConnectDB.getConnection();
    }
    
    public ArrayList<Phim> getAllPhim() {
        ArrayList<Phim> dsPhim = new ArrayList<Phim>();
        try {
       	 if (ConnectDB.getConnection().isClosed()) {
                // Mở lại kết nối
                ConnectDB.getInstance().connect();
            }
       	Connection con = ConnectDB.getConnection();
        String sql = "SELECT * FROM Phim";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Phim phim = extractPhimFromResultSet(rs);
                dsPhim.add(phim);
            }
            return dsPhim;
        } catch (SQLException e) {
        	e.printStackTrace();
            return null;
        } finally {
        	System.out.println("End PhimDAO - getallPhim");    	
        }  
    }
    
    public boolean themPhim(Phim phim) {
    	 try {
        	 if (ConnectDB.getConnection().isClosed()) {
                 // Mở lại kết nối
                 ConnectDB.getInstance().connect();
             }
        	  Connection con = ConnectDB.getConnection();
              PreparedStatement stmt = null;
              int n = 0;
              stmt = con.prepareStatement("INSERT INTO Phim (MaPhim, TenPhim, TheLoai, DaoDien, ThoiLuong, NgayCongChieu, NgonNgu, QuocGia, TrangThai, NgayBatDau, GiaThau, Anh, DoanPhimGioiThieu, TomTat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
              System.out.println("Mã Phim" + phim.getMaPhim());
              stmt.setString(1, phim.getMaPhim());
              System.out.println("Tên Phim" + phim.getTenPhim());
//              stmt.setString(2, phim.getTenPhim());
              stmt.setString(2, phim.getTenPhim().length() > 100 ? phim.getTenPhim().substring(0, 100) : phim.getTenPhim());

              System.out.println("Thể loại" + phim.getTheLoai());
              stmt.setString(3, phim.getTheLoai());
              System.out.println("Đạo diễn" + phim.getDaoDien());
              stmt.setString(4, phim.getDaoDien());
              System.out.println("Thời lượng " + phim.getThoiLuong());
              stmt.setInt(5, phim.getThoiLuong());
              System.out.println("Ngày công chiếu" + phim.getNgayCongChieu());
              stmt.setDate(6, java.sql.Date.valueOf(phim.getNgayCongChieu()));
              System.out.println("Ngôn ngữ" + phim.getNgonNgu());
              stmt.setString(7, phim.getNgonNgu());
              System.out.println("quốc gia" + phim.getQuocGia());
              stmt.setString(8, phim.getQuocGia());
              System.out.println("Trạng thái "+ phim.getTrangThai());
	  	      stmt.setString(9, phim.getTrangThai());
	  	      System.out.println("Ngày bắt đầu"+ phim.getNgayBatDau());
	  	      stmt.setDate(10, java.sql.Date.valueOf(phim.getNgayBatDau()));
	  	      System.out.println("Giá thầu"+ phim.getGiaThau());
	  	      stmt.setDouble(11, phim.getGiaThau());
	  	      System.out.println("Hình ảnh" + phim.getAnh());
	  	      stmt.setString(12, phim.getAnh());
	  	      System.out.println("Đoạn giới thiệu "+ phim.getDoanPhimGioiThieu());
	  	      stmt.setString(13, phim.getDoanPhimGioiThieu());
	  	      System.out.println("Tóm Tắt: "+ phim.getTomTat());
	  	      stmt.setString(14, phim.getTomTat());
	  	      
	  	  System.out.println("Độ dài Tên Phim: " + phim.getTenPhim().length());
	  	  System.out.println("Độ dài Thể loại: " + phim.getTheLoai().length());
	  	  System.out.println("Độ dài Đạo diễn: " + phim.getDaoDien().length());
	  	  System.out.println("Độ dài Ngôn ngữ: " + phim.getNgonNgu().length());
	  	  System.out.println("Độ dài Quốc gia: " + phim.getQuocGia().length());
	  	  System.out.println("Độ dài Hình ảnh: " + phim.getAnh().length());
	  	  System.out.println("Độ dài Đoạn giới thiệu: " + phim.getDoanPhimGioiThieu().length());
	  	  System.out.println("Độ dài Tóm tắt: " + phim.getTomTat().length());

	  	  
	  	      n = stmt.executeUpdate();
	  	      return n > 0;
    	 } catch (Exception e) {
    		 
    		  e.printStackTrace();
              return false;
		}finally {
        	System.out.println("End PhimDAO - thêm(Phim)");
        }   
              
              
    }
    
    
    
    	 private void setPhimParameters(PreparedStatement stmt, Phim phim) throws SQLException {
    	        stmt.setString(1, phim.getTenPhim());
    	        stmt.setString(2, phim.getTheLoai());
    	        stmt.setString(3, phim.getDaoDien());
    	        stmt.setInt(4, phim.getThoiLuong());
    	        stmt.setDate(5, java.sql.Date.valueOf(phim.getNgayCongChieu()));
    	        stmt.setString(6, phim.getNgonNgu());
    	        stmt.setString(7, phim.getQuocGia());
    	        stmt.setString(8, phim.getTrangThai());
    	        stmt.setDate(9, java.sql.Date.valueOf(phim.getNgayBatDau()));
    	        stmt.setDouble(10, phim.getGiaThau());
    	        stmt.setString(11, phim.getAnh());
    	        stmt.setString(12, phim.getDoanPhimGioiThieu());
    	        stmt.setString(13, phim.getTomTat());
    	    }
    
    
    	 public boolean xoaPhim(String maPhim) {
    		 try {
    				if (ConnectDB.getConnection().isClosed()) {
    	                // Mở lại kết nối
    	                ConnectDB.getInstance().connect();
    	            }
    				Connection con = ConnectDB.getConnection();
    				 String sql = "DELETE FROM Phim WHERE MaPhim = ?";
    			    PreparedStatement state = con.prepareStatement(sql);
    			    state.setString(1, maPhim);
    			
    			    int n = state.executeUpdate(); // Thực thi lệnh xóa và lưu số dòng bị ảnh hưởng vào 
    			    return n > 0; // Trả về true nếu xóa thành công (n > 0)
    	     
    	        } catch (SQLException e) {
    	        	e.printStackTrace();
    				return false;
    	        }finally {
    				System.out.println("End PhimDAO - xoaPhim(Phim)");
    			}
    	    }
    	//kiểm tra sự tồn tại của mã phim trong cơ sở dữ liệu
    	    public boolean exists(String maPhim) {
    	      

    	        try {    		 
    	        		if (ConnectDB.getConnection().isClosed()) {
    	            // Mở lại kết nối
    	            ConnectDB.getInstance().connect();
    	        		}
    	        	  
    	              Connection con = ConnectDB.getConnection();
    	              PreparedStatement stmt = null;
    	              ResultSet rs = null;
    	              boolean exists = false;
    	        	
    	            String query = "SELECT COUNT(*) FROM Phim WHERE maPhim = ?";
    	            stmt = con.prepareStatement(query);
    	            stmt.setString(1, maPhim);
    	            rs = stmt.executeQuery();
    	            if (rs.next()) {
    	                int count = rs.getInt(1);
    	                exists = (count > 0);
    	            }
    	            return exists;
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	            return false;
    	        } finally {
    	        	 System.out.println("End Phim_DAO - exits");
    	        }

    	        
    	    }
    
    
    	    public boolean capNhatPhim(Phim phim) {
    	    	try {
    	        	if (ConnectDB.getConnection().isClosed()) {
    	                // Mở lại kết nối
    	                ConnectDB.getInstance().connect();
    	            }
    	   	        Connection con = ConnectDB.getConnection();
    	   	        PreparedStatement stmt = null;
    	   	        int n = 0;
    	   	     stmt = con.prepareStatement("UPDATE Phim SET "
    	        		+ "    TenPhim = ?,"
    	        		+ "    TheLoai = ?,"
    	        		+ "    DaoDien = ?,"
    	        		+ "    ThoiLuong = ?,"
    	        		+ "    NgayCongChieu = ?,"
    	        		+ "    NgonNgu = ?,"
    	        		+ "    QuocGia = ?,"
    	        		+ "    TrangThai = ?,"
    	        		+ "    NgayBatDau = ?,"
    	        		+ "    GiaThau = ?,"
    	        		+ "    Anh = ?,"
    	        		+ "    DoanPhimGioiThieu = ?,"
    	        		+ "    TomTat = ? "
    	        		+ "WHERE MaPhim = ?");

    	   	  System.out.println("Mã Phim" + phim.getMaPhim());
              stmt.setString(14, phim.getMaPhim());
              System.out.println("Tên Phim" + phim.getTenPhim());
              stmt.setString(1, phim.getTenPhim());
              System.out.println("Thể loại" + phim.getTheLoai());
              stmt.setString(2, phim.getTheLoai());
              System.out.println("Đạo diễn" + phim.getDaoDien());
              stmt.setString(3, phim.getDaoDien());
              System.out.println("Thời lượng " + phim.getThoiLuong());
              stmt.setInt(4, phim.getThoiLuong());
              System.out.println("Ngày công chiếu" + phim.getNgayCongChieu());
              stmt.setDate(5, java.sql.Date.valueOf(phim.getNgayCongChieu()));
              System.out.println("Ngôn ngữ" + phim.getNgonNgu());
              stmt.setString(6, phim.getNgonNgu());
              System.out.println("quốc gia" + phim.getQuocGia());
              stmt.setString(7, phim.getQuocGia());
              System.out.println("Trạng thái "+ phim.getTrangThai());
	  	      stmt.setString(8, phim.getTrangThai());
	  	      System.out.println("Ngày bắt đầu"+ phim.getNgayBatDau());
	  	      stmt.setDate(9, java.sql.Date.valueOf(phim.getNgayBatDau()));
	  	      System.out.println("Giá thầu"+ phim.getGiaThau());
	  	      stmt.setDouble(10, phim.getGiaThau());
	  	      System.out.println("Hình ảnh" + phim.getAnh());
	  	      stmt.setString(11, phim.getAnh());
	  	      System.out.println("Đoạn giới thiệu "+ phim.getDoanPhimGioiThieu());
	  	      stmt.setString(12, phim.getDoanPhimGioiThieu());
	  	      System.out.println("Tóm Tắt: "+ phim.getTomTat());
	  	      stmt.setString(13, phim.getTomTat());
	  	      n = stmt.executeUpdate();
	  	      return n > 0;
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	            return false;
    	        } finally {
//   	        	 System.out.println("End Phim_DAO - capNhatPhim");
 	            
    	        }
    	    }
    
    
    

    public ArrayList<Phim> findPhimByTenPhim(String tenPhim) {
        ArrayList<Phim> dsPhim = new ArrayList<Phim>();
        try {
        	
       	 // Kiểm tra kết nối
           if (ConnectDB.getConnection().isClosed()) {
               // Mở lại kết nối
               ConnectDB.getInstance().connect();
           }
           Connection con = ConnectDB.getConnection();
           PreparedStatement statement = null;
        String sql = "SELECT * FROM Phim WHERE TenPhim LIKE ?";
        statement = con.prepareStatement(sql);
        // Sử dụng dấu % để tìm kiếm tất cả các phim có tên chứa từ khóa tìm kiếm
            statement.setString(1, "%" + tenPhim + "%");
            ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Phim phim = extractPhimFromResultSet(rs);
                    dsPhim.add(phim);
                }
                return dsPhim;
          
        } catch (SQLException e) {
        	e.printStackTrace();
            return null;
        }finally {
       	 System.out.println("End PhimDAO - timPhimTheoTen");
		}

    }

//    public Phim getPhimByMa(String maPhim) {
//        String sql = "SELECT * FROM Phim WHERE MaPhim = ?";
//        Phim phim = null;
//
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, maPhim);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                phim = extractPhimFromResultSet(rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }        
//        return phim;
//    }
    public Phim getPhimByMa(String maPhim) {
        Phim phim = new Phim(); // Khởi tạo đối tượng phim
        Connection con = null; // Khai báo kết nối

        try {
            // Kiểm tra và mở lại kết nối nếu cần
            con = ConnectDB.getConnection();
            if (con.isClosed()) {
                ConnectDB.getInstance().connect();
                con = ConnectDB.getConnection();
            }

            String sql = "SELECT * FROM Phim WHERE MaPhim = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, maPhim);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    phim = extractPhimFromResultSet(rs);
                } else {
                    // Nếu không tìm thấy phim, có thể trả về một đối tượng Phim với thông báo hoặc giá trị mặc định
                    phim.setMaPhim(maPhim); // Có thể lưu mã phim vào đối tượng
                    // Hoặc có thể xử lý theo cách khác nếu không tìm thấy
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối nếu cần
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End PhimDAO - getPhimByMa");
        }
        return phim; // Trả về đối tượng phim
    }

//    public ArrayList<Phim> getAllPhim() {
//        ArrayList<Phim> dsPhim = new ArrayList<>();
//        String sql = "SELECT * FROM Phim";
//
//        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                Phim phim = extractPhimFromResultSet(rs);
//                dsPhim.add(phim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }        
//        return dsPhim;
//    }
/*
    public boolean isMaPhimExists(String maPhim) {
        String query = "SELECT COUNT(*) FROM Phim WHERE MaPhim = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, maPhim);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
*/ 
//    public String getNextMaPhim() {
//        String sql = "SELECT NEXT VALUE FOR PhimSequence";
//        try (PreparedStatement pstmt = connection.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//            if (rs.next()) {
//                int nextValue = rs.getInt(1);
//                return "P" + String.format("%03d", nextValue);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public String getNextMaPhim() {
        Connection con = null;
        String maPhim = null;

        try {
            // Kiểm tra và mở lại kết nối nếu cần
            con = ConnectDB.getConnection();
            if (con == null || con.isClosed()) {
                ConnectDB.getInstance().connect();
                con = ConnectDB.getConnection();
            }

            String sql = "SELECT NEXT VALUE FOR PhimSequence";
            try (PreparedStatement pstmt = con.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int nextValue = rs.getInt(1);
                    maPhim = "P" + String.format("%03d", nextValue);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối nếu cần
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("End PhimDAO - getNextMaPhim");
        }
        
        return maPhim;
    }

//    private void setPhimParameters(PreparedStatement stmt, Phim phim) throws SQLException {
//        stmt.setString(1, phim.getTenPhim());
//        stmt.setString(2, phim.getTheLoai());
//        stmt.setString(3, phim.getDaoDien());
//        stmt.setInt(4, phim.getThoiLuong());
//        stmt.setDate(5, java.sql.Date.valueOf(phim.getNgayCongChieu()));
//        stmt.setString(6, phim.getNgonNgu());
//        stmt.setString(7, phim.getQuocGia());
//        stmt.setString(8, phim.getTrangThai());
//        stmt.setDate(9, java.sql.Date.valueOf(phim.getNgayBatDau()));
//        stmt.setDouble(10, phim.getGiaThau());
//        stmt.setString(11, phim.getAnh());
//        stmt.setString(12, phim.getDoanPhimGioiThieu());
//        stmt.setString(13, phim.getTomTat());
//    }

    private Phim extractPhimFromResultSet(ResultSet rs) throws SQLException {
        return new Phim(
            rs.getString("MaPhim"),
            rs.getString("TenPhim"),
            rs.getString("TheLoai"),
            rs.getString("DaoDien"),
            rs.getInt("ThoiLuong"),
            rs.getDate("NgayCongChieu").toLocalDate(),
            rs.getString("NgonNgu"),
            rs.getString("QuocGia"),
            rs.getString("TrangThai"),
            rs.getDate("NgayBatDau").toLocalDate(),
            rs.getDouble("GiaThau"),
            rs.getString("Anh"),
            rs.getString("DoanPhimGioiThieu"),
            rs.getString("TomTat")
        );
    }
}