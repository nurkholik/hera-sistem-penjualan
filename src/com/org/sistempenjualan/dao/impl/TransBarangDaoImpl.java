/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.dao.TransBarangDao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author DIOR
 */
public class TransBarangDaoImpl implements TransBarangDao{
    Connection con = DbConnect.ConnectDb();
    
    @Override
    public boolean addStockTransaction(int idBarang, int jumlah, java.sql.Date transactionDate, String description) {
        try{       
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement(
                    "UPDATE mst_barang a SET a.jumlah = (a.jumlah + ?) "
                    + "WHERE id = ?");
            pst.setInt(1, jumlah);
            pst.setInt(2, idBarang);
            System.out.println("Update stok barang : " + pst.executeUpdate());
            
            PreparedStatement pst2 = con.prepareStatement(
                    "INSERT INTO trans_barang "
                    + "(type, date, description, id_barang, jumlah) VALUES "
                    + "(?, ?, ?, ?, ?)");
            pst2.setString(1, jumlah <= 0 ? "OUT" : "IN");
            pst2.setDate(2, transactionDate);
            pst2.setString(3, description);
            pst2.setInt(4, idBarang);
            pst2.setInt(5, jumlah);
            System.out.println("Insert trans barang : " + pst2.execute());
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException | HeadlessException e){
            e.printStackTrace();
            return false;
        }
    }
    
}
