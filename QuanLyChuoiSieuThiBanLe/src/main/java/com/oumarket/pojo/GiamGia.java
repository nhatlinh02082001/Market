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
public class GiamGia {
    private int maGiamGia;
    private String noiDung;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public GiamGia() {
    }

    public GiamGia(int maGiamGia, String noiDung, Date ngayBatDau, Date ngayKetThuc) {
        this.maGiamGia = maGiamGia;
        this.noiDung = noiDung;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    @Override
    public String toString() {
        return this.noiDung;
    }
    
    /**
     * @return the maGiamGia
     */
    public int getMaGiamGia() {
        return maGiamGia;
    }

    /**
     * @param maGiamGia the maGiamGia to set
     */
    public void setMaGiamGia(int maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    /**
     * @return the noiDung
     */
    public String getNoiDung() {
        return noiDung;
    }

    /**
     * @param noiDung the noiDung to set
     */
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    /**
     * @return the ngayBatDau
     */
    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    /**
     * @param ngayBatDau the ngayBatDau to set
     */
    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    /**
     * @return the ngayKetThuc
     */
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    /**
     * @param ngayKetThuc the ngayKetThuc to set
     */
    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}
