/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.services;

import com.oumarket.pojo.PhanLoai;
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
public class PhanLoaiServies {
    public List<PhanLoai> getPhanLoai() throws SQLException {
        List<PhanLoai> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM phanloai");
            
            while (rs.next()) {
                PhanLoai p = new PhanLoai(rs.getInt("maLoai"), rs.getString("tenLoai"));
                results.add(p);
            }
        }
        return results;
    }
}
