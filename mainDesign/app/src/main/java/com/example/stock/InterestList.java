package com.example.stock;

import java.io.Serializable;
import java.util.HashMap;

public class InterestList implements Serializable {

    private HashMap<String,String> interestList;

    public HashMap<String, String> getInterestList() {
        return interestList;
    }

    public void setInterestList(HashMap<String, String> interestList){
        this.interestList = interestList;
    }
}
