package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Wallet;

public interface WalletService {
    void save(Wallet wallet);
    Wallet findByUserId(Long id);
}
