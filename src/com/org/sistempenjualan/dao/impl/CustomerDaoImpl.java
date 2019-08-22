/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.dao.CustomerDao;
import com.org.sistempenjualan.entity.Entity;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author DIOR
 */
public class CustomerDaoImpl implements CustomerDao{
    Connection con = DbConnect.ConnectDb();
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public ResultSet setCustomerTable() {
        ResultSet res = null;
        try{
            String sql = "SELECT a.no_customer as 'No Customer', "
                        + "a.nama_customer as 'Nama Customer', "
                        + "a.alamat Alamat, "
                        + "a.kota Kota, "
                        + "a.no_telp as 'No Telepon', "
                        + "DATE_FORMAT(a.tanggal_system, '%d %M %Y %T') as 'Tanggal Entri', "
                        + "a.update_by NIK "
                        + "FROM mst_customer a "
                        + "ORDER BY a.no_customer ASC ";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public ResultSet getCustomerByParam(String param) {
        ResultSet res = null;
        try{
            String sql = "SELECT a.no_customer as 'No Customer', "
                        + "a.nama_customer as 'Nama Customer', "
                        + "a.alamat Alamat, "
                        + "a.kota Kota, "
                        + "a.no_telp as 'No Telepon', "
                        + "DATE_FORMAT(a.tanggal_system, '%d %M %Y %T') as 'Tanggal Entri', "
                        + "a.update_by NIK "
                        + "FROM mst_customer a "
                        + "WHERE a.no_customer LIKE ? "
                        + "OR a.nama_customer LIKE ? "
                        + "OR a.alamat LIKE ? "
                        + "OR a.kota LIKE ? "
                        + "OR a.no_telp LIKE ? "
                        + "ORDER BY a.no_customer ASC ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+param+"%");
            pst.setString(2, "%"+param+"%");
            pst.setString(3, "%"+param+"%");
            pst.setString(4, "%"+param+"%");
            pst.setString(5, "%"+param+"%");
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public boolean addCustomer(Entity entity) {
        boolean result = false;
        try{
            String sql = "INSERT INTO mst_customer (no_customer,nama_customer,alamat,kota,no_telp,tanggal_system,update_by) "
                    + "VALUES (?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNoCustomer());
            pst.setString(2, entity.getNamaCustomer());
            pst.setString(3, entity.getAlamatCustomer());
            pst.setString(4, entity.getKotaCustomer());
            pst.setString(5, entity.getTlpCustomer());
            pst.setString(6, entity.getNikSession());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Tambah Data Customer ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean editCustomer(Entity entity) {
        boolean result = false;
        try{
            String sql = "UPDATE mst_customer a "
                        + "SET a.nama_customer = ?, "
                        + "a.alamat = ?, "
                        + "a.kota = ?, "
                        + "a.no_telp = ?, "
                        + "a.tanggal_system = DATE_FORMAT(NOW(),'%Y-%m-%d %T'), "
                        + "a.update_by = ? "
                        + "WHERE a.no_customer = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNamaCustomer());
            pst.setString(2, entity.getAlamatCustomer());
            pst.setString(3, entity.getKotaCustomer());
            pst.setString(4, entity.getTlpCustomer());
            pst.setString(5, entity.getNikSession());
            pst.setString(6, entity.getNoCustomer());
            pst.executeUpdate();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Ubah Data Customer ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean deleteCustomer(String noCustomer) {
        boolean result = false;
        try{
            String sql = "DELETE FROM mst_customer WHERE no_customer = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, noCustomer);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Hapus Data Supplier ("+e.toString()+")");
        }
        
        return result;
    }
    
    @Override
    public String getLastNoCustomer() {
        String result = "";
        try{
            String sql = "SELECT a.no_customer FROM mst_customer a ORDER BY a.no_customer DESC LIMIT 1";
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
    public Vector getListCustomer() {
         Vector vector = new Vector();
        try{
            String sql = "SELECT CONCAT(a.no_customer,' - ',a.nama_customer) FROM mst_customer a ORDER BY a.no_customer ASC ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                vector.add(rs.getString(1));
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error get last kode supplier ("+e.toString()+")");
        }
        return vector;
    }
    
}
