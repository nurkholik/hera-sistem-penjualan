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
public interface PembayaranDao {
    public ResultSet setPembayaranTable();
    public ResultSet setPembayaranTable(String param);
    public ResultSet setPembayaranTable(String param, String param2);
    public int getIdPembayaran(String noTransaksi);
    public boolean createPembayaran(Entity entity);
    public boolean updatePembayaran(Entity entity);
    public String getLastNoPembayaran();
    public ResultSet getReportBulanan(String tglAwal, String tglAkhir);
}
