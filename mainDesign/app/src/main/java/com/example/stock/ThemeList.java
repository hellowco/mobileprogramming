package com.example.stock;

public class ThemeList {
    String userId;
    String name;
    String code;

    public ThemeList(String name, String code) {
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
