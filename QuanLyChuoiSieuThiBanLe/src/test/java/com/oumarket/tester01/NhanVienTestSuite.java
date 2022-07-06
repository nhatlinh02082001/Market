/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.oumarket.tester01;

import com.oumarket.pojo.NhanVien;
import com.oumarket.services.NhanVienServices;
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
public class NhanVienTestSuite {
    private static Connection conn;
    private static NhanVienServices s;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s = new NhanVienServices();
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
        String kw = "you";
        List<NhanVien> nhanviens = s.getNhanViens(kw);
        
        for (NhanVien q: nhanviens)
            Assertions.assertTrue(q.getTenNV().toLowerCase().contains(kw.toLowerCase()));
    }
    
    @Test
    public void testSearchInvalid() throws SQLException {
        String kw = "youuuuuuuuuuuuuuuuuuuuuuuuuuuu";
        List<NhanVien> nhanviens = s.getNhanViens(kw);
        
        Assertions.assertEquals(nhanviens.size(), 0);
    }
    
    @Test
    public void testSearchUnscure() throws SQLException {
        String kw = "1 OR 1=1";
        List<NhanVien> nhanviens = s.getNhanViens(kw);
        
        Assertions.assertEquals(nhanviens.size(), 0);
    }
    
    @Test
    public void testDeleteFail() throws SQLException {
        String id = "499999670eb24-3985-40f9-bab6-a86de52a5c34";
        Assertions.assertFalse(s.deleteNhanVien(id));
    }
    
    @Test
    public void testDeleteSuccess() throws SQLException {
        String id = "38de3ee4-630b-4d0a-9db1-7ed7756bba2a";
        Assertions.assertTrue(s.deleteNhanVien(id));
        Assertions.assertNull(s.getNhanVienById(id));
    }
    
    @Test
    public void testAddSuccess() throws SQLException {
        NhanVien nv = new NhanVien(UUID.randomUUID().toString(), "test", java.sql.Date.valueOf("2015-03-31"), 
                                    "0303030303", "test@gmail.com", "Nam", "test");
        
        Assertions.assertTrue(s.themNhanVien(nv));
        
        NhanVien nvs = s.getNhanVienById(nv.getMaNV());
        Assertions.assertEquals(nv.getTenNV(), nvs.getTenNV());
        Assertions.assertEquals(nv.getNamSinh(), nvs.getNamSinh());
    }
    
    @Test
    public void testEditSuccess() throws SQLException {
        String id = "f514a2dd-a06a-484e-91fd-23faa70bc601";
        NhanVien nv = new NhanVien(id, "test34", java.sql.Date.valueOf("2015-03-31"), 
                                    "0303030303", "test@gmail.com", "Nam", "test");
        
        Assertions.assertTrue(s.editNhanVien(nv));
        
        NhanVien nvs = s.getNhanVienById(nv.getMaNV());
        Assertions.assertEquals(nv.getNamSinh(), nvs.getNamSinh());
        Assertions.assertEquals(nv.getTenNV(), nvs.getTenNV());
    }
    
    @Test
    public void testEditFail() throws SQLException {
        String id = "499999670eb24-3985-40f9-bab6-a86de52a5c34";
        NhanVien nv = new NhanVien(id, "test34", java.sql.Date.valueOf("2015-03-31"), 
                                    "0303030303", "test@gmail.com", "Nam", "test");
        Assertions.assertFalse(s.editNhanVien(nv));
    }
}
