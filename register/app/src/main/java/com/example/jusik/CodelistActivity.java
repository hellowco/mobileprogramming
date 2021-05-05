package com.example.jusik;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CodelistActivity extends AppCompatActivity {

    ListView listView;
    ListViewAdapter adapter;
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> code = new ArrayList<>();
    ArrayList<JusikList> arrayList = new ArrayList<>();
    SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codelist);
        mSearchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listView);
        String name1 = "";
        String code1 = "";

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        if (!response.isEmpty()) { // 로그인에 성공한 경우
                            String name1 = jsonObject.getString("userID");
                            String code1 = jsonObject.getString("userPassword");
                            name.add(name1);
                            code.add(code1);

                            Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        } else { // 로그인에 실패한 경우
                            Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i<name.size(); i++){
                    JusikList jusikList = new JusikList(name.get(i), code.get(i));
                    arrayList.add(jusikList);
                }
                adapter = new ListViewAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        ListRequest listRequest = new ListRequest(name1, code1, responseListener);
        RequestQueue queue = Volley.newRequestQueue(CodelistActivity.this);
        queue.add(listRequest);


        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else{
                    adapter.filter(newText);
                }

                return true;
            }
        });
    }
}
