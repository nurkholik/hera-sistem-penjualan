/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.Utility;
import com.org.sistempenjualan.dao.CustomerDao;
import com.org.sistempenjualan.dao.impl.CustomerDaoImpl;
import com.org.sistempenjualan.entity.Entity;
import java.awt.KeyboardFocusManager;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DIOR
 */
public class CustomerForm extends javax.swing.JFrame {
    public SupplierForm supplierForm;
    
    // Global Objek
    Entity entity = new Entity();
    Utility util = new Utility();
    CustomerDao dao = new CustomerDaoImpl();
    
    // Global Variabel
    boolean flagUpdate = false;
    String nikSession = "";
    int idCustomer;
    
    public CustomerForm(Entity a) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        txtAlamat.setWrapStyleWord(true);
        txtAlamat.setLineWrap(true);
        txtAlamat.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        txtAlamat.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
        nikSession = a.getNikSession();
        userSession.setText("Login As: "+a.getUserSession());
        entity.setNikSession(nikSession);
        entity.setUserSession(a.getUserSession());
        entity.setRoleSession(a.getRoleSession());
        mbCustomer.add(Box.createHorizontalGlue());
        mbCustomer.add(todayDate);
        mbCustomer.add(userSession);
        util.tanggalSekarang(todayDate);
        txtNoCustomer.setEditable(false);
        btnHapus.setEnabled(true);
        setupTable();
        clearForm();
    }
    
    public void setupTable(){
        tblCustomer.setModel(DbUtils.resultSetToTableModel(dao.setCustomerTable()));
        tblCustomer.removeColumn(tblCustomer.getColumnModel().getColumn(0));
        util.adjustColumn(tblCustomer);
    }
    
    public void clearForm(){
        // Clear Value
        btnCustBaru.setText("Baru");
        txtNoCustomer.setText("");
        txtNamaCustomer.setText("");
        txtAlamat.setText("");
        txtKota.setText("");
        txtNoTelp.setText("");
        setupTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNoCustomer = new javax.swing.JLabel();
        txtNoCustomer = new javax.swing.JTextField();
        lblNamaCustomer = new javax.swing.JLabel();
        txtNamaCustomer = new javax.swing.JTextField();
        lblAlamat = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        lblKota = new javax.swing.JLabel();
        txtKota = new javax.swing.JTextField();
        btnCustBaru = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        lblCariCust = new javax.swing.JLabel();
        txtCariCust = new javax.swing.JTextField();
        lblNoTelp = new javax.swing.JLabel();
        txtNoTelp = new javax.swing.JTextField();
        mbCustomer = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnLogOut = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuSupplier = new javax.swing.JMenuItem();
        todayDate = new javax.swing.JMenu();
        userSession = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Customer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblNoCustomer.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoCustomer.setText("No Customer");

        lblNamaCustomer.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaCustomer.setText("Nama Customer");

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAlamat.setText("Alamat");

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        jScrollPane1.setViewportView(txtAlamat);

        lblKota.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblKota.setText("Kota");

        btnCustBaru.setText("Baru");
        btnCustBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustBaruActionPerformed(evt);
            }
        });

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCustomer);

        lblCariCust.setText("Cari :");

        txtCariCust.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariCustKeyReleased(evt);
            }
        });

        lblNoTelp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNoTelp.setText("No Telepon");

        jMenu1.setText("Server");

        btnLogOut.setText("Log Out");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        jMenu1.add(btnLogOut);

        mbCustomer.add(jMenu1);

        jMenu2.setText("Form");

        menuSupplier.setText("Form Supplier");
        menuSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSupplierActionPerformed(evt);
            }
        });
        jMenu2.add(menuSupplier);

        mbCustomer.add(jMenu2);

        todayDate.setText("Date");
        mbCustomer.add(todayDate);

        userSession.setText("User");
        mbCustomer.add(userSession);

        setJMenuBar(mbCustomer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCariCust)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCariCust, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblKota)
                                        .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNoTelp)
                                        .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addComponent(lblAlamat)
                                        .addComponent(lblNamaCustomer)
                                        .addComponent(txtNamaCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                        .addComponent(lblNoCustomer)
                                        .addComponent(txtNoCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(btnCustBaru, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCariCust)
                    .addComponent(txtCariCust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNoCustomer)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNoCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCustBaru))
                        .addGap(18, 18, 18)
                        .addComponent(lblNamaCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(txtNamaCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAlamat)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblKota)
                        .addGap(18, 18, 18)
                        .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNoTelp)
                        .addGap(18, 18, 18)
                        .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnHapus)
                            .addComponent(btnReset)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCustBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustBaruActionPerformed
        String txtBtnCust = btnCustBaru.getText();
        String newKode = "";
        Calendar cal = new GregorianCalendar();
        int tahun = cal.get(Calendar.YEAR);
        if(txtBtnCust.equals("Baru")){
            flagUpdate = false;
            btnCustBaru.setText("Cancel");
            String lastKode = dao.getLastNoCustomer();
            if(!lastKode.equals("")){
                String lastSeq = lastKode.substring(6);
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

                newKode = "CS"+tahun+nol+newSeqKode;
            }else{
                newKode = "CS"+tahun+"0001";
            }
            
            txtNoCustomer.setText(newKode);
            txtNamaCustomer.setText("");
            txtAlamat.setText("");
            txtKota.setText("");
            txtNoTelp.setText("");
        }else{
            flagUpdate = true;
            btnCustBaru.setText("Baru");
            txtNoCustomer.setText("");
        }
    }//GEN-LAST:event_btnCustBaruActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        boolean result = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan data?","Simpan Data",JOptionPane.YES_NO_OPTION);
        try{
            if(confirm==0){
                if(!flagUpdate){
                    entity.setNoCustomer(txtNoCustomer.getText());
                    entity.setNamaCustomer(txtNamaCustomer.getText());
                    entity.setAlamatCustomer(txtAlamat.getText());
                    entity.setKotaCustomer(txtKota.getText());
                    entity.setTlpCustomer(txtNoTelp.getText());
                    entity.setUserSession(nikSession);
                    result = dao.addCustomer(entity);
                    if(result){
                        JOptionPane.showMessageDialog(rootPane,"Data Berhasil disimpan!");
                        clearForm();
                    }else{
                        JOptionPane.showMessageDialog(rootPane,"Error Simpan Customer");
                    }
                }else{
                    entity.setNamaCustomer(txtNamaCustomer.getText());
                    entity.setAlamatCustomer(txtAlamat.getText());
                    entity.setKotaCustomer(txtKota.getText());
                    entity.setTlpCustomer(txtNoTelp.getText());
                    entity.setUserSession(nikSession);
                    entity.setIdCustomer(idCustomer);
                    result = dao.editCustomer(entity);
                    if(result){
                        JOptionPane.showMessageDialog(rootPane,"Data Berhasil diupdate!");
                        clearForm();
                    }else{
                        JOptionPane.showMessageDialog(rootPane,"Error Update Customer");
                    }
                }
            }
        }catch(Exception e){
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        boolean result = false;
        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?","Hapus Data",JOptionPane.YES_NO_OPTION);
        try{
            if(confirm==0){
                result = dao.deleteCustomer(idCustomer);
                if(result){
                    JOptionPane.showMessageDialog(rootPane,"Data Berhasil dihapus!");
                    clearForm();
                }else{
                    JOptionPane.showMessageDialog(rootPane,"Error Hapus Customer!");
                }
            }
        }catch(Exception e){
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        btnCustBaru.setText("Baru");
        flagUpdate = true;
        int row = tblCustomer.getSelectedRow();
        
        TableModel model = tblCustomer.getModel();
        
        idCustomer = Integer.parseInt(model.getValueAt(row, 0).toString());
        txtNoCustomer.setText(model.getValueAt(row, 1).toString());
        txtNamaCustomer.setText(model.getValueAt(row, 2).toString());
        txtAlamat.setText(model.getValueAt(row, 3).toString());
        txtKota.setText(model.getValueAt(row, 4).toString());
        txtNoTelp.setText(model.getValueAt(row, 5).toString());
        btnHapus.setEnabled(true);
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtCariCustKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariCustKeyReleased
        String param = txtCariCust.getText();
        tblCustomer.setModel(DbUtils.resultSetToTableModel(dao.getCustomerByParam(param)));
        tblCustomer.removeColumn(tblCustomer.getColumnModel().getColumn(0));
        util.adjustColumn(tblCustomer);
    }//GEN-LAST:event_txtCariCustKeyReleased

    private void menuSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSupplierActionPerformed
        this.setVisible(false);
        new SupplierForm(entity).setVisible(true);
    }//GEN-LAST:event_menuSupplierActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        this.setVisible(false);
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, 
            "Apakah anda yakin ingin menutup form?", "Tutup Form", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCustBaru;
    private javax.swing.JButton btnHapus;
    private javax.swing.JMenuItem btnLogOut;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblCariCust;
    private javax.swing.JLabel lblKota;
    private javax.swing.JLabel lblNamaCustomer;
    private javax.swing.JLabel lblNoCustomer;
    private javax.swing.JLabel lblNoTelp;
    private javax.swing.JMenuBar mbCustomer;
    private javax.swing.JMenuItem menuSupplier;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JMenu todayDate;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCariCust;
    private javax.swing.JTextField txtKota;
    private javax.swing.JTextField txtNamaCustomer;
    private javax.swing.JTextField txtNoCustomer;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JMenu userSession;
    // End of variables declaration//GEN-END:variables
}
