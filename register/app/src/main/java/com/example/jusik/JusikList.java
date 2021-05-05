package com.example.jusik;

public class JusikList {

    String name;
    String code;

    public JusikList(String name, String code) {
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
