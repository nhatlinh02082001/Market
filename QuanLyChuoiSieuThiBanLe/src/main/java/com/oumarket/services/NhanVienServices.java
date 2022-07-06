/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.services;

import com.oumarket.pojo.NhanVien;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhtuan
 */
public class NhanVienServices {
    public boolean themNhanVien(NhanVien n) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO nhanvien(MaNV, TenNV, NamSinh, SDT, Email, GioiTinh, DiaChi) VALUES(?, ?, ?, ?, ?, ?, ?)");
            stm.setString(1, n.getMaNV());
            stm.setString(2, n.getTenNV());
            stm.setDate(3, n.getNamSinh());
            stm.setString(4, n.getSdt());
            stm.setString(5, n.getEmail());
            stm.setString(6, n.getGioiTinh());
            stm.setString(7, n.getDiaChi());
            
            return stm.executeUpdate() > 0;
        }
    }
    
    public boolean deleteNhanVien(String maNV) throws SQLException {
       try (Connection conn = JdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("DELETE FROM nhanvien WHERE MaNV=?");
           stm.setString(1, maNV);
           
           return stm.executeUpdate() > 0;
       }
    }
    
    public boolean editNhanVien(NhanVien n) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("UPDATE nhanvien SET "+"TenNV=?,"+"NamSinh=?,"+"SDT=?,"+"Email=?,"+"GioiTinh=?,"+"DiaChi=? WHERE MaNV=?");
            stm.setString(1, n.getTenNV());
            stm.setDate(2, n.getNamSinh());
            stm.setString(3, n.getSdt());
            stm.setString(4, n.getEmail());
            stm.setString(5, n.getGioiTinh());
            stm.setString(6, n.getDiaChi());
            stm.setString(7, n.getMaNV());
            
            return stm.executeUpdate() > 0;
        }
    }
    
    public List<NhanVien> getNhanViens(String kw) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM nhanvien WHERE TenNV like concat('%', ?, '%', '%', '%', '%', '%')");
        
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<NhanVien> nhanviens = new ArrayList<>();
           
           while (rs.next()) {
               String maNV = rs.getString("maNV");
               String tenNV = rs.getString("tenNV");
               Date namSinh = rs.getDate("namSinh");
               String sdt = rs.getString("sdt");
               String email = rs.getString("email");
               String gioiTinh = rs.getString("gioiTinh");
               String diaChi = rs.getString("diaChi");
               
               nhanviens.add(new NhanVien(maNV, tenNV, namSinh, sdt, email, gioiTinh, diaChi));
           }
           return nhanviens;
        }
    }
    
    public List<NhanVien> getNhanVien() throws SQLException {
        List<NhanVien> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien");
            
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getDate("namSinh"), 
                                        rs.getString("sdt"), rs.getString("email"), rs.getString("gioiTinh"), 
                                        rs.getString("diaChi"));
                results.add(nv);
            }
        }
        return results;
    }
    
    public NhanVien getNhanVienById(String maHang) throws SQLException {
       try (Connection conn = JdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM nhanvien WHERE MaNV=?");
           stm.setString(1, maHang);
           
           ResultSet rs = stm.executeQuery();
           
           NhanVien h = null;
           if (rs.next()) {
               h = new NhanVien();
               h.setMaNV(rs.getString("maNV"));
               h.setTenNV(rs.getString("tenNV"));
               h.setNamSinh(rs.getDate("namSinh"));
               h.setSdt(rs.getString("sdt"));
               h.setEmail(rs.getString("email"));
               h.setGioiTinh(rs.getString("gioiTinh"));
               h.setDiaChi(rs.getString("diaChi"));
           }
           
           return h;
       }
    }
}
