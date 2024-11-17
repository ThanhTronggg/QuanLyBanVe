/*
 * @(#) KhuyenMaiGUI.java 1.0 Oct 31, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package gui.app.khuyenmai;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import dao.KhuyenMaiDAO;
import entity.KhuyenMai;
import net.miginfocom.swing.MigLayout;


/**
 * @description:
 * @author: Thanh Trong
 * @date: Oct 31, 2024
 * @version: 1.0
 */

public class QuanLyKhuyenMaiGUI extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtTim;
	private JButton btnThem;
	private JButton btnSua;
	private DefaultTableModel tableModel;
	private JTable table;
	private JComboBox<String> cboLoaiHienThi;
	private JButton btnXoa;
	private KhuyenMaiDAO km_dao;


	public QuanLyKhuyenMaiGUI() {
		
		setLayout(new BorderLayout());
		
		JPanel pnlTitle = new JPanel();
		JLabel lblTitle = new JLabel("THÔNG TIN KHUYẾN MÃI");
		lblTitle.setAlignmentX(CENTER_ALIGNMENT);
		lblTitle.setFont(new Font(lblTitle.getFont().getFontName(), 1, 40));
		pnlTitle.add(lblTitle);
		add(pnlTitle, BorderLayout.NORTH);
		
		JPanel pnlNor = new JPanel();
		pnlNor.setLayout(new BoxLayout(pnlNor, BoxLayout.Y_AXIS));
		add(pnlNor, BorderLayout.SOUTH);
		
		JPanel pnlRow1 = new JPanel();
		pnlRow1.setLayout(new MigLayout("", "[][]push[][]", ""));
		pnlNor.add(pnlRow1);
		
		txtTim = new JTextField();
		txtTim.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,  "Tìm theo mã");
		txtTim.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("gui/icon/svg/search.svg", 0.35f));
		pnlRow1.add(txtTim, "w 200!");
		cboLoaiHienThi = new JComboBox<String>();
		cboLoaiHienThi.addItem("5 khuyến mãi gần nhất");
		cboLoaiHienThi.addItem("Còn hiệu lực");
		cboLoaiHienThi.addItem("Hết hạn");
		cboLoaiHienThi.addItem("Toàn bộ");
		pnlRow1.add(cboLoaiHienThi);
		
		btnThem = new JButton("Thêm mới");
		btnThem.setIcon(new FlatSVGIcon("gui/icon/svg/add-khuyen-mai.svg", 20, 20));
		pnlRow1.add(btnThem);
		
		
		btnSua = new JButton("Sửa");
		FlatSVGIcon icon = new FlatSVGIcon("gui/icon/svg/update.svg", 20, 20);
		FlatSVGIcon.ColorFilter f = new FlatSVGIcon.ColorFilter(color -> Color.decode("#000"));
		icon.setColorFilter(f);
		btnSua.setIcon(icon);
		pnlRow1.add(btnSua);
