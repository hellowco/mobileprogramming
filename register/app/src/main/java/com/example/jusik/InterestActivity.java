package com.example.jusik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class InterestActivity extends AppCompatActivity {
    InterestViewAdapater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);
        ListView listView = findViewById(R.id.interestView);

        Intent intent = this.getIntent();
        InterestList interestList = (InterestList) intent.getSerializableExtra("interestList");
        System.out.println("got:" + interestList);

        HashMap<String, String> hashMap = interestList.getInterestList();
        System.out.println("arr:" + hashMap);
        adapter = new InterestViewAdapater(getApplicationContext(), hashMap);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
