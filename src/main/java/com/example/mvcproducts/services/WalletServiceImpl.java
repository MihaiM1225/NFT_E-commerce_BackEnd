package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Wallet;
import com.example.mvcproducts.repositories.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUserId(Long id) {
        return walletRepository.findByUserId(id);
    }
}
