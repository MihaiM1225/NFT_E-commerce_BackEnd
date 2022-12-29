package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.History;

import java.util.List;

public interface HistoryService {
    void save(History history);
    List<History> findAllByProductId(Long id);
}
