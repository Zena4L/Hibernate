package com.clement.entity;

import com.clement.generators.CustomUUIDGenerator;
import com.clement.keys.ProductKey;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@IdClass(ProductKey.class) // for composite IDs
public class Product {



    /**
     *
     * Custom simple Id generator
     @Id
    @GenericGenerator(name = "UUIDGenerator", type = CustomUUIDGenerator.class)
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(length = 500)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
     */

    private String id;

    private String name;

    @Id
    private String code;

    @Id
    private Long  number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
