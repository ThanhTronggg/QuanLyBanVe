package entity;

import java.util.Objects;

public class TaiKhoan {
	private String ID;
	private String taiKhoan;
	private String matKhau;
	
	public TaiKhoan(String iD, String taiKhoan, String matKhau) {
		super();
		ID = iD;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}

	public TaiKhoan(String taiKhoan, String matKhau) {
		super();
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}

	public TaiKhoan() {
		super();
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getID() {
		return ID;
	}
	
	

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "TaiKhoan [ID=" + ID + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(ID, other.ID);
	}
}
