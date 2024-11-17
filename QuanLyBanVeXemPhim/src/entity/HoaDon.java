package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HoaDon {

	private String maHoaDon;
	private LocalDateTime ngayDat;
	private int soGhe;
	private String ghiChu;
	private KhachHang kh;
	private NhanVien nv;
	private KhuyenMai km;
	private double VAT;
	private double tongTien;
	


	public HoaDon(String maHoaDon, LocalDateTime ngayDat, int soGhe, String ghiChu, KhachHang kh, NhanVien nv,
			KhuyenMai km, double vAT, double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayDat = ngayDat;
		this.soGhe = soGhe;
		this.ghiChu = ghiChu;
		this.kh = kh;
		this.nv = nv;
		this.km = km;
		VAT = vAT;
		this.tongTien = tongTien;
	}
	
	

	public HoaDon(LocalDateTime ngayDat, int soGhe, String ghiChu, KhachHang kh, NhanVien nv, KhuyenMai km,
			double vAT) {
		super();
		this.ngayDat = ngayDat;
		this.soGhe = soGhe;
		this.ghiChu = ghiChu;
		this.kh = kh;
		this.nv = nv;
		this.km = km;
		VAT = vAT;
	}



	public HoaDon() {
		super();
	}
	

	/**
	 * @param maHoaDon the maHoaDon to set
	 */
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDateTime getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(LocalDateTime ngayDat) {
		this.ngayDat = ngayDat;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	/**
	 * @return the km
	 */
	public KhuyenMai getKm() {
		return km;
	}



	/**
	 * @param km the km to set
	 */
	public void setKm(KhuyenMai km) {
		this.km = km;
	}



	/**
	 * @return the vAT
	 */
	public double getVAT() {
		return VAT;
	}



	/**
	 * @param vAT the vAT to set
	 */
	public void setVAT(double vAT) {
		VAT = vAT;
	}



	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayDat=" + ngayDat + ", soGhe=" + soGhe + ", ghiChu=" + ghiChu
				+ ", kh=" + kh + ", nv=" + nv + "]";
	}
	
	public void setTongTien(ArrayList<ChiTietHoaDon> dschiTietHD, ArrayList<Ghe> dsVe, LichChieu lichChieu) {
		double tongTien = 0;
		for (ChiTietHoaDon chiTietHD : dschiTietHD) {
			tongTien += chiTietHD.getTongTienSP();
		}
		for (Ghe ghe : dsVe) {
			if(ghe.getLoai().getTenLoaiGhe().equals("Ghế đôi Sweetbox")) {
				tongTien += lichChieu.getGiaMotGhe()*2;
        	}
        	if(ghe.getLoai().getTenLoaiGhe().equals("Ghế VIP")) {
        		tongTien += lichChieu.getGiaMotGhe()*1.5;
        	}
        	if(ghe.getLoai().getTenLoaiGhe().equals("Ghế thường")) {
        		tongTien += lichChieu.getGiaMotGhe();
        	}
		}
		double phanTram = 0;
		if (km != null) {
			phanTram = km.getPhanTramKhuyenMai();
		}
		this.tongTien = (tongTien + tongTien*VAT)*(1-phanTram);
	}

	public double getTongTien() {
		return tongTien;
	}
	
}
