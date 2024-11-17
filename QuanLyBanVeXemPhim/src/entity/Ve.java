package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Ve {

	private String maVe;
	private LocalDate ngayPhatHanh;
	private Ghe ghe;
	private LichChieu lc;
	private HoaDon hd;
	


	public Ve(String maVe, LocalDate ngayPhatHanh, Ghe ghe, LichChieu lc, HoaDon hd) {
		super();
		this.maVe = maVe;
		this.ngayPhatHanh = ngayPhatHanh;
		this.ghe = ghe;
		this.lc = lc;
		this.hd = hd;
	}



	/**
	 * @param ngayPhatHanh the ngayPhatHanh to set
	 */
	public void setNgayPhatHanh(LocalDate ngayPhatHanh) {
		this.ngayPhatHanh = ngayPhatHanh;
	}



	public Ve() {
		super();
	}

	

	/**
	 * @param maVe the maVe to set
	 */
	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}



	public Ghe getGhe() {
		return ghe;
	}


	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}


	public LichChieu getLc() {
		return lc;
	}


	public void setLc(LichChieu lc) {
		this.lc = lc;
	}


	public HoaDon getHd() {
		return hd;
	}


	public void setHd(HoaDon hd) {
		this.hd = hd;
	}


	public String getMaVe() {
		return maVe;
	}


	public LocalDate getNgayPhatHanh() {
		return ngayPhatHanh;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maVe);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ve other = (Ve) obj;
		return Objects.equals(maVe, other.maVe);
	}


	@Override
	public String toString() {
		return "Ve [maVe=" + maVe + ", ngayPhatHanh=" + ngayPhatHanh + ", ghe=" + ghe + ", lc=" + lc + ", hd=" + hd
				+ "]";
	}
	
	
	
	
}
