package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.entity.Item;
import com.repository.ItemRepository;
import com.entity.Dataset;
import com.repository.SalesRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final SalesRepository salesRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, SalesRepository salesRepository) {
        this.itemRepository = itemRepository;
        this.salesRepository = salesRepository;
    }

    

    @Override
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item saveItem(String name, int quantity, int amount,int modal) {
        Item newItem = new Item(name, quantity, amount, modal);
        return itemRepository.save(newItem);
    }

    @Override
    public void deleteItemById(long itemId) {
        Item item = itemRepository.findById(itemId);
        if (item != null) {
            itemRepository.delete(item);
        } else {
            System.out.println("Item not found for id : " + itemId);
        }
    }

    @Override
    public Item updateItem(long itemId, String newName, int newQuantity, int newAmount, int newModal,int newSalary) {
        Item itemToUpdate = itemRepository.findById(itemId);
        System.out.println("Cek2");
        System.out.println(itemId);
        if (itemToUpdate != null) {
            System.out.println("a");
            itemToUpdate.setName(newName);
            itemToUpdate.setQuantity(newQuantity);
            itemToUpdate.setAmount(newAmount);
            itemToUpdate.setModal(newModal);
            itemToUpdate.setSalary(newSalary);
            return itemRepository.save(itemToUpdate);
        }
        return null;
    }

    @Override
    public int getTotalItems() {
        System.out.println(itemRepository.countAllItems());
        return itemRepository.countAllItems();
    }

    @Override
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }
    @Override
    public Item updateViewByClick(Long itemId){
        Item itemToUpdate = itemRepository.findById(itemId).orElse(null);
        if(itemToUpdate!=null){
            itemToUpdate.setView(itemToUpdate.getView() + 1);
            return itemRepository.save(itemToUpdate);
        }
        return null;
    }
    
    @Override
    public void updateQuantityAndSalaryWhenBuy(Long itemId, int quantityBuy){
        Item itemToUpdate = itemRepository.findById(itemId).orElse(null);
        if(itemToUpdate!=null){
            int quantityTotal =salesRepository.getTotalQuantityById(itemId);
            System.out.println(quantityTotal);
            itemToUpdate.setQuantity(itemToUpdate.getQuantity()-quantityBuy );
            itemToUpdate.setSalary((itemToUpdate.getAmount()-itemToUpdate.getModal())*quantityTotal);
            itemRepository.save(itemToUpdate);
        }
    }

    @Override
    public List<Dataset> getAllItemsWithTotalSales() {
        List<Item> items = itemRepository.findAll();
        List<Dataset> itemsWithTotalSales = new ArrayList<>();
        
        for (Item item : items) {
            int totalSales = salesRepository.getTotalQuantityById(item.getId());
            Dataset itemWithTotalSales = new Dataset(item, totalSales);
            itemsWithTotalSales.add(itemWithTotalSales);
        }
        
        return itemsWithTotalSales;
    }

}
