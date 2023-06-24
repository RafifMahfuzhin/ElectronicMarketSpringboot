package com.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.service.ItemService;
import com.service.SalesService;
// import com.service.UserService;
import com.entity.*;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
public class MarketController {

    private ItemService itemService;
    private SalesService salesService;
    // private UserService userService;

    // public MarketController(ItemService itemService, SalesService salesService, UserService userService) {
    //     this.itemService = itemService;
    //     this.salesService = salesService;
    //     this.userService = userService;
    // }


    public MarketController(ItemService itemService, SalesService salesService) {
        this.itemService = itemService;
        this.salesService = salesService;
    }

    @GetMapping("/market")
    public String market(Model model){
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        return "market";
    }

    @GetMapping("/buy-item/{itemId}")
    public String showBuyItemPage(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.getItemById(itemId);
        model.addAttribute("item", item);
        itemService.updateViewByClick(itemId);
        return "buy-item";
    }
    
    @PostMapping("/buy-item/{itemId}/{quantity}")
    public String buyItem(@PathVariable("itemId") Long itemId, @PathVariable("quantity") int quantity, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Item item = itemService.getItemById(itemId);
        int amount = item.getAmount();
        salesService.saveSales(itemId, userId, quantity, amount);
        itemService.updateQuantityAndSalaryWhenBuy(itemId, quantity);
        return "market";
    }
}
