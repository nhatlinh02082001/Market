/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.pojo;

/**
 *
 * @author anhtuan
 */
public class HangHoa {
    private String maHang;
    private String tenHang;
    private int soLuong;
    private float donGia;
    private String nguonGoc;
    private int maLoai;
    private int maGiamGia;
    private int selectedCount;

    public HangHoa() {
    }

    public HangHoa(String maHang, String tenHang, int soLuong, float donGia, String nguonGoc, int maLoai, int maGiamGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.nguonGoc = nguonGoc;
        this.maLoai = maLoai;
        this.maGiamGia = maGiamGia;
        this.selectedCount = 1;
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
     * @return the tenHang
     */
    public String getTenHang() {
        return tenHang;
    }

    /**
     * @param tenHang the tenHang to set
     */
    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
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
     * @return the donGia
     */
    public float getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    /**
     * @return the nguonGoc
     */
    public String getNguonGoc() {
        return nguonGoc;
    }

    /**
     * @param nguonGoc the nguonGoc to set
     */
    public void setNguonGoc(String nguonGoc) {
        this.nguonGoc = nguonGoc;
    }

    /**
     * @return the phanLoai
     */
    public int getMaLoai() {
        return maLoai;
    }

    /**
     * @param maLoai the maLoai to set
     */
    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
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
     * @param selectedCount the selectedCount to set
     */
    public void setSelectedCountHang(int selectedCount) {
        this.selectedCount = selectedCount;
    }

    /**
     * @return the SelectedCount
     */
    public int getSelectedCount() {
        return this.selectedCount;
    }
}
