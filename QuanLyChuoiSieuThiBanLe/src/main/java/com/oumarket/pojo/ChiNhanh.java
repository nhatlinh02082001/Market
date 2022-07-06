/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.pojo;

/**
 *
 * @author anhtuan
 */
public class ChiNhanh {
    private String maChiNhanh;
    private String soNha;
    private String duong;
    private String phuong;
    private String quan;
    private String thanhPho;

    public ChiNhanh() {
    }

    public ChiNhanh(String maChiNhanh, String soNha, String duong, String phuong, String quan, String thanhPho) {
        this.maChiNhanh = maChiNhanh;
        this.soNha = soNha;
        this.duong = duong;
        this.phuong = phuong;
        this.quan = quan;
        this.thanhPho = thanhPho;
    }

    @Override
    public String toString() {
        return this.thanhPho;
    }

    /**
     * @return the maChiNhanh
     */
    public String getMaChiNhanh() {
        return maChiNhanh;
    }

    /**
     * @param maChiNhanh the maChiNhanh to set
     */
    public void setMaChiNhanh(String maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

    /**
     * @return the soNha
     */
    public String getSoNha() {
        return soNha;
    }

    /**
     * @param soNha the soNha to set
     */
    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    /**
     * @return the duong
     */
    public String getDuong() {
        return duong;
    }

    /**
     * @param duong the duong to set
     */
    public void setDuong(String duong) {
        this.duong = duong;
    }

    /**
     * @return the phuong
     */
    public String getPhuong() {
        return phuong;
    }

    /**
     * @param phuong the phuong to set
     */
    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    /**
     * @return the quan
     */
    public String getQuan() {
        return quan;
    }

    /**
     * @param quan the quan to set
     */
    public void setQuan(String quan) {
        this.quan = quan;
    }

    /**
     * @return the thanhPho
     */
    public String getThanhPho() {
        return thanhPho;
    }

    /**
     * @param thanhPho the thanhPho to set
     */
    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }
}
