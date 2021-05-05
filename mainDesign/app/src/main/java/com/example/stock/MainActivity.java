package com.example.stock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView newsListView;
    private NewsListAdapter adapter;
    private List<News> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsListView = (ListView) findViewById(R.id.noticeListView);
        newsList = new ArrayList<News>();
        // 공지사항 데이터베이스 만들기 전이라
        newsList.add(new News("기사1", "이름", "날짜"));
        newsList.add(new News("기사2", "이름", "날짜"));
        newsList.add(new News("기사3", "이름", "날짜"));
        newsList.add(new News("기사4", "이름", "날짜"));
        newsList.add(new News("기사5", "이름", "날짜"));
        newsList.add(new News("기사6", "이름", "날짜"));
        adapter = new NewsListAdapter(getApplicationContext(), newsList);
        newsListView.setAdapter(adapter);

        final Button listButton = (Button) findViewById(R.id.listButton);
        final Button interestButton = (Button) findViewById(R.id.interestButton);
        final Button themeButton = (Button) findViewById(R.id.themeButton);
        final Button recommendButton = (Button) findViewById(R.id.recommendButton);
        final Button memoButton = (Button) findViewById(R.id.memoButton);
        final LinearLayout home = (LinearLayout) findViewById(R.id.home);

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.GONE);
                listButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
                interestButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                themeButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                recommendButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                memoButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new StockListFragment());
                fragmentTransaction.commit();
            }
        });

        interestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.GONE);
                listButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                interestButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
                themeButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                recommendButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                memoButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new InterestFragment());
                fragmentTransaction.commit();
            }
        });

        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.GONE);
                listButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                interestButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                themeButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
                recommendButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                memoButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ThemeFragment());
                fragmentTransaction.commit();
            }
        });

        recommendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.GONE);
                listButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                interestButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                themeButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                recommendButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
                memoButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new RecommendFragment());
                fragmentTransaction.commit();
            }
        });

        memoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home.setVisibility(View.GONE);
                listButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                interestButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                themeButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                recommendButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
                memoButton.setBackgroundColor(getResources().getColor(R.color.purple_700));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new MemoFragment());
                fragmentTransaction.commit();
            }
        });
    }
}