/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.UIUtil;
import com.org.sistempenjualan.dao.BarangDao;
import com.org.sistempenjualan.dao.impl.BarangDaoImpl;
import com.org.sistempenjualan.entity.TransSellDetail;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Window;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class OfficeSuppliesOrderItemForm extends javax.swing.JPanel {

    public OfficeSuppliesOrderItemForm(Window window) {
        initComponents();
        initDialog(window);        
    }
    
    public static final int ADD = 0;
    public static final int EDIT = 1;
    public static final int VIEW = 2;
    
    private final BarangDao barangDao = new BarangDaoImpl();
    
    private JDialog modelDialog;
    private ActionListener actionListener;
    private TransSellDetail item;
    private int FLAG;

    private void loadItemOption(List<String> exclude) {
        Vector<String> availableOptions = new Vector<>();
        Vector<String> options = barangDao.getListBarang();
        for (String v : options) {
            if (exclude.stream().noneMatch(e -> e.equals(v.split("[-]")[0].trim())))
                availableOptions.add(v);
        }
        cmbBarang.setModel(new DefaultComboBoxModel(availableOptions)); 
        UIUtil.setSelectedIndex(cmbBarang, this.item != null ? String
                .valueOf(this.item.getIdBarang()) : null);
    }
    
    public static interface ActionListener {
        void onUpdated(TransSellDetail item);
        void onAdded(TransSellDetail item);
        void onDeleted(TransSellDetail item);
    }
    
    private void initDialog(Window win) {
        modelDialog = new JDialog(win,Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setTitle(TOOL_TIP_TEXT_KEY);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        
        dialogContainer.add(this, BorderLayout.SOUTH);
        modelDialog.setSize(350, 300);
        modelDialog.setLocationRelativeTo(null);
    }
    
    public void show(TransSellDetail item, int FLAG, ActionListener mActionListener, List<String> excludeCode) {
        this.FLAG = FLAG;
        this.item = this.FLAG == ADD ? new TransSellDetail() : item;
        this.actionListener = mActionListener;
        
        btnDelete.setVisible(FLAG == EDIT);
        cmbBarang.setEnabled(FLAG == ADD);
        txtQty.setEditable(FLAG == EDIT || FLAG == ADD);
        txtRemark.setEditable(FLAG == EDIT || FLAG == ADD);
        btnSubmit.setVisible(FLAG == EDIT || FLAG == ADD);
        btnCancel.setText(FLAG == VIEW ? "TUTUP" : "BATAL");
        
        loadItemOption(excludeCode);
        setItemData();

        modelDialog.setTitle((item == null ? "Add" : "Update") + " Item");
        modelDialog.setVisible(true);
    }
    
    private void dismis() {
        modelDialog.setVisible(false);
        clearForm();
    }
    
    private void clearForm() {
        txtQty.setText("0");
        txtRemark.setText("");
        UIUtil.setSelectedIndex(cmbBarang, null);
    }
    
    private void setItemData() {
        if (item == null)
            clearForm();
        else {
            UIUtil.setSelectedIndex(cmbBarang, item.getKodeBarang());
            txtQty.setText(NumberFormat.getInstance().format(item.getQty()));
            txtRemark.setText(item.getRemark());
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

        btnCancel = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbBarang = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtRemark = new javax.swing.JTextField();

        btnCancel.setText("CANCEL");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSubmit.setText("SIMPAN");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        jLabel2.setText("Barang");

        txtQty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtQtyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtQtyFocusLost(evt);
            }
        });
        txtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtyKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQtyKeyTyped(evt);
            }
        });

        jLabel1.setText("Jumlah");

        btnDelete.setText("HAPUS");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel3.setText("Remark");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbBarang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQty)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSubmit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(btnCancel))
                    .addComponent(txtRemark)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRemark, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnCancel)
                    .addComponent(btnDelete))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dismis();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        try {
            if (cmbBarang.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(null, "Barang belum dipilih !");
                return;
            }
            
            int qty = NumberFormat.getInstance().parse(txtQty.getText()).intValue();
            if (qty == 0) {
                JOptionPane.showMessageDialog(null, "Jumlah barang harus diisi !");
                return;
            }
            
            if (this.FLAG == ADD) {
                String[] barang = cmbBarang.getSelectedItem().toString().split("[-]");
                item.setKodeBarang(barang[0].trim());
                item.setNamaBarang(barang[1].trim());
                item.setQty(qty);
                item.setRemark(txtRemark.getText());
                actionListener.onAdded(item);
                dismis();
            } else if (this.FLAG == EDIT) {
                item.setQty(qty);
                item.setRemark(txtRemark.getText());
                actionListener.onUpdated(item);
                dismis();
            }
        } catch (ParseException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Isi jumlah barang dengan angka!");
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void txtQtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtyFocusGained
        UIUtil.deFormatNumber(txtQty);
    }//GEN-LAST:event_txtQtyFocusGained

    private void txtQtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtQtyFocusLost
        UIUtil.formatNumber(txtQty);
    }//GEN-LAST:event_txtQtyFocusLost

    private void txtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyPressed
        
    }//GEN-LAST:event_txtQtyKeyPressed

    private void txtQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtyKeyTyped
        UIUtil.checkOnlyNumber(evt);
    }//GEN-LAST:event_txtQtyKeyTyped

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        actionListener.onDeleted(item);
        dismis();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> cmbBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtRemark;
    // End of variables declaration//GEN-END:variables
}
