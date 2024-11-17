package gui.app;

import java.awt.Component;
import java.awt.Font;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import entity.NhanVien;
import gui.app.form.LoginForm;
import gui.app.form.MainForm;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Main app;
	private final LoginForm loginForm;
	private MainForm mainForm;

	private Main() {
		initComponents();
		loginForm = new LoginForm();
		setContentPane(loginForm);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		app = this;
		
	}

	public static Main getInstance() {
		return app;
	}

	public MainForm getMainForm() {
		return mainForm;
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void createMainForm(NhanVien employee) {
		mainForm = new MainForm(employee);
	}

	public static void showMainForm(Component component) {
		component.applyComponentOrientation(app.getComponentOrientation());
		app.mainForm.showForm(component);
	}

	public static void setSelectedMenu(int index, int subIndex) {
		app.mainForm.setSelectedMenu(index, subIndex);
	}

	public static void logout() {
		app.loginForm.resetLogin();
		FlatAnimatedLafChange.showSnapshot();
		app.setContentPane(app.loginForm);
		app.loginForm.applyComponentOrientation(app.getComponentOrientation());
		SwingUtilities.updateComponentTreeUI(app.loginForm);
		FlatAnimatedLafChange.hideSnapshotWithAnimation();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(false);
		setResizable(false);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 719, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 521, Short.MAX_VALUE));

		pack();
	}

	public static void main(String args[]) {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("gui.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 16));
		FlatMacLightLaf.setup();
//		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
		SwingUtilities.invokeLater(() -> {
	        // Tạo instance của Main
	        Main mainApp = new Main();
	        mainApp.setVisible(false); // Không hiển thị JFrame chính ngay lúc đầu

	        // Hiển thị màn hình splash thông qua LoginForm
	        LoginForm.getInstance().showSplashScreen(() -> {
	            // Khi splash kết thúc, hiển thị JFrame chính
	            mainApp.setVisible(true);
	            mainApp.setContentPane(mainApp.getLoginForm());
	            mainApp.revalidate();
	            mainApp.repaint();
	        });
	    });
	}

}
