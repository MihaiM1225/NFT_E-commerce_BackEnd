package com.example.mvcproducts.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
public class Login {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;

    @OneToOne
    private User user;
}
