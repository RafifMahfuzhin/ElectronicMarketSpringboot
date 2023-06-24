package com.entity;


public class Dataset {
    private Item item;
    private int totalSales;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public Dataset(Item item, int totalSales) {
        this.item = item;
        this.totalSales = totalSales;
    }
}
