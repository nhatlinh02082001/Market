/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.services;

import com.oumarket.pojo.HangHoa;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhtuan
 */
public class HangHoaServices {
    public boolean themHangHoa(HangHoa h) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO hanghoa(MaHang, TenHang, SoLuong, DonGia, NguonGoc, MaLoai, MaGiamGia) VALUES(?, ?, ?, ?, ?, ?, ?)");
            stm.setString(1, h.getMaHang());
            stm.setString(2, h.getTenHang());
            stm.setInt(3, h.getSoLuong());
            stm.setFloat(4, h.getDonGia());
            stm.setString(5, h.getNguonGoc());
            stm.setInt(6, h.getMaLoai());
            stm.setInt(7, h.getMaGiamGia());
            
            return stm.executeUpdate() > 0;
        }
    }
    
    public boolean deleteHangHoa(String maHang) throws SQLException {
       try (Connection conn = JdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM hanghoa WHERE MaHang=?");
           stm.setString(1, maHang);
           
           return stm.executeUpdate() > 0;
       }
    }
    
    public boolean editHangHoa(HangHoa h) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("UPDATE hanghoa SET "+"TenHang=?,"+"SoLuong=?,"+"DonGia=?,"+"NguonGoc=?,"+"MaLoai=?,"+"MaGiamGia=? WHERE MaHang=?");
            stm.setString(1, h.getTenHang());
            stm.setInt(2, h.getSoLuong());
            stm.setFloat(3, h.getDonGia());
            stm.setString(4, h.getNguonGoc());
            stm.setInt(5, h.getMaLoai());
            stm.setInt(6, h.getMaGiamGia());
            stm.setString(7, h.getMaHang());
            
            return stm.executeUpdate() > 0;
        }
    }
    
    public List<HangHoa> getHangHoas(String kw) throws SQLException {
       try (Connection conn = JdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa WHERE TenHang like concat('%', ?, '%', '%', '%', '%', '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<HangHoa> hanghoas = new ArrayList<>();
           
           while (rs.next()) {
               String maHang = rs.getString("maHang");
               String tenHang = rs.getString("tenHang");
               int soLuong = rs.getInt("soLuong");
               float donGia = rs.getFloat("donGia");
               String nguonGoc = rs.getString("nguonGoc");
               int maLoai = rs.getInt("maLoai");
               int maGiamGia = rs.getInt("maGiamGia");
               hanghoas.add(new HangHoa(maHang, tenHang, soLuong, donGia, nguonGoc, maLoai, maGiamGia));
           }
           
           return hanghoas;
       }
    }
    
    public List<HangHoa> getHangHoasById(String kw) throws SQLException {
       try (Connection conn = JdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa WHERE MaHang like concat(?, '%', '%', '%', '%', '%', '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<HangHoa> hanghoas = new ArrayList<>();
           
           while (rs.next()) {
               String maHang = rs.getString("maHang");
               String tenHang = rs.getString("tenHang");
               int soLuong = rs.getInt("soLuong");
               float donGia = rs.getFloat("donGia");
               String nguonGoc = rs.getString("nguonGoc");
               int maLoai = rs.getInt("maLoai");
               int maGiamGia = rs.getInt("maGiamGia");
               hanghoas.add(new HangHoa(maHang, tenHang, soLuong, donGia, nguonGoc, maLoai, maGiamGia));
           }
           
           return hanghoas;
       }
    }
    
    public HangHoa getHangHoaById(String maHang) throws SQLException {
       try (Connection conn = JdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM hanghoa WHERE MaHang=?");
           stm.setString(1, maHang);
           
           ResultSet rs = stm.executeQuery();
           
           HangHoa h = null;
           if (rs.next()) {
               h = new HangHoa();
               h.setMaHang(rs.getString("maHang"));
               h.setTenHang(rs.getString("tenHang"));
               h.setSoLuong(rs.getInt("soLuong"));
               h.setDonGia(rs.getFloat("donGia"));
               h.setNguonGoc(rs.getString("nguonGoc"));
               h.setMaLoai(rs.getInt("maLoai"));
               h.setMaGiamGia(rs.getInt("maGiamGia"));
           }
           
           return h;
       }
    }
}
