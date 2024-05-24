package com.clement.entity;

import com.clement.generators.CustomUUIDGenerator;
import com.clement.keys.ProductKey;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;


@Entity
//@IdClass(ProductKey.class) // for composite IDs
public class Product {


    /**
     * Custom simple Id generator
     *
     * @Id
     * @GenericGenerator(name = "UUIDGenerator", type = CustomUUIDGenerator.class)
     * @GeneratedValue(generator = "UUIDGenerator")
     * @Column(length = 500)
     * @GeneratedValue(strategy = GenerationType.SEQUENCE)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String name;

    private int price;

    //    @Id
//    private String code;

    //    @Id
//    private Long number;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public Long getNumber() {
//        return number;
//    }

//    public void setNumber(Long number) {
//        this.number = number;
//    }
}
