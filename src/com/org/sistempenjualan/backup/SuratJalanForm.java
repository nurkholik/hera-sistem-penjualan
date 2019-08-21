/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.backup;

import com.org.sistempenjualan.Utility;
import com.org.sistempenjualan.dao.SuratJalanDao;
import com.org.sistempenjualan.dao.impl.SuratJalanDaoImpl;
import com.org.sistempenjualan.entity.Entity;
import com.org.sistempenjualan.gui.SupplierForm;
import javax.swing.Box;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DIOR
 */
public class SuratJalanForm extends javax.swing.JFrame {
    public DetailSuratJalanForm detialForm;
    public SupplierForm supplierForm;
    
    // Global Objek
    Entity entity = new Entity();
    Utility util = new Utility();
    SuratJalanDao dao = new SuratJalanDaoImpl();
    
    // Global Variabel
    String nikSession = "";
    
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
        setupTable();
        mbSuratJalan.add(Box.createHorizontalGlue());
        mbSuratJalan.add(todayDate);
        mbSuratJalan.add(userSession);
        util.tanggalSekarang(todayDate);
        this.setEnabled(true);
    }
    
    public void setupTable(){
//        tblSuratJalan.setModel(DbUtils.resultSetToTableModel(dao.setHeaderSuratJalanTable()));
//        tblSuratJalan.removeColumn(tblSuratJalan.getColumnModel().getColumn(0));
//        util.adjustColumn(tblSuratJalan);
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
        jLabel1 = new javax.swing.JLabel();
        btnSuratBaru = new javax.swing.JButton();
        mbSuratJalan = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnLogOut = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuSupplier = new javax.swing.JMenu();
        menuItem = new javax.swing.JMenuItem();
        todayDate = new javax.swing.JMenu();
        userSession = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Surat Jalan");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblSuratJalan = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column)
            {
                return false;//This causes all cells to be not editable
            }
        };
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
        jScrollPane1.setViewportView(tblSuratJalan);

        txtCariSuratJalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariSuratJalanKeyReleased(evt);
            }
        });

        jLabel1.setText("Cari :");

        btnSuratBaru.setText("Buat Baru");
        btnSuratBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuratBaruActionPerformed(evt);
            }
        });

        jMenu1.setText("Server");

        btnLogOut.setText("Log Out");
        jMenu1.add(btnLogOut);

        mbSuratJalan.add(jMenu1);

        jMenu2.setText("Form");

        menuSupplier.setText("Form Supplier");
        jMenu2.add(menuSupplier);

        menuItem.setText("Form Item");
        jMenu2.add(menuItem);

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
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1075, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSuratBaru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCariSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCariSuratJalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnSuratBaru))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuratBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuratBaruActionPerformed
        this.setVisible(false);
        new SuratJalanBaruForm(entity).setVisible(true);
    }//GEN-LAST:event_btnSuratBaruActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, 
            "Apakah anda yakin ingin menutup form?", "Tutup Form", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void txtCariSuratJalanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariSuratJalanKeyReleased
        
    }//GEN-LAST:event_txtCariSuratJalanKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnLogOut;
    private javax.swing.JButton btnSuratBaru;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar mbSuratJalan;
    private javax.swing.JMenuItem menuItem;
    private javax.swing.JMenu menuSupplier;
    private javax.swing.JTable tblSuratJalan;
    private javax.swing.JMenu todayDate;
    private javax.swing.JTextField txtCariSuratJalan;
    private javax.swing.JMenu userSession;
    // End of variables declaration//GEN-END:variables
}
