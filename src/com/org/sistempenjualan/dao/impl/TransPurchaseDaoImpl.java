/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.dao.impl;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.constant.ApprovalStatus;
import com.org.sistempenjualan.dao.TransPurchaseDao;
import com.org.sistempenjualan.entity.TransPurchaseDetail;
import com.org.sistempenjualan.entity.TransPurchase;
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
public class TransPurchaseDaoImpl implements TransPurchaseDao {
    Connection con = DbConnect.ConnectDb();
    PreparedStatement stmt = null;
    
   
    @Override
    public boolean add(TransPurchase transPurchase) {
        try {
            con.setAutoCommit(false);
            
            // Insert trans purchase
            stmt = con.prepareStatement("INSERT INTO trans_purchase "
                    + "(project, department, level_status, request_by, "
                    + "request_date) VALUES (?, ?, ?, ?, now())");
            stmt.setString(1, transPurchase.getProject());
            stmt.setString(2, transPurchase.getDepartment());
            stmt.setString(3, transPurchase.getLevelStatus());
            stmt.setString(4, transPurchase.getRequestBy());
            stmt.execute();
            
            // Get trans_purchase id
            stmt = con.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rs = stmt.executeQuery();
            int purchaseId = rs.next() ? rs.getInt(1) : 0;
            
            // Insert trans purchase detail
            for (TransPurchaseDetail detail : transPurchase.getDetails()) {
                 stmt = con.prepareStatement("INSERT INTO "
                        + "trans_purchase_detail (purchase_id, id_barang, qty) "
                        + "VALUES (?, (select id from mst_barang where kode_barang = ? limit 1), ?)");
                 stmt.setInt(1, purchaseId);
                 stmt.setString(2, detail.getKodeBarang());
                 stmt.setInt(3, detail.getJumlahBarang());
                 stmt.execute();
            }
            
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException | HeadlessException e) {
            Logger.getLogger(TransPurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, e);
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
                    + "a.id as 'ID', a.project as 'Project', "
                    + "a.department as 'Department', "
                    + "b.nama_user as 'Request by', "
                    + "a.request_date as 'Request Date', "
//                    + "c.nama_user as 'Approved/Rejected/Revise Requested by', "
//                    + "a.approved_date as 'Approved/Rejected/Revise Requested Date', "
                    + "a.status as 'Status' "
                    + "FROM trans_purchase a "
                    + "INNER JOIN mst_user b ON a.request_by = b.nik "
                    + "LEFT JOIN mst_user c ON a.approved_by = c.nik "
                    + "WHERE (DATE(a.request_date) BETWEEN DATE(?) and DATE(?)) "
                    + "AND (? IS NULL OR ? = b.nik) "
                    + "AND (a.department LIKE CONCAT('%', ?, '%') OR a.project LIKE CONCAT('%', ?, '%') ) "
                    + "AND (? IS NULL OR ? = a.status) ");
            stmt.setDate(1, new java.sql.Date(from.getTime()));
            stmt.setDate(2, new java.sql.Date(to.getTime()));
            stmt.setString(3, nik);
            stmt.setString(4, nik);
            stmt.setString(5, search);
            stmt.setString(6, search);
            stmt.setString(7, status);
            stmt.setString(8, status);
            
            return stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(TransPurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement("DELETE FROM trans_purchase_detail a WHERE a.purchase_id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            
            stmt = con.prepareStatement("DELETE FROM trans_purchase a WHERE a.id = ?");
            stmt.setInt(1, id);
            stmt.execute();
            
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(TransPurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public TransPurchase findById(int purchaseId) {
        try {
            stmt = con.prepareStatement("SELECT a.id, a.project, a.department, a.request_date, b.nama_user as request_by, a.approved_date, c.nama_user as approved_by,"
                    + "a.status, a.remark "
                    + "FROM trans_purchase a "
                    + "INNER JOIN mst_user b on a.request_by = b.nik "
                    + "LEFT JOIN mst_user c on a.approved_by = c.nik "
                    + "WHERE a.id = ?");
            stmt.setInt(1, purchaseId);
            ResultSet rs1 = stmt.executeQuery();
                        
            if (rs1.next()) {
                TransPurchase transPurchase = new TransPurchase();
                transPurchase.setId(purchaseId);
                transPurchase.setProject(rs1.getString("project"));
                transPurchase.setDepartment(rs1.getString("department"));
                transPurchase.setRequestBy(rs1.getString("request_by"));
                transPurchase.setRequestDate(rs1.getDate("request_date"));
                transPurchase.setApprovedBy(rs1.getString("approved_by"));
                transPurchase.setApprovedDate(rs1.getDate("approved_date"));
                transPurchase.setStatus(rs1.getString("status"));
                transPurchase.setRemark(rs1.getString("remark"));
                
                List<TransPurchaseDetail> details = new ArrayList<>();
                
                stmt = con.prepareStatement("SELECT a.id, a.id_barang, b.kode_barang, b.nama_barang, a.qty "
                    + "FROM trans_purchase_detail a "
                    + "INNER JOIN mst_barang b ON a.id_barang = b.id "
                    + "WHERE a.purchase_id = ?");
                stmt.setInt(1, purchaseId);
                ResultSet rs2 = stmt.executeQuery();
                while(rs2.next()) {
                    details.add(new TransPurchaseDetail(
                            rs2.getInt("id"),
                            rs2.getInt("id_barang"),
                            rs2.getString("kode_barang"),
                            rs2.getString("nama_barang"),
                            rs2.getInt("qty"),
                            null
                    ));
                }
                transPurchase.setDetails(details);
                
                return transPurchase;
            } else {
                return null;
            }
        } catch (SQLException e) {
            Logger.getLogger(TransPurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public boolean update(TransPurchase purchase) {
        try {
            con.setAutoCommit(false);
            
            stmt = con.prepareStatement("UPDATE trans_purchase SET project = ?, department = ?, "
                            + "status = (CASE WHEN status = '"+ApprovalStatus.NEED_REVISED+"' THEN '"+ApprovalStatus.REJECTED+"' ELSE status END) "
                            + "WHERE id = ?");
            stmt.setString(1, purchase.getProject());
            stmt.setString(2, purchase.getDepartment());
            stmt.setInt(3, purchase.getId());
            stmt.execute();
            
            stmt = con.prepareStatement("SELECT (SELECT x.kode_barang FROM mst_barang x WHERE x.id = id_barang) as kode_barang FROM trans_purchase_detail WHERE purchase_id = ?");
            stmt.setInt(1, purchase.getId());
            ResultSet rsA = stmt.executeQuery();
            List<String> existingItem = new ArrayList<>();
            while(rsA.next()) {
                String kodeBarang = rsA.getString("kode_barang");
                if (purchase.getDetails().stream().anyMatch(e -> e.getKodeBarang().equals(kodeBarang))) {
                    existingItem.add(kodeBarang);
                } else {
                    stmt = con.prepareStatement("DELETE FROM trans_purchase_detail "
                            + "WHERE purchase_id = ? AND id_barang = (SELECT x.id FROM mst_barang x WHERE x.kode_barang = ?)");
                    stmt.setInt(1, purchase.getId());
                    stmt.setString(2, kodeBarang);
                    stmt.execute();
                }
            }
            
            for (TransPurchaseDetail d : purchase.getDetails()) {
                if (existingItem.stream().anyMatch(e -> e.equals(d.getKodeBarang()))) {
                    // update
                    stmt = con.prepareStatement("UPDATE trans_purchase_detail SET qty = ? "
                            + "WHERE purchase_id = ? AND id_barang = (SELECT x.id FROM mst_barang x WHERE x.kode_barang = ?)");
                    stmt.setInt(1, d.getJumlahBarang());
                    stmt.setInt(2, purchase.getId());
                    stmt.setString(3, d.getKodeBarang());
                    stmt.execute();
                } else {
                    // insert
                    stmt = con.prepareStatement("INSERT INTO "
                            + "trans_purchase_detail (purchase_id, id_barang, qty) "
                            + "VALUES (?, (select id from mst_barang where kode_barang = ? limit 1), ?)");
                    stmt.setInt(1, purchase.getId());
                    stmt.setString(2, d.getKodeBarang());
                    stmt.setInt(3, d.getJumlahBarang());
                    stmt.execute();
                }
            }
            
            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(TransPurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public boolean revise(String nik, int purchaseId, String reason) {
        return updateStatus(nik, purchaseId, ApprovalStatus.NEED_REVISED, reason);
    }

    @Override
    public boolean reject(String nik, int purchaseId, String reason) {
        return updateStatus(nik, purchaseId, ApprovalStatus.REJECTED, reason);
    }

    @Override
    public boolean approve(String nik, int purchaseId) {
        return updateStatus(nik, purchaseId, ApprovalStatus.APPROVED, "");
    }
    
    public boolean updateStatus(String nik, int purchaseId, String status, String reason) {
        try {
            con.setAutoCommit(false);
            stmt = con.prepareStatement("UPDATE trans_purchase SET status = ?, remark = ?, approved_by = ?, approved_date = now() WHERE id = ?");
            stmt.setString(1, status);
            stmt.setString(2, reason);
            stmt.setString(3, nik);
            stmt.setInt(4, purchaseId);
            stmt.execute();
            
            if (status.equals(ApprovalStatus.APPROVED)) {
                stmt = con.prepareStatement("UPDATE mst_barang a "
                        + "SET a.jumlah = a.jumlah + (select sum(x.qty) from trans_purchase_detail x where x.purchase_id = ? and x.id_barang = a.id group by x.purchase_id, x.id_barang) " 
                        + "WHERE id in(select id_barang from trans_purchase_detail where purchase_id = ?)");
                stmt.setInt(1, purchaseId);
                stmt.setInt(2, purchaseId);
                stmt.execute();
            }
            
            con.commit();
            con.setAutoCommit(true);
            return true;            
        } catch (SQLException e) {
            Logger.getLogger(TransPurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
}
