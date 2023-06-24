package com.repository;

import com.entity.CSVData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CSVDataRepository extends JpaRepository<CSVData, Long> {
    List<CSVData> findAllByCluster(int cluster);
}
