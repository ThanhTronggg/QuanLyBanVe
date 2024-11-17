/*
 * @(#) SanPhamThongKe.java 1.0 Nov 6, 2024
 * Copyright (c) 2024 IUH.
 * All rights reserved.
 */
package entity;

/**
 * @description:
 * @author: Thanh Trong
 * @date: Nov 6, 2024
 * @version: 1.0
 */

public class SanPhamThongKe {
    private String maSanPham;
    private String tenSanPham;
    private int soLuongDaBan;
    private double tongTienBanDuoc;

    public SanPhamThongKe(String maSanPham, String tenSanPham, int soLuongDaBan, double tongTienBanDuoc) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuongDaBan = soLuongDaBan;
        this.tongTienBanDuoc = tongTienBanDuoc;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuongDaBan() {
        return soLuongDaBan;
    }

    public void setSoLuongDaBan(int soLuongDaBan) {
        this.soLuongDaBan = soLuongDaBan;
    }

    public double getTongTienBanDuoc() {
        return tongTienBanDuoc;
    }

    public void setTongTienBanDuoc(double tongTienBanDuoc) {
        this.tongTienBanDuoc = tongTienBanDuoc;
    }

    @Override
    public String toString() {
        return "SanPhamThongKe{" +
                "maSanPham='" + maSanPham + '\'' +
                ", tenSanPham='" + tenSanPham + '\'' +
                ", soLuongDaBan=" + soLuongDaBan +
                ", tongTienBanDuoc=" + tongTienBanDuoc +
                '}';
    }
}

