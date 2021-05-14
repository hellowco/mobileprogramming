package com.example.stock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ModifyMemo extends AppCompatActivity {
    private EditText detailTitle;
    private EditText detailContext;
    private TextView detailDate;

    private FloatingActionButton exit;
    private FloatingActionButton update;

    private String id;
    private String title;
    private String context;
    private String date;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_modify);

        initialized();

        // 수정
        update.setOnClickListener(v -> {
            System.out.println(id);
            title = detailTitle.getText().toString();
            context = detailContext.getText().toString();
            date = detailDate.getText().toString();

            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                   System.out.println(response);
                }};
            MemoModifyRequest memoModifyRequest = new MemoModifyRequest(id, title, context, date, responseListener);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(memoModifyRequest);
            finish();
        });
        //그냥 종료
        exit.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplication(), MemoFragment.class);
//            startActivity(intent);
            finish();
        });
    }

    private void initialized() {
        update = findViewById(R.id.update);
        exit = findViewById(R.id.exit);
        detailTitle = findViewById(R.id.detailTitle);
        detailContext = findViewById(R.id.detailContent);
        detailDate = findViewById(R.id.detailDate);
        Intent intent = getIntent();

        id = intent.getStringExtra("objID");
        title = intent.getStringExtra("title");
        context = intent.getStringExtra("content");
        date = intent.getStringExtra("date");

        detailTitle.setText(title);
        detailContext.setText(context);
        detailDate.setText(date);
    }
}
