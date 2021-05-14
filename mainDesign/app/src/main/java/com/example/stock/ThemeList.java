package com.example.stock;

public class ThemeList {
    String userId;
    String name;
    String code;
    String theme;

    public ThemeList(String name, String code, String theme) {
        this.code = code;
        this.name = name;
        this.theme = theme;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getTheme() {return this.theme;}

}
