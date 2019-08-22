/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.Utility;
import com.org.sistempenjualan.dao.PembayaranDao;
import com.org.sistempenjualan.dao.PemesananDao;
import com.org.sistempenjualan.dao.SuratJalanDao;
import com.org.sistempenjualan.dao.UserDao;
import com.org.sistempenjualan.dao.impl.PembayaranDaoImpl;
import com.org.sistempenjualan.dao.impl.PemesananDaoImpl;
import com.org.sistempenjualan.dao.impl.SuratJalanDaoImpl;
import com.org.sistempenjualan.dao.impl.UserDaoImpl;
import com.org.sistempenjualan.entity.Entity;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DIOR
 */
public class PembayaranForm extends javax.swing.JFrame {
    
    // Global Objek
    Entity entity = new Entity();
    Utility util = new Utility();
    PembayaranDao dao = new PembayaranDaoImpl();
    SuratJalanDao sjlDao = new SuratJalanDaoImpl();
    PemesananDao psnDao = new PemesananDaoImpl();
    UserDao usrDao = new UserDaoImpl();
    
    // Global Variabel
    String nikSession = "";
    int idPemesanan = 0;
    int idSuratJalan = 0;
    int totalBayar = 0;
    /**
     * Creates new form PembayaranForm
     */
    public PembayaranForm(Entity a) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        nikSession = a.getNikSession();
        userSession.setText("Login As: "+a.getUserSession());
        entity.setNikSession(nikSession);
        entity.setUserSession(a.getUserSession());
        entity.setRoleSession(a.getRoleSession());
        mbPembayaran.add(Box.createHorizontalGlue());
        mbPembayaran.add(todayDate);
        mbPembayaran.add(userSession);
        util.tanggalSekarang(todayDate);
        txtNoTransaksi.setEditable(false);
        txtNoSuratJalan.setEditable(false);
        txtTglBayar.setEnabled(true);
        cbJenisPembayaran.setEnabled(true);
        txtSelisih.setEditable(false);
        txtTotalBayar.setEditable(false);
        setupTable();
        generateNoPembayaran();
        getListPemesanan();
        clearForm();
    }
    
    public void setupTable(){
        tblPembayaran.setModel(DbUtils.resultSetToTableModel(dao.setPembayaranTable()));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(0));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(1));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(2));
        util.adjustColumn(tblPembayaran);
    }
    
    public void generateNoPembayaran(){
        try{
            Calendar cal = new GregorianCalendar();
            int intTanggal = cal.get(Calendar.DAY_OF_MONTH);
            String tanggal;
            if(intTanggal<10){
                tanggal = "0"+intTanggal;
            }else{
                tanggal = String.valueOf(intTanggal);
            }
            int intBulan = cal.get(Calendar.MONTH);
            String bulan = "";
            if(intBulan<10){
                bulan = "0"+(intBulan+1);
            }else{
                bulan = String.valueOf((intBulan+1));
            }
            int tahun = cal.get(Calendar.YEAR);
            
            String lastKode = dao.getLastNoPembayaran();
            String newKode = "";
            if(!lastKode.equals("")){
                String lastSeq = lastKode.substring(15);
                String newSeqKode = ""+(Integer.parseInt(lastSeq)+1);
                String nol = "";
                if(newSeqKode.length() == 1){
                    nol = "000";
                } else if(newSeqKode.length() == 2){
                    nol = "00";
                } else if(newSeqKode.length() == 3){
                    nol = "0";
                } else if(newSeqKode.length() == 4){
                    nol = "";
                }

                newKode = "STR/"+tahun+"/"+bulan+"/"+tanggal+"/"+nol+newSeqKode;
            }else{
                newKode = "STR/"+tahun+"/"+bulan+"/"+tanggal+"/0001";
            }
            
            txtNoTransaksi.setText(newKode);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
    }
    
    public void clearForm(){
        txtCariNoPembayaran.setText("");
        generateNoPembayaran();
        cbJenisPembayaran.setSelectedIndex(0);
        cbMetodePembayaran.setSelectedIndex(0);
        cbPemesanan.setSelectedIndex(0);
        txtNoSuratJalan.setText("");
        ((JTextField)txtTglBayar.getDateEditor().getUiComponent()).setText("");
        ((JTextField)txtTglTransaksi.getDateEditor().getUiComponent()).setText("");
        txtTotalBayar.setText("0");
        txtYangDibayar.setText("0");
        txtSelisih.setText("0");
    }
    
    public void getListPemesanan(){
        cbPemesanan.setModel(new DefaultComboBoxModel(psnDao.getAllNoPemesanan()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblPembayaran = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtNoTransaksi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbJenisPembayaran = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbMetodePembayaran = new javax.swing.JComboBox();
        txtTglTransaksi = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTglBayar = new com.toedter.calendar.JDateChooser();
        btnBaru = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtSelisih = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCariNoPembayaran = new javax.swing.JTextField();
        lblCariPembayaran = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTotalBayar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtYangDibayar = new javax.swing.JTextField();
        cbPemesanan = new javax.swing.JComboBox();
        txtNoSuratJalan = new javax.swing.JTextField();
        mbPembayaran = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnLogOut = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        todayDate = new javax.swing.JMenu();
        userSession = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Pembayaran");
        setAutoRequestFocus(false);

        tblPembayaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblPembayaran);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("No Transaksi");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Metode Pembayaran");

        cbJenisPembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih Jenis Pembayaran --", "Tunai", "Transfer" }));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Jenis Pembayaran");

        cbMetodePembayaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Pilih Metode Pembayaran --", "Tunai", "Pay Later" }));
        cbMetodePembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMetodePembayaranActionPerformed(evt);
            }
        });

        txtTglTransaksi.setDateFormatString("dd MMMM yyyy");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Tanggal Transaksi");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Tanggal Bayar");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("No Surat Jalan");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("No Pemesanan");

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Total Bayar");

        txtTglBayar.setDateFormatString("dd MMMM yyyy");

        btnBaru.setText("Baru");

        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtSelisih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSelisihActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Selisih");

        txtCariNoPembayaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariNoPembayaranActionPerformed(evt);
            }
        });

        lblCariPembayaran.setText("Cari :");

        jLabel11.setText("Rp.");

        jLabel12.setText("Rp.");

        txtTotalBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalBayarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("Yang Dibayarkan");

        jLabel14.setText("Rp.");

        txtYangDibayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtYangDibayarActionPerformed(evt);
            }
        });
        txtYangDibayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtYangDibayarKeyReleased(evt);
            }
        });

        cbPemesanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPemesananActionPerformed(evt);
            }
        });

        jMenu1.setText("Server");

        btnLogOut.setText("Log Out");
        jMenu1.add(btnLogOut);

        mbPembayaran.add(jMenu1);

        jMenu2.setText("Form");
        mbPembayaran.add(jMenu2);

        todayDate.setText("Date");
        mbPembayaran.add(todayDate);

        userSession.setText("User");
        mbPembayaran.add(userSession);

        setJMenuBar(mbPembayaran);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCariPembayaran)
                        .addGap(18, 18, 18)
                        .addComponent(txtCariNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBaru))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(cbPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNoSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel2)
                                            .addComponent(cbMetodePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5)
                                            .addComponent(txtTglBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4)
                                            .addComponent(txtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel13)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel14)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtYangDibayar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel12)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel9)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel11)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtSelisih, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(cbJenisPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCariPembayaran))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtYangDibayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBaru))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(cbPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNoSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTglBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbMetodePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(cbJenisPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSelisih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnReset))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariNoPembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariNoPembayaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariNoPembayaranActionPerformed

    private void txtSelisihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSelisihActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSelisihActionPerformed

    private void txtTotalBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalBayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalBayarActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtYangDibayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtYangDibayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYangDibayarActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        boolean result = false;
        String noTransaksi = txtNoTransaksi.getText();
        String metodePembayaran = "";
        if(cbMetodePembayaran.getSelectedIndex() != 0){
            metodePembayaran = (String) cbMetodePembayaran.getSelectedItem();
        }
        String jenisPembayaran = "";
        if(cbJenisPembayaran.getSelectedIndex() != 0){
            jenisPembayaran = (String) cbJenisPembayaran.getSelectedItem();
        }
        int dibayar = Integer.parseInt(txtYangDibayar.getText());
        int selisih = Integer.parseInt(txtSelisih.getText());
        try{
            entity.setNoTransaksi(noTransaksi);
            entity.setMetodePembayaran(metodePembayaran);
            entity.setJenisPembayaran(jenisPembayaran);
            entity.setTanggalTransaksi(((JTextField)txtTglTransaksi.getDateEditor().getUiComponent()).getText());
            entity.setTanggalBayar(((JTextField)txtTglBayar.getDateEditor().getUiComponent()).getText());
            entity.setIdPemesanan(idPemesanan);
            entity.setIdSuratJalan(idSuratJalan);
            entity.setTotalBayar(totalBayar);
            entity.setYangDibayar(dibayar);
            entity.setSelisih(selisih);
            result = dao.createPembayaran(entity);
            if(result){
                JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan!");
                clearForm();
                setupTable();
            }else{
                JOptionPane.showMessageDialog(null, "Error simpan transaksi!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error simpan transaksi! - "+e);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void cbPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPemesananActionPerformed
        String noPemesanan = (String) cbPemesanan.getSelectedItem();
        Map<String, Object> map = new HashMap<String, Object>();
        
        try{
            idPemesanan = psnDao.getIdPemesanan(noPemesanan);
            totalBayar = psnDao.getTotalBayar(idPemesanan);
            txtTotalBayar.setText(String.valueOf(totalBayar));
            txtYangDibayar.setText(String.valueOf(totalBayar));
            map = sjlDao.getNoSuratJalan(noPemesanan);
            if(!map.isEmpty()){
                idSuratJalan = Integer.parseInt(map.get("idSuratJalan").toString());
                entity.setIdSuratJalan(idSuratJalan);
                txtNoSuratJalan.setText(map.get("noSuratJalan").toString());
            }else{
                entity.setIdSuratJalan(idSuratJalan);
                txtNoSuratJalan.setText("");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_cbPemesananActionPerformed

    private void cbMetodePembayaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodePembayaranActionPerformed
        if(cbMetodePembayaran.getSelectedIndex() == 1){
            cbJenisPembayaran.setEnabled(false);
            txtTglBayar.setEnabled(true);
        }else if(cbMetodePembayaran.getSelectedIndex() == 2){
            cbJenisPembayaran.setEnabled(true);
            txtTglBayar.setEnabled(false);
            ((JTextField)txtTglBayar.getDateEditor().getUiComponent()).setText("");
        }
    }//GEN-LAST:event_cbMetodePembayaranActionPerformed

    private void txtYangDibayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYangDibayarKeyReleased
        int totalBayar = 0;
        totalBayar = Integer.parseInt(txtTotalBayar.getText());
        int dibayar = 0;
        dibayar = Integer.parseInt(txtYangDibayar.getText());
        int selisih = 0;
        
        selisih = totalBayar - dibayar;
        txtSelisih.setText(String.valueOf(selisih));
    }//GEN-LAST:event_txtYangDibayarKeyReleased

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaru;
    private javax.swing.JMenu btnLogOut;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cbJenisPembayaran;
    private javax.swing.JComboBox cbMetodePembayaran;
    private javax.swing.JComboBox cbPemesanan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCariPembayaran;
    private javax.swing.JMenuBar mbPembayaran;
    private javax.swing.JTable tblPembayaran;
    private javax.swing.JMenu todayDate;
    private javax.swing.JTextField txtCariNoPembayaran;
    private javax.swing.JTextField txtNoSuratJalan;
    private javax.swing.JTextField txtNoTransaksi;
    private javax.swing.JTextField txtSelisih;
    private com.toedter.calendar.JDateChooser txtTglBayar;
    private com.toedter.calendar.JDateChooser txtTglTransaksi;
    private javax.swing.JTextField txtTotalBayar;
    private javax.swing.JTextField txtYangDibayar;
    private javax.swing.JMenu userSession;
    // End of variables declaration//GEN-END:variables
}
