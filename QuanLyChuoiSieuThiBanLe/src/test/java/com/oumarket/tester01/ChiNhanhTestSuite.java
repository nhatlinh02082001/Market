/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.tester01;

import com.oumarket.pojo.ChiNhanh;
import com.oumarket.services.ChiNhanhServices;
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
public class ChiNhanhTestSuite {
    private static Connection conn;
    private static ChiNhanhServices cns;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChiNhanhTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cns = new ChiNhanhServices();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChiNhanhTestSuite.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testSearchSuccessful() throws SQLException {
        String kw = "you";
        List<ChiNhanh> chinhanhs = cns.getChiNhanhs(kw);
        
        for (ChiNhanh cn: chinhanhs)
            Assertions.assertTrue(cn.getThanhPho().toLowerCase().contains(kw.toLowerCase()));
    }
    
    @Test
    public void testSearchInvalid() throws SQLException {
        String kw = "youuuuuuuuuuuuuuuuuuuuuuuuuuuu";
        List<ChiNhanh> chinhanhs = cns.getChiNhanhs(kw);
        
        Assertions.assertEquals(chinhanhs.size(), 0);
    }
    
    @Test
    public void testSearchUnscure() throws SQLException {
        String kw = "1 OR 1=1";
        List<ChiNhanh> chinhanhs = cns.getChiNhanhs(kw);
        
        Assertions.assertEquals(chinhanhs.size(), 0);
    }
    
    @Test
    public void testDeleteFail() throws SQLException {
        String id = "499999670eb24-3985-40f9-bab6-a86de52a5c34";
        Assertions.assertFalse(cns.deleteChiNhanh(id));
    }
    
    @Test
    public void testDeleteSuccess() throws SQLException {
        String id = "4ef65997-dffd-49a6-854e-c3d675d25828";
        Assertions.assertTrue(cns.deleteChiNhanh(id));
        Assertions.assertNull(cns.getChiNhanhById(id));
    }
    
    @Test
    public void testAddSuccess() throws SQLException {
        ChiNhanh q = new ChiNhanh(UUID.randomUUID().toString(), "123", "test", "test", "test", "test");
        
        Assertions.assertTrue(cns.themChiNhanh(q));
        
        ChiNhanh hhs = cns.getChiNhanhById(q.getMaChiNhanh());
        Assertions.assertEquals(q.getDuong(), hhs.getDuong());
        Assertions.assertEquals(q.getThanhPho(), hhs.getThanhPho());
    }
    
    @Test
    public void testEditSuccess() throws SQLException {
        String id = "dc9ba7bd-a313-4855-aae7-755f75858e74";
        ChiNhanh q = new ChiNhanh(id, "123", "test", "test2", "test", "test");
        
        Assertions.assertTrue(cns.editChiNhanh(q));
        
        ChiNhanh hhs = cns.getChiNhanhById(q.getMaChiNhanh());
        Assertions.assertEquals(q.getDuong(), hhs.getDuong());
        Assertions.assertEquals(q.getThanhPho(), hhs.getThanhPho());
    }
    
    @Test
    public void testEditFail() throws SQLException {
        String id = "79b6973c-3dd8-4858-b1df-26c9f130bd02";
        ChiNhanh q = new ChiNhanh(id, "123", "test", "test2", "test", "test");
        
        Assertions.assertFalse(cns.editChiNhanh(q));
    }
}
