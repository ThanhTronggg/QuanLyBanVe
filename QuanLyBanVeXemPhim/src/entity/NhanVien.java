package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String hoTen;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private String email;
	private String soDienThoai;
	private String vaiTro;
	private LocalDate ngayBatDauLam;
	private double luong;
	private TaiKhoan tk;
	private String trangThai;

	// Constructor đầy đủ tham số
	public NhanVien(String maNhanVien, String hoTen, boolean gioiTinh, LocalDate ngaySinh, String email,
			String soDienThoai, String vaiTro, LocalDate ngayBatDauLam, double luong, TaiKhoan tk, String trangThai) {
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.vaiTro = vaiTro;
		this.ngayBatDauLam = ngayBatDauLam;
		this.luong = luong;
		this.tk = tk;
		this.trangThai = trangThai;
	}

	// Constructor không có mã nhân viên, dùng khi mã nhân viên tự động tạo
	public NhanVien(String hoTen, boolean gioiTinh, LocalDate ngaySinh, String email, String soDienThoai, String vaiTro,
			LocalDate ngayBatDauLam, double luong, TaiKhoan tk, String trangThai) {
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.vaiTro = vaiTro;
		this.ngayBatDauLam = ngayBatDauLam;
		this.luong = luong;
		this.tk = tk;
		this.trangThai = trangThai;
	}

	// Constructor rỗng
	public NhanVien() {
	}

	// Constructor tối giản với các thuộc tính cơ bản
	public NhanVien(String maNhanVien, String hoTen, boolean gioiTinh, String soDienThoai, String email,
			String vaiTro, String trangThai) {
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.vaiTro = vaiTro;
		this.trangThai = trangThai;
	}

	// Getters và Setters cho tất cả các thuộc tính
	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	public LocalDate getNgayBatDauLam() {
		return ngayBatDauLam;
	}

	public void setNgayBatDauLam(LocalDate ngayBatDauLam) {
		this.ngayBatDauLam = ngayBatDauLam;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public TaiKhoan getTk() {
		return tk;
	}

	public void setTk(TaiKhoan tk) {
		this.tk = tk;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	// Override phương thức toString
	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", ngaySinh="
				+ ngaySinh + ", email=" + email + ", soDienThoai=" + soDienThoai + ", vaiTro=" + vaiTro
				+ ", ngayBatDauLam=" + ngayBatDauLam + ", luong=" + luong + ", tk=" + tk + ", trangThai=" + trangThai
				+ "]";
	}

	// Override phương thức hashCode và equals
	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
}
