package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class LichChieu {

	private String maLichChieu;
	private LocalDateTime gioBatDau;
	private LocalDateTime gioKetThuc;
	private double giaMotGhe;
	private Phong phong;
	private Phim phim;
	
	public LichChieu(String maLichChieu, LocalDateTime gioBatDau, LocalDateTime gioKetThuc, double giaMotGhe, Phong phong,
			Phim phim) {
		super();
		this.maLichChieu = maLichChieu;
		this.gioBatDau = gioBatDau;
		this.gioKetThuc = gioKetThuc;
		this.giaMotGhe = giaMotGhe;
		this.phong = phong;
		this.phim = phim;
	}

	public LichChieu(LocalDateTime gioBatDau, LocalDateTime gioKetThuc, double giaMotGhe, Phong phong, Phim phim) {
		super();
		this.gioBatDau = gioBatDau;
		this.gioKetThuc = gioKetThuc;
		this.giaMotGhe = giaMotGhe;
		this.phong = phong;
		this.phim = phim;
	}

	public LichChieu() {
		super();
	}

	public LocalDateTime getGioBatDau() {
		return gioBatDau;
	}

	public void setGioBatDau(LocalDateTime gioBatDau) {
		this.gioBatDau = gioBatDau;
	}

	public LocalDateTime getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(LocalDateTime gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public double getGiaMotGhe() {
		return giaMotGhe;
	}

	public void setGiaMotGhe(double giaMotGhe) {
		this.giaMotGhe = giaMotGhe;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public Phim getPhim() {
		return phim;
	}

	public void setPhim(Phim phim) {
		this.phim = phim;
	}

	public String getMaLichChieu() {
		return maLichChieu;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLichChieu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LichChieu other = (LichChieu) obj;
		return Objects.equals(maLichChieu, other.maLichChieu);
	}

	@Override
	public String toString() {
		return "LichChieu [maLichChieu=" + maLichChieu + ", gioBatDau=" + gioBatDau + ", gioKetThuc=" + gioKetThuc
				+ ", giaMotGhe=" + giaMotGhe + ", phong=" + phong + ", phim=" + phim + "]";
	}
}
