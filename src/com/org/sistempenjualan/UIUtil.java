/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.sistempenjualan;

import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class UIUtil {
    
    private static final Utility utility = new Utility();
    
    public static void toUppperCase(JTextField txtField) {
        txtField.setText(txtField.getText().toUpperCase());
    }

    public static void toCurrencyFormat(JTextField textField) {
        textField.setText(utility
                .formatCurrency(Long.parseLong(textField.getText())));
    }
    
    public static void toDeCurrencyFormat(JTextField textField) {
        textField.setText(String.valueOf(utility
                .deFormatCurrency(textField.getText())));
        
    }
    
    public static void formatNumber(JTextField textField) {
        try {
            textField.setText(NumberFormat.getNumberInstance()
                    .format(Long.parseLong(textField.getText())));            
        } catch (NumberFormatException e) {
            textField.setText(NumberFormat.getNumberInstance()
                    .format(0));
        }
    }
    
    public static void deFormatNumber(JTextField textField) {
        try {
            textField.setText(String.valueOf(NumberFormat
                    .getNumberInstance().parse(textField.getText())));
        } catch (ParseException ex) {
            textField.setText(String.valueOf(0));
        }
    }
    
    public static void checkOnlyNumber(KeyEvent evt) {
        if (evt.isConsumed()) return;
        int kc = (int) evt.getKeyChar();
        if (!((kc >= KeyEvent.VK_0 && kc <= KeyEvent.VK_9) || 
                kc == KeyEvent.VK_BACK_SPACE || 
                kc == KeyEvent.VK_DELETE || 
                kc == KeyEvent.VK_LEFT || 
                kc == KeyEvent.VK_RIGHT || 
                kc == KeyEvent.VK_END || 
                kc == KeyEvent.VK_HOME || 
                kc == KeyEvent.VK_TAB)) {
            evt.consume();
        }
    }
    
    public static void setSelectedIndex(JComboBox cmb, String key) {
        if (cmb.getModel() != null && cmb.getModel().getSize() > 0) {
            cmb.setSelectedIndex(0);
            if (key != null) {
                for (int index = 0; index < cmb.getModel().getSize(); index++) {
                    if (cmb.getModel().getElementAt(index).toString().split("-")[0].trim().equals(key)) {
                        cmb.setSelectedIndex(index);
                        break;
                    }
                }
            }
        }
    }
    
}
