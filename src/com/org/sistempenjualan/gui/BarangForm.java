/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan.gui;

import com.org.sistempenjualan.UIUtil;
import com.org.sistempenjualan.entity.Entity;
import javax.swing.Box;
import com.org.sistempenjualan.Utility;
import com.org.sistempenjualan.dao.BarangDao;
import com.org.sistempenjualan.dao.impl.BarangDaoImpl;
import java.awt.Component;
import java.awt.HeadlessException;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author DIOR
 */
public class BarangForm extends javax.swing.JFrame {
     public UserForm userForm;
    
    // Global Objek
    Entity entity = new Entity();
    Utility util = new Utility();
    BarangDao dao = new BarangDaoImpl();
    
    // Global Variabel
    boolean flagUpdate = false;
    String nikSession = "";
    
    public BarangForm(Entity a) {
        initComponents();
        setupWindow();
        setupUserInfo(a);
        setupTable();
        clearForm();
    }
    
    private void setupWindow() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    private void setupUserInfo(Entity a) {
        nikSession = a.getNikSession();
        userSession.setText("Login As: "+a.getUserSession());
        entity.setNikSession(nikSession);
        entity.setUserSession(a.getUserSession());
        entity.setRoleSession(a.getRoleSession());
        mbItem.add(Box.createHorizontalGlue());
        mbItem.add(todayDate);
        mbItem.add(userSession);
        util.tanggalSekarang(todayDate);
    }
    
    private void setupTable(){
        tblBarang.setModel(DbUtils.resultSetToTableModel(dao.setBarangTable()));
        tblBarang.getColumnModel().getColumn(3).setCellRenderer(new NumberTableCellRenderer());
        tblBarang.getColumnModel().getColumn(4).setCellRenderer(new NumberTableCellRenderer());
        util.adjustColumn(tblBarang);
    }
    
    public class NumberTableCellRenderer extends DefaultTableCellRenderer {
        public NumberTableCellRenderer() {
            setHorizontalAlignment(JLabel.RIGHT);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Number) {
                value = NumberFormat.getNumberInstance().format(value);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
    
    private void clearForm(){
        txtKodeBarang.setText("");
        txtNamaBarang.setText("");
        txtCariBarang.setText("");
        txtNamaBarang.setText("");
        txtJmlBarang.setText("0");
        txtHarga.setText("Rp 0");
        flagUpdate = false;
        btnHapus.setVisible(false);
        tblBarang.clearSelection();
        stockComponentVisible(false);
    }
    
    private void stockComponentVisible(boolean isVisible) {
        lblStock.setVisible(isVisible);
        txtJmlBarang.setVisible(isVisible);
    }
    
    private Integer getSelectedIdBarang() {
        if (tblBarang.getSelectedRow() >= 0) {
            return Integer.parseInt(tblBarang
                           .getModel()
                           .getValueAt(tblBarang.getSelectedRow(), 0)
                           .toString());   
        } 
        return null;
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
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        txtCariBarang = new javax.swing.JTextField();
        lblCariBarang = new javax.swing.JLabel();
        frameInfo = new javax.swing.JPanel();
        txtNamaBarang = new javax.swing.JTextField();
        lblJmlBarang = new javax.swing.JLabel();
        lblNamaBarang1 = new javax.swing.JLabel();
        txtKodeBarang = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        lblNamaBarang = new javax.swing.JLabel();
        lblStock = new javax.swing.JLabel();
        txtJmlBarang = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblHarga2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        mbItem = new javax.swing.JMenuBar();
        todayDate = new javax.swing.JMenu();
        userSession = new javax.swing.JMenu();
        btnLogOut = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        btnTransaction = new javax.swing.JMenu();
        menuPurchase = new javax.swing.JMenuItem();
        menuPenjualan = new javax.swing.JMenuItem();

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Barang");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        tblBarang.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tblBarangCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        txtCariBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariBarangKeyReleased(evt);
            }
        });

