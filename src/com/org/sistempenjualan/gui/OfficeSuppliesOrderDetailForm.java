/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.UIUtil;
import com.org.sistempenjualan.Utility;
import com.org.sistempenjualan.constant.ApprovalStatus;
import com.org.sistempenjualan.constant.UserRole;
import com.org.sistempenjualan.dao.TransSellDao;
import com.org.sistempenjualan.dao.impl.TransSellDaoImpl;
import com.org.sistempenjualan.entity.Entity;
import com.org.sistempenjualan.entity.TransSell;
import com.org.sistempenjualan.entity.TransSellDetail;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class OfficeSuppliesOrderDetailForm extends javax.swing.JFrame {

    private DefaultTableModel model = new DefaultTableModel();
    private final Entity entity;
    private final TransSellDao sellDao = new TransSellDaoImpl();
    private final Utility util;
    
    public static int ADD = 1;
    public static int EDIT = 2;
    public static int VIEW = 3;
    
    private int FLAG;
    private int sellId;
    private ActionListener actionListener;
    private JDialog modelDialog;

    /**
     * @param parent
     * @param entity
     */
    public OfficeSuppliesOrderDetailForm(Window parent, Entity entity) {
        this.util = new Utility();
        initComponents();
        clear();
        initTable();      
        initDialog(parent);
        this.entity = entity;
    }
    
    public interface ActionListener {
        void onSubmit();
    }
    
    private final OfficeSuppliesOrderItemForm.ActionListener detailActionListener = 
            new OfficeSuppliesOrderItemForm.ActionListener() {
        @Override
        public void onUpdated(TransSellDetail item) {
            model.setValueAt(
                    item.getQty(), 
                    item.getIndex(), 
                    2);
            model.setValueAt(
                    item.getRemark(), 
                    item.getIndex(), 
                    3);
        }

        @Override
        public void onAdded(TransSellDetail item) {
            model.addRow(new Object[]{
                item.getKodeBarang(), 
                item.getNamaBarang(), 
                item.getQty(),
                item.getRemark()
            });
        }

        @Override
        public void onDeleted(TransSellDetail item) {
            model.removeRow(item.getIndex());
        }
    };
        
    public void show(Integer sellId, int FLAG, ActionListener mActionListener) {
        this.FLAG = FLAG;
        this.actionListener = mActionListener;
        
        btnAddItem.setVisible(FLAG == EDIT || FLAG == ADD);
        btnSave.setVisible(FLAG == EDIT || FLAG == ADD);
        btnClose.setVisible(FLAG == VIEW);
        txtDepartment.setEditable(FLAG == EDIT || FLAG == ADD);
        
        if (sellId != null) 
            loadSellData(sellId);
        
        modelDialog.setTitle((FLAG == EDIT ? "Edit" : FLAG == ADD ? "Create" : "Detail") + " Request");
        modelDialog.setVisible(true);
    }
    
    private void dismis() {
        modelDialog.dispose();
    }
    private void initDialog(Window win) {
        modelDialog = new JDialog(win,Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setTitle(TOOL_TIP_TEXT_KEY);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        
        dialogContainer.add(this.getContentPane(), BorderLayout.SOUTH);
        modelDialog.setSize(800, 485);
        modelDialog.setLocationRelativeTo(null);
    }
        
    private void initTable() {
        model = new DefaultTableModel();
        model.addColumn("Item Code");
        model.addColumn("Item Description");
        model.addColumn("Qty");
        model.addColumn("Remark");
        tblItem.setModel(model);
        tblItem.setDefaultEditor(Object.class, null);
        
        tblItem.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cc = e.getClickCount();
                if (cc == 1)
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
        util.adjustColumn(tblItem);
    }
    
    private void clear() {
        txtDepartment.setText("");
        btnDetail.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        panelApproval.setVisible(false);
        btnApprove.setVisible(false);
        btnReject.setVisible(false);
        btnRevise.setVisible(false);
    }
    
    private void loadSellData(int sellId) {
        this.sellId = sellId;
        TransSell transSell = sellDao.findById(sellId);
        txtOrderNumber.setText(transSell.getOrderNumber());
        txtDepartment.setText(transSell.getDepartment());     
        transSell.getDetails().forEach(e -> {
            model.addRow(new Object[]{
                e.getKodeBarang(), e.getNamaBarang(), e.getQty(), e.getRemark()
            });
        });
        
        boolean isRequested = transSell.getStatus().equals(ApprovalStatus.REQUESTED);
        boolean isDirektur = entity.getRoleSession().equalsIgnoreCase(UserRole.DIREKTUR);
        btnApprove.setVisible(isDirektur && isRequested);
        btnReject.setVisible(isDirektur && isRequested);
        btnRevise.setVisible(isDirektur && isRequested);
        panelApproval.setVisible(!isRequested);
        
        if (!isRequested) {
            userApprove.setText(": " + (transSell.getApprovedBy() == null ? "-" : transSell.getApprovedBy()));
            lblReason.setText(": " + (transSell.getRemark() == null ? "-" : transSell.getRemark()));
            lblUserApprove.setText(" " + ApprovalStatus.getLabel(transSell.getStatus()));
            
            Dimension dim = modelDialog.getSize();
            modelDialog.setSize(dim.width, dim.height + 65);
        }
    }
    
    private void setActionButtonVisibility() {
        boolean isRowSelected = tblItem.getSelectedRow() >= 0;
        btnDetail.setVisible(isRowSelected && FLAG==VIEW);
        btnEdit.setVisible(isRowSelected && (FLAG==EDIT || FLAG==ADD));
        btnDelete.setVisible(isRowSelected && (FLAG==EDIT || FLAG==ADD));
    }
    
    private void openDetail(int FLAG) {
        int row = tblItem.getSelectedRow();
        TransSellDetail item = new TransSellDetail();
        item.setIndex(row);
        item.setKodeBarang(tblItem.getValueAt(row, 0).toString());
        item.setNamaBarang(tblItem.getValueAt(row, 1).toString());
        item.setQty(Integer.parseInt(tblItem.getValueAt(row, 2).toString()));
        item.setRemark(tblItem.getValueAt(row, 3).toString());
        new OfficeSuppliesOrderItemForm(this).show(
                item, 
                FLAG,
                detailActionListener, 
                Collections.emptyList());
    }
    
    private boolean isValidInput() {
        if (txtDepartment.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Department belum diisi !");
            return false;
        }
        
        if (txtOrderNumber.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Order Number belum diisi !");
            return false;
        }
                
        if (tblItem.getModel().getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Item belum ditambahkan !");
            return false;
        }
        return true;
    }
    
    private List<String> getExistCode() {
        List<String> existCode = new ArrayList<>();
        for (int i=0; i<tblItem.getModel().getRowCount(); i++) {
            existCode.add(tblItem.getValueAt(i, 0).toString());
        }
        return existCode;
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
        jEditorPane1 = new javax.swing.JEditorPane();
        txtDepartment = new javax.swing.JTextField();
        lbl2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnAddItem = new javax.swing.JButton();
        lbl4 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnApprove = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        btnRevise = new javax.swing.JButton();
        panelApproval = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblUserApprove = new javax.swing.JLabel();
        userApprove = new javax.swing.JLabel();
        lblReason = new javax.swing.JLabel();
        txtOrderNumber = new javax.swing.JTextField();
        lbl3 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Office Supplies Order Detail");
        setResizable(false);

        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });
        txtDepartment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDepartmentKeyReleased(evt);
            }
        });

        lbl2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbl2.setText("Department");

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblItem);

        btnSave.setText("SIMPAN");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnAddItem.setText("Tambah");
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        lbl4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbl4.setText("Item List");

        btnDelete.setText("Hapus");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDetail.setText("Detail");
        btnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailActionPerformed(evt);
            }
        });

        btnClose.setText("TUTUP");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnApprove.setText("APPROVE");
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        btnReject.setText("REJECT");
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });

        btnRevise.setText("REVISE");
        btnRevise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviseActionPerformed(evt);
            }
        });

        jLabel2.setText(" Note");

        lblUserApprove.setText(" Approved by ");

        userApprove.setText(": ...");

        lblReason.setText(": ...");

        javax.swing.GroupLayout panelApprovalLayout = new javax.swing.GroupLayout(panelApproval);
        panelApproval.setLayout(panelApprovalLayout);
        panelApprovalLayout.setHorizontalGroup(
            panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelApprovalLayout.createSequentialGroup()
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserApprove)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReason, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelApprovalLayout.setVerticalGroup(
            panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelApprovalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserApprove)
                    .addComponent(userApprove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelApprovalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblReason))
                .addGap(40, 40, 40))
        );

        txtOrderNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderNumberActionPerformed(evt);
            }
        });
        txtOrderNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOrderNumberKeyReleased(evt);
            }
        });

        lbl3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lbl3.setText("Order Number");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDetail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddItem))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClose)
                                .addGap(18, 18, 18)
                                .addComponent(btnApprove)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReject)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRevise))
                            .addComponent(panelApproval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtOrderNumber))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl2)
                            .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(331, 331, 331))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOrderNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4)
                    .addComponent(btnAddItem)
                    .addComponent(btnDelete)
                    .addComponent(btnEdit)
                    .addComponent(btnDetail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnClose)
                    .addComponent(btnApprove)
                    .addComponent(btnReject)
                    .addComponent(btnRevise))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentActionPerformed

    private void txtDepartmentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepartmentKeyReleased
        UIUtil.toUppperCase(txtDepartment);
    }//GEN-LAST:event_txtDepartmentKeyReleased

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menyimpan data ?","Simpan Data",JOptionPane.YES_NO_OPTION);
        if (confirm != 0)
            return;
        
        if (isValidInput()) {
            TransSell sell = new TransSell();
            sell.setOrderNumber(txtOrderNumber.getText());
            sell.setDepartment(txtDepartment.getText());
            sell.setRequestBy(entity.getNikSession());
            
            List<TransSellDetail> details = new ArrayList<>();
            for (int index = 0; index < tblItem.getModel().getRowCount(); index++) {
                details.add(new TransSellDetail(
                        tblItem.getValueAt(index, 0).toString(), 
                        Integer.parseInt(tblItem.getValueAt(index, 2).toString()),
                        tblItem.getValueAt(index, 3).toString()
                ));
            }
            sell.setDetails(details);
            if (FLAG == ADD) {
                if (sellDao.add(sell)) {
                    JOptionPane.showMessageDialog(null, "Pengajuan berhasil dibuat.");
                    actionListener.onSubmit();
                    dismis();
                } else
                    JOptionPane.showMessageDialog(null, "Pengajuan gagal dibuat!");                
            } 
            if (FLAG == EDIT) {
                sell.setId(this.sellId);
                if (sellDao.update(sell)) {
                    JOptionPane.showMessageDialog(null, "Pengajuan berhasil diperbarui.");
                    actionListener.onSubmit();
                    dismis();
                } else
                    JOptionPane.showMessageDialog(null, "Pengajuan gagal diperbarui!");
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        new OfficeSuppliesOrderItemForm(this).show(null, OfficeSuppliesOrderItemForm.ADD, detailActionListener, getExistCode());
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus item?","Hapus Item",JOptionPane.YES_NO_OPTION);
        if(confirm == 0){
            model.removeRow(tblItem.getSelectedRow());
            setActionButtonVisibility();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        openDetail(OfficeSuppliesOrderItemForm.EDIT);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailActionPerformed
        openDetail(OfficeSuppliesOrderItemForm.VIEW);
    }//GEN-LAST:event_btnDetailActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dismis();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed
        if (JOptionPane.showConfirmDialog(this, 
                    "Apakah anda yakin ingin menyetujui pengajuan ini ?", 
                    "Konfirmasi",JOptionPane.YES_NO_OPTION) == 0) {
            if (sellDao.approve(entity.getNikSession(), sellId)) {
                JOptionPane.showMessageDialog(this, "Pengajuan berhasil di setujui.");
                actionListener.onSubmit();
                dismis();
            }
        }
    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        if (JOptionPane.showConfirmDialog(this, 
                    "Apakah anda yakin ingin menolak pengajuan ini ?", 
                    "Konfirmasi",JOptionPane.YES_NO_OPTION) == 0) {
            
            String reason = JOptionPane.showInputDialog(this, 
                    "Masukkan keterangan !", "Keterangan", 
                    JOptionPane.OK_CANCEL_OPTION);
            
            if ((reason != null) && sellDao.reject(entity.getNikSession(), sellId, reason)) {
                JOptionPane.showMessageDialog(this, "Pengajuan berhasil ditolak.");
                actionListener.onSubmit();
                dismis();
            }
        }
    }//GEN-LAST:event_btnRejectActionPerformed

    private void btnReviseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviseActionPerformed
        if (JOptionPane.showConfirmDialog(this, 
                    "Apakah anda yakin ingin meminta perubahan pada pengajuan ini ?", 
                    "Konfirmasi",JOptionPane.YES_NO_OPTION) == 0) {
            
            String reason = JOptionPane.showInputDialog(this, 
                    "Masukkan keterangan !", "Keterangan", 
                    JOptionPane.OK_CANCEL_OPTION);
            
            if ((reason != null) && sellDao.revise(entity.getNikSession(), sellId, reason)) {
                JOptionPane.showMessageDialog(this, "Pengajuan berhasil dikembalikan.");
                actionListener.onSubmit();
                dismis();
            }
        }
    }//GEN-LAST:event_btnReviseActionPerformed

    private void txtOrderNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrderNumberActionPerformed

    private void txtOrderNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrderNumberKeyReleased
        UIUtil.toUppperCase(txtOrderNumber);
    }//GEN-LAST:event_txtOrderNumberKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReject;
    private javax.swing.JButton btnRevise;
    private javax.swing.JButton btnSave;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lblReason;
    private javax.swing.JLabel lblUserApprove;
    private javax.swing.JPanel panelApproval;
    private javax.swing.JTable tblItem;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtOrderNumber;
    private javax.swing.JLabel userApprove;
    // End of variables declaration//GEN-END:variables
}
