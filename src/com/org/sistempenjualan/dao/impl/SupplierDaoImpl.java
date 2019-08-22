/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.dao.SupplierDao;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.entity.Entity;
import java.util.Vector;

/**
 *
 * @author DIOR
 */
public class SupplierDaoImpl implements SupplierDao{
    Connection con = DbConnect.ConnectDb();
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public ResultSet setSupplierTable() {
        ResultSet res = null;
        try{
            String sql = "SELECT a.kode_supplier as 'Kode Supplier', "
                        + "a.nama_supplier as 'Nama Supplier', "
                        + "a.alamat Alamat, "
                        + "a.kota Kota, "
                        + "a.no_telp as 'No Telepon',"
                        + "DATE_FORMAT(a.tanggal_entri, '%d %M %Y %T') as 'Tanggal Entri', "
                        + "a.nik NIK "
                        + "FROM mst_supplier a ";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public String getLastKodeSupplier() {
        String result = "";
        try{
            String sql = "SELECT a.kode_supplier FROM mst_supplier a ORDER BY a.kode_supplier DESC LIMIT 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error get last kode supplier ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean addSupplier(Entity entity) {
        boolean result = false;
        try{
            String sql = "INSERT INTO mst_supplier VALUES (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getKodeSupplier());
            pst.setString(2, entity.getNamaSupplier());
            pst.setString(3, entity.getAlamatSupplier());
            pst.setString(4, entity.getKota());
            pst.setString(5, entity.getTlpSupplier());
            pst.setString(6, entity.getNikSession());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Tambah Data Supplier ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean editSupplier(Entity entity) {
        boolean result = false;
        try{
            String sql = "UPDATE mst_supplier a "
                        + "SET a.nama_supplier = ?, "
                        + "a.alamat = ?, "
                        + "a.kota = ?, "
                        + "a.no_telp = ?, "
                        + "a.tanggal_entri = DATE_FORMAT(NOW(),'%Y-%m-%d %T'), "
                        + "a.nik = ? "
                        + "WHERE a.kode_supplier = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNamaSupplier());
            pst.setString(2, entity.getAlamatSupplier());
            pst.setString(3, entity.getKota());
            pst.setString(4, entity.getTlpSupplier());
            pst.setString(5, entity.getNikSession());
            pst.setString(6, entity.getKodeSupplier());
            pst.executeUpdate();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Ubah Data Supplier ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean deleteSupplier(String kodeSupplier) {
        boolean result = false;
        try{
            String sql = "DELETE FROM mst_supplier WHERE kode_supplier = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, kodeSupplier);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Hapus Data Supplier ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public ResultSet getSupplierByParam(String param) {
        ResultSet res = null;
        try{
            String sql = "SELECT a.kode_supplier as 'Kode Supplier', "
                        + "a.nama_supplier as 'Nama Supplier', "
                        + "a.alamat Alamat, "
                        + "a.kota Kota, "
                        + "a.no_telp as 'No Telepon',"
                        + "DATE_FORMAT(a.tanggal_entri, '%d %M %Y %T') as 'Tanggal Entri', "
                        + "a.nik NIK "
                        + "FROM mst_supplier a "
                        + "WHERE a.kode_supplier LIKE ? "
                        + "OR a.nama_supplier LIKE ?"
                        + "OR a.alamat LIKE ?"
                        + "OR a.kota LIKE ?"
                        + "OR a.no_telp LIKE ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+param+"%");
            pst.setString(2, "%"+param+"%");
            pst.setString(3, "%"+param+"%");
            pst.setString(4, "%"+param+"%");
            pst.setString(5, "%"+param+"%");
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Search Supplier ("+e.toString()+")");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Search Supplier ("+ex.toString()+")");
        }
        return res;
    }

    @Override
    public Vector getListSupplier() {
        Vector vector = new Vector();
        try{
            String sql = "SELECT CONCAT(a.kode_supplier, ' - ', a.nama_supplier) "
                        + "FROM mst_supplier a "
                        + "ORDER BY a.kode_supplier ASC ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            vector.add("-- Pilih Supplier --");
            while(rs.next()){
                vector.add(rs.getString(1));
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Search Supplier ("+e.toString()+")");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Search Supplier ("+ex.toString()+")");
        }
        return vector;
    }
    
}
