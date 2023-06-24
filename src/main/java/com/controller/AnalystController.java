package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.entity.Dataset;
import com.opencsv.CSVWriter;
import com.service.ItemService;
// import com.service.SalesService;
// import com.service.UserService;
import org.springframework.stereotype.Controller;


@Controller
public class AnalystController {
    private ItemService itemService;
    // private UserService userService;
    // private SalesService salesService;

    public AnalystController(ItemService itemService) {
        this.itemService = itemService;
    }

    // public AnalystController(ItemService itemService, UserService userService, SalesService salesService) {
    //     this.itemService = itemService;
    //     this.userService = userService;
    //     this.salesService = salesService;
    // }

    @GetMapping("/analyst")
    public String analyst(Model model) {
        model.addAttribute("items", itemService.getAllItemsWithTotalSales());
        return "analyst";
    }

    @GetMapping("/analyst/download")
    public String downloadCSV(HttpServletResponse response) throws IOException {
        List<Dataset> items = itemService.getAllItemsWithTotalSales();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"analyst.csv\"");

        CSVWriter csvWriter = new CSVWriter(response.getWriter());

        String[] header = {"ID Item", "Item Name", "View", "Quantity", "Total Sales", "Total Salary"};
        csvWriter.writeNext(header);

        for (Dataset item : items) {
            String[] row = {
                String.valueOf(item.getItem().getId()),
                item.getItem().getName(),
                String.valueOf(item.getItem().getView()),
                String.valueOf(item.getItem().getQuantity()),
                String.valueOf(item.getTotalSales()),
                String.valueOf(item.getItem().getSalary())
            };
            csvWriter.writeNext(row);
        }

        csvWriter.close();

        return "redirect:/index";
    }

  



    


}
