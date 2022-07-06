package com.oumarket.tester01;


import com.oumarket.pojo.PhanLoai;
import com.oumarket.services.PhanLoaiServies;
import com.oumarket.utils.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anhtuan
 */
public class PhanLoaiTestSuite {
    private static Connection conn;
    private static PhanLoaiServies s;
    
    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(PhanLoaiTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        s = new PhanLoaiServies();
    }
    
    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(PhanLoaiTestSuite.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Test
    public void testPhanLoaiNameNotNull() {
        try {
            List<PhanLoai> phanloais = s.getPhanLoai();
            
            phanloais.forEach(c -> Assertions.assertNotNull(c.getTenLoai()));
        } catch (SQLException ex) {
            Logger.getLogger(PhanLoaiTestSuite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testPhanLoaiNameUnique() throws SQLException {
        List<PhanLoai> phanloais = s.getPhanLoai();
        
        List<String> tens = phanloais.stream().flatMap(c -> Stream.of(c.getTenLoai())).collect(Collectors.toList());
        Set<String> others = new HashSet<>(tens);
        
        Assertions.assertEquals(tens.size(), others.size());
    }
}
