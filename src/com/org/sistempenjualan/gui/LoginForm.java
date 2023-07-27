/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.dao.UserDao;
import com.org.sistempenjualan.dao.impl.UserDaoImpl;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.org.sistempenjualan.entity.Entity;
import javax.swing.ImageIcon;

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

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNik = new javax.swing.JTextField();
        lblNik = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ARKA");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resource/images/logo.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNikKeyTyped(evt);
            }
        });

        lblNik.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNik.setText("NIK");

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblPassword.setText("PASSWORD");

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

        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(lblNik)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNik)
                        .addComponent(txtPassword)
                        .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblNik)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
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
//                        pembayaranForm = new PembayaranForm(entity);
//                        pembayaranForm.setVisible(true);
//                        detailPemesananForm = new DetailPemesananForm(entity);
//                        detailPemesananForm.setVisible(true);
//                        pemesananBaruForm = new PemesananBaruForm(entity);
//                        pemesananBaruForm.setVisible(true);
//                        pemesananForm = new PemesananForm(entity);
//                        pemesananForm.setVisible(true);
//                        suratJalanForm = new SuratJalanForm(entity);
//                        suratJalanForm.setVisible(true);
//                        customerForm = new CustomerForm(entity);
//                        customerForm.setVisible(true);
//                        supplierForm = new SupplierForm(entity);
//                        supplierForm.setVisible(true);
                        userForm = new UserForm(entity);
                        userForm.setVisible(true);
//                        barangForm = new BarangForm(entity);
//                        barangForm.setVisible(true);
                        this.dispose();   
                        break;
                    case"inventory":
//                        supplierForm = new SupplierForm(entity);
//                        supplierForm.setVisible(true);
                        barangForm = new BarangForm(entity);
                        barangForm.setVisible(true);
                        this.dispose();   
                        break;
                    case"kasir":
                        pembayaranForm = new PembayaranForm(entity);
                        pembayaranForm.setVisible(true);
                        this.dispose();   
                        break;
                    case"registrasi":
                        customerForm = new CustomerForm(entity);
                        customerForm.setVisible(true);
                        this.dispose();   
                        break;
                    case"direktur":
                        new PurchaseApprovalForm(entity).setVisible(true);
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
        java.awt.EventQueue.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblNik;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField txtNik;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
