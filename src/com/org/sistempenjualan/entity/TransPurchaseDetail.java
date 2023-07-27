/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.sistempenjualan.entity;

/**
 *
 * @author user
 */
public class TransPurchaseDetail {
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    private int id;
    private int idBarang;
    private String kodeBarang;
    private String namaBarang;
    private int jumlahBarang;
    private String type;

    public TransPurchaseDetail() {
    }

    public TransPurchaseDetail(String kodeBarang, int jumlahBarang) {
        this.kodeBarang = kodeBarang;
        this.jumlahBarang = jumlahBarang;
    }
        
    public TransPurchaseDetail(int id, int idBarang, String namaBarang, int jumlahBarang, String type) {
        this.id = id;
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.jumlahBarang = jumlahBarang;
        this.type = type;
    }
    
    public TransPurchaseDetail(int id, int idBarang, String kodeBarang, String namaBarang, int jumlahBarang, String type) {
        this.id = id;
        this.idBarang = idBarang;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.jumlahBarang = jumlahBarang;
        this.type = type;
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

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public int getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(int jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
