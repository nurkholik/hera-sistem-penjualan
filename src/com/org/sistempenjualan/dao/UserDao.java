/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao;

import com.org.sistempenjualan.entity.Entity;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author DIOR
 */
public interface UserDao {
    // Form Login
    public String getUserLogin(Entity entity);
    public String getLastNik();
    
    // Form User
    public ResultSet setUserTable();
    public boolean addUser(Entity entity);
    public boolean editUser(Entity entity);
    public boolean deleteUser(String nik);
    public ResultSet getUserByParam(String nik);
    
    // Form Cari Pengirim (tidak digunakan)
    public ResultSet setPengirimTable();
    public ResultSet setPengirimTableByParam(String param);
    public Map<String, String> getPengirim(String nik);
    
    // Form Surat Jalan
    public Vector getListPengirim();
}
