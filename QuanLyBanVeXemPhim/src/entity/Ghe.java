package entity;

import java.util.Objects;

public class Ghe {
	
	private String maGhe;
	private String viTri;
	private Phong phong;
	private LoaiGhe loai;
	
	public Ghe(String maGhe, String viTri, Phong phong, LoaiGhe loai) {
		super();
		this.maGhe = maGhe;
		this.viTri = viTri;
		this.phong = phong;
		this.loai = loai;
	}

	public Ghe() {
		super();
	}

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public LoaiGhe getLoai() {
		return loai;
	}

	public void setLoai(LoaiGhe loai) {
		this.loai = loai;
	}

	public String getMaGhe() {
		return maGhe;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maGhe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ghe other = (Ghe) obj;
		return Objects.equals(maGhe, other.maGhe);
	}

	@Override
	public String toString() {
		return "Ghe [maGhe=" + maGhe + ", viTri=" + viTri + ", phong=" + phong + ", loai=" + loai + "]";
	}
}
