package com.clement.keys;

import java.io.Serializable;

public class ProductKey implements Serializable {
    private String code;

    private Long  number;

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
