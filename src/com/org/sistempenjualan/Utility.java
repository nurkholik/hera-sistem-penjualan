/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan;

import com.org.sistempenjualan.gui.UserForm;
import java.awt.Component;
import static java.lang.Thread.sleep;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.fill.SimpleTextFormat;

/**
 *
 * @author DIOR
 */
public class Utility {
   
    private final NumberFormat currencyIDFormat;
    public Utility() {
        this.currencyIDFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    }
            
    public void tanggalSekarang(JMenu todayDate){     
        Thread clock = new Thread(){
            public void run(){
                for(;;){
                    Calendar cal = new GregorianCalendar();
                    int tanggal = cal.get(Calendar.DAY_OF_MONTH);
                    int bulan1 = cal.get(Calendar.MONTH);
                    int tahun = cal.get(Calendar.YEAR);
                    java.util.Date d = new java.util.Date();
                    String bulan = "MMMM";
                    String dayweek = "EEEE";
                    String jam = "HH";
                    String menit = "mm";
                    String detik = "ss";
                    SimpleDateFormat sdf = new SimpleDateFormat(dayweek);
                    SimpleDateFormat bln = new SimpleDateFormat(bulan);
                    SimpleDateFormat hr = new SimpleDateFormat(jam);
                    SimpleDateFormat mnt = new SimpleDateFormat(menit);
                    SimpleDateFormat sd = new SimpleDateFormat(detik);
                    todayDate.setText("Tanggal: "+sdf.format(d)+", "+tanggal+" "+(bln.format(d))+" "+tahun+"   Waktu: "+hr.format(d)+":"+(mnt.format(d))+":"+sd.format(d));
//                    tgl_resep.setText(""+sdf.format(d)+", "+tanggal+" / "+(bulan1+1)+" / "+tahun);
//                    tgl_rekam_medis.setText(tanggal+"-"+(bulan1+1)+"-"+tahun);
                    try{
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UserForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };        
        clock.start();        
    }
    
    public void adjustColumn(JTable table){
     TableColumnModel modelKolom = table.getColumnModel();
     for(int kol=0; kol < modelKolom.getColumnCount(); kol++){
    		int lebarKolomMax=0;
    		for(int baris=0;baris<table.getRowCount();baris++){
                    TableCellRenderer rend=table.getCellRenderer(baris,kol);
                    Object nilaiTablel=table.getValueAt(baris,kol);
                    Component comp=rend.getTableCellRendererComponent(table,nilaiTablel,false,false,baris,kol);
                    lebarKolomMax=Math.max(comp.getPreferredSize().width,lebarKolomMax);
    		}//akhir for baris
    		TableColumn kolom=modelKolom.getColumn(kol);
    		kolom.setPreferredWidth(lebarKolomMax);
    	}//akhir for kolom
    }
    
    public String formatCurrency(long value) {
        return currencyIDFormat.format(value).replace("Rp", "Rp ");
    }
    
    public Long deFormatCurrency(String value) {
        try {
            return currencyIDFormat.parse(value.replace(" ", "")).longValue();
        } catch (ParseException ex) {
            return 0L;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new Utility().formatCurrency(100000));
        System.out.println(new Utility().deFormatCurrency("Rp 150.000,00"));
    }
}