        lblCariBarang.setText("Cari :");

        frameInfo.setToolTipText("");

        txtNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaBarangActionPerformed(evt);
            }
        });
        txtNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNamaBarangKeyReleased(evt);
            }
        });

        lblJmlBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblJmlBarang.setText("Harga Barang");

        lblNamaBarang1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaBarang1.setText("Kode Barang");

        txtKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeBarangActionPerformed(evt);
            }
        });
        txtKodeBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKodeBarangKeyReleased(evt);
            }
        });

        txtHarga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtHargaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtHargaFocusLost(evt);
            }
        });
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });
        txtHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtHargaKeyTyped(evt);
            }
        });

        lblNamaBarang.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNamaBarang.setText("Nama Barang");

        lblStock.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblStock.setText("Stok Barang");

        txtJmlBarang.setEditable(false);

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

        javax.swing.GroupLayout frameInfoLayout = new javax.swing.GroupLayout(frameInfo);
        frameInfo.setLayout(frameInfoLayout);
        frameInfoLayout.setHorizontalGroup(
            frameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameInfoLayout.createSequentialGroup()
                .addGroup(frameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtKodeBarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                        .addComponent(txtNamaBarang, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblJmlBarang, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblNamaBarang, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblNamaBarang1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtHarga, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(lblStock)
                    .addComponent(txtJmlBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(frameInfoLayout.createSequentialGroup()
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        frameInfoLayout.setVerticalGroup(
            frameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameInfoLayout.createSequentialGroup()
                .addComponent(lblNamaBarang1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNamaBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblJmlBarang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtJmlBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(frameInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnHapus)
                    .addComponent(btnReset))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        lblHarga2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHarga2.setText("Daftar Barang");

        jButton1.setText("LAPORAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        btnTransaction.setText("Form");

        menuPurchase.setText("Goods Order");
        menuPurchase.setToolTipText("");
        menuPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPurchaseActionPerformed(evt);
            }
        });
        btnTransaction.add(menuPurchase);

        menuPenjualan.setText("Office Supplies Order");
        menuPenjualan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPenjualanActionPerformed(evt);
            }
        });
        btnTransaction.add(menuPenjualan);

        mbItem.add(btnTransaction);

        setJMenuBar(mbItem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(frameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblHarga2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCariBarang)
                            .addGap(18, 18, 18)
                            .addComponent(txtCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(frameInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblCariBarang))
                            .addComponent(lblHarga2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 16, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        flagUpdate = true;
        int row = tblBarang.getSelectedRow();
        TableModel model = tblBarang.getModel();
        txtKodeBarang.setText(model.getValueAt(row, 1).toString());
        txtNamaBarang.setText(model.getValueAt(row, 2).toString());
        txtJmlBarang.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getValueAt(row, 3).toString())));
        txtHarga.setText(util.formatCurrency(Long.parseLong(model.getValueAt(row, 4).toString())));
        btnHapus.setVisible(true);
        stockComponentVisible(true);
    }//GEN-LAST:event_tblBarangMouseClicked

    private void txtCariBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariBarangKeyReleased
        String param = txtCariBarang.getText();
        tblBarang.setModel(DbUtils.resultSetToTableModel(dao.setBarangTable(param)));
        util.adjustColumn(tblBarang);
    }//GEN-LAST:event_txtCariBarangKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, 
            "Apakah anda yakin ingin menutup form?", "Tutup Form", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        flagUpdate = false;
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        String kodeBarang = txtKodeBarang.getText();
        int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menghapus data?","Simpan Data",JOptionPane.YES_NO_OPTION);
        try{
            if(confirm==0){
                if(dao.deleteBarang(kodeBarang)){
                    JOptionPane.showMessageDialog(rootPane, "Berhasil hapus barang! ");
                    setupTable();
                    clearForm();
                }else{
                    JOptionPane.showMessageDialog(rootPane, "Error hapus barang! ");
                }
            }
        }catch(HeadlessException e){
            JOptionPane.showMessageDialog(rootPane, "Error hapus barang! "+e);
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try{            
            entity.setKodeBarang(txtKodeBarang.getText());
            entity.setNamaBarang(txtNamaBarang.getText());
            entity.setHargaBarang(util.deFormatCurrency(txtHarga.getText()).intValue());
            
            if (txtNamaBarang.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Nama Barang Tidak Boleh Kosong!");
            } else if (txtKodeBarang.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Kode Barang Tidak Boleh Kosong!");
            } else if (dao.isExistByKodeBarang(txtKodeBarang.getText(), getSelectedIdBarang())) {
                JOptionPane.showMessageDialog(this, "Kode Barang Tidak Boleh Duplikat!");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null, "Anda yakin ingin menyimpan data?","Simpan Data",JOptionPane.YES_NO_OPTION);
                if(confirm == 0){
                    if(!flagUpdate){
                        if(dao.addBarang(entity)){
                            JOptionPane.showMessageDialog(rootPane, "Berhasil simpan barang!");
                            setupTable();
                            clearForm();
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Error simpan barang!");
                        }
                    }else{
                        entity.setIdBarang(getSelectedIdBarang());
                        if(dao.editBarang(entity)){
                            JOptionPane.showMessageDialog(rootPane, "Berhasil update barang!");
                            setupTable();
                            clearForm();
                        }else{
                            JOptionPane.showMessageDialog(rootPane, "Error update barang!");
                        }
                    }
                }
            }
        }catch(HeadlessException | NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Error simpan barang! "+e);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtNamaBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaBarangKeyReleased
        UIUtil.toUppperCase(txtNamaBarang);
    }//GEN-LAST:event_txtNamaBarangKeyReleased

    private void txtNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBarangActionPerformed

    private void txtKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeBarangActionPerformed

    private void txtHargaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaFocusGained
        UIUtil.toDeCurrencyFormat(txtHarga);
    }//GEN-LAST:event_txtHargaFocusGained

    private void txtHargaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtHargaFocusLost
        UIUtil.toCurrencyFormat(txtHarga);
    }//GEN-LAST:event_txtHargaFocusLost

    private void tblBarangCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblBarangCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBarangCaretPositionChanged

    private void txtHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHargaKeyTyped
        UIUtil.checkOnlyNumber(evt);
    }//GEN-LAST:event_txtHargaKeyTyped

    private void txtKodeBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodeBarangKeyReleased
        UIUtil.toUppperCase(txtKodeBarang);
    }//GEN-LAST:event_txtKodeBarangKeyReleased

    private void menuPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPurchaseActionPerformed
        this.dispose();
        new GoodsOrderForm(entity).setVisible(true);
    }//GEN-LAST:event_menuPurchaseActionPerformed

    private void menuPenjualanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPenjualanActionPerformed
        this.dispose();
        new OfficeSuppliesOrderForm(entity).setVisible(true);
    }//GEN-LAST:event_menuPenjualanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new StockReport().show(this);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JMenu btnLogOut;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JMenu btnTransaction;
    private javax.swing.JPanel frameInfo;
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCariBarang;
    private javax.swing.JLabel lblHarga2;
    private javax.swing.JLabel lblJmlBarang;
    private javax.swing.JLabel lblNamaBarang;
    private javax.swing.JLabel lblNamaBarang1;
    private javax.swing.JLabel lblStock;
    private javax.swing.JMenuBar mbItem;
    private javax.swing.JMenuItem menuPenjualan;
    private javax.swing.JMenuItem menuPurchase;
    private javax.swing.JTable tblBarang;
    private javax.swing.JMenu todayDate;
    private javax.swing.JTextField txtCariBarang;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJmlBarang;
    private javax.swing.JTextField txtKodeBarang;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JMenu userSession;
    // End of variables declaration//GEN-END:variables
}
