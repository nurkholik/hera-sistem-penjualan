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
public interface CustomerDao {
    // Form Customer
    public ResultSet setCustomerTable();
    public ResultSet getCustomerByParam(String param);
    public boolean addCustomer(Entity entity);
    public boolean editCustomer(Entity entity);
    public boolean deleteCustomer(int idCustomer);
    public String getLastNoCustomer();
    
    // Form Pemesanan Baru
    public Vector getListCustomer();
}
