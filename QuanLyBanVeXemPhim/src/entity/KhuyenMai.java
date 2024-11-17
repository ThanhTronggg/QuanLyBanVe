/*
 * @(#) KhuyenMai.java 1.0 Nov 1, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 1, 2024
 * @version: 1.0
 */

public class KhuyenMai {
	private String maKhuyenMai;
	private String tenKhuyenMai;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private double tongTienToiThieu;
	private double phanTramKhuyenMai;
	
	/**
	 * @return the tenKhuyenMai
	 */
	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	/**
	 * @param tenKhuyenMai the tenKhuyenMai to set
	 */
	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	/**
	 * @return the ngayBatDau
	 */
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	/**
	 * @param ngayBatDau the ngayBatDau to set
	 */
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	/**
	 * @return the ngayKetThuc
	 */
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	/**
	 * @param ngayKetThuc the ngayKetThuc to set
	 */
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	/**
	 * @return the tongTienToiThieu
	 */
	public double getTongTienToiThieu() {
		return tongTienToiThieu;
	}

	/**
	 * @param tongTienToiThieu the tongTienToiThieu to set
	 */
	public void setTongTienToiThieu(double tongTienToiThieu) {
		this.tongTienToiThieu = tongTienToiThieu;
	}

	/**
	 * @return the phanTramKhuyenMai
	 */
	public double getPhanTramKhuyenMai() {
		return phanTramKhuyenMai;
	}

	/**
	 * @param phanTramKhuyenMai the phanTramKhuyenMai to set
	 */
	public void setPhanTramKhuyenMai(double phanTramKhuyenMai) {
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}

	/**
	 * @return the maKhuyenMai
	 */
	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhuyenMai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(maKhuyenMai, other.maKhuyenMai);
	}

	public KhuyenMai() {
		super();
	}


	public KhuyenMai(String maKhuyenMai, String tenKhuyenMai, LocalDate ngayBatDau, LocalDate ngayKetThuc,
			double tongTienToiThieu, double phanTramKhuyenMai) {
		super();
		this.maKhuyenMai = maKhuyenMai;
		this.tenKhuyenMai = tenKhuyenMai;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.tongTienToiThieu = tongTienToiThieu;
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}

	public KhuyenMai(String tenKhuyenMai, LocalDate ngayBatDau, LocalDate ngayKetThuc, double tongTienToiThieu,
			double phanTramKhuyenMai) {
		super();
		this.tenKhuyenMai = tenKhuyenMai;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.tongTienToiThieu = tongTienToiThieu;
		this.phanTramKhuyenMai = phanTramKhuyenMai;
	}

	@Override
	public String toString() {
		return "KhuyenMai [maKhuyenMai=" + maKhuyenMai + ", tenKhuyenMai=" + tenKhuyenMai + ", ngayBatDau=" + ngayBatDau
				+ ", ngayKetThuc=" + ngayKetThuc + ", tongTienToiThieu=" + tongTienToiThieu + ", phanTramKhuyenMai="
				+ phanTramKhuyenMai + "]";
	}
	
}
