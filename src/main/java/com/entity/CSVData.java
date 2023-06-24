package com.entity;

import javax.persistence.*;

// import javax.persistence.*;
@Entity
@Table(name="csvdata")
public class CSVData {
    @Id
    private String name;
    private int view;
    private int quantity;
    private int totalsales;
    private int totalsalary;
    private int cluster;
    public CSVData(String name, int view, int quantity, int totalsales, int totalsalary, int cluster) {
        this.name = name;
        this.view = view;
        this.quantity = quantity;
        this.totalsales = totalsales;
        this.totalsalary = totalsalary;
        this.cluster = cluster;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getView() {
        return view;
    }
    public void setView(int view) {
        this.view = view;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getTotalsales() {
        return totalsales;
    }
    public void setTotalsales(int totalsales) {
        this.totalsales = totalsales;
    }
    public int getTotalsalary() {
        return totalsalary;
    }
    public void setTotalsalary(int totalsalary) {
        this.totalsalary = totalsalary;
    }
    public int getCluster() {
        return cluster;
    }
    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
    public CSVData() {
    }
    
    
    


}
