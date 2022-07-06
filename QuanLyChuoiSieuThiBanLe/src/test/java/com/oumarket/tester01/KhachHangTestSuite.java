/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.tester01;

import com.oumarket.pojo.KhachHang;
import com.oumarket.services.KhachHangServices;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author anhtuan
 */
public class KhachHangTestSuite {
    private static Connection conn;
    private static KhachHangServices s;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s = new KhachHangServices();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(NhanVienTestSuite.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Test
    public void testSearchSuccessful() throws SQLException {
        String kw = "0564243269";
        List<KhachHang> khachhangs = s.getKhachHangBySdt(kw);
        
        for (KhachHang q: khachhangs)
            Assertions.assertTrue(q.getSdt().contains(kw));
    }
    
    @Test
    public void testSearchInvalid() throws SQLException {
        String kw = "youuuuuuuuuuuuuuuuuuuuuuuuuuuu";
        List<KhachHang> khachhangs = s.getKhachHangBySdt(kw);
        
        Assertions.assertEquals(khachhangs.size(), 0);
    }
    
    @Test
    public void testSearchUnscure() throws SQLException {
        String kw = "1 OR 1=1";
        List<KhachHang> khachhangs = s.getKhachHangBySdt(kw);
        
        Assertions.assertEquals(khachhangs.size(), 0);
    }
}