//		pnlNor.add(Box.createVerticalStrut(30));
		
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new FlatSVGIcon("gui/icon/svg/delete.svg", 15, 18));
		pnlRow1.add(btnXoa);
		
		String []header = {"Mã khuyến mãi", "Tên khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc", "Tổng tiền tối thiểu", "Phần trăm khuyến mãi"};
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		table.setRowHeight(25);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(null);
		add(scroll, BorderLayout.CENTER);

		table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
		table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

		table.getTableHeader()
				.setDefaultRenderer(getAlignmentCellRender(table.getTableHeader().getDefaultRenderer(), true));
		table.setDefaultRenderer(Object.class,
				getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));
		
		cboLoaiHienThi.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		
		hienThi();
		
		txtTim.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				hienThi();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				hienThi();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				hienThi();
			}
		});
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(cboLoaiHienThi)) {
			hienThi();
		}
		if(o.equals(btnThem)) {
			them();
		}
		if(o.equals(btnXoa)) {
			xoa();
		}
		if(o.equals(btnSua)) {
			sua();
		}
		
	}

	private void sua() {
		int index = table.getSelectedRow();
		if (index == -1) {
			UIManager.put("OptionPane.yesButtonText", "Có");
	        UIManager.put("OptionPane.noButtonText", "Không");
	        UIManager.put("Button.background", Color.decode("#273167"));
	        UIManager.put("Button.foreground", Color.WHITE);
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi cần sửa!", "Thông báo", JOptionPane.OK_OPTION);
	        UIManager.put("Button.background", Color.white);
			UIManager.put("Button.foreground", Color.black);
	        return;	
		}
		if (index != -1) {
			String maKM = tableModel.getValueAt(index, 0).toString();
			KhuyenMai kmSua = km_dao.timKhuyenMai(maKM);
			SuaKhuyenMaiDialog suaKMDialog = new SuaKhuyenMaiDialog(kmSua);
			suaKMDialog.setModal(true);
			suaKMDialog.setVisible(true);
			suaKMDialog.setQuanLyKhuyenMaiGUI(this);
			hienThi();
		}
	}


	private void xoa() {
		int index = table.getSelectedRow();
		if (index == -1) {
			UIManager.put("OptionPane.yesButtonText", "Có");
	        UIManager.put("OptionPane.noButtonText", "Không");
	        UIManager.put("Button.background", Color.decode("#273167"));
	        UIManager.put("Button.foreground", Color.WHITE);
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn khuyến mãi cần xóa!", "Thông báo", JOptionPane.OK_OPTION);
	        UIManager.put("Button.background", Color.white);
			UIManager.put("Button.foreground", Color.black);
	        return;			
		}
		if (index != -1) {
			UIManager.put("OptionPane.yesButtonText", "Có");
	        UIManager.put("OptionPane.noButtonText", "Không");
	        UIManager.put("Button.background", Color.decode("#273167"));
	        UIManager.put("Button.foreground", Color.WHITE);
			if(JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				String maKM = tableModel.getValueAt(index, 0).toString();
				if(km_dao.xoaKhuyenMai(maKM)) {
					tableModel.removeRow(index);
					table.clearSelection();
					hienThi();
				}
			}
			UIManager.put("Button.background", Color.white);
			UIManager.put("Button.foreground", Color.black);
		}
	}


	private void them() {
		ThemKhuyenMaiDialog themKM = new ThemKhuyenMaiDialog();
		themKM.setModal(true);
		themKM.setVisible(true);
		themKM.setQuanLyKhuyenMaiGUI(this);
	}
	


	private void hienThi() {
		docData(cboLoaiHienThi.getSelectedItem().toString());
	}
	
	public void docData(String options) {
		String textTimKiem = txtTim.getText();
		km_dao = new KhuyenMaiDAO();
		List<KhuyenMai> list = null;
		if (options.equals("5 khuyến mãi gần nhất")) {
			list = km_dao.getNamKhuyenMaiSapHetHan(textTimKiem);
		}
		if (options.equals("Còn hiệu lực")) {
			list = km_dao.getKhuyenMaiConHan(textTimKiem);
		}
		if (options.equals("Hết hạn")) {
			list = km_dao.getKhuyenMaiHetHan(textTimKiem);
		}
		if (options.equals("Toàn bộ")) {
			list = km_dao.getTatCaKhuyenMai(textTimKiem);
		}
		tableModel.setRowCount(0);
		for (KhuyenMai obj: list) {
			String[] row = {obj.getMaKhuyenMai(), obj.getTenKhuyenMai(), obj.getNgayBatDau().toString(), obj.getNgayKetThuc().toString(), Double.toString(obj.getTongTienToiThieu()), Double.toString(obj.getPhanTramKhuyenMai() * 100) + "%"};
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}
	
	@SuppressWarnings("serial")
	private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
	    return new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
	                                                      boolean hasFocus, int row, int column) {
	            Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	             if (com instanceof JLabel) {
	                 JLabel label = (JLabel) com;
	                 label.setHorizontalAlignment(SwingConstants.CENTER);
	             }
	            return com;
	        }
	    };
	}

	
	
}
