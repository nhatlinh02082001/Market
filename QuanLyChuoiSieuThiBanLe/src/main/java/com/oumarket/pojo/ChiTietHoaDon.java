/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.pojo;

/**
 *
 * @author anhtuan
 */
public class ChiTietHoaDon {
    private String maHoaDon;
    private String maHang;
    private int soLuong;
    private float thanhTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maHoaDon, String maHang, int soLuong, float thanhTien) {
        this.maHoaDon = maHoaDon;
        this.maHang = maHang;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }
    
    

    /**
     * @return the maHoaDon
     */
    public String getMaHoaDon() {
        return maHoaDon;
    }

    /**
     * @param maHoaDon the maHoaDon to set
     */
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    /**
     * @return the maHang
     */
    public String getMaHang() {
        return maHang;
    }

    /**
     * @param maHang the maHang to set
     */
    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the thanhTien
     */
    public float getThanhTien() {
        return thanhTien;
    }

    /**
     * @param thanhTien the thanhTien to set
     */
    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }
}
