package com.clement.entity.inheritance;

import jakarta.persistence.Entity;

@Entity
public class Electronic extends ProductR {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private int voltage;

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }
}
