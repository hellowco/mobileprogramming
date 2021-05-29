package com.example.stock;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    //public static boolean DEBUG_MODE = true;

    private ListView newsListView;
    private NewsListAdapter adapter;
    private List<News> newsList;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> news = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    String userId = "";

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userID", userId);
                startActivity(intent);
                break;
            case R.id.logoutButton:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("로그아웃").setMessage("로그아웃을 하시겠습니까?")
                        .setPositiveButton("로그아웃", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userID");
        System.out.println(userId);

        Bundle bundle = new Bundle(1);
        bundle.putString("userId",userId);


        newsListView = (ListView) findViewById(R.id.noticeListView);
        newsList = new ArrayList<News>();
        // 공지사항 데이터베이스 만들기 전이라
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
//                    System.out.println(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        System.out.println(jsonObject);
                        if (!response.isEmpty()) {
                            String name1 = jsonObject.getString("stockName");
                            String time1 = jsonObject.getString("time");
                            String news1 = jsonObject.getString("stockNews");
                            name.add(name1);
                            time.add(time1);
                            news.add(news1);
                        } else {
                            Toast.makeText(getApplicationContext(), "목록 불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i<name.size(); i++){
                    newsList.add(new News(news.get(i), name.get(i), time.get(i)));
                }

                adapter = new NewsListAdapter(getApplicationContext(), newsList);
                newsListView.setAdapter(adapter);
            }
        };

        NewsListRequest listRequest = new NewsListRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(listRequest);

        final Button listButton = (Button) findViewById(R.id.listButton);
        final Button interestButton = (Button) findViewById(R.id.interestButton);
        final Button themeButton = (Button) findViewById(R.id.themeButton);
        final Button recommendButton = (Button) findViewById(R.id.recommendButton);
        final Button memoButton = (Button) findViewById(R.id.memoButton);
        final ImageButton logoutButton = (ImageButton) findViewById(R.id.logoutButton);
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
                Fragment fragment = new StockListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragment.setArguments(bundle);
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
                Fragment fragment = new InterestFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragment.setArguments(bundle);
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
                Fragment fragment = new ThemeFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragment.setArguments(bundle);
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
                Fragment fragment = new RecommendFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragment.setArguments(bundle);
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
                Fragment fragment = new MemoFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();
            }
        });
    }
}