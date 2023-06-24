package com.service;

import java.util.List;
import com.entity.CSVData;
import com.repository.CSVDataRepository;
import org.springframework.stereotype.Service;

@Service
public class CSVServiceImpl implements CSVService {
    private CSVDataRepository csvDataRepository;

    public CSVServiceImpl(CSVDataRepository csvDataRepository) {
        this.csvDataRepository = csvDataRepository;
    }

    @Override
    public void saveCSVData(CSVData csvData) {
        csvDataRepository.save(csvData);
    }

    @Override
    public CSVData getCSVDataById(long id) {
        return csvDataRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCSVData(CSVData csvData) {
        csvDataRepository.save(csvData);
    }

    @Override
    public void deleteAllCSVData() {
            csvDataRepository.deleteAll();
    }

    @Override
    public List<CSVData> getCSVDataByCluster(int cluster) {
        return csvDataRepository.findAllByCluster(cluster);
    }

    @Override
    public int getTotalSalary() {
        List<CSVData> csvData = csvDataRepository.findAll();
        int totalSalary = 0;
        for (CSVData csv : csvData) {
            totalSalary += csv.getTotalsalary();
        }
        return totalSalary;
    }

    @Override
    public String getNameWithHighSales() {
        List<CSVData> csvdatas = csvDataRepository.findAll();

        int maxTotalSales = 0;
        CSVData dataWithHighestTotalSales = null;

        for (CSVData csvdata : csvdatas) {
            if (csvdata.getTotalsales() > maxTotalSales) {
                maxTotalSales = csvdata.getTotalsales();
                dataWithHighestTotalSales = csvdata;
            }
        }

        if (dataWithHighestTotalSales != null) {
            return dataWithHighestTotalSales.getName();
        }

        return null;
    }

    @Override
    public String getNameWithHighSalary() {
        List<CSVData> csvdatas = csvDataRepository.findAll();

        int maxTotalSalary = 0;
        CSVData dataWithHighestTotalSalary = null;

        for (CSVData csvdata : csvdatas) {
            if (csvdata.getTotalsalary() > maxTotalSalary) {
                maxTotalSalary = csvdata.getTotalsalary();
                dataWithHighestTotalSalary = csvdata;
            }
        }

        if (dataWithHighestTotalSalary != null) {
            return dataWithHighestTotalSalary.getName();
        }

        return null;
    }

    @Override
    public String getNameWithHighView() {
        List<CSVData> csvdatas = csvDataRepository.findAll();

        int maxView = 0;
        CSVData dataMaxView = null;

        for (CSVData csvdata : csvdatas) {
            if (csvdata.getTotalsalary() > maxView) {
                maxView = csvdata.getTotalsalary();
                dataMaxView = csvdata;
            }
        }

        if (dataMaxView != null) {
            return dataMaxView.getName();
        }

        return null;
    }

    @Override
    public double getAvgTotalViewByCluster(int cluster){
        List<CSVData> csvDatas = csvDataRepository.findAllByCluster(cluster);
        int totalView = 0;
        double row =0 ;
        for(CSVData csvdata : csvDatas){
            row +=1;
            totalView = totalView + csvdata.getView();
        }
        double avgView = totalView / row;
        return avgView; 
    }

    @Override
    public double getAvgTotalSalesByCluster(int cluster){
        List<CSVData> csvDatas = csvDataRepository.findAllByCluster(cluster);
        int n = 0;
        double r =0 ;
        for(CSVData csvdata : csvDatas){
            r +=1;
            n = n + csvdata.getTotalsales();
        }
        double avg = n / r;
        return avg; 
    }

    @Override
    public double getAvgTotalQuantityByCluster(int cluster){
        List<CSVData> csvDatas = csvDataRepository.findAllByCluster(cluster);
        int n = 0;
        double r =0 ;
        for(CSVData csvdata : csvDatas){
            r +=1;
            n = n + csvdata.getQuantity();
        }
        double avg = n / r;
        return avg; 
    }

    @Override
    public double getAvgTotalSalaryByCluster(int cluster){
        List<CSVData> csvDatas = csvDataRepository.findAllByCluster(cluster);
        int n = 0;
        double r =0 ;
        for(CSVData csvdata : csvDatas){
            r +=1;
            n = n + csvdata.getTotalsalary();
        }
        double avg = n / r;
        return avg; 
    }

}
