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
public interface SupplierDao {
    public ResultSet setSupplierTable();
    public String getLastKodeSupplier();
    public boolean addSupplier(Entity entity);
    public boolean editSupplier(Entity entity);
    public boolean deleteSupplier(String kodeSupplier);
    public ResultSet getSupplierByParam(String param);
}
