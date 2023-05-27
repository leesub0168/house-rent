package com.org.houserent.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class House {
    @Id @GeneratedValue
    @Column(name = "house_id")
    private Long id;

    private String city;

    private String zipcode;


}
