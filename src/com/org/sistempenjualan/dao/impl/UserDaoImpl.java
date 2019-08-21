/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;


import com.org.sistempenjualan.dao.UserDao;
import com.org.sistempenjualan.gui.LoginForm;
import java.awt.HeadlessException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.org.sistempenjualan.entity.Entity;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import com.org.sistempenjualan.DbConnect;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author DIOR
 */
public class UserDaoImpl implements UserDao{
    // Variabel koneksi
    Connection con = DbConnect.ConnectDb();
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @Override
    public String getUserLogin(Entity entity) {
        String result ="";
        try{
         String sql="select nik, nama_user, role "
                  + "from mst_user "
                  + "where nik = ? and password= ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNik());
            pst.setString(2, entity.getMd5(entity.getPassword()));
            rs = pst.executeQuery();
            if(rs.next()){
                entity.setNikSession(rs.getString("nik"));
                entity.setUserSession(rs.getString("nama_user"));
                entity.setRoleSession(rs.getString("role"));
            }else{
                JOptionPane.showMessageDialog(null,"NIK atau Password salah");
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"login rusak"+e.toString());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    @Override
    public String getLastNik() {
        String result = "";
        try{
            String sql = "SELECT nik FROM mst_user ORDER BY NIK DESC LIMIT 1";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Generate NIK ("+e.toString()+")");
        }
        
        return result;
    }

    @Override
    public boolean addUser(Entity entity) {
        boolean result = false;
        try{
            String sql = "INSERT INTO mst_user values (?,?,?,?,DATE_FORMAT(NOW(),'%Y-%m-%d %T'),?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getNik());
            pst.setString(2, entity.getUserName());
            pst.setString(3, entity.getRole());
            pst.setString(4, entity.getMd5(entity.getPassword()));
            pst.setString(5, entity.getNikSession());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Membuat User Baru ("+e.toString()+")");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

    @Override
    public ResultSet setUserTable() {
        ResultSet res = null;
        try{
            String sql = "SELECT nik NIK, "
                        + "nama_user as 'Nama User', "
                        + "role 'Jabatan', "
                        + "password Password, "
                        + "DATE_FORMAT(tanggal_system, '%d %M %Y %T') as 'Tanggal System', "
                        + "update_by as 'Update By'"
                        + "FROM mst_user "
                        + "where nik <> '00000' ";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Generate Data User ("+e.toString()+")");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Generate Data User ("+ex.toString()+")");
        }
        return res;
    }

    @Override
    public boolean editUser(Entity entity) {
        boolean result = false;
        try{
            String sql = "UPDATE mst_user a "
                        + "SET a.nama_user = ?, "
                        + "a.role = ?, "
                        + "a.tanggal_system = DATE_FORMAT(NOW(),'%Y-%m-%d %T'),"
                        + "a.update_by = ? "
                        + "WHERE a.nik = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, entity.getUserName());
            pst.setString(2, entity.getRole());
            pst.setString(3, entity.getNikSession());
            pst.setString(4, entity.getNik());
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Edit User ("+e.toString()+")");
        } 
        
        return result;
    }

    @Override
    public boolean deleteUser(String nik) {
        boolean result = false;
        try{
            String sql = "DELETE FROM mst_user "
                        + "WHERE nik = ? ";
            pst = con.prepareStatement(sql);
            pst.setString(1, nik);
            pst.execute();
            result = true;
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Hapus NIK ("+e.toString()+")");
        } 
        
        return result;
    }

    @Override
    public ResultSet getUserByParam(String param) {
        ResultSet res = null;
        try{
            String sql = "SELECT nik NIK, "
                        + "nama_user as 'Nama User', "
                        + "role 'Jabatan', "
                        + "password Password, "
                        + "DATE_FORMAT(tanggal_system, '%d %M %Y %T') as 'Tanggal System', "
                        + "update_by as 'Update By'"
                        + "FROM mst_user "
                        + "WHERE nik <> '00000' "
                        + "AND (nik LIKE ? "
                        + "OR nama_user LIKE ? "
                        + "OR role LIKE ? )";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+param+"%");
            pst.setString(2, "%"+param+"%");
            pst.setString(3, "%"+param+"%");
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Search User ("+e.toString()+")");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Search User ("+ex.toString()+")");
        }
        return res;
    }

    @Override
    public ResultSet setPengirimTable() {
        ResultSet res = null;
        try{
            String sql = "SELECT nik NIK, "
                        + "nama_user as 'Nama User', "
                        + "role 'Jabatan' "
                        + "FROM mst_user "
                        + "WHERE nik <> '00000' "
                        + "AND role = 'Kurir' ";
            pst = con.prepareStatement(sql);
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Generate Data Pengirim ("+e.toString()+")");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Generate Data Pengirim ("+ex.toString()+")");
        }
        return res;
    }

    @Override
    public Map<String, String> getPengirim(String nik) {
        Map<String, String> map = new HashMap<String, String>();
        try{
            String sql = "SELECT nik NIK, "
                        + "nama_user as 'Nama User', "
                        + "role 'Jabatan' "
                        + "FROM mst_user "
                        + "WHERE nik <> '00000' "
                        + "AND role = 'Kurir' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                map.put("nikPengirim", rs.getString(1));
                map.put("namaPengirim", rs.getString(2));
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Get Data Pengirim ("+e.toString()+")");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Get Data Pengirim ("+ex.toString()+")");
        }
        return map;
    }

    @Override
    public ResultSet setPengirimTableByParam(String param) {
        ResultSet res = null;
        try{
            String sql = "SELECT nik NIK, "
                        + "nama_user as 'Nama User', "
                        + "role 'Jabatan' "
                        + "FROM mst_user "
                        + "WHERE nik <> '00000' "
                        + "AND role = 'Kurir' "
                        + "AND (nik LIKE ? "
                        + "OR nama_user LIKE ? "
                        + "OR role LIKE ?) ";
            pst = con.prepareStatement(sql);
            pst.setString(1, "%"+param+"%");
            pst.setString(2, "%"+param+"%");
            pst.setString(3, "%"+param+"%");
            res = pst.executeQuery();
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Generate Data Pengirim ("+e.toString()+")");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Generate Data Pengirim ("+ex.toString()+")");
        }
        return res;
    }

    @Override
    public Vector getListPengirim() {
        Vector vector = new Vector();
        try{
            String sql = "SELECT CONCAT(nik, ' - ', nama_user) "
                        + "FROM mst_user "
                        + "WHERE nik <> '00000' "
                        + "AND role = 'Kurir' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            vector.add("-- Pilih Pengirim --");
            while(rs.next()){
                vector.add(rs.getString(1));
            }
        } catch (SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null,"Error Get Data Pengirim ("+e.toString()+")");
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Error Get Data Pengirim ("+ex.toString()+")");
        }
        
        return vector;
    }
    
    
}
