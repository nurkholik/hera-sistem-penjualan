/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.sistempenjualan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DIOR
 */
public class DbConnect {
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/sistempenjualan?zeroDateTimeBehavior=convertToNull","root","");
            Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/hera?zeroDateTimeBehavior=convertToNull","development","development**");
            System.out.println("Database Terkoneksi!");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    } 
}
