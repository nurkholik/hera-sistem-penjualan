/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.sistempenjualan.constant;

/**
 *
 * @author user
 */
public class ApprovalStatus {
    public static final String REQUESTED = "Requested";
    public static final String APPROVED = "Approved";
    public static final String REJECTED = "Rejected";
    public static final String NEED_REVISED = "Need Revised";

    public static String getLabel(String status) {
        switch (status) {
            case REQUESTED: return "Requested by";
            case APPROVED: return "Approved by";
            case REJECTED: return "Rejected by";
            case NEED_REVISED: return "Revise requested by";
            default: return "";
        }
    }
}
