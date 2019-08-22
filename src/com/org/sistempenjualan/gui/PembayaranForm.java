/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.DbConnect;
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
import java.io.File;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
    boolean flagUpdate = false;
    /**
     * Creates new form PembayaranForm
     */
    public PembayaranForm(Entity a) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
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
        txtNoTransaksi.setText("");
        btnBaru.setText("Baru");
        setupTable();
        //generateNoPembayaran();
        getListPemesanan();
        clearForm();
        txtTglTransaksi.getCalendar();
        txtTglTransaksi.setMinSelectableDate(new Date());
        txtTglBayar.getCalendar();
        txtTglBayar.setMinSelectableDate(new Date());
    }
    
    public void setupTable(){
        tblPembayaran.setModel(DbUtils.resultSetToTableModel(dao.setPembayaranTable()));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(0));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(0));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(0));
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
        txtNoTransaksi.setText("");
//        generateNoPembayaran();
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
    
    public void cetakResep(String noTransaksi){
        try {
            String namaFile = "src"+File.separator+"com"+File.separator+"org"+File.separator+"sistempenjualan"+File.separator+"report"+File.separator+"TandaTerima.jasper";
            Connection conn = DbConnect.ConnectDb();

            Map<String,Object> map =  new HashMap<String, Object>();
            map.put("no_transaksi", noTransaksi);
            JasperPrint jprint = JasperFillManager.fillReport(namaFile.toString(), map, conn);
            if(jprint.getPages().size() != 0){
                JasperViewer.viewReport(jprint,false);
            }else{
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan !");
            }
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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
        btnCetakResep = new javax.swing.JButton();
        mbPembayaran = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnLogOut = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        todayDate = new javax.swing.JMenu();
        userSession = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Pembayaran");
        setAutoRequestFocus(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
        tblPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPembayaranMouseClicked(evt);
            }
        });
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
        btnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaruActionPerformed(evt);
            }
        });

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
        txtCariNoPembayaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariNoPembayaranKeyReleased(evt);
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

        btnCetakResep.setText("CETAK");
        btnCetakResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakResepActionPerformed(evt);
            }
        });

        jMenu1.setText("Server");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        jMenu1.add(btnLogOut);

        mbPembayaran.add(jMenu1);

        jMenu2.setText("Form");

        jMenuItem1.setText("Form Report");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNoSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBaru))
                            .addComponent(jLabel8)
                            .addComponent(cbPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(cbJenisPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(cbMetodePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtTglBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(73, 73, 73)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtSelisih, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel13)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtYangDibayar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCetakResep, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblCariPembayaran)
                            .addGap(18, 18, 18)
                            .addComponent(txtCariNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariNoPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCariPembayaran))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel12))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotalBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtYangDibayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtTglTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtTglBayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSelisih, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cbMetodePembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(cbJenisPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(cbPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSimpan)
                                .addComponent(btnReset)
                                .addComponent(btnCetakResep))
                            .addComponent(txtNoSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        getListPemesanan();
        cbPemesanan.setEnabled(true);
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
            if(!flagUpdate){
                result = dao.createPembayaran(entity);
                if(result){
                    JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan!");
                    clearForm();
                    setupTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Error simpan transaksi!");
                }
            }else{
                result = dao.updatePembayaran(entity);
                if(result){
                    JOptionPane.showMessageDialog(null, "Transaksi berhasil diupdate!");
                    clearForm();
                    setupTable();
                }else{
                    JOptionPane.showMessageDialog(null, "Error update transaksi!");
                }
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

    private void btnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaruActionPerformed
        String txtBtnBaru = btnBaru.getText();
        if(txtBtnBaru.equals("Baru")){
            flagUpdate = false;
            btnBaru.setText("Cancel");
            clearForm();
            generateNoPembayaran();
        }else{
            flagUpdate = true;
            btnBaru.setText("Baru");
            clearForm();
            
        }
    }//GEN-LAST:event_btnBaruActionPerformed

    private void tblPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPembayaranMouseClicked
        btnBaru.setText("Baru");
        flagUpdate = true;
        int row = tblPembayaran.getSelectedRow();
        
        TableModel model = tblPembayaran.getModel();
        String noSuratJalan = "";
        if(model.getValueAt(row, 5) != null){
            noSuratJalan = model.getValueAt(row, 5).toString();
        }
        
        txtNoSuratJalan.setText(noSuratJalan);
        
        if(model.getValueAt(row, 1) != null){
            idPemesanan = Integer.parseInt(model.getValueAt(row, 1).toString());
        }
        if(model.getValueAt(row, 2) != null){
            idSuratJalan = Integer.parseInt(model.getValueAt(row, 2).toString());
        }
        if(model.getValueAt(row, 3) != null){
            txtNoTransaksi.setText(model.getValueAt(row, 3).toString());
        }else{
            txtNoTransaksi.setText("");
        }
        if(model.getValueAt(row, 4) != null){
            Vector vector = new Vector();
            vector.add(model.getValueAt(row, 4).toString());
            cbPemesanan.setModel(new DefaultComboBoxModel(vector));
            cbPemesanan.setEnabled(false);
            vector.clear();
            
        }else{
            cbPemesanan.setSelectedIndex(0);
        }
        
        if(model.getValueAt(row, 6) != null){
            cbMetodePembayaran.setSelectedItem(model.getValueAt(row, 6).toString());
        }else{
            cbMetodePembayaran.setSelectedIndex(0);
        }
        if(model.getValueAt(row, 7) != null){
            cbJenisPembayaran.setSelectedItem(model.getValueAt(row, 7).toString());
        }else{
            cbJenisPembayaran.setSelectedIndex(0);
        }
        if(model.getValueAt(row, 8) != null){
            ((JTextField)txtTglTransaksi.getDateEditor().getUiComponent()).setText(model.getValueAt(row, 8).toString());
        }else{
            ((JTextField)txtTglTransaksi.getDateEditor().getUiComponent()).setText("");
        }
        if(model.getValueAt(row, 9) != null){
            ((JTextField)txtTglBayar.getDateEditor().getUiComponent()).setText(model.getValueAt(row, 9).toString());
        }else{
            ((JTextField)txtTglBayar.getDateEditor().getUiComponent()).setText("");
        }
        if(model.getValueAt(row, 10) != null){
            txtTotalBayar.setText(model.getValueAt(row, 10).toString());
            totalBayar = Integer.parseInt(model.getValueAt(row, 10).toString());
        }else{
            txtTotalBayar.setText("0");
            totalBayar = 0;
        }
        if(model.getValueAt(row, 11) != null){
            txtYangDibayar.setText(model.getValueAt(row, 11).toString());
        }else{
            txtYangDibayar.setText("0");
        }
        if(model.getValueAt(row, 12) != null){
            txtSelisih.setText(model.getValueAt(row, 12).toString());
        }else{
            txtSelisih.setText("0");
        }
    }//GEN-LAST:event_tblPembayaranMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, 
            "Apakah anda yakin ingin menutup form?", "Tutup Form", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void txtCariNoPembayaranKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariNoPembayaranKeyReleased
        String param = txtCariNoPembayaran.getText();
        tblPembayaran.setModel(DbUtils.resultSetToTableModel(dao.setPembayaranTable(param)));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(0));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(0));
        tblPembayaran.removeColumn(tblPembayaran.getColumnModel().getColumn(0));
        util.adjustColumn(tblPembayaran);
    }//GEN-LAST:event_txtCariNoPembayaranKeyReleased

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        this.setVisible(false);
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnCetakResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakResepActionPerformed
        String noTransaksi = txtNoTransaksi.getText();
        if(!noTransaksi.equals("")){
            cetakResep(noTransaksi);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Silahkan Pilih Nomor Transaksi!");
        }  
    }//GEN-LAST:event_btnCetakResepActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        new ReportBulananForm(entity).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaru;
    private javax.swing.JButton btnCetakResep;
    private javax.swing.JMenuItem btnLogOut;
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
    private javax.swing.JMenuItem jMenuItem1;
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
