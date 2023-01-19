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
public class UploadBody {

    private String id_user;
    private String name;
    private String collection;
    private String description;
    private String currency;
    private double price;
    private String image;

}
