package com.clement.entity;

import jakarta.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "capital_city")
    private CapitalCity capitalCity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CapitalCity getCapitalCity() {
        return capitalCity;
    }

    public void setCapitalCity(CapitalCity capitalCity) {
        this.capitalCity = capitalCity;
    }
}
