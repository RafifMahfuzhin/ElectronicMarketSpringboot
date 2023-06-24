package com.repository;

import java.util.List;
import com.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAll();

    Item findById(long id);

    @Query("SELECT COUNT(*) FROM Item")
    int countAllItems();


}
