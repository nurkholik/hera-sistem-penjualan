/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.DbConnect;
import com.org.sistempenjualan.Utility;
import com.org.sistempenjualan.dao.PemesananDao;
import com.org.sistempenjualan.dao.SuratJalanDao;
import com.org.sistempenjualan.dao.UserDao;
import com.org.sistempenjualan.dao.impl.PemesananDaoImpl;
import com.org.sistempenjualan.dao.impl.SuratJalanDaoImpl;
import com.org.sistempenjualan.dao.impl.UserDaoImpl;
import com.org.sistempenjualan.entity.Entity;
import java.io.File;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class SuratJalanForm extends javax.swing.JFrame {

    // Global Objek
    Entity entity = new Entity();
    Utility util = new Utility();
    SuratJalanDao dao = new SuratJalanDaoImpl();
    PemesananDao psnDao = new PemesananDaoImpl();
    UserDao usrDao = new UserDaoImpl();
    
    // Global Variabel
    String nikSession = "";
    int idPemesanan = 0;
    int idSuratJalan = 0;
    boolean flagUpdate = false;
    
    /**
     * Creates new form SuratJalanForm
     */
    public SuratJalanForm(Entity a) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        nikSession = a.getNikSession();
        userSession.setText("Login As: "+a.getUserSession());
        entity.setNikSession(nikSession);
        entity.setUserSession(a.getUserSession());
        entity.setRoleSession(a.getRoleSession());
        mbSuratJalan.add(Box.createHorizontalGlue());
        mbSuratJalan.add(todayDate);
        mbSuratJalan.add(userSession);
        util.tanggalSekarang(todayDate);
        txtNoSuratJalan.setEditable(false);
        setupTable();
//        generateNoSuratJalan();
        getListPemesanan();
        getListPengirim();
        clearForm();
        cbNoPesanan.setEnabled(true);
        txtTglPengiriman.getCalendar();
        txtTglPengiriman.setMinSelectableDate(new Date());
        flagUpdate = false;
        btnCancel.setEnabled(true);
    }
    
    public void setupTable(){
        tblSuratJalan.setModel(DbUtils.resultSetToTableModel(dao.setSuratJalanTable()));
        tblSuratJalan.removeColumn(tblSuratJalan.getColumnModel().getColumn(0));
        util.adjustColumn(tblSuratJalan);
    }
    
    public void generateNoSuratJalan(){
        try{
            Calendar cal = new GregorianCalendar();
            int intBulan = cal.get(Calendar.MONTH);
            String bulan = "";
            if(intBulan<10){
                bulan = "0"+(intBulan+1);
            }else{
                bulan = String.valueOf((intBulan+1));
            }
            int tahun = cal.get(Calendar.YEAR);
            
            String lastKode = dao.getLastNoSuratJalan();
            String newKode = "";
            if(!lastKode.equals("")){
                String lastSeq = lastKode.substring(12);
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

                newKode = "SJL/"+tahun+"/"+bulan+"/"+nol+newSeqKode;
            }else{
                newKode = "SJL/"+tahun+"/"+bulan+"/0001";
            }
            
            txtNoSuratJalan.setText(newKode);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this,e);
        }
    }
    
    public void getListPemesanan(){
        cbNoPesanan.setModel(new DefaultComboBoxModel(psnDao.getListNoPemesanan()));
    }
    
    public void getListPengirim(){
        cbPengirim.setModel(new DefaultComboBoxModel(usrDao.getListPengirim()));
    }
    
    public void clearForm(){
        txtCariSuratJalan.setText("");
        cbNoPesanan.setSelectedIndex(0);
        cbPengirim.setSelectedIndex(0);
        ((JTextField)txtTglPengiriman.getDateEditor().getUiComponent()).setText("");
    }
    
    public void cetakSuratJalan(String noSuratJalan){
        try {
            String namaFile = "src"+File.separator+"com"+File.separator+"org"+File.separator+"sistempenjualan"+File.separator+"report"+File.separator+"SuratJalan.jasper";
            Connection conn = DbConnect.ConnectDb();

            Map<String,Object> map =  new HashMap<String, Object>();
            map.put("no_surat_jalan", noSuratJalan);
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
        tblSuratJalan = new javax.swing.JTable();
        txtCariSuratJalan = new javax.swing.JTextField();
        lblCariSuratJalan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNoSuratJalan = new javax.swing.JTextField();
        cbNoPesanan = new javax.swing.JComboBox();
        lblNoPesanan = new javax.swing.JLabel();
        lblPengirim = new javax.swing.JLabel();
        cbPengirim = new javax.swing.JComboBox();
        btnSimpan = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        lblTglPengiriman = new javax.swing.JLabel();
        txtTglPengiriman = new com.toedter.calendar.JDateChooser();
        btnReset = new javax.swing.JButton();
        btnBaru = new javax.swing.JButton();
        btnCetakSuratJalan = new javax.swing.JButton();
        mbSuratJalan = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnLogOut = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuCustomer = new javax.swing.JMenuItem();
        menuPemesanan = new javax.swing.JMenuItem();
        todayDate = new javax.swing.JMenu();
        userSession = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Surat Jalan");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblSuratJalan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSuratJalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuratJalanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSuratJalan);

        txtCariSuratJalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariSuratJalanKeyReleased(evt);
            }
        });

        lblCariSuratJalan.setText("Cari :");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("No Surat Jalan");

        cbNoPesanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNoPesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNoPesananActionPerformed(evt);
            }
        });

        lblNoPesanan.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoPesanan.setText("No Pemesanan");

        lblPengirim.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPengirim.setText("Pengirim");

        cbPengirim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPengirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPengirimActionPerformed(evt);
            }
        });

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        lblTglPengiriman.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTglPengiriman.setText("Tanggal Pengiriman");

        txtTglPengiriman.setDateFormatString("dd MMMM yyyy");

        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnBaru.setText("Baru");
        btnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaruActionPerformed(evt);
            }
        });

        btnCetakSuratJalan.setText("CETAK");
        btnCetakSuratJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakSuratJalanActionPerformed(evt);
            }
        });

        jMenu1.setText("Server");

        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        jMenu1.add(btnLogOut);

        mbSuratJalan.add(jMenu1);

        jMenu2.setText("Form");

        menuCustomer.setText("Form Customer");
        menuCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCustomerActionPerformed(evt);
            }
        });
        jMenu2.add(menuCustomer);

        menuPemesanan.setText("Form Pemesanan");
        menuPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPemesananActionPerformed(evt);
            }
        });
        jMenu2.add(menuPemesanan);

        mbSuratJalan.add(jMenu2);

        todayDate.setText("Date");
        mbSuratJalan.add(todayDate);

        userSession.setText("User");
        mbSuratJalan.add(userSession);

        setJMenuBar(mbSuratJalan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNoSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblNoPesanan)
                            .addComponent(cbNoPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPengirim)
                            .addComponent(lblTglPengiriman)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtTglPengiriman, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbPengirim, javax.swing.GroupLayout.Alignment.LEADING, 0, 214, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCetakSuratJalan)
                        .addGap(157, 157, 157)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCariSuratJalan)
                        .addGap(18, 18, 18)
                        .addComponent(txtCariSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCariSuratJalan))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBaru))
                .addGap(18, 18, 18)
                .addComponent(lblNoPesanan)
                .addGap(18, 18, 18)
                .addComponent(cbNoPesanan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPengirim)
                .addGap(18, 18, 18)
                .addComponent(cbPengirim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTglPengiriman)
                .addGap(18, 18, 18)
                .addComponent(txtTglPengiriman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnCancel)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addComponent(btnCetakSuratJalan)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbNoPesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNoPesananActionPerformed
        String noPemesanan = (String) cbNoPesanan.getSelectedItem();
        try{
            idPemesanan = psnDao.getIdPemesanan(noPemesanan);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_cbNoPesananActionPerformed

    private void cbPengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPengirimActionPerformed
        
    }//GEN-LAST:event_cbPengirimActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        boolean result = false;
        String cbVal = (String) cbPengirim.getSelectedItem();
        String noPengiriman = (String) cbNoPesanan.getSelectedItem();
        int idPemesanan;
        String[] arrCb = cbVal.split(" - ");
        String pengirim = arrCb[0];
        String noSuratJalan = txtNoSuratJalan.getText();
        try{
            if(!flagUpdate){
                if(cbNoPesanan.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(rootPane, "Harus Pilih No Pemesanan Terlebih Dulu!");
                } else if(cbPengirim.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(rootPane, "Harus Pilih Pengirim Terlebih Dulu!");
                } else {
                    int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan data?","Simpan Data",JOptionPane.YES_NO_OPTION);
                    if(confirm==0){
                        idPemesanan = psnDao.getIdPemesanan(noPengiriman);
                        entity.setNoSuratJalan(noSuratJalan);
                        entity.setIdPemesanan(idPemesanan);
                        entity.setNikPengirim(pengirim);
                        entity.setTanggalPengiriman(((JTextField)txtTglPengiriman.getDateEditor().getUiComponent()).getText());
                        result = dao.createSuratJalan(entity);
                        if(result){
                            JOptionPane.showMessageDialog(null, "Berhasil Simpan Data!");
                            generateNoSuratJalan();
                            getListPemesanan();
                            clearForm();
                            setupTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error Simpan Data!");
                        }
                    }
                }
            }else{
                if(cbPengirim.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(rootPane, "Harus Pilih Pengirim Terlebih Dulu!");
                }else{
                    int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan data?","Simpan Data",JOptionPane.YES_NO_OPTION);
                    if(confirm==0){
                        idPemesanan = psnDao.getIdPemesanan(noPengiriman);
                        entity.setNoSuratJalan(noSuratJalan);
                        entity.setNikPengirim(pengirim);
                        entity.setTanggalPengiriman(((JTextField)txtTglPengiriman.getDateEditor().getUiComponent()).getText());
                        result = dao.editSuratJalan(entity);
                        if(result){
                            JOptionPane.showMessageDialog(null, "Berhasil Simpan Data!");
                            generateNoSuratJalan();
                            getListPemesanan();
                            cbNoPesanan.setEnabled(true);
                            clearForm();
                            setupTable();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error Simpan Data!");
                        }
                    }
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        boolean result = false;
        String noSuratJalan = txtNoSuratJalan.getText();
        
        try{
            if(idSuratJalan == 0){
                idSuratJalan = dao.getIdSuratJalan(noSuratJalan);
            }
            result = dao.cancelSuratJalan(idSuratJalan);
            if(result){
                JOptionPane.showMessageDialog(null, "Berhasil Cancel Data!");
                generateNoSuratJalan();
                getListPemesanan();
                clearForm();
                setupTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error Cancel Data!");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtCariSuratJalanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariSuratJalanKeyReleased
        String param = txtCariSuratJalan.getText();
        tblSuratJalan.setModel(DbUtils.resultSetToTableModel(dao.setSuratJalanTable(param)));
        tblSuratJalan.removeColumn(tblSuratJalan.getColumnModel().getColumn(0));
        util.adjustColumn(tblSuratJalan);
    }//GEN-LAST:event_txtCariSuratJalanKeyReleased

    private void tblSuratJalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuratJalanMouseClicked
        Vector vector = new Vector();
        
        int row = tblSuratJalan.getSelectedRow();
        
        TableModel model = tblSuratJalan.getModel();
        
        idSuratJalan = Integer.parseInt(model.getValueAt(row, 0).toString());
        
        txtNoSuratJalan.setText(model.getValueAt(row, 1).toString());
        cbPengirim.setSelectedItem(model.getValueAt(row, 2).toString());
        ((JTextField)txtTglPengiriman.getDateEditor().getUiComponent()).setText(model.getValueAt(row, 3).toString());
        vector.add(model.getValueAt(row, 4).toString());
        cbNoPesanan.setModel(new DefaultComboBoxModel(vector));
        vector.clear();
        cbNoPesanan.setEnabled(false);
        flagUpdate = true;
    }//GEN-LAST:event_tblSuratJalanMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        getListPemesanan();
        cbNoPesanan.setEnabled(true);
        clearForm();
        flagUpdate = false;
    }//GEN-LAST:event_btnResetActionPerformed

    private void menuCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCustomerActionPerformed
        this.setVisible(false);
        new CustomerForm(entity).setVisible(true);
    }//GEN-LAST:event_menuCustomerActionPerformed

    private void menuPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPemesananActionPerformed
        this.setVisible(false);
        new PemesananForm(entity).setVisible(true);
    }//GEN-LAST:event_menuPemesananActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, 
            "Apakah anda yakin ingin menutup form?", "Tutup Form", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaruActionPerformed
        String txtBtnBaru = btnBaru.getText();
        if(txtBtnBaru.equals("Baru")){
            btnBaru.setText("Cancel");
            flagUpdate = false;
            generateNoSuratJalan();
            getListPemesanan();
            cbNoPesanan.setEnabled(true);
            clearForm();
            btnCancel.setEnabled(true);
        }else{
            btnBaru.setText("Baru");
            flagUpdate = true;
            getListPemesanan();
            cbNoPesanan.setEnabled(true);
            clearForm();
            txtNoSuratJalan.setText("");
            btnCancel.setEnabled(false);
        }
    }//GEN-LAST:event_btnBaruActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        this.setVisible(false);
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnCetakSuratJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakSuratJalanActionPerformed
        String noSuratJalan = txtNoSuratJalan.getText();
        if(!noSuratJalan.equals("")){
            cetakSuratJalan(noSuratJalan);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Silahkan Pilih Nomor Surat Jalan!");
        }   
    }//GEN-LAST:event_btnCetakSuratJalanActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaru;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCetakSuratJalan;
    private javax.swing.JMenuItem btnLogOut;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cbNoPesanan;
    private javax.swing.JComboBox cbPengirim;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCariSuratJalan;
    private javax.swing.JLabel lblNoPesanan;
    private javax.swing.JLabel lblPengirim;
    private javax.swing.JLabel lblTglPengiriman;
    private javax.swing.JMenuBar mbSuratJalan;
    private javax.swing.JMenuItem menuCustomer;
    private javax.swing.JMenuItem menuPemesanan;
    private javax.swing.JTable tblSuratJalan;
    private javax.swing.JMenu todayDate;
    private javax.swing.JTextField txtCariSuratJalan;
    private javax.swing.JTextField txtNoSuratJalan;
    private com.toedter.calendar.JDateChooser txtTglPengiriman;
    private javax.swing.JMenu userSession;
    // End of variables declaration//GEN-END:variables
}
