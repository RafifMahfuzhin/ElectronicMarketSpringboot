package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.Sales;
import com.repository.SalesRepository;
import org.springframework.stereotype.Service;

@Service
public class SalesServiceImpl implements SalesService {
    private final SalesRepository salesRepository;

    @Autowired
    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Sales saveSales(Long itemId, Long userId, int quantity, int amount) {
        Sales newSales = new Sales(itemId, userId, quantity,amount);
        return salesRepository.save(newSales);
    }

    @Override
    public List<Sales> findAllSales() {
        return salesRepository.findAll();
    }

    @Override
    public int getTotalSales() {
        System.out.println(salesRepository.countAllSales());
        return salesRepository.countAllSales();
    }

    // @Override
    // public int getTotalQuantityById(Long itemId) {
    //     return salesRepository.getTotalQuantityById(itemId);
    // }

    // public Sales getSalesById(Long salesId) {
    //     return salesRepository.findById(salesId)
    //             .orElse(null);
    // }

    // public void deleteSales(Long salesId) {
    //     salesRepository.deleteById(salesId);
    // }
}
