package com.example.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecommendSecond extends Fragment {

    ListView listView;
    RecommendListViewAdapter adapter;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> code = new ArrayList<>();
    ArrayList<StockList> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recommend_2p, container, false);
        listView = view.findViewById(R.id.recommend_2p);
        String name1 = "";
        String code1 = "";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (!response.isEmpty()) {
                            String name1 = jsonObject.getString("Name");
                            String code1 = jsonObject.getString("Code");
                            name.add(name1);
                            code.add(code1);
                        } else {
                            Toast.makeText(getContext(), "목록 불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
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
                adapter = new RecommendListViewAdapter(getActivity(), arrayList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        RecommendTwoListRequest listRequest = new RecommendTwoListRequest(name1, code1, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(listRequest);

        return view;
    }
}
