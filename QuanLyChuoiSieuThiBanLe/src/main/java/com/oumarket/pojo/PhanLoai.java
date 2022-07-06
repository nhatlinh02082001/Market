/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.pojo;

/**
 *
 * @author anhtuan
 */
public class PhanLoai {
    private int maLoai;
    private String tenLoai;

    public PhanLoai() {
    }

    public PhanLoai(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    @Override
    public String toString() {
        return this.tenLoai;
    }
    
    /**
     * @return the maLoai
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
     * @return the tenLoai
     */
    public String getTenLoai() {
        return tenLoai;
    }

    /**
     * @param tenLoai the tenLoai to set
     */
    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
    
}
