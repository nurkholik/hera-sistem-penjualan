/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.DbConnect;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.HeadlessException;
import java.awt.Window;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author user
 */
public class StockReport extends javax.swing.JPanel {

    private JDialog modelDialog;
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Creates new form StockReport
     */
    public StockReport() {
        initComponents();
    }
    
    public void show(Window win) {
        modelDialog = new JDialog(win,Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setTitle(TOOL_TIP_TEXT_KEY);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        
        dialogContainer.add(this, BorderLayout.SOUTH);
        modelDialog.setSize(365, 200);
        modelDialog.setLocationRelativeTo(null);
        
        dtStart.setDate(new Date());
        dtEnd.setDate(new Date());
        
        modelDialog.setTitle("Laporan Stok Barang");
        modelDialog.setVisible(true);
    }
    
    private void dismis() {
        modelDialog.dispose();
    }
    
    private void createReport() {
        try {
            String namaFile = "src"+File.separator+"com"+File.separator+"org"+File.separator+"sistempenjualan"+File.separator+"report"+File.separator+"StockReport.jasper";
            Connection conn = DbConnect.ConnectDb();

            Map<String,Object> map =  new HashMap<>();
            map.put("dateStart", dtStart.getDate());
            map.put("dateEnd", dtEnd.getDate());
            map.put("strDateStart", df.format(dtStart.getDate()));
            map.put("strDateEnd", df.format(dtEnd.getDate()));
            JasperPrint jprint = JasperFillManager.fillReport(namaFile, map, conn);
            if(!jprint.getPages().isEmpty()){
                JasperViewer.viewReport(jprint,false);
            }else{
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan !");
            }
        } catch (HeadlessException | JRException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
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

        btnPrint = new javax.swing.JButton();
        dtEnd = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        dtStart = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();

        setToolTipText("Laporan Stok Barang");

        btnPrint.setText("CETAK");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        dtEnd.setDateFormatString("yyyy-MM-dd");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Periode");

        dtStart.setDateFormatString("yyyy-MM-dd");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("to");

        btnCancel.setText("TUTUP");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(dtStart, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(dtStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dtEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnCancel))
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        createReport();
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        dismis();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPrint;
    private com.toedter.calendar.JDateChooser dtEnd;
    private com.toedter.calendar.JDateChooser dtStart;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
