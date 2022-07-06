/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.pojo;

import java.sql.Date;
/**
 *
 * @author anhtuan
 */
public class HoaDon {
    private String maHoaDon;
    private String maNV;
    private String maKH;
    private Date ngayHD;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, String maNV, String maKH, Date ngayHD) {
        this.maHoaDon = maHoaDon;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayHD = ngayHD;
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
     * @return the maNV
     */
    public String getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the maKH
     */
    public String getMaKH() {
        return maKH;
    }

    /**
     * @param maKH the maKH to set
     */
    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    /**
     * @return the ngayHD
     */
    public Date getNgayHD() {
        return ngayHD;
    }

    /**
     * @param ngayHD the ngayHD to set
     */
    public void setNgayHD(Date ngayHD) {
        this.ngayHD = ngayHD;
    }
}
