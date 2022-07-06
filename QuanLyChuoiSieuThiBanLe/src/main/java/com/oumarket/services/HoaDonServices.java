/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.services;

import com.oumarket.pojo.HoaDon;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author anhtuan
 */
public class HoaDonServices {
    public void themHoaDon(HoaDon h) throws SQLException {
        String q1 = "INSERT INTO hoadon(MaHoaDon, MaNV, MaKH, NgayHD) VALUES(?, ?, ?, ?)";
        String q2 = "INSERT INTO chitiethoadon(MaHoaDon, MaHang, SoLuong, DonGia, ThanhTien) VALUES(?, ?, ?, ?, ?)";
        
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareStatement("INSERT INTO hoadon(MaHoaDon, MaNV, MaKH, NgayHD) VALUES(?, ?, ?, ?)");
            stm.setString(1, h.getMaHoaDon());
            stm.setString(2, h.getMaNV());
            stm.setString(3, h.getMaKH());
            stm.setDate(4, h.getNgayHD());
            
            stm.executeUpdate();
        }
    }
}
