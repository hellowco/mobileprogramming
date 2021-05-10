package com.example.stock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> context = new ArrayList<>();
//    RecyclerAdapter adapter;
    ArrayList<MemoList> arrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, container, false);
//        adapter = new RecyclerAdapter(container.getContext(), arrayList);
        recyclerView = view.findViewById(R.id.recyclerView);
        addButton = view.findViewById(R.id.addMemo);

        return view;
    }
}