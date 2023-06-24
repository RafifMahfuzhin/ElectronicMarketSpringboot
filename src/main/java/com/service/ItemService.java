package com.service;

import java.util.List;

import com.entity.Dataset;
import com.entity.Item;

public interface ItemService {
    Item saveItem(String name, int quantity, int amount,int modal);
    List<Item> findAllItems();
    void deleteItemById(long itemId);
    Item updateItem(long itemId, String newName, int newQuantity, int newAmount, int newModal,int newSalary);
    int getTotalItems();
    public Item getItemById(Long itemId);
    Item updateViewByClick(Long itemId);
    void updateQuantityAndSalaryWhenBuy(Long itemId, int quantityBuy);
    List<Dataset> getAllItemsWithTotalSales();
}
