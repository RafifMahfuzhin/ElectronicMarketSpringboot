package com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Sales;
import com.service.SalesService;

@Controller
public class SalesController {
    private SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/sales")
    public String getItemList(Model model) {
        List<Sales> sales = salesService.findAllSales();
        model.addAttribute("sales", sales);
        return "sales :: content";
    }

    @PostMapping("/sales/save")
    public String addItem(@RequestParam("userId") Long userId,
                          @RequestParam("itemId") Long itemId,
                          @RequestParam("quantity") int quantity,
                          @RequestParam("amount") int amount) {
        salesService.saveSales(userId,itemId,quantity,amount);
        return "redirect:/index";
    }



    // @GetMapping("/items/{id}/delete")
    // public String deleteItem(@PathVariable("id") long id) {
    //     itemService.deleteItemById(id);
    //     return "redirect:/index";
    // }

    // @PostMapping("/items/{itemId}/update")
    // public String updateItem(@PathVariable long itemId, @RequestParam("name") String newName,
    //                         @RequestParam("quantity") int newQuantity, @RequestParam("amount") int newAmount) {
    //     Item item = itemService.updateItem(itemId, newName, newQuantity, newAmount);
    //     System.out.println("Cek1");
    //     if (item != null) {
    //         return "redirect:/index"; 
    //     } else {
    //         return "error-page"; 
    //     }
    // }

}
