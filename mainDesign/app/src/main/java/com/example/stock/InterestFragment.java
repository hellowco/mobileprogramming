package com.example.stock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class InterestFragment extends Fragment {

    Context mContext;
    ListView listView;
    SwipeRefreshLayout swipe;
    InterestListViewAdapter adapter;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> code = new ArrayList<>();
    ArrayList<StockList> arrayList = new ArrayList<>();
    String userId = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_interest, container, false);
        listView = view.findViewById(R.id.interestView);
        swipe = view.findViewById(R.id.swipeRefresh);

        String usid = "";
        Bundle bundle = getArguments();
        if (bundle != null){
            usid = bundle.getString("userId");
            userId = usid;
        }
        adapter = new InterestListViewAdapter(getContext(), arrayList, userId);

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                Fragment fragment = new InterestFragment();
                ft.replace(R.id.fragment, fragment);
                fragment.setArguments(bundle);
                ft.commit();
                swipe.setRefreshing(false);
            }
        });

        String name1 = "";
        String code1 = "";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                if(response.equals("[]")) {
                    String name1 = "empty";
                    String code1 = "empty";
                    name.add(name1);
                    code.add(code1);
                }
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (!response.isEmpty()) {
                        String name1 = jsonObject.getString("Name");
                        String code1 = jsonObject.getString("Code");
                        name.add(name1);
                        code.add(code1);
                    } else {
                        Toast.makeText(getContext(), "?????? ??????????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int i = 0; i<name.size(); i++){
                StockList stockList = new StockList(name.get(i), code.get(i));
                arrayList.add(stockList);
            }
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }};
        InterestListRequest listRequest = new InterestListRequest(name1, code1, userId, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(listRequest);
        return view;
    }

    @Override public void onDestroyView() { super.onDestroyView(); }
}