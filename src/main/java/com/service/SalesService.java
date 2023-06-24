package com.service;

import java.util.List;

import com.entity.Sales;

public interface SalesService {
    Sales saveSales(Long itemId, Long userId, int quantity, int amount);
    List<Sales> findAllSales();
    int getTotalSales();
    // int getTotalQuantityById(Long itemId);
    // void deleteItemById(long itemId);
    // Item updateItem(long itemId, String newName, int newQuantity, int newAmount);
}
