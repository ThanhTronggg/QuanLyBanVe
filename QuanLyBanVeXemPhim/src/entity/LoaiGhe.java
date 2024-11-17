package entity;

import java.util.Objects;

public class LoaiGhe {

	private String maLoaiGhe;
	private String tenLoaiGhe;
	private String moTaLoaiGhe;
	
	public LoaiGhe(String maLoaiGhe, String tenLoaiGhe, String moTaLoaiGhe) {
		super();
		this.maLoaiGhe = maLoaiGhe;
		this.tenLoaiGhe = tenLoaiGhe;
		this.moTaLoaiGhe = moTaLoaiGhe;
	}

	public LoaiGhe() {
		super();
	}

	public String getTenLoaiGhe() {
		return tenLoaiGhe;
	}

	public void setTenLoaiGhe(String tenLoaiGhe) {
		this.tenLoaiGhe = tenLoaiGhe;
	}

	public String getMoTaLoaiGhe() {
		return moTaLoaiGhe;
	}

	public void setMoTaLoaiGhe(String moTaLoaiGhe) {
		this.moTaLoaiGhe = moTaLoaiGhe;
	}

	public String getMaLoaiGhe() {
		return maLoaiGhe;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiGhe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiGhe other = (LoaiGhe) obj;
		return Objects.equals(maLoaiGhe, other.maLoaiGhe);
	}

	@Override
	public String toString() {
		return "LoaiGhe [maLoaiGhe=" + maLoaiGhe + ", tenLoaiGhe=" + tenLoaiGhe + ", moTaLoaiGhe=" + moTaLoaiGhe + "]";
	}
	
	
}
