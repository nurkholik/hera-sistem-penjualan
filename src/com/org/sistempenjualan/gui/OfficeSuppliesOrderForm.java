/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.Utility;
import com.org.sistempenjualan.constant.ApprovalStatus;
import com.org.sistempenjualan.dao.TransSellDao;
import com.org.sistempenjualan.dao.impl.TransSellDaoImpl;
import com.org.sistempenjualan.entity.Entity;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.Box;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author user
 */
public class OfficeSuppliesOrderForm extends javax.swing.JFrame {

    private Entity entity;
    private final Utility util = new Utility();
    private final TransSellDao sellDao = new TransSellDaoImpl();
    
    /**
     * @param entity
     */
    public OfficeSuppliesOrderForm(Entity entity) {
        initComponents();
        setupWindow();
        setupUserInfo(entity);
        initForm();
        initTable();
        loadDataTable();
    }
    
    private void setupWindow() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    private void setupUserInfo(Entity a) {
        this.entity = a;
        userSession.setText("Login As: "+a.getUserSession());
        entity.setNikSession(a.getNikSession());
        entity.setUserSession(a.getUserSession());
        entity.setRoleSession(a.getRoleSession());
        mbItem.add(Box.createHorizontalGlue());
        mbItem.add(todayDate);
        mbItem.add(userSession);
        util.tanggalSekarang(todayDate);
    }
    
    private void initForm() {
        dtRequestFrom.setDate(new Date());
        dtRequestTo.setDate(new Date());
        txtSearch.setText("");
    }
    
    private void loadDataTable() {
        ResultSet res = sellDao.findBy(
                dtRequestFrom.getDate(), 
                dtRequestTo.getDate(), 
                entity.getNik(), 
                txtSearch.getText(), 
                cmbStatus.getSelectedItem().toString());
        if (res != null) {
            tbl.setModel(DbUtils.resultSetToTableModel(res));
            util.adjustColumn(tbl);
        } else {
            JOptionPane.showMessageDialog(null, "Gagal memuat data riwayat!");
        }
        setActionButtonVisibility();
    }
    
    private void initTable() {
        tbl.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1)
                    setActionButtonVisibility();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        tbl.setDefaultEditor(Object.class, null);
    }
    
    private void setActionButtonVisibility() {
        if (tbl.getSelectedRow() < 0) {
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
            btnDelete.setVisible(false);
            btnDetail.setVisible(false);
        } else {
            String status = tbl.getValueAt(tbl.getSelectedRow(), 4).toString();
            boolean isCanEdit = status.equals(ApprovalStatus.REQUESTED) || status.equals(ApprovalStatus.NEED_REVISED);
            btnEdit.setVisible(isCanEdit);
            btnDelete.setVisible(isCanEdit);
            btnDelete.setVisible(isCanEdit);
            btnDetail.setVisible(!isCanEdit);            
        }
    }
    
    private void confirmCloseWindow() {
        if (JOptionPane.showConfirmDialog(this, 
            "Apakah anda yakin ingin menutup form?", "Tutup Form", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        lbl4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        lblCariBarang = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        dtRequestFrom = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dtRequestTo = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbStatus = new javax.swing.JComboBox<>();
        mbItem = new javax.swing.JMenuBar();
        todayDate = new javax.swing.JMenu();
        userSession = new javax.swing.JMenu();
        btnLogOut = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        form = new javax.swing.JMenu();
        menu1 = new javax.swing.JMenuItem();
        menu2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Office Supplies Order");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl);

        lbl4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbl4.setText("History");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        lblCariBarang.setText("Cari :");

        btnCreate.setText("TAMBAH");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        dtRequestFrom.setDateFormatString("yyyy-MM-dd");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Request Date");

        jLabel3.setText("s/d");

        dtRequestTo.setDateFormatString("yyyy-MM-dd");

        jButton2.setText("TAMPILKAN");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnDelete.setText("HAPUS");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDetail.setText("DETAIL");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Status");

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Requested", "Approved", "Rejected", "Need Revised" }));
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });

        todayDate.setText("Date");
        mbItem.add(todayDate);

        userSession.setText("User");
        mbItem.add(userSession);

        btnLogOut.setText("Server");

        jMenuItem1.setText("Log Out");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        btnLogOut.add(jMenuItem1);

        mbItem.add(btnLogOut);

        form.setText("Form");

        menu1.setText("Barang");
        menu1.setToolTipText("");
        menu1.setActionCommand("");
        menu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu1ActionPerformed(evt);
            }
        });
        form.add(menu1);

        menu2.setText("Goods Order");
        menu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu2ActionPerformed(evt);
            }
        });
        form.add(menu2);

        mbItem.add(form);

        setJMenuBar(mbItem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetail)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCariBarang)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(dtRequestFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dtRequestTo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2)))))
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dtRequestTo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dtRequestFrom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCariBarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnEdit)
                    .addComponent(btnDetail)
                    .addComponent(btnDelete))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu1ActionPerformed
        this.dispose();
        new BarangForm(entity).setVisible(true);
    }//GEN-LAST:event_menu1ActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        loadDataTable();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        new OfficeSuppliesOrderDetailForm(this, entity).show(null, OfficeSuppliesOrderDetailForm.ADD, () -> loadDataTable());
    }//GEN-LAST:event_btnCreateActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadDataTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        confirmCloseWindow();
    }//GEN-LAST:event_formWindowClosing

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin membatalkan pengajuan ini?","Batalkan pengajuan", JOptionPane.YES_NO_OPTION);
        if(confirm == 0){
            if (sellDao.deleteById(Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString()))) {
                JOptionPane.showMessageDialog(null, "Pengajuan berhasil dibatalkan.");
                loadDataTable();
            } else
                JOptionPane.showMessageDialog(null, "Gagal membatalkan pengajuan !");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        new OfficeSuppliesOrderDetailForm(this, entity).show(
                Integer.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0).toString()), 
                OfficeSuppliesOrderDetailForm.EDIT, () -> loadDataTable());
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        new OfficeSuppliesOrderDetailForm(this, entity).show(
                Integer.valueOf(tbl.getValueAt(tbl.getSelectedRow(), 0).toString()),
                OfficeSuppliesOrderDetailForm.VIEW, () -> loadDataTable());
    }//GEN-LAST:event_btnDetailActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void menu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu2ActionPerformed
        this.dispose();
        new GoodsOrderForm(entity).setVisible(true);
    }//GEN-LAST:event_menu2ActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnEdit;
    private javax.swing.JMenu btnLogOut;
    private javax.swing.JComboBox<String> cmbStatus;
    private com.toedter.calendar.JDateChooser dtRequestFrom;
    private com.toedter.calendar.JDateChooser dtRequestTo;
    private javax.swing.JMenu form;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lblCariBarang;
    private javax.swing.JMenuBar mbItem;
    private javax.swing.JMenuItem menu1;
    private javax.swing.JMenuItem menu2;
    private javax.swing.JTable tbl;
    private javax.swing.JMenu todayDate;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JMenu userSession;
    // End of variables declaration//GEN-END:variables
}
