/*
 * @(#) KhachHangThongKe.java 1.0 Nov 15, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package entity;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 15, 2024
 * @version: 1.0
 */

public class KhachHangThongKe {
	
	private String maKhachHang;
	private String tenKhachHang;
	private double tongTien;
	
	public KhachHangThongKe(String maKhachHang, String tenKhachHang, double tongTien) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.tongTien = tongTien;
	}

	public KhachHangThongKe() {
		super();
	}

	/**
	 * @return the tenKhachHang
	 */
	public String getTenKhachHang() {
		return tenKhachHang;
	}

	/**
	 * @param tenKhachHang the tenKhachHang to set
	 */
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	/**
	 * @return the tongTien
	 */
	public double getTongTien() {
		return tongTien;
	}

	/**
	 * @param tongTien the tongTien to set
	 */
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	/**
	 * @return the maKhachHang
	 */
	public String getMaKhachHang() {
		return maKhachHang;
	}

	@Override
	public String toString() {
		return "KhachHangThongKe [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", tongTien="
				+ tongTien + "]";
	}
	
}
