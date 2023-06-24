package com.service;

import java.util.List;

import com.entity.CSVData;

public interface CSVService {
    void saveCSVData(CSVData csvData);
    CSVData getCSVDataById(long id);
    void updateCSVData(CSVData csvData);
    void deleteAllCSVData();
    List<CSVData> getCSVDataByCluster(int cluster);
    int getTotalSalary();
    String getNameWithHighSales();
    String getNameWithHighSalary();
    String getNameWithHighView();
    double getAvgTotalViewByCluster(int cluster);
    double getAvgTotalSalaryByCluster(int cluster);
    double getAvgTotalSalesByCluster(int cluster);
    double getAvgTotalQuantityByCluster(int cluster);
}
