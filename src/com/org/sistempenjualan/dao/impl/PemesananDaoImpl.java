/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.dao.PemesananDao;
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
public class PemesananDaoImpl implements PemesananDao {
    Connection con = DbConnect.ConnectDb();
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public ResultSet setPemesananTable() {
        ResultSet res = null;
        try{
            String sql = "SELECT a.id_pemesanan, "
                        + "a.no_pemesanan as 'No PO', "
                        + "a.no_customer as 'No Customer', "
                        + "DATE_FORMAT(a.tanggal_pemesanan, '%d %M %Y') as 'Tanggal Pemesanan', "
                        + "a.update_by NIK "
                        + "FROM header_pemesanan a "
                        + "ORDER BY a.no_pemesanan DESC ";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public ResultSet setPemesananTable(String param) {
        ResultSet res = null;
        try{
            String sql = "SELECT a.id_pemesanan, "
                        + "a.no_pemesanan as 'No PO', "
                        + "a.no_customer as 'No Customer', "
                        + "DATE_FORMAT(a.tanggal_pemesanan, '%d %M %Y') as 'Tanggal Pemesanan', "
                        + "a.update_by NIK "
                        + "FROM header_pemesanan a "
                        + "WHERE (a.no_pemesanan LIKE ? "
                        + "OR a.no_customer LIKE ?) "
                        + "ORDER BY a.no_pemesanan DESC ";
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
    public String getLastNoPemesanan() {
        String result = "";
        try{
            String sql = "SELECT a.no_pemesanan "
                        + "FROM header_pemesanan a "
                        + "WHERE DATE_FORMAT(a.tanggal_entri,'%Y-%m') = DATE_FORMAT(NOW(),'%Y-%m') "
                        + "ORDER BY a.no_pemesanan DESC LIMIT 1 ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Get No Pemesanan ("+e.toString()+")");
        }
        return result;
    }

    @Override
    public boolean createPemesanan(Entity entity) {
        boolean result = false;
        try{
            String sql = "INSERT INTO header_pemesanan "
                    + "(no_pemesanan, no_customer, total_pemesanan, tanggal_pemesanan, status_pemesanan, "
                    + "tanggal_entri, update_by) "
                    + "VALUES (?,?,0,STR_TO_DATE(?,'%d %M %Y'),0,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?) ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNoPemesanan());
            pst.setString(2, entity.getNoCustomer());
            pst.setString(3, entity.getTanggalPemesanan());
            pst.setString(4, entity.getNikSession());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Membuat Pemesanan Baru ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public int getIdPemesanan(String noPemesanan) {
        int result = 0;
        try{
            String sql = "SELECT a.id_pemesanan "
                        + "FROM header_pemesanan a "
                        + "WHERE a.no_pemesanan = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, noPemesanan);
            rs = pst.executeQuery();
            while(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error get id pemesanan ("+e.toString()+")");
        }
        return result;
    }

    @Override
    public ResultSet setDetailPemesananTable(int idPemesanan) {
        ResultSet res = null;
        try{
            String sql = "SELECT b.id_detail_pemesanan, "
                        + "a.id_pemesanan, "  
                        + "d.kode_barang, "
                        + "a.no_pemesanan as 'No PO', "
                        + "c.no_customer as 'No Customer', "
                        + "CONCAT(d.kode_barang,' - ',d.nama_barang) as 'Nama Barang',"
                        + "b.jumlah Jumlah,"
                        + "b.keterangan "
                        + "FROM header_pemesanan a, detail_pemesanan b, mst_customer c, mst_barang d "
                        + "WHERE a.id_pemesanan = b.id_pemesanan "
                        + "AND a.no_customer = c.no_customer "
                        + "AND b.kode_barang = d.kode_barang "
                        + "AND a.id_pemesanan = ? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idPemesanan);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public ResultSet setDetailPemesananTable(String noPemesanan) {
        ResultSet res = null;
        try{
            String sql = "SELECT b.id_detail_pemesanan, "
                        + "a.id_pemesanan, "  
                        + "d.kode_barang, "
                        + "a.no_pemesanan as 'No PO', "
                        + "c.no_customer as 'No Customer', "
                        + "CONCAT(d.kode_barang,' - ',d.nama_barang) as 'Nama Barang',"
                        + "b.jumlah Jumlah,"
                        + "b.keterangan "
                        + "FROM header_pemesanan a, detail_pemesanan b, mst_customer c, mst_barang d "
                        + "WHERE a.id_pemesanan = b.id_pemesanan "
                        + "AND a.no_customer = c.no_customer "
                        + "AND b.kode_barang = d.kode_barang "
                        + "AND a.no_pemesanan = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, noPemesanan);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public boolean createDetailPemesanan(Entity entity) {
        boolean result = false;
        try{
            String sql = "INSERT INTO detail_pemesanan "
                    + "(id_pemesanan, kode_barang, jumlah, keterangan, "
                    + "tanggal_entri, update_by) "
                    + "VALUES (?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?) ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, entity.getIdPemesanan());
            pst.setString(2, entity.getKodeBarang());
            pst.setInt(3, entity.getJumlahBarang());
            pst.setString(4, entity.getKeteranganPemesanan());
            pst.setString(5, entity.getNikSession());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Membuat Pemesanan Baru ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean deleteDetailPemesanan(int idDetailPemesanan) {
        boolean result = false;
        try{
            String sql = "DELETE FROM detail_pemesanan WHERE id_detail_pemesanan = ? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idDetailPemesanan);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Menghapus Detail Pesanan ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean tambahJumlahPemesanan(int idPemesanan, int jumlah) {
        boolean result = false;
        try{
            String sql = "UPDATE header_pemesanan a "
                        + "SET a.total_pemesanan = a.total_pemesanan + ? "
                        + "WHERE a.id_pemesanan = ? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, jumlah);
            pst.setInt(2, idPemesanan);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Tambah Jumlah Pemesanan ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean kurangJumlahPemesanan(int idPemesanan, int jumlah) {
        boolean result = false;
        try{
            String sql = "UPDATE header_pemesanan a "
                        + "SET a.total_pemesanan = a.total_pemesanan - ? "
                        + "WHERE a.id_pemesanan = ? ";
            pst = con.prepareStatement(sql);
            pst.setInt(1, jumlah);
            pst.setInt(2, idPemesanan);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Kurang Jumlah Pemesanan ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public Vector getListNoPemesanan() {
        Vector vector = new Vector();
        try{
            String sql = "SELECT a.no_pemesanan "
                        + "FROM header_pemesanan a "
                        + "WHERE NOT EXISTS (SELECT 1 FROM surat_jalan b where b.id_pemesanan = a.id_pemesanan) ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            vector.add("-- Pilih No Pemesanan --");
            while(rs.next()){
                vector.add(rs.getString(1));
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return vector;
    }

    @Override
    public Vector getAllNoPemesanan() {
        Vector vector = new Vector();
        try{
            String sql = "SELECT a.no_pemesanan "
                        + "FROM header_pemesanan a "
                        + "WHERE NOT EXISTS (SELECT 1 FROM transaksi b where b.id_pemesanan = a.id_pemesanan) "
                        + "ORDER BY a.no_pemesanan DESC ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            vector.add("-- Pilih No Pemesanan --");
            while(rs.next()){
                vector.add(rs.getString(1));
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return vector;
    }

    @Override
    public int getTotalBayar(int idPemesanan) {
        int result = 0;
        try{
            String sql = "SELECT SUM(b.jumlah * c.harga)\n" +
                         "FROM header_pemesanan a, detail_pemesanan b, mst_barang c\n" +
                         "WHERE a.id_pemesanan = b.id_pemesanan\n" +
                         "AND b.kode_barang = c.kode_barang\n" +
                         "AND a.id_pemesanan = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idPemesanan);
            rs = pst.executeQuery();
            while(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return result;
    }
    
}
