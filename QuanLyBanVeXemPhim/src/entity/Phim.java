package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Phim {
	
	private String maPhim;
	private String tenPhim;
	private String theLoai;
	private String daoDien;
	private int thoiLuong;
	private LocalDate ngayCongChieu;
	private String ngonNgu;
	private String quocGia;
	private String trangThai;
	private LocalDate ngayBatDau;
	private double giaThau;
	private String anh;
	private String doanPhimGioiThieu;
	private String tomTat;
	
	public Phim(String maPhim, String tenPhim, String theLoai, String daoDien, int thoiLuong, LocalDate ngayCongChieu,
			String ngonNgu, String quocGia, String trangThai, LocalDate ngayBatDau, double giaThau, String anh,
			String doanPhimGioiThieu, String tomTat) {
//		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.theLoai = theLoai;
		this.daoDien = daoDien;
		this.thoiLuong = thoiLuong;
		this.ngayCongChieu = ngayCongChieu;
		this.ngonNgu = ngonNgu;
		this.quocGia = quocGia;
		this.trangThai = trangThai;
		this.ngayBatDau = ngayBatDau;
		this.giaThau = giaThau;
		this.anh = anh;
		this.doanPhimGioiThieu = doanPhimGioiThieu;
		this.tomTat = tomTat;
	}
	
    public Phim(String tenPhim, String theLoai, int thoiLuong, String daoDien, String anhMinhHoa) {
        this.tenPhim = tenPhim;
        this.theLoai = theLoai;
        this.thoiLuong = thoiLuong;
        this.daoDien = daoDien;
        this.anh = anhMinhHoa;
    }

	public Phim() {
		super();
	}
	
	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public LocalDate getNgayCongChieu() {
		return ngayCongChieu;
	}

	public void setNgayCongChieu(LocalDate ngayCongChieu) {
		this.ngayCongChieu = ngayCongChieu;
	}

	public String getNgonNgu() {
		return ngonNgu;
	}

	public void setNgonNgu(String ngonNgu) {
		this.ngonNgu = ngonNgu;
	}

	public String getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public double getGiaThau() {
		return giaThau;
	}

	public void setGiaThau(double giaThau) {
		this.giaThau = giaThau;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getDoanPhimGioiThieu() {
		return doanPhimGioiThieu;
	}

	public void setDoanPhimGioiThieu(String doanPhimGioiThieu) {
		this.doanPhimGioiThieu = doanPhimGioiThieu;
	}

	public String getTomTat() {
		return tomTat;
	}

	public void setTomTat(String tomTat) {
		this.tomTat = tomTat;
	}

	public String getMaPhim() {
		return maPhim;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhim);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phim other = (Phim) obj;
		return Objects.equals(maPhim, other.maPhim);
	}

	@Override
	public String toString() {
		return tenPhim;
	}
}
