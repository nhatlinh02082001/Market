/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.tester01;

import com.oumarket.pojo.HangHoa;
import com.oumarket.services.HangHoaServices;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
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
public class HangHoaTestSuite {
    private static Connection conn;
    private static HangHoaServices h;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(HangHoaTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        h = new HangHoaServices();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(HangHoaTestSuite.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testSearchSuccessful() throws SQLException {
        String kw = "you";
        List<HangHoa> hanghoas = h.getHangHoas(kw);
        
        for (HangHoa q: hanghoas)
            Assertions.assertTrue(q.getTenHang().toLowerCase().contains(kw.toLowerCase()));
    }
    
    @Test
    public void testSearchInvalid() throws SQLException {
        String kw = "youuuuuuuuuuuuuuuuuuuuuuuuuuuu";
        List<HangHoa> hanghoas = h.getHangHoas(kw);
        
        Assertions.assertEquals(hanghoas.size(), 0);
    }
    
    @Test
    public void testSearchUnscure() throws SQLException {
        String kw = "1 OR 1=1";
        List<HangHoa> hanghoas = h.getHangHoas(kw);
        
        Assertions.assertEquals(hanghoas.size(), 0);
    }
    
    @Test
    public void testDeleteFail() throws SQLException {
        String id = "499999670eb24-3985-40f9-bab6-a86de52a5c34";
        Assertions.assertFalse(h.deleteHangHoa(id));
    }
    
    @Test
    public void testDeleteSuccess() throws SQLException {
        String id = "5bfa650d-1688-4b28-bbcb-648345c1b31b";
        Assertions.assertTrue(h.deleteHangHoa(id));
        Assertions.assertNull(h.getHangHoaById(id));
    }
    
    @Test
    public void testAddSuccess() throws SQLException {
        HangHoa q = new HangHoa(UUID.randomUUID().toString(), "test", 4, 25400, "test", 4, 1);
        
        Assertions.assertTrue(h.themHangHoa(q));
        
        HangHoa hhs = h.getHangHoaById(q.getMaHang());
        Assertions.assertEquals(q.getTenHang(), hhs.getTenHang());
        Assertions.assertEquals(q.getMaLoai(), hhs.getMaLoai());
    }
    
    @Test
    public void testEditSuccess() throws SQLException {
        String id = "79b6973c-3dd8-4858-b1df-26c9f130bd02";
        HangHoa q = new HangHoa(id, "test", 4, 25400, "test3", 4, 1);
        
        Assertions.assertTrue(h.editHangHoa(q));
        
        HangHoa hhs = h.getHangHoaById(q.getMaHang());
        Assertions.assertEquals(q.getTenHang(), hhs.getTenHang());
        Assertions.assertEquals(q.getMaLoai(), hhs.getMaLoai());
    }
    
    @Test
    public void testEditFail() throws SQLException {
        String id = "79b6973c-3dd8-4858-b1df-26c9f130bd02";
        HangHoa q = new HangHoa(id, "test", 4, 25400, "test3", 4, 1);
        
        Assertions.assertFalse(h.editHangHoa(q));
    }
}
