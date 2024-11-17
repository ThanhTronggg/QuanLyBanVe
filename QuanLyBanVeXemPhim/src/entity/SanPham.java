package entity;

import java.util.Objects;

public class SanPham {
	
	private String maSanPham;
	private String tenSanPham;
	private int soLuong;
	private double giaMua;
	private String loaiSanPham;
	private String anh;
	private double giaBan;
	
	public SanPham(String maSanPham, String tenSanPham, int soLuong, double giaMua, String loaiSanPham, String anh) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaMua = giaMua;
		this.loaiSanPham = loaiSanPham;
		this.anh = anh;
		dinhGiaBan();
	}
	
	

	public SanPham(String tenSanPham, int soLuong, double giaMua, String loaiSanPham, String anh) {
		super();
		this.tenSanPham = tenSanPham;
		this.soLuong = soLuong;
		this.giaMua = giaMua;
		this.loaiSanPham = loaiSanPham;
		this.anh = anh;
		this.giaBan = giaBan;
	}



	public SanPham() {
		super();
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGiaMua() {
		return giaMua;
	}

	public void setGiaMua(double giaMua) {
		this.giaMua = giaMua;
	}

	public String getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(String loaiSanPham) {
		this.loaiSanPham = loaiSanPham;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void dinhGiaBan() {
		this.giaBan = giaMua*2;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}

	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", soLuong=" + soLuong + ", giaMua="
				+ giaMua + ", loaiSanPham=" + loaiSanPham + ", anh=" + anh + "]";
	}
}
