package gui.app.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;

import entity.NhanVien;
import gui.app.Main;
import gui.app.hoso.DoiMatKhau;
import gui.app.hoso.HoSoForm;
import gui.app.khachhang.QuanLyKhachHangGUI;
import gui.app.khuyenmai.QuanLyKhuyenMaiGUI;
import gui.app.lichchieu.QuanLyLichChieuGUI;
import gui.app.nhanvien.NhanVienGUI;
import gui.app.phim.QuanLyPhimGUI;
import gui.app.sanpham.doan.QuanLyDoAnGUI;
import gui.app.sanpham.nuocuong.QuanLyNuocUongGUI;
import gui.app.thongke.ThongKeDoanhThu;
import gui.app.thongke.ThongKeKhachHang;
import gui.app.thongke.ThongKePhim;
import gui.app.thongke.ThongKeSanPham;
//import gui.application.form.other.customer.FormCustomerManagement;
//import gui.application.form.other.movie.FormMovieManagement;
//import gui.application.form.other.product.FormDrinkManagement;
//import gui.application.form.other.product.FormFoodManagement;
//import gui.application.form.other.profile.FormChangePassword;
//import gui.application.form.other.profile.FormProfileInfo;
//import gui.application.form.other.screening.FormScreeningManagement;
//import gui.application.form.other.staff.FormStaffManagement;
//import gui.application.form.other.statistics.FormStatisticsCustomer;
//import gui.application.form.other.statistics.FormStatisticsGeneral;
//import gui.application.form.other.statistics.FormStatisticsMovie;
//import gui.application.form.other.statistics.FormStatisticsProduct;
import gui.menu.Menu;
import gui.menu.MenuAction;

public class MainForm extends JLayeredPane {
	private static final long serialVersionUID = 1L;
	private Menu menu;
	private JPanel panelBody;
	private JButton menuButton;

	public MainForm(NhanVien employee) {
		init(employee);
	}

