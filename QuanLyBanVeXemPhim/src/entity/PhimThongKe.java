/*
 * @(#) PhimThongKe.java 1.0 Nov 7, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package entity;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 7, 2024
 * @version: 1.0
 */

public class PhimThongKe {
	private String maPhim;
	private String tenPhim;
	private int soLuotXem;
	private double tongTienThuDuoc;
	
	public PhimThongKe(String maPhim, String tenPhim, int soLuotXem, double tongTienThuDuoc) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.soLuotXem = soLuotXem;
		this.tongTienThuDuoc = tongTienThuDuoc;
	}

	public PhimThongKe() {
		super();
	}

	/**
	 * @return the tenPhim
	 */
	public String getTenPhim() {
		return tenPhim;
	}

	/**
	 * @param tenPhim the tenPhim to set
	 */
	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	/**
	 * @return the soLuotXem
	 */
	public int getSoLuotXem() {
		return soLuotXem;
	}

	/**
	 * @param soLuotXem the soLuotXem to set
	 */
	public void setSoLuotXem(int soLuotXem) {
		this.soLuotXem = soLuotXem;
	}

	/**
	 * @return the tongTienThuDuoc
	 */
	public double getTongTienThuDuoc() {
		return tongTienThuDuoc;
	}

	/**
	 * @param tongTienThuDuoc the tongTienThuDuoc to set
	 */
	public void setTongTienThuDuoc(double tongTienThuDuoc) {
		this.tongTienThuDuoc = tongTienThuDuoc;
	}

	/**
	 * @return the maPhim
	 */
	public String getMaPhim() {
		return maPhim;
	}

	@Override
	public String toString() {
		return "PhimThongKe [maPhim=" + maPhim + ", tenPhim=" + tenPhim + ", soLuotXem=" + soLuotXem
				+ ", tongTienThuDuoc=" + tongTienThuDuoc + "]";
	}
	
}
