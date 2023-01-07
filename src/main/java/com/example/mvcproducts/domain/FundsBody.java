package com.example.mvcproducts.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FundsBody {

    private String id_user;
    private String currency;
    private double price;

}
