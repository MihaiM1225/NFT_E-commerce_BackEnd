package com.example.mvcproducts.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SellBody {

    private String id_user;
    private String id_nft;
    private String currency;
    private double price;

}
