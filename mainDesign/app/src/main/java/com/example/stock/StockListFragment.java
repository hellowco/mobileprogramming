package com.example.stock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class StockListFragment extends Fragment {

    ListView listView;
    StockListViewAdapter adapter;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> code = new ArrayList<>();
    ArrayList<StockList> arrayList = new ArrayList<>();
    SearchView mSearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mSearchView = view.findViewById(R.id.searchView);
        listView = view.findViewById(R.id.stockListView);
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
                adapter = new StockListViewAdapter(getActivity(), arrayList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        StockListRequest listRequest = new StockListRequest(name1, code1, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(listRequest);


        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    ((StockListViewAdapter) adapter).filter("");
                    listView.clearTextFilter();
                }
                else{
                    ((StockListViewAdapter) adapter).filter(newText);
                }
                return true;
            }
        });
        return view;
    }
}