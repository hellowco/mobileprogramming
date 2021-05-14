package com.example.stock;

public class MemoList {
    String userId;
    String title;
    String context;
    String date;
    String objID;

    public MemoList(String userId, String objID, String title, String context, String date) {
        this.objID = objID;
        this.userId = userId;
        this.title = title;
        this.context = context;
        this.date = date;
    }

    public String getObjID() { return objID; }

    public String getTitle() {
        return this.title;
    }

    public String getContext() {
        return this.context;
    }

    public String getUserId() { return this.userId; }

    public String getDate() { return this.date;    }
}
