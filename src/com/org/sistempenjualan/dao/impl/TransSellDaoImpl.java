/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.constant.ApprovalStatus;
import com.org.sistempenjualan.dao.TransSellDao;
import com.org.sistempenjualan.entity.TransSellDetail;
import com.org.sistempenjualan.entity.TransSell;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DIOR
 */
public class TransSellDaoImpl implements TransSellDao {
    Connection con = DbConnect.ConnectDb();
    PreparedStatement stmt = null;
    
   
    @Override
    public boolean add(TransSell transSell) {
        try {
            con.setAutoCommit(false);
            
            // Insert trans sell
            stmt = con.prepareStatement("INSERT INTO trans_sell "
                    + "(department, order_number, request_by, request_date) VALUES (?, ?, ?, now())");
            stmt.setString(1, transSell.getDepartment());
            stmt.setString(2, transSell.getOrderNumber());
            stmt.setString(3, transSell.getRequestBy());
            stmt.execute();
            
            // Get trans sell id
            stmt = con.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = stmt.executeQuery();
            int sellId = rs.next() ? rs.getInt(1) : 0;
            
            // Insert trans sell detail
            for (TransSellDetail detail : transSell.getDetails()) {
                 stmt = con.prepareStatement("INSERT INTO "
                        + "trans_sell_detail (sell_id, id_barang, qty, remark) "
                        + "VALUES (?, (select id from mst_barang where kode_barang = ? limit 1), ?, ?)");
                 stmt.setInt(1, sellId);
                 stmt.setString(2, detail.getKodeBarang());
                 stmt.setInt(3, detail.getQty());
                 stmt.setString(4, detail.getRemark());
                 stmt.execute();
            }
            
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException | HeadlessException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     *
     * @param from
     * @param to
     * @param nik
     * @param search
     * @param status
     * @return
     */
    @Override
    public ResultSet findBy(Date from, Date to, String nik, String search, String status) {
        try {
            stmt = con.prepareStatement("SELECT "
                    + "a.id as 'ID', "
                    + "a.department as 'Department', "
                    + "b.nama_user as 'Request by', "
                    + "a.request_date as 'Request Date', "
                    + "a.status as 'Status' "
                    + "FROM trans_sell a "
                    + "INNER JOIN mst_user b ON a.request_by = b.nik "
                    + "LEFT JOIN mst_user c ON a.approved_by = c.nik "
                    + "WHERE (DATE(a.request_date) BETWEEN DATE(?) and DATE(?)) "
                    + "AND (? IS NULL OR ? = b.nik) "
                    + "AND (a.department LIKE CONCAT('%', ?, '%')) "
                    + "AND (? IS NULL OR ? = a.status) ");
            stmt.setDate(1, new java.sql.Date(from.getTime()));
            stmt.setDate(2, new java.sql.Date(to.getTime()));
            stmt.setString(3, nik);
            stmt.setString(4, nik);
            stmt.setString(5, search);
            stmt.setString(6, status);
            stmt.setString(7, status);
            
            return stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement("DELETE FROM trans_sell_detail a WHERE a.sell_id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            
            stmt = con.prepareStatement("DELETE FROM trans_sell a WHERE a.id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public TransSell findById(int sellId) {
        try {
            stmt = con.prepareStatement("SELECT a.id, a.order_number, "
                    + "a.department, a.request_date, b.nama_user as request_by, "
                    + "a.approved_date, c.nama_user as approved_by,"
                    + "a.status, a.remark "
                    + "FROM trans_sell a "
                    + "INNER JOIN mst_user b on a.request_by = b.nik "
                    + "LEFT JOIN mst_user c on a.approved_by = c.nik "
                    + "WHERE a.id = ?");
            stmt.setInt(1, sellId);
            ResultSet rs1 = stmt.executeQuery();
                        
            if (rs1.next()) {
                TransSell transSell = new TransSell();
                transSell.setId(sellId);
                transSell.setDepartment(rs1.getString("department"));
                transSell.setOrderNumber(rs1.getString("order_number"));
                transSell.setRequestBy(rs1.getString("request_by"));
                transSell.setRequestDate(rs1.getDate("request_date"));
                transSell.setApprovedBy(rs1.getString("approved_by"));
                transSell.setApprovedDate(rs1.getDate("approved_date"));
                transSell.setStatus(rs1.getString("status"));
                transSell.setRemark(rs1.getString("remark"));
                
                List<TransSellDetail> details = new ArrayList<>();
                
                stmt = con.prepareStatement("SELECT a.id, a.id_barang, b.kode_barang, b.nama_barang, a.qty, a.remark "
                    + "FROM trans_sell_detail a "
                    + "INNER JOIN mst_barang b ON a.id_barang = b.id "
                    + "WHERE a.sell_id = ?");
                stmt.setInt(1, sellId);
                ResultSet rs2 = stmt.executeQuery();
                while(rs2.next()) {
                    details.add(new TransSellDetail(
                            rs2.getInt("id"),
                            rs2.getInt("id_barang"),
                            rs2.getString("kode_barang"),
                            rs2.getString("nama_barang"),
                            rs2.getInt("qty"),
                            rs2.getString("remark")
                    ));
                }
                transSell.setDetails(details);
                
                return transSell;
            } else {
                return null;
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public boolean update(TransSell sell) {
        try {
            con.setAutoCommit(false);
            
            stmt = con.prepareStatement("UPDATE trans_sell SET department = ?, order_number = ?, "
                            + "status = (CASE WHEN status = '"+ApprovalStatus.NEED_REVISED+"' THEN '"+ApprovalStatus.REQUESTED+"' ELSE status END) "
                            + "WHERE id = ?");
            stmt.setString(1, sell.getDepartment());
            stmt.setString(2, sell.getOrderNumber());
            stmt.setInt(3, sell.getId());
            stmt.execute();
            
            stmt = con.prepareStatement("SELECT (SELECT x.kode_barang FROM mst_barang x WHERE x.id = id_barang) as kode_barang FROM trans_sell_detail WHERE sell_id = ?");
            stmt.setInt(1, sell.getId());
            ResultSet rsA = stmt.executeQuery();
            List<String> existingItem = new ArrayList<>();
            while(rsA.next()) {
                String kodeBarang = rsA.getString("kode_barang");
                if (sell.getDetails().stream().anyMatch(e -> e.getKodeBarang().equals(kodeBarang))) {
                    existingItem.add(kodeBarang);
                } else {
                    stmt = con.prepareStatement("DELETE FROM trans_sell_detail "
                            + "WHERE sell_id = ? AND id_barang = (SELECT x.id FROM mst_barang x WHERE x.kode_barang = ?)");
                    stmt.setInt(1, sell.getId());
                    stmt.setString(2, kodeBarang);
                    stmt.execute();
                }
            }
            
            for (TransSellDetail d : sell.getDetails()) {
                if (existingItem.stream().anyMatch(e -> e.equals(d.getKodeBarang()))) {
                    // update
                    stmt = con.prepareStatement("UPDATE trans_sell_detail SET qty = ?, remark =? "
                            + "WHERE sell_id = ? AND id_barang = (SELECT x.id FROM mst_barang x WHERE x.kode_barang = ?)");
                    stmt.setInt(1, d.getQty());
                    stmt.setString(2, d.getRemark());
                    stmt.setInt(3, sell.getId());
                    stmt.setString(4, d.getKodeBarang());
                    stmt.execute();
                } else {
                    // insert
                    stmt = con.prepareStatement("INSERT INTO "
                            + "trans_sell_detail (sell_id, id_barang, qty, remark) "
                            + "VALUES (?, (select id from mst_barang where kode_barang = ? limit 1), ?, ?)");
                    stmt.setInt(1, sell.getId());
                    stmt.setString(2, d.getKodeBarang());
                    stmt.setInt(3, d.getQty());
                    stmt.setString(4, d.getRemark());
                    stmt.execute();
                }
            }
            
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public boolean revise(String nik, int sellId, String reason) {
        return updateStatus(nik, sellId, ApprovalStatus.NEED_REVISED, reason);
    }

    @Override
    public boolean reject(String nik, int sellId, String reason) {
        return updateStatus(nik, sellId, ApprovalStatus.REJECTED, reason);
    }

    @Override
    public boolean approve(String nik, int sellId) {
        return updateStatus(nik, sellId, ApprovalStatus.APPROVED, "");
    }
    
    public boolean updateStatus(String nik, int sellId, String status, String reason) {
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement("UPDATE trans_sell SET status = ?, remark = ?, approved_by = ?, approved_date = now() WHERE id = ?");
            stmt.setString(1, status);
            stmt.setString(2, reason);
            stmt.setString(3, nik);
            stmt.setInt(4, sellId);
            stmt.execute();
            
            if (status.equals(ApprovalStatus.APPROVED)) {
                stmt = con.prepareStatement("UPDATE mst_barang a "
                        + "SET a.jumlah = a.jumlah - (select sum(x.qty) from trans_sell_detail x where x.sell_id = ? and x.id_barang = a.id group by x.sell_id, x.id_barang) " 
                        + "WHERE id in(select id_barang from trans_sell_detail where sell_id = ?)");
                stmt.setInt(1, sellId);
                stmt.setInt(2, sellId);
                stmt.execute();
            }
            
            con.commit();
            con.setAutoCommit(true);
            return true;            
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
