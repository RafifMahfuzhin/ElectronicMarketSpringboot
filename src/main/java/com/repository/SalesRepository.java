package com.repository;

import java.util.List;
import com.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findAll();

    Sales findById(long id);

    @Query("SELECT COUNT(*) FROM Sales")
    int countAllSales();

    @Query("SELECT SUM(quantity) FROM Sales WHERE itemId = :itemId")
    int getTotalQuantityById(@Param("itemId") Long itemId);
}
