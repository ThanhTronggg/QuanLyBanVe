package entity;

import java.util.Objects;

public class Phong {

	private String maPhong;
	private String tenPhong;
	private int soLuongGhe;
	
	public Phong(String maPhong, String tenPhong, int soLuongGhe) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.soLuongGhe = soLuongGhe;
	}

	public Phong() {
		super();
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public int getSoLuongGhe() {
		return soLuongGhe;
	}

	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}

	public String getMaPhong() {
		return maPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

	@Override
	public String toString() {
		return tenPhong;
	}
}
