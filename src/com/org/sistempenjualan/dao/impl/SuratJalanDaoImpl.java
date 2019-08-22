/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.dao.SuratJalanDao;
import com.org.sistempenjualan.entity.Entity;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author DIOR
 */
public class SuratJalanDaoImpl implements SuratJalanDao{
    Connection con = DbConnect.ConnectDb();
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public ResultSet setSuratJalanTable() {
        ResultSet res = null;
        try{
            String sql = "SELECT a.id_surat_jalan, "
                        + "a.no_surat_jalan as 'No Surat Jalan', "
                        + "CONCAT(a.nik_pengirim,' - ',b.nama_user) Pengirim, "
                        + "DATE_FORMAT(a.tanggal_pengiriman,'%d %M %Y') as 'Tanggal Pengiriman', "
                        + "c.no_pemesanan as 'No Pemesanan', "
                        + "a.tanggal_entri as 'Tanggal Entri',"
                        + "a.update_by NIK "
                        + "FROM surat_jalan a,"
                        + "mst_user b, "
                        + "header_pemesanan c "
                        + "WHERE a.nik_pengirim = b.nik "
                        + "AND a.id_pemesanan = c.id_pemesanan "
                        + "ORDER BY a.no_surat_jalan DESC ";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error set Data Table ("+e.toString()+")");
        }
        return res;
    }

    @Override
    public ResultSet setSuratJalanTable(String param) {
        ResultSet res = null;
        try{
            String sql = "SELECT a.id_surat_jalan, "
                        + "a.no_surat_jalan as 'No Surat Jalan', "
                        + "CONCAT(a.nik_pengirim,' - ',b.nama_user) Pengirim, "
                        + "DATE_FORMAT(a.tanggal_pengiriman,'%d %M %Y') as 'Tanggal Pengiriman', "
                        + "c.no_pemesanan as 'No Pemesanan', "
                        + "a.tanggal_entri as 'Tanggal Entri',"
                        + "a.update_by NIK "
                        + "FROM surat_jalan a,"
                        + "mst_user b, "
                        + "header_pemesanan c "
                        + "WHERE a.nik_pengirim = b.nik "
                        + "AND a.id_pemesanan = c.id_pemesanan "
                        + "AND (a.no_surat_jalan LIKE ? "
                        + "OR a.nik_pengirim LIKE ?"
                        + "OR b.nama_user LIKE ?) "
                        + "ORDER BY a.no_surat_jalan DESC ";
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
    public String getLastNoSuratJalan() {
        String result = "";
        try{
            String sql = "SELECT a.no_surat_jalan "
                        + "FROM surat_jalan a "
                        + "WHERE DATE_FORMAT(a.tanggal_entri,'%Y-%m') = DATE_FORMAT(NOW(),'%Y-%m') "
                        + "ORDER BY a.no_surat_jalan DESC LIMIT 1 ";
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

    @Override
    public boolean createSuratJalan(Entity entity) {
        boolean result = false;
        try{
            String sql = "INSERT INTO surat_jalan "
                    + "(no_surat_jalan, id_pemesanan, nik_pengirim, tanggal_pengiriman, tanggal_entri, update_by) "
                    + "VALUES (?,?,?,STR_TO_DATE(?,'%d %M %Y'),DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?) ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNoSuratJalan());
            pst.setInt(2, entity.getIdPemesanan());
            pst.setString(3, entity.getNikPengirim());
            pst.setString(4, entity.getTanggalPengiriman());
            pst.setString(5, entity.getNikSession());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Membuat Surat Jalan ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public int getIdSuratJalan(String noSuratJalan) {
        int result = 0;
        try{
            String sql = "SELECT a.id_surat_jalan "
                        + "FROM surat_jalan a "
                        + "WHERE a.no_surat_jalan = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, rs.getString(1));
            rs = pst.executeQuery();
            while(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Get Id Surat Jalan ("+e.toString()+")");
        }
        return result;
    }

    @Override
    public boolean cancelSuratJalan(int idSuratJalan) {
        boolean result = false;
        try{
            String sql = "DELETE FROM surat_jalan WHERE id_surat_jalan = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, idSuratJalan);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Membuat Surat Jalan ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public Map<String, Object> getNoSuratJalan(String noPemesanan) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String sql = "SELECT a.id_surat_jalan, a.no_surat_jalan "
                        + "FROM surat_jalan a, header_pemesanan b "
                        + "WHERE a.id_pemesanan = b.id_pemesanan "
                        + "AND b.no_pemesanan = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, noPemesanan);
            rs = pst.executeQuery();
            while(rs.next()){
                map.put("idSuratJalan", rs.getInt(1));
                map.put("noSuratJalan", rs.getString(2));
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Membuat Surat Jalan ("+e.toString()+")");
        }
        
        return map;
    }
}
