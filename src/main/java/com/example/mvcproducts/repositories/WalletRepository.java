package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet,Long> {
    Wallet findByUserId(Long id);
}
