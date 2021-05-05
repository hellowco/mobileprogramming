package com.example.stock;

public class StockList {
    String name;
    String code;

    public StockList(String name, String code) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }
}
