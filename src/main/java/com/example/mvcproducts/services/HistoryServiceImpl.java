package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.History;
import com.example.mvcproducts.repositories.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public void save(History history) {
        historyRepository.save(history);
    }

    @Override
    public List<History> findAllByProductId(Long id) {
        return historyRepository.findAllByProductId(id);
    }
}
