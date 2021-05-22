package com.example.stock;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;

public class AddMemo extends AppCompatActivity {
    private EditText title;
    private EditText content;
    String mTitle = "";
    String mContent = "";
    String userId = ""; // 추후에 로그인한 사용자 받아와야함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_add);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

        initialized();
    }

    private void initialized() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
    }

    //메모저장하는 버튼
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_memo_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save:
                save_memo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void save_memo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        mTitle = title.getText().toString();
        mContent = content.getText().toString();
        builder.setTitle("저장하시겠습니까?");


        builder.setPositiveButton("저장", (dialog, which) -> {
            // db에 저장하기
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                }
            };
            MemoAddRequest memoAddRequest  = new MemoAddRequest(userId, mTitle, mContent, responseListener);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(memoAddRequest);
            Toast.makeText(getApplicationContext(), "저장되었습니다.\n 스와이프하여 새로고침 해주세요.",Toast.LENGTH_SHORT).show();
            finish();
        });

        builder.setNegativeButton("취소", (dialog, which) -> {
            dialog.dismiss();
        });

        builder.show();
    }
}
