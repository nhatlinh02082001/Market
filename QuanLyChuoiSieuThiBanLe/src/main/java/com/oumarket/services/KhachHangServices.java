/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.services;

import com.oumarket.pojo.KhachHang;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhtuan
 */
public class KhachHangServices {
    public List<KhachHang> getKhachHangBySdt(String kw) throws SQLException {
       try (Connection conn = JdbcUtils.getConn()) {
           PreparedStatement stm = conn.prepareStatement("SELECT * FROM khachhang WHERE SDT like concat('%', '%', ?, '%', '%', '%')");
           
           if (kw == null)
               kw = "";
           stm.setString(1, kw);
           
           ResultSet rs = stm.executeQuery();
           
           List<KhachHang> khachhangs = new ArrayList<>();
           
           while (rs.next()) {
               String maKH = rs.getString("maKH");
               String tenKH = rs.getString("tenKH");
               String sdt = rs.getString("sdt");
               String diaChi = rs.getString("diaChi");
               Date namSinh = rs.getDate("namSinh");
               String email = rs.getString("email");
               khachhangs.add(new KhachHang(maKH, tenKH, sdt, diaChi, namSinh, email));
           }
           
           return khachhangs;
       }
    }
}
