package com.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.CSVData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.service.ItemService;
import com.service.SalesService;
import com.service.UserService;
import com.service.CSVService;


@Controller
public class DashboardController {
    private ItemService itemService;
    private UserService userService;
    private SalesService salesService;
    private List<CSVData> csvDataList;
    private CSVService csvService;

    public DashboardController(ItemService itemService, UserService userService, SalesService salesService,
            List<CSVData> csvDataList, CSVService csvService) {
        this.itemService = itemService;
        this.userService = userService;
        this.salesService = salesService;
        this.csvDataList = csvDataList;
        this.csvService = csvService;
    }

    // public DashboardController(ItemService itemService, UserService userService, SalesService salesService,
    //         List<CSVData> csvDataList) {
    //     this.itemService = itemService;
    //     this.userService = userService;
    //     this.salesService = salesService;
    //     this.csvDataList = csvDataList;
    // }

    // public DashboardController(ItemService itemService, UserService userService, SalesService salesService) {
    //     this.itemService = itemService;
    //     this.userService = userService;
    //     this.salesService = salesService;
    // }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        int totalItems = itemService.getTotalItems();
        model.addAttribute("totalItems", totalItems);
        int totalRoleUser = userService.getTotalUserRoles();
        model.addAttribute("totalRoleUsers", totalRoleUser);
        int totalSales = salesService.getTotalSales();
        model.addAttribute("totalSales", totalSales);
        model.addAttribute("csvCluster0",csvService.getCSVDataByCluster(0));
        model.addAttribute("csvCluster1",csvService.getCSVDataByCluster(1));
        model.addAttribute("csvCluster2",csvService.getCSVDataByCluster(2));
        model.addAttribute("csvCluster3",csvService.getCSVDataByCluster(3));
        model.addAttribute("totalSalary",csvService.getTotalSalary());
        model.addAttribute("bestSales", csvService.getNameWithHighSales());
        model.addAttribute("bestSalary", csvService.getNameWithHighSalary());
        model.addAttribute("bestView", csvService.getNameWithHighView());
        model.addAttribute("avgView0",csvService.getAvgTotalViewByCluster(0));
        model.addAttribute("avgView1",csvService.getAvgTotalViewByCluster(1));
        model.addAttribute("avgView2",csvService.getAvgTotalViewByCluster(2));
        model.addAttribute("avgView3",csvService.getAvgTotalViewByCluster(3));
        model.addAttribute("avgSales0", csvService.getAvgTotalSalesByCluster(0));
        model.addAttribute("avgSales1", csvService.getAvgTotalSalesByCluster(1));
        model.addAttribute("avgSales2", csvService.getAvgTotalSalesByCluster(2));
        model.addAttribute("avgSales3", csvService.getAvgTotalSalesByCluster(3));
        model.addAttribute("avgSalary0", csvService.getAvgTotalSalaryByCluster(0));
        model.addAttribute("avgSalary1", csvService.getAvgTotalSalaryByCluster(1));
        model.addAttribute("avgSalary2", csvService.getAvgTotalSalaryByCluster(2));
        model.addAttribute("avgSalary3", csvService.getAvgTotalSalaryByCluster(3));
        model.addAttribute("avgQuantity0", csvService.getAvgTotalQuantityByCluster(0));
        model.addAttribute("avgQuantity1", csvService.getAvgTotalQuantityByCluster(1));
        model.addAttribute("avgQuantity2", csvService.getAvgTotalQuantityByCluster(2));
        model.addAttribute("avgQuantity3", csvService.getAvgTotalQuantityByCluster(3));

        return "dashboard";
    }

    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file, Model model) {
        if (file.getContentType().equals("text/csv")) {
            try {
                csvDataList = readCSVData(file.getInputStream());
                csvService.deleteAllCSVData();
                for (CSVData csvData : csvDataList) {
                    csvService.saveCSVData(csvData);
                }

                model.addAttribute("csvDataList", csvDataList);

                return "index";
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }

        return "index";
    }

    private List<CSVData> readCSVData(InputStream inputStream) throws IOException, CsvValidationException {
        List<CSVData> csvDataList = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] line;
            boolean firstLineSkipped = false; 
            while ((line = reader.readNext()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; 
                }
                CSVData csvData = new CSVData(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]),Integer.parseInt(line[5]));
                csvDataList.add(csvData);
            }
        } catch (CsvValidationException e) {
            e.printStackTrace(); 
            throw e; 
        }

        return csvDataList;
    }



}
