/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.dao.PembayaranDao;
import com.org.sistempenjualan.entity.Entity;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DIOR
 */
public class PembayaranDaoImpl implements PembayaranDao{
    Connection con = DbConnect.ConnectDb();
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @Override
    public ResultSet setPembayaranTable() {
        ResultSet res = null;
        try{
          String sql = "SELECT a.id_transaksi, "+
                    "a.id_pemesanan, "+
                    "a.id_surat_jalan, "+
                    "a.no_transaksi as 'No Transaksi', "+
                    "b.no_pemesanan as 'No Pemesanan', "+
                    "c.no_surat_jalan as 'No Surat Jalan', "+
                    "a.metode_pembayaran as 'Metode Pembayaran', "+
                    "a.jenis_pembayaran as 'Jenis Pembayaran', "+
                    "DATE_FORMAT(a.tanggal_transaksi,'%d %M %Y') as 'Tanggal Transaksi', "+
                    "DATE_FORMAT(a.tanggal_bayar,'%d %M %Y') as 'Tanggal Bayar', "+
                    "a.total_bayar as 'Total Bayar', "+
                    "a.yang_dibayar as 'Yang Dibayarkan', "+
                    "a.selisih Selisih, "+
                    "a.tanggal_entri as 'Tanggal Entri', "+
                    "a.update_by NIK "+
                    "FROM transaksi a "+
                    "JOIN header_pemesanan b "+
                    "ON a.id_pemesanan = b.id_pemesanan "+
                    "LEFT JOIN surat_jalan c "+
                    "ON b.id_pemesanan = c.id_pemesanan "+
                    "ORDER BY a.no_transaksi DESC";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();  
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public ResultSet setPembayaranTable(String param) {
        ResultSet res = null;
        try{
          String sql = "SELECT a.id_transaksi, "+
                    "a.id_pemesanan, "+
                    "a.id_surat_jalan, "+
                    "a.no_transaksi as 'No Transaksi', "+
                    "b.no_pemesanan as 'No Pemesanan', "+
                    "c.no_surat_jalan as 'No Surat Jalan', "+
                    "a.metode_pembayaran as 'Metode Pembayaran', "+
                    "a.jenis_pembayaran as 'Jenis Pembayaran', "+
                    "DATE_FORMAT(a.tanggal_transaksi,'%d %M %Y') as 'Tanggal Transaksi', "+
                    "DATE_FORMAT(a.tanggal_bayar,'%d %M %Y') as 'Tanggal Bayar', "+
                    "a.total_bayar as 'Total Bayar', "+
                    "a.yang_dibayar as 'Yang Dibayarkan', "+
                    "a.selisih Selisih, "+
                    "a.tanggal_entri as 'Tanggal Entri', "+
                    "a.update_by NIK "+
                    "FROM transaksi a "+
                    "JOIN header_pemesanan b "+
                    "ON a.id_pemesanan = b.id_pemesanan "+
                    "LEFT JOIN surat_jalan c "+
                    "ON b.id_pemesanan = c.id_pemesanan "
                    + "WHERE (a.no_transaksi LIKE ? "
                    + "OR b.no_pemesanan LIKE ? "
                    + "OR c.no_surat_jalan LIKE ?) "
                    + "ORDER BY a.no_transaksi DESC ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+param+"%");
            pst.setString(2, "%"+param+"%");
            pst.setString(3, "%"+param+"%");
            res = pst.executeQuery();  
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public int getIdPembayaran(String noTransaksi) {
        int result = 0;
        try{
            String sql = "SELECT a.id_transaksi "
                        + "FROM transaksi a "
                        + "WHERE a.no_transaksi = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, noTransaksi);
            rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean createPembayaran(Entity entity) {
        boolean result = false;
        try{
            String sql = "INSERT INTO transaksi(no_transaksi, metode_pembayaran, jenis_pembayaran, tanggal_transaksi, "
                        + "tanggal_bayar, id_pemesanan, id_surat_jalan, total_bayar, yang_dibayar, "
                        + "selisih, tanggal_entri, update_by) "
                        + "VALUES (?,?,?,"
                        + "STR_TO_DATE(?,'%d %M %Y'),STR_TO_DATE(?,'%d %M %Y'),"
                        + "?,?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?) ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNoTransaksi());
            pst.setString(2, entity.getMetodePembayaran());
            pst.setString(3, entity.getJenisPembayaran());
            pst.setString(4, entity.getTanggalTransaksi());
            pst.setString(5, entity.getTanggalBayar());
            pst.setInt(6, entity.getIdPemesanan());
            pst.setInt(7, entity.getIdSuratJalan());
            pst.setInt(8, entity.getTotalBayar());
            pst.setInt(9, entity.getYangDibayar());
            pst.setInt(10, entity.getSelisih());
            pst.setString(11, entity.getNikSession());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean updatePembayaran(Entity entity) {
        boolean result = false;
        try{
            String sql = "UPDATE transaksi a "
                        + "SET a.metode_pembayaran = ?, "
                        + "a.jenis_pembayaran = ?, "
                        + "a.tanggal_bayar = STR_TO_DATE(?,'%d %M %Y'), "
                        + "a.total_bayar = ?, "
                        + "a.yang_dibayar = ?, "
                        + "a.selisih = ?, "
                        + "a.tanggal_entri = DATE_FORMAT(NOW(),'%Y-%m-%d %T'), "
                        + "a.update_by = ? "
                        + "WHERE a.no_transaksi = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getMetodePembayaran());
            pst.setString(2, entity.getJenisPembayaran());
            pst.setString(3, entity.getTanggalBayar());
            pst.setInt(4, entity.getTotalBayar());
            pst.setInt(5, entity.getYangDibayar());
            pst.setInt(6, entity.getSelisih());
            pst.setString(7, entity.getNikSession());
            pst.setString(8, entity.getNoTransaksi());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Update transaksi ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public String getLastNoPembayaran() {
        String result = "";
        try{
            String sql = "SELECT a.no_transaksi "
                        + "FROM transaksi a "
                        + "WHERE DATE_FORMAT(a.tanggal_entri,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') "
                        + "ORDER BY a.no_transaksi DESC LIMIT 1 ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return result;
    }
    
}
