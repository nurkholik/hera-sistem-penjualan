/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao;

import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author DIOR
 */
public interface BarangDao {
    // Form Barang
    public ResultSet setBarangTable();
    public ResultSet setBarangTable(String param);
    public String getLastKodeBarang();
    
    // Form Detail Pemesanan & Edit Detail Pemesanan
    public int getLastStok(String namaBarang);
    public Vector getListBarang();
    public boolean updateStokBarang(String kodeBarang, int jumlah);
    public boolean returnStokBarang(String kodeBarang, int jumlah);
}
