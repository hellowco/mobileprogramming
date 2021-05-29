package com.example.stock;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MemoFragment extends Fragment {

    FloatingActionButton addButton;
    RecyclerView recyclerView;
    ArrayList<String> ID = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();
    MemoAdapter adapter;
    ArrayList<MemoList> arrayList = new ArrayList<>();
    SwipeRefreshLayout swipe;
    TextView textView;
    String uID = "";
    String userId = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, container, false);
        adapter = new MemoAdapter(arrayList);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addButton = view.findViewById(R.id.addMemo);
        swipe = view.findViewById(R.id.swipeRefreshMemo);
        textView = view.findViewById(R.id.emptyMemo);

        textView.setVisibility(View.INVISIBLE);

        String usid = "";
        Bundle bundle = getArguments();
        if (bundle != null){
            usid = bundle.getString("userId");
            userId = usid;
        }

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                Fragment fragment = new MemoFragment();
                ft.replace(R.id.fragment, fragment);
                fragment.setArguments(bundle);
                ft.commit();
                swipe.setRefreshing(false);
            }
        });

        String mID = "";
        String mTitle="";
        String mContent="";
        String mDate="";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (!response.isEmpty()) {
                            JSONObject oID = jsonObject.getJSONObject("_id");
                            String mID = oID.getString("$oid");
                            String mName = jsonObject.getString("name");
                            String mTitle = jsonObject.getString("title");
                            String mContent = jsonObject.getString("content");
                            String mDate = jsonObject.getString("date");
                            uID = mName;
                            ID.add(mID);
                            title.add(mTitle);
                            content.add(mContent);
                            date.add(mDate);
                        } else {
                            Toast.makeText(getContext(), "목록 불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i<title.size(); i++){
                    MemoList memoList = new MemoList(uID, ID.get(i), title.get(i), content.get(i), date.get(i));
                    arrayList.add(memoList);
                }
                if (arrayList.size() == 0){
                    textView.setVisibility(View.VISIBLE);
                }
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }};
        MemoRequest memoRequest = new MemoRequest(userId, mID, mTitle, mContent, mDate, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(memoRequest);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddMemo.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
    @Override public void onDestroyView() { super.onDestroyView(); }
}