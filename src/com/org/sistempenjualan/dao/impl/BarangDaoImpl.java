/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.dao.BarangDao;
import com.org.sistempenjualan.entity.Entity;
import java.awt.HeadlessException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DIOR
 */
public class BarangDaoImpl implements BarangDao{
    Connection con = DbConnect.ConnectDb();
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @Override
    public ResultSet setBarangTable() {
        ResultSet res = null;
        try{
            String sql = "SELECT a.id as 'ID', a.kode_barang as 'Kode Barang', "
                        + "a.nama_barang as 'Nama Barang', "
                        + "a.jumlah Jumlah, "
                        + "a.harga Harga "
                        + "FROM mst_barang a "
                        + "ORDER BY a.kode_barang ASC ";
//                        + "CONCAT (a.kode_supplier,' - ',b.nama_supplier) Supplier "
//                        + "FROM mst_barang a, mst_supplier b "
//                        + "WHERE a.kode_supplier = b.kode_supplier "
//                        + "ORDER BY a.kode_barang ASC ";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public int getLastStok(String namaBarang) {
        int result = 0;
        try{
            String sql = "SELECT a.jumlah "
                        + "FROM mst_barang a "
                        + "WHERE a.nama_barang = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, namaBarang);
            rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error get Stok ("+e.toString()+")");
        }
        return result;
    }
    
    @Override
    public Vector getListBarang() {
        Vector vector = new Vector();
        try {
            String sql = "SELECT CONCAT(a.kode_barang, ' - ', a.nama_barang)  "
                        + "FROM mst_barang a "
                        + "ORDER BY a.kode_barang ASC ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            vector.add("-- Pilih Barang --");
            while (rs.next()) {
                vector.add(rs.getString(1));
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Get Data Barang ("+e.toString()+")");
        }
        return vector;
    }
    
    @Override
    public boolean updateStokBarang(String kodeBarang, int jumlah) {
        boolean result = false;
        try{
            String sql = "UPDATE mst_barang a "
                        + "SET a.jumlah = a.jumlah - ? "
                        + "WHERE a.kode_barang = ? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, jumlah);
            pst.setString(2, kodeBarang);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Update Stok Barang ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean returnStokBarang(String kodeBarang, int jumlah) {
        boolean result = false;
        try{
            String sql = "UPDATE mst_barang a "
                        + "SET a.jumlah = a.jumlah + ? "
                        + "WHERE a.kode_barang = ? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, jumlah);
            pst.setString(2, kodeBarang);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Update Stok Barang ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public ResultSet setBarangTable(String param) {
        ResultSet res = null;
        try{
            String sql = "SELECT a.kode_barang as 'Kode Barang', "
                        + "a.nama_barang as 'Nama Barang', "
                        + "a.jumlah Jumlah, "
                        + "a.harga Harga "
//                        + "CONCAT (a.kode_supplier,' - ',b.nama_supplier) Supplier "
                        + "FROM mst_barang a "
                        + "WHERE a.kode_barang LIKE ? "
                        + "OR a.nama_barang LIKE ? "
                        + "ORDER BY a.kode_barang ASC ";
//                        + "WHERE a.kode_supplier = b.kode_supplier "
//                        + "AND (a.kode_barang LIKE ? "
//                        + "OR a.nama_barang LIKE ? "
//                        + "OR b.nama_supplier LIKE ?) "
//                        + "ORDER BY a.kode_barang ASC ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+param+"%");
            pst.setString(2, "%"+param+"%");
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public String getLastKodeBarang() {
        String result = "";
        try{
            String sql = "SELECT a.kode_barang "
                        + "FROM mst_barang a "
                        + "ORDER BY a.kode_barang DESC ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error get Stok ("+e.toString()+")");
        }
        return result;
    }

    @Override
    public boolean addBarang(Entity entity) {
        boolean result = false;
        try{
//            String sql = "INSERT INTO mst_barang (kode_barang,nama_barang,jumlah,harga,kode_supplier,tanggal_entri,update_by) "
//                    + "values (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
//            pst = con.prepareStatement(sql);
//            pst.setString(1, entity.getKodeBarang());
//            pst.setString(2, entity.getNamaBarang());
//            pst.setInt(3, entity.getJumlahBarang());
//            pst.setInt(4, entity.getHargaBarang());
//            pst.setString(5, entity.getKodeSupplier());
//            pst.setString(6, entity.getNikSession());
//            pst.execute();
//            result = true;
            String sql = "INSERT INTO mst_barang (kode_barang,nama_barang,jumlah,harga,tanggal_entri,update_by) "
                    + "values (?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getKodeBarang());
            pst.setString(2, entity.getNamaBarang());
            pst.setInt(3, entity.getJumlahBarang());
            pst.setInt(4, entity.getHargaBarang());
            pst.setString(5, entity.getNikSession());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error get Stok ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean editBarang(Entity entity) {
        boolean result = false;
        try{
//            String sql = "UPDATE mst_barang a "
//                        + "SET a.nama_barang = ?, "
//                        + "a.jumlah = jumlah + ?, "
//                        + "a.harga = ?, "
//                        + "a.kode_supplier = ?, "
//                        + "a.tanggal_entri = DATE_FORMAT(NOW(),'%Y-%m-%d %T'), "
//                        + "a.update_by = ? "
//                        + "WHERE a.kode_barang = ? ";
//            pst = con.prepareStatement(sql);
//            pst.setString(1, entity.getNamaBarang());
//            pst.setInt(2, entity.getJumlahBarang());
//            pst.setInt(3, entity.getHargaBarang());
//            pst.setString(4, entity.getKodeSupplier());
//            pst.setString(5, entity.getNikSession());
//            pst.setString(6, entity.getKodeBarang());
//            pst.executeUpdate();
//            result = true;
            String sql = "UPDATE mst_barang a "
                        + "SET a.nama_barang = ?, "
                        + "a.jumlah = jumlah + ?, "
                        + "a.harga = ?, "
//                        + "a.kode_supplier = ?, "
                        + "a.tanggal_entri = DATE_FORMAT(NOW(),'%Y-%m-%d %T'), "
                        + "a.update_by = ?, "
                        + "a.kode_barang = ? "
                        + "WHERE a.id = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNamaBarang());
            pst.setInt(2, entity.getJumlahBarang());
            pst.setInt(3, entity.getHargaBarang());
//            pst.setString(4, entity.getKodeSupplier());
            pst.setString(4, entity.getNikSession());
            pst.setString(5, entity.getKodeBarang());
            pst.setInt(6, entity.getIdBarang());
            pst.executeUpdate();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error get Stok ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean deleteBarang(String kodeBarang) {
        boolean result = false;
        try{
            String sql = "DELETE FROM mst_barang WHERE kode_barang = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeBarang);
            pst.executeUpdate();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error get Stok ("+e.toString()+")");
        }
        
        return result;
    }
    
    @Override
    public boolean isExistByKodeBarang(String kodeBarang, Integer idBarang) {
        try {
            pst = con.prepareStatement("SELECT (count(id) > 0) as is_exist FROM mst_barang WHERE kode_barang = ? "
                    + "AND (? IS NULL OR id <> ?)");
            pst.setString(1, kodeBarang);
            pst.setInt(2, idBarang);
            pst.setInt(3, idBarang);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getBoolean("is_exist");
            }
            return false;
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            return true;
        }
    }
}
