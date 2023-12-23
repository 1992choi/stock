package com.example.stock.repository.stock;

import com.example.stock.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByStockIdNotIn(List<Long> id);

}
