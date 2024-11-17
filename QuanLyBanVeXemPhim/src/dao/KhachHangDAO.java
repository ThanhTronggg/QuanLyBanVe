package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jfree.data.category.DefaultCategoryDataset;

import connectDB.ConnectDB;
import entity.KhachHang;


public class KhachHangDAO {
	ArrayList<KhachHang> dsKhachHang;
	
	private ArrayList<KhachHang> listKhachHang;

    public KhachHangDAO() {
        listKhachHang = new ArrayList<>();
    }

    public ArrayList<KhachHang> getAllKhachHang() {
        ArrayList<KhachHang> listKhachHang = new ArrayList<KhachHang>();
        try {
            if (ConnectDB.getConnection().isClosed()) {
                // Mở lại kết nối
                ConnectDB.getInstance().connect();
            }
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhachHang";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    String maKhachHang = rs.getString("maKhachHang");
                    String tenKhachHang = rs.getString("tenKhachHang");
                    String soDienThoai = rs.getString("soDienThoai");
                    String email = rs.getString("email");

                    KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, soDienThoai, email);
                    listKhachHang.add(khachHang);
                
            }
            return listKhachHang;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("END KhachHangDAO - getAllKhachHang");
        }
    }
    //Tạo khách hàng mới 
    public boolean createKH(KhachHang khachHang) {
        boolean result = false;
        String sql = "INSERT INTO KhachHang VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) { 
            stmt.setString(1, khachHang.getMaKhachHang());
            stmt.setString(2, khachHang.getTenKhachHang());
            stmt.setString(3, khachHang.getSoDienThoai());
            stmt.setString(4, khachHang.getEmail());
            int n = stmt.executeUpdate();
            result = n > 0;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
        	System.out.println("End KhachHangDAO - createKH");
		}
        
    
    }

    // Tìm khách hàng theo số điện thoại
    public ArrayList<KhachHang> timKiemKhachHangTheoSDT(String soDienThoai) throws SQLException {
        ArrayList<KhachHang> listKhachHang = new ArrayList<>();
        try {
            if (ConnectDB.getConnection().isClosed()) {
                // Mở lại kết nối
                ConnectDB.getInstance().connect();
            }
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM KhachHang WHERE soDienThoai LIKE ?";
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setString(1, "%" + soDienThoai + "%");
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        String maKhachHang = rs.getString("maKhachHang");
                        String tenKhachHang = rs.getString("tenKhachHang");
                        String soDienThoaiResult = rs.getString("soDienThoai");
                        String email = rs.getString("email");

                        KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, soDienThoaiResult, email);
                        listKhachHang.add(khachHang);
                    }
                }
            }
            return listKhachHang;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            System.out.println("End KhachHangDAO - tìm theo số điện thoại khách hàng");
        }
    }

    public boolean updateKhachHang(KhachHang khachHang) {
        try {
            if (ConnectDB.getConnection().isClosed()) {
                // Mở lại kết nối
                ConnectDB.getInstance().connect();
            }
            Connection con = ConnectDB.getConnection();
            String sql = "UPDATE KhachHang SET TenKhachHang = ?, SoDienThoai = ?, Email = ? WHERE MaKhachHang = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, khachHang.getTenKhachHang());
                stmt.setString(2, khachHang.getSoDienThoai());
                stmt.setString(3, khachHang.getEmail());
                stmt.setString(4, khachHang.getMaKhachHang());

                int n = stmt.executeUpdate();
                return n > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("End KhachHangDAO - capNhat");
        }
    }

    public boolean deleteKhachHang(String maKhachHang) throws SQLException {
        try {
            if (ConnectDB.getConnection().isClosed()) {
                ConnectDB.getInstance().connect();
            }
            Connection con = ConnectDB.getConnection();

            // Kiểm tra xem khách hàng có hóa đơn liên quan không
            String checkSql = "SELECT COUNT(*) FROM HoaDon WHERE MaKhachHang = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, maKhachHang);
            ResultSet checkRs = checkStmt.executeQuery();
            
            if (checkRs.next() && checkRs.getInt(1) > 0) {
                // Hiển thị thông báo lỗi lên màn hình cho nhân viên
                JOptionPane.showMessageDialog(null, 
                    "Không thể xóa khách hàng này vì khách hàng này đang có hóa đơn.", 
                    "Lỗi", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }

            // Nếu không có hóa đơn liên quan, thực hiện xóa khách hàng
            String sql = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, maKhachHang);
            
            int n = state.executeUpdate();
            return n > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            System.out.println("End KhachHangDAO - deleteKhachHang");
        }
    }

	
	public String themKhachHangMoi(String soDienThoai, String tenKhachHang, String email) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    String maKhachHang = null;
	    try {
	        PreparedStatement preparedStatement = con.prepareStatement(
	        		"INSERT INTO KhachHang (TenKhachHang, SoDienThoai, Email) " +
	        		"OUTPUT inserted.MaKhachHang " +
	        	    "VALUES (?, ?, ?)"
	        );
	        
	        preparedStatement.setString(1, tenKhachHang);
	        preparedStatement.setString(2, soDienThoai);
	        preparedStatement.setString(3, email);
	        
	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            maKhachHang = rs.getString(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return maKhachHang;
	}

	
	public String capNhatTenVaEmailKhachHangTheoSoDienThoai(String soDienThoai, String tenKhachHangMoi, String emailMoi) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    String maKhachHang = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "UPDATE KhachHang " +
	            "SET TenKhachHang = ?, Email = ? " +
	            "OUTPUT inserted.MaKhachHang " +
	            "WHERE SoDienThoai = ?"
	        );

	        preparedStatement.setString(1, tenKhachHangMoi);
	        preparedStatement.setString(2, emailMoi);
	        preparedStatement.setString(3, soDienThoai);

	        ResultSet rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            maKhachHang = rs.getString(1);
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
	    return maKhachHang;
	}

	
	public boolean kiemTraSoDienThoaiTonTai(String soDienThoai) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "SELECT 1 " +
	            "FROM KhachHang " +
	            "WHERE SoDienThoai = ?"
	        );

	        preparedStatement.setString(1, soDienThoai);
	        rs = preparedStatement.executeQuery();
	        return rs.next();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public KhachHang timKhachHangTheoSoDienThoai(String soDienThoai) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "select *\r\n" +
	            "from KhachHang\r\n" +
	            "where SoDienThoai = ?");

	        preparedStatement.setString(1, soDienThoai);
	        rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            String maKhachHang = rs.getString("MaKhachHang");
	            String tenKhachHang = rs.getString("TenKhachHang");
	            String soDienThoai1 = rs.getString("SoDienThoai");
	            String email = rs.getString("Email");

	            KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, soDienThoai1, email);
	            return khachHang;
	        }
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public KhachHang timKhachHangTheoMa(String maKhachHang) {
	    ConnectDB.getInstance().connect();
	    Connection con = ConnectDB.getConnection();
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        preparedStatement = con.prepareStatement(
	            "select *\r\n" +
	            "from KhachHang\r\n" +
	            "where MaKhachHang = ?");

	        preparedStatement.setString(1, maKhachHang);
	        rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	            String maKhachHang1 = rs.getString("MaKhachHang");
	            String tenKhachHang = rs.getString("TenKhachHang");
	            String soDienThoai = rs.getString("SoDienThoai");
	            String email = rs.getString("Email");

	            KhachHang khachHang = new KhachHang(maKhachHang1, tenKhachHang, soDienThoai, email);
	            return khachHang;
	        }
	        return null;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	    finally {
	    	try {
				if (rs != null) {
					rs.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
