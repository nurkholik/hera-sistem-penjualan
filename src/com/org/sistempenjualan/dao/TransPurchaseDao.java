/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.sistempenjualan.dao;

import com.org.sistempenjualan.entity.TransPurchase;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author user
 */
public interface TransPurchaseDao {
    boolean add(TransPurchase transPurchase);
    default ResultSet findBy(Date from, Date to, String nik, String search) {
        return findBy(from, to, nik, search, null);
    }
    ResultSet findBy(Date from, Date to, String nik, String search, String status);
    boolean deleteById(int id);
    TransPurchase findById(int purchaseId);
    boolean update(TransPurchase purchase);
    public boolean approve(String nik, int purchaseId, String remark);
    public boolean reject(String nik, int purchaseId, String remark);
    public boolean revise(String nik, int purchaseId, String remark);
}
