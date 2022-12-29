package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.History;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HistoryRepository extends CrudRepository<History, Long> {
    List<History> findAllByProductId(Long id);
}
