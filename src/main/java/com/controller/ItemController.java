package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.servlet.ModelAndView;

import com.entity.Item;
import com.service.ItemService;

@Controller
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public String getItemList(Model model) {
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        return "items :: content";
    }

    @PostMapping("/items/save")
    public String addItem(@RequestParam("name") String name,
                          @RequestParam("quantity") int quantity,
                          @RequestParam("amount") int amount,
                          @RequestParam("modal") int modal) {
        itemService.saveItem(name, quantity, amount,modal);
        return "redirect:/index";
    }


    @GetMapping("/items/{id}/delete")
    public String deleteItem(@PathVariable("id") long id) {
        itemService.deleteItemById(id);
        return "redirect:/index";
    }

    @PostMapping("/items/{itemId}/update")
    public String updateItem(@PathVariable long itemId, @RequestParam("name") String newName,
                            @RequestParam("quantity") int newQuantity, @RequestParam("amount") int newAmount, @RequestParam("modal") int newModal,@RequestParam("salary") int newSalary) {
        Item item = itemService.updateItem(itemId, newName, newQuantity, newAmount, newModal,newSalary);
        System.out.println("Cek1");
        if (item != null) {
            return "redirect:/index"; 
        } else {
            return null; 
        }
    }

}
