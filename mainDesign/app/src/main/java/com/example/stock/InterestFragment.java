package com.example.stock;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.HashMap;

public class InterestFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        ListView listView = findViewById(R.id.interestView);
//
//        Intent intent = this.getIntent();
//        InterestList interestList = (InterestList) intent.getSerializableExtra("interestList");
//        System.out.println("got:" + interestList);
//
//        HashMap<String, String> hashMap = interestList.getInterestList();
//        System.out.println("arr:" + hashMap);
//        adapter = new InterestViewAdapater(getApplicationContext(), hashMap);
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        return inflater.inflate(R.layout.fragment_interest, container, false);
    }
}