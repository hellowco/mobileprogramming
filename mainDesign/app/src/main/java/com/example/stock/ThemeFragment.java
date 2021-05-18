package com.example.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ThemeFragment extends Fragment {

    ThemeListViewAdapter adapter;

    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> code = new ArrayList<>();
    ArrayList<StockList> arrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        TabLayout tabLayout = view.findViewById(R.id.themeTableLayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.themePage);
        ThemeAdapter themeAdapter = new ThemeAdapter(getActivity());

        String name1 = "";
        String code1 = "";


        // tabElement는 상위 테마 5개로 변경할 것
        // SEARCH * FROM (날짜)_theme
        // theme table 길이는 5개.
        // name 은 테마 이름, code는 테마 코드

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
                            String name1 = jsonObject.getString("themeName");
                            String code1 = jsonObject.getString("themeCode");
//                            System.out.println(name1);
//                            System.out.println(code1);
                            if(!name.contains(name1))
                                name.add(name1);
                            if(!code.contains(code1))
                                code.add(code1);
                        } else {
                            Toast.makeText(getContext(), "목록 불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int k=0; k<5; k++){
                    Fragment frag = new ThemeListViewFragment(code.get(k));
                    themeAdapter.addFrag(frag);
                }

                viewPager2.setAdapter(themeAdapter);
                themeAdapter.notifyDataSetChanged();

                String[] tabElement = name.toArray(new String[name.size()]);

                new TabLayoutMediator(tabLayout, viewPager2,
                        ((tab, position) -> tab.setText(tabElement[position]))).attach();
                adapter.notifyDataSetChanged();
            }
        };
        ThemeRequest listRequest = new ThemeRequest(name1, code1, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(listRequest);



        return view;
    }
}