/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.services;

import com.oumarket.pojo.GiamGia;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhtuan
 */
public class GiamGiaServices {
    public List<GiamGia> getGiamGia() throws SQLException {
        List<GiamGia> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM giamgia");
            
            while (rs.next()) {
                GiamGia g = new GiamGia(rs.getInt("maGiamGia"), rs.getString("noiDung"), 
                                        rs.getDate("ngayBatDau"), rs.getDate("ngayKetThuc"));
                results.add(g);
            }
        }
        return results;
    }
}
