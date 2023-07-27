/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author DIOR
 */
public class Entity {
    // variabel global
    private String userSession;
    private String nikSession;
    private String roleSession;
    
    // variabel user
    private String nik;
    private String userName;
    private String kodeUser;
    private String password;
    private String role;
    
    // variabel supplier
    private String kodeSupplier;
    private String namaSupplier;
    private String alamatSupplier;
    private String kota;
    private String tlpSupplier;
    
    // variabel customer
    private int idCustomer;
    private String noCustomer;
    private String namaCustomer;
    private String alamatCustomer;
    private String kotaCustomer;
    private String tlpCustomer;
    
    // variabel barang
    private int idBarang;
    private String kodeBarang;
    private String namaBarang;
    private int jumlahBarang;
    private int hargaBarang;
    
    // variabel surat jalan
    private int idSuratJalan;
    private String noSuratJalan;
    private String nikPengirim;
    private String namaPengirim;
    private String tanggalPengiriman;
    private int statusPengiriman;
    
    // variabel pemesanan
    private int idPemesanan;
    private String noPemesanan;
    private int totalOrder;
    private String tanggalPemesanan;
    private int statusPemesanan;
    private String keteranganPemesanan;
    private int idDetailPemesanan;
    
    // variabel pembayaran/transaksi
    private int idTransaksi;
    private String noTransaksi;
    private String metodePembayaran;
    private String jenisPembayaran;
    private String tanggalTransaksi;
    private String tanggalBayar;
    private int totalBayar;
    private int yangDibayar;
    private int selisih;
    
    public String getMd5(String password) throws NoSuchAlgorithmException{
        String hash ="";
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        
        hash = sb.toString();
        System.out.println(hash);
        return hash;
    }
    
    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getKodeUser() {
        return kodeUser;
    }
    
    public void setKodeUser(String kodeUser) {
        this.kodeUser = kodeUser;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }   
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getAlamatSupplier() {
        return alamatSupplier;
    }

    public void setAlamatSupplier(String alamatSupplier) {
        this.alamatSupplier = alamatSupplier;
    }

    public String getKota() {
        return kota;
    }
    
    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getUserSession() {
        return userSession;
    }

    public void setUserSession(String userSession) {
        this.userSession = userSession;
    }
    
    public String getNikSession() {
        return nikSession;
    }

    public void setNikSession(String nikSession) {
        this.nikSession = nikSession;
    }

    public String getRoleSession() {
        return roleSession;
    }

    public void setRoleSession(String roleSession) {
        this.roleSession = roleSession;
    }

    public String getKodeSupplier() {
        return kodeSupplier;
    }

    public void setKodeSupplier(String kodeSupplier) {
        this.kodeSupplier = kodeSupplier;
    }

    public String getTlpSupplier() {
        return tlpSupplier;
    }

    public void setTlpSupplier(String tlpSupplier) {
        this.tlpSupplier = tlpSupplier;
    }

    public String getNoCustomer() {
        return noCustomer;
    }

    public void setNoCustomer(String noCustomer) {
        this.noCustomer = noCustomer;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer = namaCustomer;
    }

    public String getAlamatCustomer() {
        return alamatCustomer;
    }

    public void setAlamatCustomer(String alamatCustomer) {
        this.alamatCustomer = alamatCustomer;
    }

    public String getKotaCustomer() {
        return kotaCustomer;
    }

    public void setKotaCustomer(String kotaCustomer) {
        this.kotaCustomer = kotaCustomer;
    }

    public String getTlpCustomer() {
        return tlpCustomer;
    }

    public void setTlpCustomer(String tlpCustomer) {
        this.tlpCustomer = tlpCustomer;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
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

    public int getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(int jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public int getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(int hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getIdSuratJalan() {
        return idSuratJalan;
    }

    public void setIdSuratJalan(int idSuratJalan) {
        this.idSuratJalan = idSuratJalan;
    }

    public String getNoSuratJalan() {
        return noSuratJalan;
    }

    public void setNoSuratJalan(String noSuratJalan) {
        this.noSuratJalan = noSuratJalan;
    }

    public String getNikPengirim() {
        return nikPengirim;
    }

    public void setNikPengirim(String nikPengirim) {
        this.nikPengirim = nikPengirim;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getTanggalPemesanan() {
        return tanggalPemesanan;
    }

    public void setTanggalPemesanan(String tanggalPemesanan) {
        this.tanggalPemesanan = tanggalPemesanan;
    }

    public String getTanggalPengiriman() {
        return tanggalPengiriman;
    }

    public void setTanggalPengiriman(String tanggalPengiriman) {
        this.tanggalPengiriman = tanggalPengiriman;
    }

    public int getStatusPengiriman() {
        return statusPengiriman;
    }

    public void setStatusPengiriman(int statusPengiriman) {
        this.statusPengiriman = statusPengiriman;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public int getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(int idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public String getNoPemesanan() {
        return noPemesanan;
    }

    public void setNoPemesanan(String noPemesanan) {
        this.noPemesanan = noPemesanan;
    }

    public int getStatusPemesanan() {
        return statusPemesanan;
    }

    public void setStatusPemesanan(int statusPemesanan) {
        this.statusPemesanan = statusPemesanan;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public int getIdDetailPemesanan() {
        return idDetailPemesanan;
    }

    public void setIdDetailPemesanan(int idDetailPemesanan) {
        this.idDetailPemesanan = idDetailPemesanan;
    }

    public String getKeteranganPemesanan() {
        return keteranganPemesanan;
    }

    public void setKeteranganPemesanan(String keteranganPemesanan) {
        this.keteranganPemesanan = keteranganPemesanan;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(String tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    public int getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(int totalBayar) {
        this.totalBayar = totalBayar;
    }

    public int getYangDibayar() {
        return yangDibayar;
    }

    public void setYangDibayar(int yangDibayar) {
        this.yangDibayar = yangDibayar;
    }

    public int getSelisih() {
        return selisih;
    }

    public void setSelisih(int selisih) {
        this.selisih = selisih;
    }
    
}
