package gui.app.phim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import connectDB.ConnectDB;
import dao.PhimDAO;
import entity.Phim;
import net.miginfocom.swing.MigLayout;

public class QuanLyPhimGUI extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    private JLabel lblTitle;
    private JTextField txtTim;
    private JButton btnThem;
    private JButton btnSua;
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> cboLoaiHienThi;
    private JButton btnXoa;
    private PhimDAO phim_dao;

    public QuanLyPhimGUI() {
    	// Kết nối đến cơ sở dữ liệu
        ConnectDB.getInstance().connect();
        
        // Lấy kết nối từ ConnectDB
        Connection connection = ConnectDB.getConnection();
        
        if (connection != null) {
            phim_dao = new PhimDAO(); // Khởi tạo DAO với kết nối
        } else {
            System.err.println("Không thể kết nối đến cơ sở dữ liệu.");
            return; // Dừng lại nếu không có kết nối
        }
       

        setLayout(new BorderLayout());

        // Panel bao gồm lblTitle và pnlTimKiem
        JPanel pnlNor = new JPanel();
        pnlNor.setLayout(new BorderLayout());

        // Tiêu đề
        lblTitle = new JLabel("THÔNG TIN PHIM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        pnlNor.add(lblTitle, BorderLayout.NORTH);

        // Panel tìm kiếm và loại hiển thị
        JPanel pnlTimKiem = new JPanel();
        pnlTimKiem.setLayout(new MigLayout("", "[][]push[]", ""));

        txtTim = new JTextField();
        txtTim.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm theo tên phim");
        txtTim.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("gui/icon/svg/search.svg", 0.35f));
        pnlTimKiem.add(txtTim, "w 200!");

        cboLoaiHienThi = new JComboBox<>(new String[]{ "Phim đang chiếu", "Phim chưa chiếu", "Toàn bộ"});
        pnlTimKiem.add(cboLoaiHienThi);

        // Thêm panel tìm kiếm vào dưới tiêu đề
        pnlNor.add(pnlTimKiem, BorderLayout.SOUTH);
        add(pnlNor, BorderLayout.NORTH);

        // Bảng hiển thị phim
        String[] header = {"Mã phim", "Tên phim", "Trạng Thái", "Thời lượng [phút]"};
        tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
     // Add MouseListener for row click
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the movie code (maPhim) from the selected row
                    String maPhim = (String) tableModel.getValueAt(selectedRow, 0);
                    
                    // Retrieve the movie details from the DAO
                    Phim selectedPhim = phim_dao.getPhimByMa(maPhim);

                    // Show the movie details in a dialog
                    ChiTietPhimDialog dialog = new ChiTietPhimDialog(selectedPhim);
                    dialog.setModal(true);
                    dialog.setVisible(true);
                }
            }
        });

        table.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
        

        // Panel chứa các nút chức năng
        JPanel pnlChucNang = new JPanel(new MigLayout("insets 0, align right", "[][][]", ""));
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        btnThem = new JButton("Thêm mới");
        btnThem.setIcon(new FlatSVGIcon("gui/icon/svg/add.svg", 20, 20));
        btnThem.setBackground(new Color(9, 87, 208));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFont(buttonFont);
        pnlChucNang.add(btnThem, "gapright 15");

        btnSua = new JButton("Cập nhật");
        btnSua.setIcon(new FlatSVGIcon("gui/icon/svg/update.svg", 20, 20));
        btnSua.setBackground(new Color(237, 203, 37));
        btnSua.setForeground(Color.WHITE);
        btnSua.setFont(buttonFont);
        pnlChucNang.add(btnSua, "gapright 15");

        btnXoa = new JButton("Xóa");
        btnXoa.setIcon(new FlatSVGIcon("gui/icon/svg/delete.svg", 15, 18));
        btnXoa.setBackground(new Color(255, 0, 0));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setFont(buttonFont);
        pnlChucNang.add(btnXoa);

        // Thêm pnlChucNang vào phía dưới cùng của giao diện
        add(pnlChucNang, BorderLayout.SOUTH);

        // Gắn sự kiện
        cboLoaiHienThi.addActionListener(this);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);

        // Hiển thị dữ liệu
        hienThi();

        // Lọc khi nhập tìm kiếm
        txtTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) { hienThi(); }
            @Override
            public void insertUpdate(DocumentEvent e) { hienThi(); }
            @Override
            public void changedUpdate(DocumentEvent e) { hienThi(); }
        });
   

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(cboLoaiHienThi)) {
            hienThi();
        } else if (o.equals(btnThem)) {
            them();
        } else if (o.equals(btnSua)) {
            sua();
        } else if (o.equals(btnXoa)) {
           xoa();
        }
    }

    private void hienThi() {
        String selectedOption = cboLoaiHienThi.getSelectedItem().toString(); // Lấy lựa chọn từ ComboBox
        docData(selectedOption); // Gọi docData với tham số là trạng thái chọn
    }

    public void docData(String option) {
        String textTimKiem = txtTim.getText();

        // Xóa tất cả hàng hiện tại trong bảng
        tableModel.setRowCount(0);

        // Lấy dữ liệu từ DAO và cập nhật bảng
        ArrayList<Phim> list = phim_dao.getAllPhim(); // Lấy tất cả phim từ DAO

        for (Phim phim : list) {
            // Điều kiện lọc phim theo trạng thái
            boolean matches = false;

            // Lọc theo trạng thái phim
            if (option.equals("Phim đang chiếu") && phim.getTrangThai().equals("Đã phát hành")) {
                matches = true;
            } else if (option.equals("Phim chưa chiếu") && phim.getTrangThai().equals("Chưa phát hành")) {
                matches = true;
            } else if (option.equals("Toàn bộ")) {
                matches = true;
            }

            // Nếu phim thỏa mãn điều kiện tìm kiếm và trạng thái, thêm vào bảng
            if (matches && (phim.getMaPhim().contains(textTimKiem) || phim.getTenPhim().toLowerCase().contains(textTimKiem.toLowerCase()))) {
                tableModel.addRow(new String[]{
                    phim.getMaPhim(),
                    phim.getTenPhim(),
                    phim.getTrangThai(),
                    Integer.toString(phim.getThoiLuong())
                });
            }
        }
    }



    private void them() {
        ThemPhimDialog themPhim = new ThemPhimDialog();
        themPhim.setModal(true);
        themPhim.setVisible(true);
        Phim newPhim = themPhim.getPhim(); // Get the movie from the dialog
        if (newPhim != null) {
            if (phim_dao.exists(newPhim.getMaPhim())) {
               // JOptionPane.showMessageDialog(this, "Mã phim đã tồn tại. Vui lòng nhập mã khác!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean isSaved = phim_dao.themPhim(newPhim);
			if (isSaved) {
			    tableModel.addRow(new String[]{
			        newPhim.getMaPhim(),
			        newPhim.getTenPhim(),
			        newPhim.getTrangThai(),
			        Integer.toString(newPhim.getThoiLuong())
			    });
			    JOptionPane.showMessageDialog(this, "Thêm phim thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			} else {
			    JOptionPane.showMessageDialog(this, "Lỗi khi thêm phim vào cơ sở dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
        } else {
            JOptionPane.showMessageDialog(this, "Không có phim mới được thêm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    

    private void sua() {
        // Kiểm tra xem có hàng nào được chọn không
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phim để cập nhật!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin phim từ hàng đã chọn
        String maPhim = (String) tableModel.getValueAt(selectedRow, 0);
        Phim phimToUpdate = phim_dao.getPhimByMa(maPhim); // Lấy phim theo mã

        // Mở hộp thoại để cập nhật thông tin phim
        CapNhatPhimDialog capNhatPhimDialog = new CapNhatPhimDialog(phimToUpdate);
        capNhatPhimDialog.setModal(true);
        capNhatPhimDialog.setVisible(true);

        // Lấy phim đã cập nhật từ hộp thoại
        Phim updatedPhim = capNhatPhimDialog.getPhim();

        // Kiểm tra nếu updatedPhim không phải là null (người dùng không nhấn hủy)
        if (updatedPhim != null) {
            // Cập nhật lại dữ liệu trong cơ sở dữ liệu
            boolean isUpdated = phim_dao.capNhatPhim(updatedPhim);
            if (isUpdated) {
                // Cập nhật lại hàng trong bảng
                tableModel.setValueAt(updatedPhim.getTenPhim(), selectedRow, 1);
                tableModel.setValueAt(updatedPhim.getTrangThai(), selectedRow, 2);
                tableModel.setValueAt(updatedPhim.getThoiLuong(), selectedRow, 3);
               // JOptionPane.showMessageDialog(this, "Cập nhật phim thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật phim vào cơ sở dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Nếu updatedPhim là null, tức là người dùng đã hủy
            JOptionPane.showMessageDialog(this, "Không có thông tin phim nào được cập nhật!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    //Xóa_phim 
    private void xoa() {
        // Kiểm tra xem có hàng nào được chọn không
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phim để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy mã phim từ hàng đã chọn
        String maPhim = (String) tableModel.getValueAt(selectedRow, 0);

        // Xác nhận xóa
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa phim này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Gọi phương thức xóa phim trong DAO
            boolean isDeleted = phim_dao.xoaPhim(maPhim); 
            if (isDeleted) {
                // Xóa hàng trong bảng
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Xóa phim thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi xóa phim khỏi cơ sở dữ liệu!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
} 