	private void init(NhanVien employee) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new MainFormLayout());
		menu = new Menu(employee.getVaiTro());
		panelBody = new JPanel(new BorderLayout());
		initMenuArrowIcon();
		menuButton.putClientProperty(FlatClientProperties.STYLE,
				"" + "background:$Menu.button.background;" + "arc:999;" + "focusWidth:0;" + "borderWidth:0");
		menuButton.addActionListener((ActionEvent e) -> {
			setMenuFull(!menu.isMenuFull());
		});
		initMenuEvent(employee);
		setLayer(menuButton, JLayeredPane.POPUP_LAYER);
		add(menuButton);
		add(menu);
		add(panelBody);
	}

	@Override
	public void applyComponentOrientation(ComponentOrientation o) {
		super.applyComponentOrientation(o);
		initMenuArrowIcon();
	}

	private void initMenuArrowIcon() {
		if (menuButton == null) {
			menuButton = new JButton();
		}
		String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
		menuButton.setIcon(new FlatSVGIcon("gui/icon/svg/" + icon, 0.8f));
	}

	private void initMenuEvent(NhanVien employee) {
		menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
			if (employee.getVaiTro().equalsIgnoreCase("Quản lý")) {
				switch (index) {
					case 0:
						Main.showMainForm(new QuanLyPhimGUI());
						break;
					case 1:
						Main.showMainForm(new QuanLyLichChieuGUI(employee));
						// System.out.println(employee);
						break;
					case 2:
						Main.showMainForm(new NhanVienGUI(employee));
						break;
					case 3:
						Main.showMainForm(new QuanLyKhachHangGUI());
						break;
					case 4:
						switch (subIndex) {
							case 1:
								Main.showMainForm(new QuanLyDoAnGUI());
								break;
							case 2:
								Main.showMainForm(new QuanLyNuocUongGUI());
								break;
							default:
								action.cancel();
								break;
						}
						break;
					case 5:
						Main.showMainForm(new QuanLyKhuyenMaiGUI());
						break;
					case 6:
						switch (subIndex) {
							case 1:
								Main.showMainForm(new ThongKeDoanhThu());
								break;
							case 2:
								Main.showMainForm(new ThongKeKhachHang());
								break;
							case 3:
								Main.showMainForm(new ThongKePhim());
								break;
							case 4:
								Main.showMainForm(new ThongKeSanPham());
								break;
							default:
								action.cancel();
								break;
						}
						break;
					case 7:
						switch (subIndex) {
							case 1:
								 Main.showMainForm(new HoSoForm(employee));
								break;
							case 2:
								Main.showMainForm(new DoiMatKhau(employee));
								break;
							default:
								action.cancel();
								break;
						}
						break;
					case 8:
						 Main.logout();
						break;
					default:
						action.cancel();
						break;
				}
			} else {
				switch (index) {
					case 0:
						Main.showMainForm(new QuanLyPhimGUI());
						break;
					case 1:
						Main.showMainForm(new QuanLyLichChieuGUI(employee));
						break;
					case 3:
						Main.showMainForm(new QuanLyKhachHangGUI());
						break;
					case 4:
						switch (subIndex) {
							case 1:
								Main.showMainForm(new QuanLyDoAnGUI());
								break;
							case 2:
								Main.showMainForm(new QuanLyNuocUongGUI());
								break;
							default:
								action.cancel();
								break;
						}
						break;
					case 5:
						Main.showMainForm(new QuanLyKhuyenMaiGUI());
						break;
					case 6:
						switch (subIndex) {
							case 1:
								Main.showMainForm(new ThongKeDoanhThu());
								break;
							case 2:
								Main.showMainForm(new ThongKeKhachHang());
								break;
							case 3:
								Main.showMainForm(new ThongKePhim());
								break;
							case 4:
								Main.showMainForm(new ThongKeSanPham());
								break;
							default:
								action.cancel();
								break;
						}
						break;
					case 7:
						switch (subIndex) {
							case 1:
								Main.showMainForm(new HoSoForm(employee));
								break;
							case 2:
								Main.showMainForm(new DoiMatKhau(employee));
								break;
							default:
								action.cancel();
								break;
						}
						break;
					case 8:
						 Main.logout();
						break;
					default:
						action.cancel();
						break;
				}
			}
		});

	}

	private void setMenuFull(boolean full) {
		String icon;
		if (getComponentOrientation().isLeftToRight()) {
			icon = (full) ? "menu_left.svg" : "menu_right.svg";
		} else {
			icon = (full) ? "menu_right.svg" : "menu_left.svg";
		}
		menuButton.setIcon(new FlatSVGIcon("gui/icon/svg/" + icon, 0.8f));
		menu.setMenuFull(full);
		revalidate();
	}

	public void hideMenu() {
		menu.hideMenuItem();
	}

	public void showForm(Component component) {
		panelBody.removeAll();
		panelBody.add(component);
		panelBody.repaint();
		panelBody.revalidate();
	}

	public void setSelectedMenu(int index, int subIndex) {
		menu.setSelectedMenu(index, subIndex);
	}

	private class MainFormLayout implements LayoutManager {

		@Override
		public void addLayoutComponent(String name, Component comp) {
		}

		@Override
		public void removeLayoutComponent(Component comp) {
		}

		@Override
		public Dimension preferredLayoutSize(Container parent) {
			synchronized (parent.getTreeLock()) {
				return new Dimension(5, 5);
			}
		}

		@Override
		public Dimension minimumLayoutSize(Container parent) {
			synchronized (parent.getTreeLock()) {
				return new Dimension(0, 0);
			}
		}

		@Override
		public void layoutContainer(Container parent) {
			synchronized (parent.getTreeLock()) {
				boolean ltr = parent.getComponentOrientation().isLeftToRight();
				Insets insets = UIScale.scale(parent.getInsets());
				int x = insets.left;
				int y = insets.top;
				int width = parent.getWidth() - (insets.left + insets.right);
				int height = parent.getHeight() - (insets.top + insets.bottom);
				int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
				int menuX = ltr ? x : x + width - menuWidth;
				menu.setBounds(menuX, y, menuWidth, height);
				int menuButtonWidth = menuButton.getPreferredSize().width;
				int menuButtonHeight = menuButton.getPreferredSize().height;
				int menubX;
				if (ltr) {
					menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
				} else {
					menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
				}
				menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
				int gap = UIScale.scale(5);
				int bodyWidth = width - menuWidth - gap;
				int bodyHeight = height;
				int bodyx = ltr ? (x + menuWidth + gap) : x;
				int bodyy = y;
				panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
			}
		}
	}
}
