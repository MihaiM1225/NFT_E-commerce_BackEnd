package com.example.mvcproducts.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductBody {

    private Long id;
    private String name;
    private String collection;
    private String description;
    private List<HistoryBody> historyList;
    private String currency;
    private double price;
    private String picture;

}
