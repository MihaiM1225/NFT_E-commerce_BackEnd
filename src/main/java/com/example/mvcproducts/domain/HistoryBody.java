package com.example.mvcproducts.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class HistoryBody {
    private String owner;
    private String currency;
    private double price;
}
