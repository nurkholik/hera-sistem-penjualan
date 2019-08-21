/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao;

import com.org.sistempenjualan.entity.Entity;
import java.sql.ResultSet;

/**
 *
 * @author DIOR
 */
public interface SuratJalanDao {
    // Form Surat Jalan
    public ResultSet setSuratJalanTable();
    public ResultSet setSuratJalanTable(String param);
    public String getLastNoSuratJalan();
    public boolean createSuratJalan(Entity entity);
    public boolean cancelSuratJalan(int idSuratJalan);
    public int getIdSuratJalan(String noSuratJalan);
}
