/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.gui;


import com.org.sistempenjualan.backup.DetailSuratJalanForm;
import com.org.sistempenjualan.backup.SuratJalanBaruForm;
import com.org.sistempenjualan.gui.SuratJalanForm;
import com.org.sistempenjualan.dao.UserDao;
import com.org.sistempenjualan.dao.impl.UserDaoImpl;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.org.sistempenjualan.entity.Entity;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JTextField;

/**
 *
 * @author DIOR
 */
public class LoginForm extends javax.swing.JFrame  {
    // Inisialisasi GUI
    public UserForm userForm;
    public SupplierForm supplierForm;
    public BarangForm barangForm;
    public CustomerForm customerForm;
    public SuratJalanForm suratJalanForm;
    public SuratJalanBaruForm suratJalanBaruForm;
    public DetailSuratJalanForm detailSuratJalanForm;
    public PemesananForm pemesananForm;
    public PemesananBaruForm pemesananBaruForm;
    public DetailPemesananForm detailPemesananForm;
    public PembayaranForm pembayaranForm;
    
    // Inisialisasi Objek
    Entity entity = new Entity();
    
    UserDao dao = new UserDaoImpl();
    
    public LoginForm() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblNik = new javax.swing.JLabel();
        txtNik = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Log In");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("FORM LOGIN");

        lblNik.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNik.setText("NIK");

        txtNik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNikKeyTyped(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPassword.setText("PASSWORD");

        btnLogin.setText("LOGIN");
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoginMouseClicked(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNik)
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(161, 161, 161))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNik, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174))))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(lblPassword)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addComponent(lblNik)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(lblPassword)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(btnLogin)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLoginMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        try{
            entity.setNik(txtNik.getText());
            entity.setPassword(String.valueOf(txtPassword.getPassword()));
            dao.getUserLogin(entity);
            String kode = entity.getRoleSession().toLowerCase();
                switch(kode){
                    case"admin":
                        pembayaranForm = new PembayaranForm(entity);
                        pembayaranForm.setVisible(true);
//                        detailPemesananForm = new DetailPemesananForm(entity);
//                        detailPemesananForm.setVisible(true);
//                        pemesananBaruForm = new PemesananBaruForm(entity);
//                        pemesananBaruForm.setVisible(true);
//                        pemesananForm = new PemesananForm(entity);
//                        pemesananForm.setVisible(true);
//                        detailSuratJalanForm = new DetailSuratJalanForm(entity);
//                        detailSuratJalanForm.setVisible(true);
//                        suratJalanForm = new SuratJalanForm(entity);
//                        suratJalanForm.setVisible(true);
//                        customerForm = new CustomerForm(entity);
//                        customerForm.setVisible(true);
//                        supplierForm = new SupplierForm(entity);
//                        supplierForm.setVisible(true);
//                        userForm = new UserForm(entity);
//                        userForm.setVisible(true);
//                        barangForm = new BarangForm(entity);
//                        barangForm.setVisible(true);
                        this.dispose();   
                        break;
                    case"inventory":
                        barangForm = new BarangForm(entity);
                        barangForm.setVisible(true);
                        this.dispose();   
                        break;
                }
        }catch(Exception e){
            try {
                throw new Exception(e);
            } catch (Exception ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//                    default:
//                        tl=new Karyawan();
//                        tl.setVisible(true);
//                        this.dispose();
//                        break;
//                }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtNikKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNikKeyTyped
        // TODO add your handling code here:
        if(txtNik.getText().length()>=5){  
           evt.consume();
        }
    }//GEN-LAST:event_txtNikKeyTyped

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
        this.btnLoginActionPerformed(evt);
    }//GEN-LAST:event_txtPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNik;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField txtNik;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
