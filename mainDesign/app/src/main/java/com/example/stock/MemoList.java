package com.example.stock;

public class MemoList {
    String userId;
    String title;
    String context;

    public MemoList(String userId, String title, String context) {
        this.userId = userId;
        this.title = title;
        this.context = context;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContext() {
        return this.context;
    }

    public String getUserId() { return this.userId; }
}
