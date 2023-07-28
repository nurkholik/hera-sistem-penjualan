/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.sistempenjualan.entity;

/**
 *
 * @author user
 */
public class TransSellDetail {
    private int index;
    
    private int id;
    private int idBarang;
    private String kodeBarang;
    private String namaBarang;
    private int qty;
    private String remark;

    public TransSellDetail() {
    }

    public TransSellDetail(String kodeBarang, int jumlahBarang, String remark) {
        this.kodeBarang = kodeBarang;
        this.qty = jumlahBarang;
        this.remark = remark;
    }
        
    public TransSellDetail(int id, int idBarang, String namaBarang, int jumlahBarang, String type) {
        this.id = id;
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.qty = jumlahBarang;
        this.remark = type;
    }
    
    public TransSellDetail(int id, int idBarang, String kodeBarang, String namaBarang, int jumlahBarang, String type) {
        this.id = id;
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.qty = jumlahBarang;
        this.remark = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
