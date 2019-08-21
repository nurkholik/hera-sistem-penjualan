/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao;

import com.org.sistempenjualan.entity.Entity;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author DIOR
 */
public interface PemesananDao {
    // Form Pemesanan
    public ResultSet setPemesananTable();
    public ResultSet setPemesananTable(String param);
    
    // Form Pemesanan Baru
    public String getLastNoPemesanan();
    public boolean createPemesanan(Entity entity);
    
    // Form Detail Pemesanan & Edit Detail Pemesanan
    public ResultSet setDetailPemesananTable(int idPemesanan);
    public ResultSet setDetailPemesananTable(String noPemesanan);
    public boolean createDetailPemesanan(Entity entity);
    public int getIdPemesanan(String noPemesanan);
    public boolean deleteDetailPemesanan(int idDetailPemesanan);
    public boolean tambahJumlahPemesanan(int idPemesanan, int jumlah);
    public boolean kurangJumlahPemesanan(int idPemesanan, int jumlah);    
    
    // Form Surat Jalan
    public Vector getListNoPemesanan();
}
