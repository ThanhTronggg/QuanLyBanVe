/*
 * @(#) DoanhThu.java 1.0 Nov 7, 2024
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

public class DoanhThu {
	private double tongTienBanSanPham;
	private double tongTienBanVe;
	private double tongDoanhThu;
	
	public DoanhThu(double tongTienBanSanPham, double tongTienBanVe, double tongDoanhThu) {
		super();
		this.tongTienBanSanPham = tongTienBanSanPham;
		this.tongTienBanVe = tongTienBanVe;
		this.tongDoanhThu = tongDoanhThu;
	}

	/**
	 * @return the tongTienBanSanPham
	 */
	public double getTongTienBanSanPham() {
		return tongTienBanSanPham;
	}

	/**
	 * @param tongTienBanSanPham the tongTienBanSanPham to set
	 */
	public void setTongTienBanSanPham(double tongTienBanSanPham) {
		this.tongTienBanSanPham = tongTienBanSanPham;
	}

	/**
	 * @return the tongTienBanVe
	 */
	public double getTongTienBanVe() {
		return tongTienBanVe;
	}

	/**
	 * @param tongTienBanVe the tongTienBanVe to set
	 */
	public void setTongTienBanVe(double tongTienBanVe) {
		this.tongTienBanVe = tongTienBanVe;
	}

	/**
	 * @return the tongDoanhThu
	 */
	public double getTongDoanhThu() {
		return tongDoanhThu;
	}

	/**
	 * @param tongDoanhThu the tongDoanhThu to set
	 */
	public void setTongDoanhThu(double tongDoanhThu) {
		this.tongDoanhThu = tongDoanhThu;
	}

	public DoanhThu() {
		super();
	}

	@Override
	public String toString() {
		return "DoanhThu [tongTienBanSanPham=" + tongTienBanSanPham + ", tongTienBanVe=" + tongTienBanVe
				+ ", tongDoanhThu=" + tongDoanhThu + "]";
	}
	
	
}
